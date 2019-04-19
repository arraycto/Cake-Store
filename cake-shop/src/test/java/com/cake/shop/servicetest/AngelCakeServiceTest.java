package com.cake.shop.servicetest;

import com.cake.shop.entity.AngelCake;
import com.cake.shop.service.IAngelCakeService;
import com.cake.shop.service.except.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AngelCakeServiceTest {
    @Autowired
    private IAngelCakeService iacs;

    @Test
    public void testShowAll() {
        List<AngelCake> list = iacs.showAllCake();

        for (AngelCake ac : list
                ) {
            System.err.println(ac.toString());
        }
    }

    @Test
    public void testShowAllType() {
        List<AngelCake> cakes = iacs.showAllCake();

        for (AngelCake ac:cakes
             ) {
            System.err.println(ac.toString());
        }
    }

    @Test
    public void testGetByType() {
        List<AngelCake> type = iacs.getByCakeType("千层蛋糕");
        for (AngelCake ac : type) {
            System.err.println(ac.toString());
        }
    }

    @Test
    public void testSearchBlur() {
        try {
            List<AngelCake> type = iacs.searchByKeyWord("千");
            for (AngelCake ac : type) {
                System.err.println(ac.toString());
            }
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }
}
