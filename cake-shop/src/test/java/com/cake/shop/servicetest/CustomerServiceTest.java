package com.cake.shop.servicetest;

import com.cake.shop.entity.Customer;
import com.cake.shop.service.ICustomerService;
import com.cake.shop.service.except.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    private ICustomerService ics;

    @Test
    public void testReg() {
        try {
            Customer customer = new Customer();
            customer.setName("pwd1122");
            customer.setPassword("1122");
            customer.setPhone("666-777");
            Integer register = ics.register(customer);
            System.err.println(register);
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testLogin() {
        try {
            Customer login = ics.login("pwd1122", "1122");
            System.err.println(login.toString());
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testFindById() {
        Customer customer = new Customer();
        try {
            customer = ics.previewOwnInform(15);
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
        System.err.println(customer.toString());
    }

    @Test
    public void testUpdateById() {
        Customer c = new Customer();
        c.setName("pwd1122");
        c.setPhone("200200");
        c.setBirth(null);
        try {
            Integer rows = ics.varyProfile(c, 6);
            System.err.println(rows);
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testModifyPassword() {
        try {
            Integer row = ics.RevampCryptogram("1234", "1234", "1234", 1);
            System.err.println(row);
        } catch (ServiceException e) {
            String message = e.getMessage();
            System.err.println(message);
        }
    }

}
