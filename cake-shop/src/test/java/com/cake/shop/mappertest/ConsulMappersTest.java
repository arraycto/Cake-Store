package com.cake.shop.mappertest;

import com.cake.shop.entity.AngelCake;
import com.cake.shop.entity.Consul;
import com.cake.shop.mappers.ConsulMappers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsulMappersTest {
    @Autowired
    private ConsulMappers consulMappers;

    @Test
    public void regNewAdmin() {
        Consul consul = new Consul();
        consul.setAccount("admin222");
        consul.setCspassword("222");
        Integer integer = consulMappers.insertAnAdmin(consul);
        System.err.println(integer);
    }

    @Test
    public void foundAllAdminTest() {
        List<Consul> consuls = consulMappers.foundAllAdmin();
        for (Consul c : consuls
        ) {
            System.err.println(c.toString());
        }
    }

    @Test
    public void adminInsertOneCakeTest() {
        AngelCake angelCake = new AngelCake();
        angelCake.setCakename("夏日罗希诺里耶");
        angelCake.setCaketype("抹茶蛋糕");
        angelCake.setPrice(22);
        angelCake.setSupplier("Flora Russ");
        angelCake.setAmount(102);

        Integer integer = consulMappers.adminInsertOneCake(angelCake);
        System.err.println(integer);
    }

    @Test
    public void updateAngelCakeByAcidTest() {
        AngelCake angelCake = new AngelCake();

        angelCake.setCakename("夏日罗希诺里耶");
        angelCake.setCaketype("抹茶蛋糕");
        angelCake.setPrice(21);
        angelCake.setSupplier("Flora Russ");
        angelCake.setAcid(5);
        angelCake.setAmount(166);

        Integer integer = consulMappers.updateAngelCakeByAcid(angelCake);
        System.err.println(integer);
    }

    @Test
    public void updateCakeTest() {
        AngelCake cake = new AngelCake();
        cake.setCakename("芙蕾蒂蜜友");
        cake.setCaketype("千层蛋糕");
        cake.setPrice(30);
        cake.setSupplier("俄萨海拉制甜行");
        cake.setAmount(200);
        cake.setAcid(2);

        Integer integer = consulMappers.updateAngelaCakeByAcid(cake);

        System.err.println(integer);
    }

    @Test
    public void updateAmountTest() {
        Integer rows = consulMappers.updateAmountByAcid(120, 15);
        System.err.println(rows);
    }

    @Test
    public void setById() {
        Integer status = consulMappers.setCancelStatusById(0, 19);
        System.err.println(status);
    }

    @Test
    public void setByVolumeId() {
        Integer[] ids = {17, 18, 19};
        Integer status = consulMappers.setCancelByVolumeId(0, ids);

        System.err.println(status);
    }

    @Test
    public void deleteByVolumeId() {
        Integer[] ids = {6, 7, 10};
        Integer status = consulMappers.volumesDeleteByIds(ids);

        System.err.println(status);
    }

    @Test
    public void deleteCartByVolumeCcnames() {
        List<String> strings = new ArrayList<>();
        strings.add("法兰雪瑞");
        Integer integer = consulMappers.multipleDeleteCakeByCcnames(strings);
        System.err.println(integer);
    }
}
