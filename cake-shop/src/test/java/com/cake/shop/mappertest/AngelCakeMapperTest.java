package com.cake.shop.mappertest;

import com.cake.shop.entity.AngelCake;
import com.cake.shop.mappers.AngelCakeMappers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AngelCakeMapperTest {

    @Autowired
    private AngelCakeMappers acm;

    @Test
    public void seleAllTest() {
        List<AngelCake> list = acm.selectAll();
        for (AngelCake angelCake :
                list) {
            System.err.println(angelCake.toString());
        }

    }

    @Test
    public void distinctByTest() {
        List<AngelCake> angelCakes = acm.selectDistinctCakeType();
        for (AngelCake ac : angelCakes
        ) {
            System.err.println(ac.toString());
        }
    }

    @Test
    public void selectByTypeTest() {
        List<AngelCake> cakes = acm.foundByCakeType("千层蛋糕");
        for (AngelCake ac : cakes
        ) {
            System.err.println(ac.toString());
        }
    }

    @Test
    public void selectLikeBlurry() {

        List<AngelCake> angelCakes = acm.selectBlurry("千");

        for (AngelCake ac : angelCakes
        ) {
            System.err.println(ac.toString());
        }
    }


    @Test
    public void selectByNameTest() {
        AngelCake cake = acm.selectByCakeName("ufo");
        System.err.println(cake.toString());
    }

    @Test
    public void multiSelectByAcidTest() {
        Integer[] ids = {1, 2, 5};
        List<AngelCake> cakes = acm.multiSelectByAcid(ids);
        for (AngelCake ac : cakes) {
            System.err.println(ac.toString());
        }
    }

    @Test
    public void updateAmountByAcidTest() {
        AngelCake cake = new AngelCake();
        cake.setAcid(1);
        cake.setAmount(900);

        AngelCake cake1 = new AngelCake();
        cake1.setAcid(2);
        cake1.setAmount(1000);

        AngelCake cake2 = new AngelCake();
        cake2.setAcid(4);
        cake2.setAmount(400);

        ArrayList<AngelCake> cakes = new ArrayList<>();
        cakes.add(cake);
        cakes.add(cake1);
        cakes.add(cake2);

        System.err.println(cakes);
        Integer integer = acm.volumeUpdateAmountByAcid(cakes);

        System.err.println(integer);
    }

    @Test
    public void updateAmountByAcidTest01() {
        LinkedList<AngelCake> cakes = new LinkedList<>();
        Integer[] acids = {1, 4, 3};
        Integer[] amounts = {230, 546, 400};
        for (int i = 0; i < acids.length && i < amounts.length; i++) {
            AngelCake cake = new AngelCake();
            cake.setAcid(acids[i]);
            cake.setAmount(amounts[i]);
            System.err.println("tostring:" + cake.toString());
            cakes.add(cake);
        }
        System.err.println("list:" + cakes);

        Integer integer = acm.volumeUpdateAmountByAcid(cakes);
        System.err.println(integer);
    }

    @Test
    public void countIdByCakeNameTest(){
        Integer was_vegas = acm.countIdByCakename("Was Vegas");
        System.err.println(was_vegas);
    }
}
