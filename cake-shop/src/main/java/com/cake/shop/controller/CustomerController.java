package com.cake.shop.controller;

import com.cake.shop.coolutil.ResponseResult;
import com.cake.shop.entity.Customer;
import com.cake.shop.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("customers")
public class CustomerController extends BaseController {
    @Autowired
    private ICustomerService ics;

    /**
     * 注册
     * 测试:http://localhost:8080/customers/c_register?name=pwd1010&password=1010
     *
     * @param customer
     * @return
     */
    @PostMapping("c_register")
    public ResponseResult<Void> handlerReg(Customer customer) {
        Integer register = ics.register(customer);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * 登录
     * <p>
     * http://localhost:8080/customers/c_login?name=pwd1010&password=1010
     *
     * @param name
     * @param password
     * @return
     */
    @PostMapping("c_login")
    public ResponseResult<Customer> handlerLogin(@RequestParam("name") String name,
                                                 @RequestParam("password") String password,
                                                 HttpSession session) {
        Customer login = ics.login(name, password);

        //存入session中
        session.setAttribute("id", login.getId());
        session.setAttribute("name", login.getName());

        return new ResponseResult<Customer>(SUCCESS, login);
    }

    /**
     * @param session
     * @return
     */
    @GetMapping("prev_exhibition")
    public ResponseResult<Customer> handlerPrevExhibition(HttpSession session) {
        Integer id = objCastToInteger(session.getAttribute("id"));
        Customer customer = ics.previewOwnInform(id);

        return new ResponseResult<Customer>(SUCCESS, customer);
    }

    /**
     * vary 变更,变化
     */
    /*
     * @param customer
     * @param session
     * @return
     */
    @PostMapping("vary_profile")
    public ResponseResult<Void> handlerAlterProfile(HttpSession session,
                                                    @RequestParam("name") String name,
                                                    @RequestParam("phone") String phone,
                                                    @RequestParam("birth") String birth,
                                                    @RequestParam("gender") Integer gender,
                                                    @RequestParam("address") String address) {
        Integer id = objCastToInteger(session.getAttribute("id"));

        Customer customer = new Customer();
        customer.setName(name);
        customer.setPhone(phone);
        customer.setGender(gender);
        customer.setaddress(address);

        customer.setBirth(strCastToDate(birth));

        Integer affected = ics.varyProfile(customer, id);
        return new ResponseResult<Void>(SUCCESS);
    }

    @PostMapping("revamp_password")
    public ResponseResult<Void> handleRevampPassword(@RequestParam("originalCryptogram") String originalCryptogram,
                                                     @RequestParam("recencyCryptogram") String recencyCryptogram,
                                                     @RequestParam("repeatRecencyCryptogram") String repeatRecencyCryptogram,
                                                     HttpSession session) {
        Integer id = objCastToInteger(session.getAttribute("id"));
        Integer rows = ics.RevampCryptogram(originalCryptogram, recencyCryptogram, repeatRecencyCryptogram, id);
        return new ResponseResult<Void>(SUCCESS);
    }

    @GetMapping("login_out_customer")
    public ResponseResult<Void> handlerLoginOutCustomer(HttpServletRequest request) {
        request.getSession().removeAttribute("id");
        return new ResponseResult<Void>(SUCCESS);
    }
}
