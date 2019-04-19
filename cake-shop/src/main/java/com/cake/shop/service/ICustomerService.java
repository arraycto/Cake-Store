package com.cake.shop.service;

import com.cake.shop.entity.Customer;
import com.cake.shop.service.except.*;
import org.apache.ibatis.annotations.Param;

/**
 * 顾客业务接口
 */
public interface ICustomerService {
    /**
     * 会员注册
     *
     * @param customer
     * @return
     * @throws UsernameConflictException
     */
    Integer register(Customer customer) throws UsernameConflictException;

    /**
     * 登录
     *
     * @param name
     * @param password
     * @return
     * @throws UsernameNotExistException
     * @throws PasswordNotMatchException
     */
    Customer login(@Param("name") String name, @Param("password") String password)
            throws UsernameNotExistException, PasswordNotMatchException;

    /**
     *
     * @param id
     * @return
     * @throws UserMustBeforeLoginException
     */
    Customer previewOwnInform(Integer id) throws UserMustBeforeLoginException;

    /**
     * @param customer
     * @param id
     * @return
     * @throws UserMustBeforeLoginException
     * @throws UsernameConflictException
     * @throws PhoneBindOverrunException
     */
    Integer varyProfile(Customer customer, Integer id) throws UserMustBeforeLoginException,
            UsernameConflictException, PhoneBindOverrunException;

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
    Integer RevampCryptogram(String originalCryptogram, String recencyCryptogram,
                             String repeatRecencyCryptogram, Integer id)
            throws AccountHasBeenCancelException, UserMustBeforeLoginException, PasswordNotMatchException;
}
