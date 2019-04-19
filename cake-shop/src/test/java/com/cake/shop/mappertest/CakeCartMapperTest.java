package com.cake.shop.mappertest;

import com.cake.shop.entity.AngelCake;
import com.cake.shop.entity.CakeCart;
import com.cake.shop.mappers.AngelCakeMappers;
import com.cake.shop.mappers.CakeCartMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CakeCartMapperTest {
    @Autowired
    private CakeCartMapper ccm;

    @Autowired
    private AngelCakeMappers acms;

    @Test
    public void testFindAll() {
        List<CakeCart> carts = ccm.foundAll();
        for (CakeCart cc : carts
        ) {
            System.err.println(cc.toString());
        }
    }

    @Test
    public void testInsert() {
        CakeCart cart = new CakeCart();
        cart.setCcname("佩梯歌意蛋糕");
        cart.setCctype("千层蛋糕");
        cart.setCcprice(20);
        cart.setCcsupplier("米莱斯糕点屋");

        Integer integer = ccm.insertIntoOneCake(cart);
        System.err.println(integer);
    }

    @Test
    public void testDelete() {
        Integer integer = ccm.deleByCcid(1);
        System.err.println(integer);
    }

    @Test
    public void testBulkInsert() {
        Integer[] acids = {1, 3, 5};
        List<AngelCake> angelCakes = acms.multiSelectByAcid(acids);
        AngelCake angelCake = angelCakes.get(0);
        AngelCake angelCake1 = angelCakes.get(1);
        AngelCake angelCake2 = angelCakes.get(2);

        CakeCart cakeCart = new CakeCart();
        CakeCart cakeCart1 = new CakeCart();
        CakeCart cakeCart2 = new CakeCart();

        cakeCart.setCcname(angelCake.getCakename());
        cakeCart.setCctype(angelCake.getCaketype());
        cakeCart.setCcprice(angelCake.getPrice());
        cakeCart.setCcsupplier(angelCake.getSupplier());
        cakeCart.setCuid(6);

        cakeCart1.setCcname(angelCake1.getCakename());
        cakeCart1.setCctype(angelCake1.getCaketype());
        cakeCart1.setCcprice(angelCake1.getPrice());
        cakeCart1.setCcsupplier(angelCake1.getSupplier());
        cakeCart1.setCuid(7);

        cakeCart2.setCcname(angelCake2.getCakename());
        cakeCart2.setCctype(angelCake2.getCaketype());
        cakeCart2.setCcprice(angelCake2.getPrice());
        cakeCart2.setCcsupplier(angelCake2.getSupplier());
        cakeCart2.setCuid(8);

        List<CakeCart> cakes = new ArrayList<CakeCart>();
        cakes.add(cakeCart);
        cakes.add(cakeCart1);
        cakes.add(cakeCart2);

        Integer rows = ccm.bulkInsertCart(cakes);
        System.err.println(rows);
    }

    @Test
    public void testBulkInsert01() {
        Integer[] acids = {1, 3, 5};
        List<AngelCake> angelCakes = acms.multiSelectByAcid(acids);

        ArrayList<CakeCart> carts = new ArrayList<CakeCart>();

        Integer rows = 0;
        Integer cuid = 7;
        for (int i = 0; i < angelCakes.size(); i++) {
            CakeCart cart = new CakeCart();
            cart.setCcname(angelCakes.get(i).getCakename());
            cart.setCctype(angelCakes.get(i).getCaketype());
            cart.setCcprice(angelCakes.get(i).getPrice());
            cart.setCcsupplier(angelCakes.get(i).getSupplier());
            cart.setCuid(cuid);

            carts.add(cart);
        }
        rows = ccm.bulkInsertCart(carts);

        System.out.println("list:" + carts);
        System.err.println(rows);
    }

    @Test
    public void delVolumeTest() {
        Integer[] ccidArr = {41, 42};
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < ccidArr.length; i++) {
            linkedList.add(ccidArr[i]);
        }
        System.err.println(linkedList);
        Integer delrows = ccm.delByOneCuidAndMultiCcid(linkedList, 9);
        System.err.println(delrows);
    }

    @Test
    public void deleteByIds(){
        Integer[] cuids={6,7,10,9,4};
        Integer i = ccm.deleteCartByUids(cuids);
        System.err.println(i);
    }
}
