package com.cake.shop.service.imp;

import com.cake.shop.entity.AngelCake;
import com.cake.shop.entity.CakeCart;
import com.cake.shop.mappers.AngelCakeMappers;
import com.cake.shop.mappers.CakeCartMapper;
import com.cake.shop.service.ICakeCartService;
import com.cake.shop.service.except.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class CakeCartServiceImpl implements ICakeCartService {
    @Autowired
    private CakeCartMapper cakeCartMapper;

    @Autowired
    private AngelCakeMappers angelCakeMappers;

    /**
     * @param acid
     * @param cuid
     * @return
     * @throws CakeAmountZeroException
     * @throws CakeNullException
     * @throws InsertIntoException
     * @throws UserMustBeforeLoginException
     */
    @Override
    public Integer addMyCart(Integer acid, Integer cuid) throws CakeAmountZeroException,
            CakeNullException, InsertIntoException, UserMustBeforeLoginException {
        if (cuid == null || "".equals(cuid)) {
            throw new UserMustBeforeLoginException("请您先进行登录,然后再将蛋糕加入篮子");
        }

        //根据页面acid获得库中数据
        AngelCake angelCake = angelCakeMappers.selectByAcid(acid);
        Integer integer = 0;

        //若为空,抛异常
        if (angelCake == null) {
            throw new CakeNullException("未知错误!该商品未寻获!");
        } else {

            //否则,判断数量是否为零
            if (angelCake.getAmount() == 0) {
                //是,抛卖光异常
                throw new CakeAmountZeroException("该商品已经卖完,很抱歉");
            } else {
                //否,逐项封装至购物车豆
                CakeCart cakeCart = new CakeCart();
                cakeCart.setCcname(angelCake.getCakename());
                cakeCart.setCctype(angelCake.getCaketype());
                cakeCart.setCcprice(angelCake.getPrice());
                cakeCart.setCcsupplier(angelCake.getSupplier());
                cakeCart.setCuid(cuid);

                //执行插入方法
                integer = cakeCartMapper.insertIntoOneCake(cakeCart);
                if (integer != 1) {
                    throw new InsertIntoException("添入购物车失败!请稍后重试");
                } else {
                    //修改angel_cake表中数量
                    Integer amount = angelCake.getAmount();
                    amount -= integer;
                    angelCakeMappers.modifyAmountByAcid(amount, acid);
                }
            }
        }
        return integer;
    }

    /**
     * @return
     */
    @Override
    public List<CakeCart> showAllCakeCart() {
        List<CakeCart> cakeCarts = cakeCartMapper.foundAll();
        return cakeCarts;
    }

    @Override
    public Integer delMultiCake(Integer[] ccids, Integer cuid)
            throws LoginStatusFailureException {
        if (cuid == null || "".equals(cuid)) {
            throw new LoginStatusFailureException("登录状态已过期,请重新登录");
        }

        //将ccids数组加进list
        List<Integer> list = addArrToList(ccids);
        Integer effects = cakeCartMapper.delByOneCuidAndMultiCcid(list, cuid);
        return effects;
    }

    /**
     * @param acids
     * @param cuid
     * @return
     * @throws UserMustBeforeLoginException
     * @throws CakeAmountZeroException
     */
    @Override
    public Integer addMultiCake(Integer[] acids, Integer cuid)
            throws UserMustBeforeLoginException, CakeAmountZeroException {
        //首先判断用户是否已经登录,否,异常
        if (cuid == null || "".equals(cuid)) {
            throw new UserMustBeforeLoginException("请您先登录,再将蛋糕加入篮子");
        }

        //将蛋糕ID数组代入,获取一个蛋糕型list集合
        List<AngelCake> list = angelCakeMappers.multiSelectByAcid(acids);

        amountEmptyNotice(list);

        //将集合代入批量插入
        Integer rows = bulkInsert(list, cuid);

        //往车中增加多份蛋糕,相应库存亦应当减少同样数量(更新)
        Integer integer = alertMultiAmountByAcids(acids, list);
        System.err.println("数量受影响行数:" + integer);

        //返回
        return rows;
    }

    /**
     * @param ccid
     * @return
     */
    @Override
    public CakeCart foundByCcid(Integer ccid) {
        return cakeCartMapper.seleByCcid(ccid);
    }

    /**
     * @param id   正在操作的用户的id
     * @param ccid
     * @return
     * @throws JurisdictionException
     */
    @Override
    public Integer deleteByCcid(Integer id, Integer ccid) throws JurisdictionException {
        CakeCart cakeCart = cakeCartMapper.seleByCcid(ccid);
        if (id != cakeCart.getCuid()) {
            throw new JurisdictionException("您没有权限进行删除操作!");
        }

        Integer integer = cakeCartMapper.deleByCcid(ccid);
        return integer;
    }

    /**
     * 根据会员ID寻找购物车中商品列表
     *
     * @param cuid
     * @return
     */
    @Override
    public List<CakeCart> foundByCuid(Integer cuid) {
        if (cuid == null || "".equals(cuid)) {
            throw new UserMustBeforeLoginException("对不起,您尚未登录,请先登录");
        }

        List<CakeCart> cakeCarts = cakeCartMapper.seleByCuid(cuid);
        return cakeCarts;
    }

    /**
     * 循环插入
     *
     * @param angelCakes 蛋糕类型集合
     * @param cuid       会员id
     * @return 影响行数
     */
    public Integer bulkInsert(List<AngelCake> angelCakes, Integer cuid) {
        ArrayList<CakeCart> carts = new ArrayList<>();
        Integer rows = 0;
        for (int i = 0; i < angelCakes.size(); i++) {
            CakeCart cart = new CakeCart();
            cart.setCcname(angelCakes.get(i).getCakename());
            cart.setCctype(angelCakes.get(i).getCaketype());
            cart.setCcsupplier(angelCakes.get(i).getSupplier());
            cart.setCcprice(angelCakes.get(i).getPrice());
            cart.setCuid(cuid);

            carts.add(cart);
        }
        rows = cakeCartMapper.bulkInsertCart(carts);

        return rows;
    }

    /**
     * @param acids
     * @param list
     * @return
     */
    public Integer alertMultiAmountByAcids(Integer[] acids, List<AngelCake> list) {
        LinkedList<AngelCake> cakes = new LinkedList<>();

        for (int i = 0; i < acids.length; i++) {
            AngelCake cake = new AngelCake();
            Integer amount = list.get(i).getAmount();
            amount -= 1;
            cake.setAcid(acids[i]);
            cake.setAmount(amount);
            cakes.add(cake);
        }
        return angelCakeMappers.volumeUpdateAmountByAcid(cakes);
    }

    /**
     * 循环判断库存售罄
     *
     * @param cakeList
     */
    public void amountEmptyNotice(List<AngelCake> cakeList) {
        for (int i = 0; i < cakeList.size(); i++) {
            Integer amount = cakeList.get(i).getAmount();
            String cakeName = cakeList.get(i).getCakename();
            if (amount == 0 || amount == null) {
                throw new CakeAmountZeroException("您选择的" + cakeName + "已经售罄");
            }
        }
    }

    /**
     * 返回一个list
     *
     * @param arr
     * @return
     */
    public List<Integer> addArrToList(Integer[] arr) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }
}
