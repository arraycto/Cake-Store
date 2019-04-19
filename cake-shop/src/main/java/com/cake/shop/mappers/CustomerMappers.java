package com.cake.shop.mappers;

import com.cake.shop.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 持久层顾客
 */
public interface CustomerMappers {
    /**
     * 插入新顾客一名
     *
     * @param customer
     * @return
     */
    Integer insertAnCustomer(Customer customer);

    /**
     * 根据名字找
     *
     * @param name
     * @return
     */
    Customer selectCustomerByName(String name);

    /**
     * 全寻
     *
     * @return
     */
    List<Customer> selectAll();

    /**
     * @param id
     * @return
     */
    Customer selectById(@Param("id") Integer id);

    /**
     * 根据ID修改个人资料
     *
     * @param customer
     * @return
     */
    Integer updateFileInfoById(Customer customer);

    /**
     * @param name
     * @return
     */
    Integer countIdByName(@Param("name") String name);

    /**
     * @param phone
     * @return
     */
    Integer countIdByPhone(@Param("phone") String phone);

    /**
     * @param password
     * @param id
     * @return
     */
    Integer updatePasswordById(@Param("password") String password, @Param("id") Integer id);

    /**
     * @param id
     * @return
     */
    Integer deleteById(@Param("id") Integer id);

    /**
     * batch:批量
     *
     * @param ids
     * @return
     */
    List<Customer> foundBatchDataByIds(@Param("array") Integer[] ids);
}
