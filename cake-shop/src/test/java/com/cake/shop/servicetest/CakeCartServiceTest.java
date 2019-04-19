package com.cake.shop.servicetest;

import com.cake.shop.entity.CakeCart;
import com.cake.shop.service.ICakeCartService;
import com.cake.shop.service.except.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CakeCartServiceTest {
    @Autowired
    private ICakeCartService iccs;

    @Test
    public void addTest() {
        try {
            Integer integer = iccs.addMyCart(1, null);
            System.err.println(integer);
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void showTest() {
        try {
            List<CakeCart> cakeCarts = iccs.showAllCakeCart();
            for (CakeCart cc : cakeCarts
            ) {
                System.err.println(cc.toString());
            }
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void deleteTest() {
        try {
            Integer integer = iccs.deleteByCcid(3, 11);
            System.err.println(integer);
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void addMultipleTest() {
        try {
            Integer[] acids = {2, 4, 6};
            Integer cuid = 6;
            Integer integer = iccs.addMultiCake(acids, cuid);
            System.err.println(integer);
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void deleMultipleTest() {
        try {
            Integer[] ccids = {43, 92};
            Integer cuid = 9;
            Integer integer = iccs.delMultiCake(ccids, cuid);
            System.err.println(integer);
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }
}
