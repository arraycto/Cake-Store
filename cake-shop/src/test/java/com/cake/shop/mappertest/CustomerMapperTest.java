package com.cake.shop.mappertest;

import com.cake.shop.entity.Customer;
import com.cake.shop.mappers.CustomerMappers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerMapperTest {
    @Autowired
    private CustomerMappers cm;

    @Test
    public void customerAddTest() {
        Customer customer = new Customer();

        customer.setName("pwd12345");
        customer.setPassword("12345");

        Integer integer = cm.insertAnCustomer(customer);
        System.err.println(integer);
    }

    @Test
    public void customerNameFindTest() {
        Customer customer = new Customer();

        Customer customer1 = cm.selectCustomerByName("pwd12345");

        System.err.println(customer1.toString());
    }

    @Test
    public void updateInfoTest() {
        Customer c = new Customer();
        c.setId(5);
        c.setName("K12345");

        Date date = new Date();
        String s = new String("2016-01-11");
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        try {
            date = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setBirth(date);
        c.setPhone("200200");

        Integer integer = cm.updateFileInfoById(c);
        System.err.println(integer);
    }

    @Test
    public void testUpdatePassword() {
        Integer row = cm.updatePasswordById("12345", 5);
        System.err.println(row);
    }

    @Test
    public void selectAllTest() {
        List<Customer> customers = cm.selectAll();
        System.err.println(customers);
        for (Customer c : customers) {
            System.err.println(c.toString());
        }
    }

    @Test
    public void selectBatchIds() {
        Integer[] ids = {17, 18, 19};
        List<Customer> cs = cm.foundBatchDataByIds(ids);
        for (Customer c : cs) {
            System.err.println(c.toString());
        }
    }
}
