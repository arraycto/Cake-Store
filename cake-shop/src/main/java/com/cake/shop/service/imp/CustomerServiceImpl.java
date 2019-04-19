package com.cake.shop.service.imp;

import com.cake.shop.entity.Customer;
import com.cake.shop.mappers.CustomerMappers;
import com.cake.shop.service.ICustomerService;
import com.cake.shop.service.except.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerMappers cm;

    /**
     * @param originalCryptogram      旧密码
     * @param recencyCryptogram       新密码
     * @param repeatRecencyCryptogram 复新密码
     * @param id                      账户ID
     * @return
     * @throws AccountHasBeenCancelException
     * @throws UserMustBeforeLoginException
     * @throws PasswordNotMatchException
     */
    @Override
    public Integer RevampCryptogram(String originalCryptogram, String recencyCryptogram,
                                    String repeatRecencyCryptogram, Integer id)
            throws AccountHasBeenCancelException, UserMustBeforeLoginException,
            PasswordNotMatchException {
        //首先判断用户是否在线
        if (id == null || "".equals(id)) {
            throw new UserMustBeforeLoginException("您已下线,请重新登录");
        }

        //将库中原数据检出
        Customer c = cm.selectById(id);

        //是否已注销
        if (c.getCancelStatus() == 0) {
            throw new AccountHasBeenCancelException("您的账户已注销");
        }

        //判断页面旧与库中旧是否匹配
        if (!originalCryptogram.equals(c.getPassword())) {
            throw new PasswordNotMatchException("旧密码错误");
        }

        //新与复新是否一致
        if (!recencyCryptogram.equals(repeatRecencyCryptogram)) {
            throw new PasswordNotMatchException("两次输入的新密码不一致");
        }

        //执行更新
        Integer affected = cm.updatePasswordById(repeatRecencyCryptogram, id);

        return affected;
    }

    /**
     * @param customer
     * @param id
     * @return
     * @throws UserMustBeforeLoginException
     * @throws UsernameConflictException
     * @throws PhoneBindOverrunException
     */
    @Override
    public Integer varyProfile(Customer customer, Integer id)
            throws UserMustBeforeLoginException, UsernameConflictException, PhoneBindOverrunException {
        //首先判断用户是否在线
        if (id == null || "".equals(id)) {
            throw new UserMustBeforeLoginException("您已下线,请重新登录");
        }

        //次之判断用户名是否冲突
        Integer rows = cm.countIdByName(customer.getName());
        System.err.println("Rows: " + rows);
        if (rows > 1) {
            throw new UsernameConflictException("该用户名已被使用,请更换另一个");
        }

        //判断电话绑定的账户数量
        Integer query = cm.countIdByPhone(customer.getPhone());
        System.err.println("Query: " + query);
        if (query > 1) {
            throw new PhoneBindOverrunException("不好意思,一个电话只能同时绑定2个账户");
        }

        customer.setId(id);
        //执行,并返回
        return cm.updateFileInfoById(customer);
    }

    /**
     * 查找库中数据并使之于视图上预览
     *
     * @param id
     * @return
     * @throws UserMustBeforeLoginException
     */
    @Override
    public Customer previewOwnInform(Integer id) throws UserMustBeforeLoginException {
        if (id == null || "".equals(id)) {
            throw new UserMustBeforeLoginException("请登录账号");
        }

        Customer customer = cm.selectById(id);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birth = null;

        try {
            birth = customer.getBirth();
            String yearMonthDay = format.format(birth);
            birth = format.parse(yearMonthDay);
        } catch (Exception e) {
            try {
                birth = format.parse("1500-01-01");
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }

        //若性别为null,在页面上显示默认保密
        try {
            Integer gender = customer.getGender();
            System.err.println("gender:" + gender);
            if (gender==null){
                customer.setGender(0);
            }
            System.err.println("getGender:"+customer.getGender());
        } catch (RuntimeException e) {
            customer.setGender(0);
            System.err.println("customer.getGender:"+customer.getGender());
        }

        customer.setBirth(birth);
        System.err.println("professional time:" + customer.getBirth());
        return customer;
    }

    /**
     * 登录
     *
     * @param name
     * @param password
     * @return
     * @throws UsernameNotExistException
     * @throws PasswordNotMatchException
     */
    @Override
    public Customer login(String name, String password)
            throws UsernameNotExistException, PasswordNotMatchException {
        //根据登录名查询库中数据
        Customer database = cm.selectCustomerByName(name);

        //若为空,表示用户名不存在,抛异常
        if (database == null) {
            throw new UsernameNotExistException("登录失败,该用户不存在!");
        } else {
            //否则,表示用户名存在

            //判断是否已注销,是,则无法登录,抛异常,否,下一步
            if (database.getCancelStatus() != 1) {
                throw new AccountHasBeenCancelException("您的账号已经被注销,无法登录");
            }

            //判断登录密码与库中密码是否匹配
            if (!password.equals(database.getPassword())) {
                //不匹配,抛异常
                throw new PasswordNotMatchException("登录失败,密码错误!");
            }

        }
        //否则,登陆成功,返回数据
        return database;
    }

    /**
     * 顾客注册成为会员
     *
     * @param customer
     * @return
     * @throws UsernameConflictException
     */
    @Override
    public Integer register(Customer customer) throws UsernameConflictException {
        //根据页面提交的注册数据查询数据库中的数据
        Customer c = cm.selectCustomerByName(customer.getName());

        //若为空,说明用户名尚未被使用,可以注册
        if (c == null) {
            //将注销状态缺省为1(未注销)
            customer.setCancelStatus(1);

            //将性别缺省为0(保密)
            customer.setGender(0);

            //限制一个电话仅能注册两个账号
            Integer rows = cm.countIdByPhone(customer.getPhone());
            if (rows > 2) {
                throw new PhoneBindOverrunException("不好意思,一个电话只能同时注册2个账户");
            }

            Integer affect = cm.insertAnCustomer(customer);
            return affect;
        } else {
            //否则,用户名已被他人所用
            throw new UsernameConflictException("不好意思,您尝试注册的用户名已被别人使用,请另行更换");
        }
    }

}
