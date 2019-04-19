package com.cake.shop.service.imp;

import com.cake.shop.entity.AngelCake;
import com.cake.shop.entity.Consul;
import com.cake.shop.entity.Customer;
import com.cake.shop.mappers.AngelCakeMappers;
import com.cake.shop.mappers.CakeCartMapper;
import com.cake.shop.mappers.ConsulMappers;
import com.cake.shop.mappers.CustomerMappers;
import com.cake.shop.service.IConsulService;
import com.cake.shop.service.except.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ConsulServiceImpl implements IConsulService {
    @Autowired
    private ConsulMappers consulMappers;

    @Autowired
    private AngelCakeMappers angelCakeMappers;

    @Autowired
    private CakeCartMapper cakeCartMapper;

    @Autowired
    private CustomerMappers customerMappers;

    @Override
    public List<AngelCake> showCakeListForArchon(Integer csid)
            throws UserMustBeforeLoginException {
        //首先检查管理是否已登录
        if (csid == null || "".equals(csid)) {
            throw new UserMustBeforeLoginException("请登录管理账号");
        }

        List<AngelCake> cakeList = angelCakeMappers.selectAll();

        return cakeList;
    }

    @Override
    public Integer banMultipleAngelCake(Integer csid, Integer[] acids)
            throws UserMustBeforeLoginException {
        //首先检查管理是否已登录
        if (csid == null || "".equals(csid)) {
            throw new UserMustBeforeLoginException("请登录管理账号");
        }

       /*
       亦同时删除购物车之库内相应蛋糕,
       首先据acids查找相应蛋糕名,以String集合盛放
        */
        List<String> strArr = getAngelCakeCcnames(acids);
        Integer rows = consulMappers.multipleDeleteCakeByCcnames(strArr);

        //再删除蛋糕库之内
        Integer affected = consulMappers.multipleDeleteCakeByAcids(acids);
        return affected;
    }

    @Override
    public Integer deleteByVolumeIds(Integer[] ids, Integer csid)
            throws UserMustBeforeLoginException {
        //首先检查管理是否已登录
        if (csid == null || "".equals(csid)) {
            throw new UserMustBeforeLoginException("请登录管理账号");
        }

        //同时删除购物车数据库中的被删用户之数据
        Integer carts = cakeCartMapper.deleteCartByUids(ids);

        //删除用户库中的用户
        Integer deletes = consulMappers.volumesDeleteByIds(ids);
        return deletes;
    }

    @Override
    public Integer batchSetStatus(Integer[] ids, Integer csid, Integer status)
            throws UserMustBeforeLoginException, RepetitiveOperationException {
        //首先检查管理是否已登录
        if (csid == null || "".equals(csid)) {
            throw new UserMustBeforeLoginException("请登录管理账号");
        }

        //查出库中用户们的数据
        List<Customer> cs = customerMappers.foundBatchDataByIds(ids);

        //status:不为0即为1,取决于管理者意图
        //取出原有状态,与status对比,若其中一名用户原状态与status一直,抛异常
        for (Customer c : cs) {
            if (c.getCancelStatus() == status) {
                throw new RepetitiveOperationException(c.getName() + "已经是操作后意向状态,建议无须重复操作");
            } else {
                System.err.println(c.getName() + "操作完毕");
            }
        }

        //执行注销/激活
        Integer affects = consulMappers.setCancelByVolumeId(status, ids);
        return affects;
    }

    @Override
    public Integer doDeleteSingleCustomer(Integer id, Integer csid)
            throws UserMustBeforeLoginException {
        //首先检查管理是否已登录
        if (csid == null || "".equals(csid)) {
            throw new UserMustBeforeLoginException("请登录管理账号");
        }

        //同时删除购物车之库之对应用户的购物车
        Integer[] ids = {id};
        Integer affected = cakeCartMapper.deleteCartByUids(ids);
        System.err.println("affected: " + affected);

        //执行删除
        Integer deleteRows = customerMappers.deleteById(id);
        return deleteRows;
    }

    @Override
    public Integer doActive(Integer id, Integer csid)
            throws UserMustBeforeLoginException, RepetitiveOperationException {
        //首先检查管理是否已登录
        if (csid == null || "".equals(csid)) {
            throw new UserMustBeforeLoginException("请登录管理账号");
        }

        //查出原有状态
        Customer c = customerMappers.selectById(id);
        //判断是否已为0
        if (c.getCancelStatus() == 1) {
            throw new RepetitiveOperationException("该用户已经为激活状态,无须再进行激活操作");
        }

        Integer affect = consulMappers.setCancelStatusById(1, id);
        return affect;
    }

    @Override
    public Integer letItCancel(Integer id, Integer csid)
            throws UserMustBeforeLoginException, RepetitiveOperationException {
        //首先检查监管理否已登录
        if (csid == null || "".equals(csid)) {
            throw new UserMustBeforeLoginException("请登录管理账号");
        }

        //查出原有状态
        Customer c = customerMappers.selectById(id);
        //判断是否已为0
        if (c.getCancelStatus() == 0) {
            throw new RepetitiveOperationException("该用户已经为注销状态,无须再进行注销操作");
        }

        //执行更新
        Integer affect = consulMappers.setCancelStatusById(0, id);
        return affect;
    }

    @Override
    public List<Customer> InspectionEachAccount(Integer csid)
            throws UserMustBeforeLoginException {
        if (csid == null || "".equals(csid)) {
            throw new UserMustBeforeLoginException("请先登录领事账号");
        }

        return customerMappers.selectAll();
    }

    /**
     * @param csid
     * @param amount
     * @param acid
     * @return
     * @throws UserMustBeforeLoginException
     * @throws CakeAmountZeroException
     */
    @Override
    public Integer changeCakeAmount(Integer csid, Integer amount, Integer acid)
            throws UserMustBeforeLoginException, CakeAmountZeroException {
        //首先判断管理是否在线
        if (csid == null && "".equals(csid)) {
            throw new UserMustBeforeLoginException("请先登录");
        }

        //判断数量是否已填,或填负数
        if (amount == null || "".equals(amount) || amount < 0) {
            throw new CakeAmountZeroException("数量一栏必填");
        }

        //执行
        Integer affected = consulMappers.updateAmountByAcid(amount, acid);

        return affected;
    }

    @Override
    public Integer editAngelaCake(AngelCake angelCake, Integer csid) throws CakeNullException,
            CakeNameDuplicateException, UserMustBeforeLoginException, PropertyNotNullException {
        //首先判断管理是否在线
        if (csid == null && "".equals(csid)) {
            throw new UserMustBeforeLoginException("请先登录");
        }

        //判断所欲改之蛋糕是否存在
        AngelCake cake = angelCakeMappers.selectByAcid(angelCake.getAcid());
        if (cake == null) {
            throw new CakeNullException("无此款蛋糕");
        }

        //判断提交之数据是否已填完
        int i = countMember(angelCake);
        if (i < 6) {
            throw new PropertyNotNullException("提交之设定未完整");
        }

        /*
        判断提交之蛋糕名是否已与其它蛋糕名重复
        根据一个名字统计对应行的数量,大于1则抛异常
         */
        Integer integer = angelCakeMappers.countIdByCakename(angelCake.getCakename());
        System.err.println("cake name:" + angelCake.getCakename());
        if (integer > 1) {
            throw new CakeNameDuplicateException("名称冲突,请另行换名");
        }

        //皆可,执行
        return consulMappers.updateAngelaCakeByAcid(angelCake);
    }

    /**
     * @param acid
     * @param csid
     * @return
     * @throws UserMustBeforeLoginException
     */
    @Override
    public AngelCake transmitsObsolete(Integer acid, Integer csid)
            throws UserMustBeforeLoginException {
        //首先判断领事是否已登录,未登录,抛异常
        if (csid == null || "".equals(csid)) {
            throw new UserMustBeforeLoginException("您尚未登录,请先登录");
        }

        //根据acid获取对应数据,并返回
        return getACakeByAcid(acid);
    }

    /**
     * 删除顺序要注意,先删除车中的,再删除蛋糕库中的,不然购物车持久层会找不到名字
     *
     * @param acid
     * @param csid
     * @return
     * @throws UserMustBeforeLoginException
     */
    @Override
    public Integer deleteOneCake(Integer acid, Integer csid) throws UserMustBeforeLoginException {
        //首先判断领事是否已登录,未登录,抛异常
        if (csid == null || "".equals(csid)) {
            throw new UserMustBeforeLoginException("您尚未登录,请先登录");
        }

        //先根据acid查找出蛋糕名
        AngelCake angelCake = angelCakeMappers.selectByAcid(acid);
        String ccName = angelCake.getCakename();

        //再根据蛋糕名删除车库中的`全部`相应蛋糕
        Integer rows = cakeCartMapper.deleteByCcName(ccName);
        System.err.println("蛋糕车被删除之数:" + rows);
        Integer integer = consulMappers.deleByAcid(acid);
        return integer;
    }

    /**
     * @param angelCake
     * @param csid
     * @return
     * @throws PropertyNotNullException
     * @throws FormatMistakeException
     * @throws CakeNameDuplicateException
     * @throws UserMustBeforeLoginException
     */
    @Override
    public Integer addNewOneCake(AngelCake angelCake, Integer csid) throws PropertyNotNullException,
            FormatMistakeException, CakeNameDuplicateException, UserMustBeforeLoginException {
        //首先判断领事是否已登录,未登录,抛异常
        if (csid == null || "".equals(csid)) {
            throw new UserMustBeforeLoginException("您尚未登录,请先登录");
        }

        //首先将页面传与之数据各设定都判断是否为空,是,抛异常
        String cakename = angelCake.getCakename();
        String caketype = angelCake.getCaketype();
        Integer price = angelCake.getPrice();
        String supplier = angelCake.getSupplier();
        Integer amount = angelCake.getAmount();
        if (cakename == null || "".equals(cakename) || caketype == null || "".equals(caketype)
                || price == null || "".equals(price) || supplier == null || "".equals(supplier)
                || amount == null || "".equals(amount)) {
            throw new PropertyNotNullException("您仍未设完所有属性数据");
        }

        //不允许名字重复
        AngelCake angelCake1 = angelCakeMappers.selectByCakeName(cakename);
        if (angelCake1 != null) {
            throw new CakeNameDuplicateException("不许使用重复的蛋糕名,请另外换一个名称");
        }

        //若输入条目的数据形式有误,抛异常
        if (!(cakename instanceof String) && !(cakename instanceof String) && !(price instanceof Integer)
                && !(supplier instanceof String) && !(amount instanceof Integer)) {
            throw new FormatMistakeException("您输入的形式有误!例如数量只能输入数字,不能输入其他字符");
        }

        Integer integer = consulMappers.adminInsertOneCake(angelCake);
        return integer;
    }

    /**
     * @param account
     * @param password
     * @return
     * @throws UsernameNotExistException
     * @throws PasswordNotMatchException
     */
    @Override
    public Consul login(String account, String password) throws
            UsernameNotExistException, PasswordNotMatchException {
        //根据页面提交之账号查询出数据
        Consul data = consulMappers.selectAdminByAccount(account);
        if (data == null || "".equals(data)) {
            //判断数据是否存在,不存在,抛异常
            throw new UsernameNotExistException("该管理账号不存在!");
        }

        //存在,下一步,判断密码
        String cspassword = data.getCspassword();
        if (!password.equals(cspassword)) {
            //密码错误,抛异常
            throw new PasswordNotMatchException("密码错误!");
        }

        //隐藏密码
        data.setCspassword("");

        //正确,返回数据
        return data;
    }

    /**
     * 封装:根据acid获取蛋糕
     *
     * @param acid
     * @return
     */
    public AngelCake getACakeByAcid(Integer acid) {
        AngelCake cake = new AngelCake();
        if (acid != null && !("".equals(acid))) {
            cake = angelCakeMappers.selectByAcid(acid);
        }
        return cake;
    }

    /**
     * 统计蛋糕豆中非空成员个数
     *
     * @param ac
     * @return
     */
    public int countMember(AngelCake ac) {
        LinkedList<String> acs = new LinkedList<>();

        if ("".equals(ac.getAcid()) || ac.getAcid() == null) {
            System.err.println("swish");
        } else {
            acs.add(Integer.toString(ac.getAcid()));
        }

        if ("".equals(ac.getCakename()) || ac.getCakename() == null) {
            System.err.println("swish");
        } else {
            acs.add(ac.getCakename());
        }

        if ("".equals(ac.getCaketype()) || ac.getCaketype() == null) {
            System.err.println("swish");
        } else {
            acs.add(ac.getCaketype());
        }

        if (ac.getPrice() == null || "".equals(ac.getPrice())) {
            System.err.println("swish");
        } else {
            acs.add(Integer.toString(ac.getPrice()));
        }

        if (ac.getSupplier() == null || "".equals(ac.getSupplier())) {
            System.err.println("swish");
        } else {
            acs.add(ac.getSupplier());
        }

        if (ac.getAmount() == null || "".equals(ac.getAmount())) {
            System.err.println("swish");
        } else {
            acs.add(Integer.toString(ac.getAmount()));
        }

        return acs.size();
    }

    /**
     * 据蛋糕ID们获取相对应之蛋糕名之集合
     *
     * @param acids
     * @return
     */
    public List<String> getAngelCakeCcnames(Integer[] acids) {
        ArrayList<String> strArr = new ArrayList<>();
        List<AngelCake> acs = angelCakeMappers.multiSelectByAcid(acids);
        for (AngelCake ac : acs) {
            strArr.add(ac.getCakename());
        }
        System.err.println(strArr);
        return strArr;
    }
}
