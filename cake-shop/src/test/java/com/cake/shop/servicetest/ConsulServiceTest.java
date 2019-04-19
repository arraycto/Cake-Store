package com.cake.shop.servicetest;

import com.cake.shop.entity.AngelCake;
import com.cake.shop.entity.Consul;
import com.cake.shop.entity.Customer;
import com.cake.shop.service.IConsulService;
import com.cake.shop.service.except.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsulServiceTest {
    @Autowired
    private IConsulService iConsulService;

    @Test
    public void loginTest() {
        try {
            Consul admin123 = iConsulService.login("admin1234", "1234");
            System.err.println(admin123.toString());
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void addTest() {
        try {

            AngelCake angelCake = new AngelCake();
            angelCake.setCakename("尼布甲尼撒宫廷款");
            angelCake.setCaketype("巧克力蛋糕");
            angelCake.setPrice(23);
            angelCake.setSupplier("克克达尔");
            angelCake.setAmount(100);

            Integer integer = iConsulService.addNewOneCake(angelCake,1);
            System.err.println(integer);
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void deleteByAcid() {
        try {
            Integer acid = 17;
            Integer integer = iConsulService.deleteOneCake(acid,1);
            System.err.println(integer);
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testTrans() {
        try {
            AngelCake angelCake = iConsulService.transmitsObsolete(15, 1);
            System.err.println(angelCake.toString());
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testUpdate() {
        try {
            AngelCake cake = new AngelCake();
            cake.setAcid(4);
            cake.setCakename("代号瓦蓝屉");
            cake.setCaketype("奶油蛋糕");
            cake.setPrice(44);
            cake.setSupplier("特兰希尔供应点");
            cake.setAmount(700);

            Integer integer = iConsulService.editAngelaCake(cake, 2);

            System.err.println(integer);
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testChangeAmount() {
        try {
            Integer integer = iConsulService.changeCakeAmount(2, 130, 15);
            System.err.println(integer);
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void inspectionTest() {
        try {
            List<Customer> cs = iConsulService.InspectionEachAccount(2);
            for (Customer c : cs) {
                System.err.println(c.toString());
            }
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void updateStatusTest() {
        try {
            Integer i = iConsulService.letItCancel(20, 1);
            System.err.println(i);
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testDeleted() {
        try {
            Integer d = iConsulService.doDeleteSingleCustomer(9, 1);
            System.err.println(d);
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testVolumeSetStatus() {
        Integer[] ids = {17, 18, 19};
        try {
            Integer integer = iConsulService.batchSetStatus(ids, 1, 1);
            System.err.println(integer);
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testDeleteByVolumeIds() {
        Integer[] ids = {3, 8};

        try {
            Integer i = iConsulService.deleteByVolumeIds(ids, 1);
            System.err.println(i);
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testBanMultipleAngelCake() {
        Integer[] ids = {24, 25};
        try {
            Integer integer = iConsulService.banMultipleAngelCake(1, ids);
            System.err.println(integer);
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }
}
