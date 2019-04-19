package com.cake.shop.controller;

import com.cake.shop.coolutil.ResponseResult;
import com.cake.shop.entity.AngelCake;
import com.cake.shop.entity.Consul;
import com.cake.shop.entity.Customer;
import com.cake.shop.service.IAngelCakeService;
import com.cake.shop.service.IConsulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("consuls")
public class ConsulController extends BaseController {
    @Autowired
    private IConsulService iConsulService;

    @Autowired
    private IAngelCakeService iAngelCakeService;

    /**
     * http://localhost:8080/consuls/admin_login?account=admin111&cspassword=111
     *
     * @param account
     * @param cspassword
     * @param session
     * @return
     */
    @PostMapping("admin_login")
    public ResponseResult<Consul> handleAdminLogin(@RequestParam("account") String account,
                                                   @RequestParam("cspassword") String cspassword,
                                                   HttpSession session) {
        Consul consul = iConsulService.login(account, cspassword);
        //存入session中
        session.setAttribute("csid", consul.getCsid());
        session.setAttribute("account", consul.getAccount());
        return new ResponseResult<Consul>(SUCCESS, consul);
    }

    /**
     * http://localhost:8080/consuls/add_new?cakename=ufo&caketype=pp&price=10&supplier=java&amount=101
     *
     * @param cakename
     * @param caketype
     * @param price
     * @param supplier
     * @param amount
     * @param session
     * @return
     */
    @PostMapping("add_new")
    public ResponseResult<Void> handleAdminAddCake(@RequestParam("cakename") String cakename,
                                                   @RequestParam("caketype") String caketype,
                                                   @RequestParam("price") Integer price,
                                                   @RequestParam("supplier") String supplier,
                                                   @RequestParam("amount") Integer amount,
                                                   HttpSession session) {
        Integer csid = objCastToInteger(session.getAttribute("csid"));

        AngelCake cake = new AngelCake();
        cake.setCakename(cakename);
        cake.setCaketype(caketype);
        cake.setPrice(price);
        cake.setSupplier(supplier);
        cake.setAmount(amount);
        Integer integer = iConsulService.addNewOneCake(cake, csid);
        System.err.println(integer);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * http://localhost:8080/consuls/dele_cake?acid=10
     *
     * @param acid
     * @return
     */
    @PostMapping("{acid}/dele_cake")
    public ResponseResult<Void> handleDele(@PathVariable("acid") Integer acid,
                                           HttpSession session) {
        Integer csid = objCastToInteger(session.getAttribute("csid"));
        Integer rows = iConsulService.deleteOneCake(acid, csid);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * transmits 传输,传达
     *
     * @param acid
     * @param hs
     * @return
     */
    @PostMapping("{acid}/transmits")
    public ResponseResult<Void> handleTransmit(@PathVariable("acid") Integer acid, HttpSession hs) {
        Integer csid = objCastToInteger(hs.getAttribute("csid"));
        AngelCake angelCake = iConsulService.transmitsObsolete(acid, csid);

        //会话存入acid
        hs.setAttribute("acid", acid);

        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * @param hs
     * @return
     */
    @GetMapping("editor")
    public ResponseResult<AngelCake> handleShowEditing(HttpSession hs) {
        Integer csid = objCastToInteger(hs.getAttribute("csid"));
        Integer acid = objCastToInteger(hs.getAttribute("acid"));

        AngelCake angelCake = iConsulService.transmitsObsolete(acid, csid);

        //显示在编辑页
        return new ResponseResult<AngelCake>(SUCCESS, angelCake);
    }

    @PostMapping("true_edit")
    public ResponseResult<Void> handleDoingEdit(HttpSession session,
                                                @RequestParam("acid") Integer acid,
                                                @RequestParam("cakename") String cakename,
                                                @RequestParam("caketype") String caketype,
                                                @RequestParam("price") Integer price,
                                                @RequestParam("supplier") String supplier,
                                                @RequestParam("amount") Integer amount) {
        Integer csid = objCastToInteger(session.getAttribute("csid"));
        AngelCake angelCake = new AngelCake();
        angelCake.setAcid(acid);
        angelCake.setCakename(cakename);
        angelCake.setCaketype(caketype);
        angelCake.setPrice(price);
        angelCake.setSupplier(supplier);
        angelCake.setAmount(amount);

        Integer affect = iConsulService.editAngelaCake(angelCake, csid);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * 预览数量
     *
     * @param acid
     * @param hs
     * @return
     */
    @PostMapping("{acid}/modify_amount")
    public ResponseResult<Void> handlePreviewAmount(@PathVariable("acid") Integer acid,
                                                    HttpSession hs) {
        Integer csid = objCastToInteger(hs.getAttribute("csid"));
        AngelCake angelCake = iConsulService.transmitsObsolete(acid, csid);

        //会话存入acid
        hs.setAttribute("acid", acid);

        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * 数量编改
     *
     * @param acid
     * @param amount
     * @param hs
     * @return
     */
    @PostMapping("modified_amount")
    public ResponseResult<Void> handleChangeAmount(@RequestParam("acid") Integer acid,
                                                   @RequestParam("amount") Integer amount,
                                                   HttpSession hs) {
        Integer csid = objCastToInteger(hs.getAttribute("csid"));
        Integer affect = iConsulService.changeCakeAmount(csid, amount, acid);

        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * http://localhost:8080/consuls/inspect
     *
     * @param session
     * @return
     */
    @GetMapping("inspect")
    public ResponseResult<List<Customer>> handlerInspectionAllRegister(HttpSession session) {
        Integer csid = objCastToInteger(session.getAttribute("csid"));
        List<Customer> customers = iConsulService.InspectionEachAccount(csid);
        return new ResponseResult<List<Customer>>(SUCCESS, customers);
    }

    /**
     * @param id
     * @param session
     * @return
     */
    @PostMapping("{id}/set_cancel")
    public ResponseResult<Void> handleSetRegisterCancel(@PathVariable("id") Integer id,
                                                        HttpSession session) {
        Integer csid = objCastToInteger(session.getAttribute("csid"));
        Integer rows = iConsulService.letItCancel(id, csid);
        System.err.println("cancel rows:" + rows);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * @param id
     * @param session
     * @return
     */
    @PostMapping("{id}/do_active")
    public ResponseResult<Void> handleDoActive(@PathVariable("id") Integer id,
                                               HttpSession session) {
        Integer csid = objCastToInteger(session.getAttribute("csid"));
        Integer rows = iConsulService.doActive(id, csid);
        System.err.println("active rows:" + rows);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * @param id
     * @param session
     * @return
     */
    @PostMapping("{id}/delete_single_account")
    public ResponseResult<Void> handlerDoDelete(@PathVariable("id") Integer id,
                                                HttpSession session) {
        Integer csid = objCastToInteger(session.getAttribute("csid"));
        Integer rows = iConsulService.doDeleteSingleCustomer(id, csid);
        System.err.println("delete rows:" + rows);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * 批量注销
     *
     * @param ids
     * @param session
     * @return
     */
    @PostMapping("batch_cancel")
    public ResponseResult<Integer> handleBatchSetCancel(@RequestParam("ids") Integer[] ids,
                                                        HttpSession session) {
        Integer csid = objCastToInteger(session.getAttribute("csid"));
        Integer affects = iConsulService.batchSetStatus(ids, csid, 0);
        return new ResponseResult<Integer>(SUCCESS, affects);
    }

    /**
     * 批量激活
     *
     * @param ids
     * @param session
     * @return
     */
    @PostMapping("batch_activate")
    public ResponseResult<Integer> handleBatchSetActivate(@RequestParam("ids") Integer[] ids,
                                                          HttpSession session) {
        Integer csid = objCastToInteger(session.getAttribute("csid"));
        Integer affects = iConsulService.batchSetStatus(ids, csid, 1);
        return new ResponseResult<Integer>(SUCCESS, affects);
    }

    /**
     * @param ids
     * @param session
     * @return
     */
    @PostMapping("batch_delete_user")
    public ResponseResult<Integer> handleBatchDeleteVolumeUser(@RequestParam("ids") Integer[] ids,
                                                               HttpSession session) {
        Integer csid = objCastToInteger(session.getAttribute("csid"));
        Integer affects = iConsulService.deleteByVolumeIds(ids, csid);
        return new ResponseResult<Integer>(SUCCESS, affects);
    }

    /**
     * @param acids
     * @param session
     * @return
     */
    @PostMapping("many_cake_off_shelve")
    public ResponseResult<Integer> handleBanCakes(@RequestParam("acids") Integer[] acids,
                                                  HttpSession session) {
        Integer csid = objCastToInteger(session.getAttribute("csid"));
        Integer affects = iConsulService.banMultipleAngelCake(csid, acids);
        return new ResponseResult<Integer>(SUCCESS, affects);
    }

    /**
     * @param request
     * @return
     */
    @GetMapping("login_archon_out")
    public ResponseResult<Void> handleArchonLoginOut(HttpServletRequest request) {
        request.getSession().removeAttribute("csid");
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     *
     * @param session
     * @return
     */
    @GetMapping("show_for_archon")
    public ResponseResult<List<AngelCake>> handlerShowCakesForArchon(HttpSession session) {
        Integer csid = objCastToInteger(session.getAttribute("csid"));
        List<AngelCake> list = iConsulService.showCakeListForArchon(csid);
        return new ResponseResult<List<AngelCake>>(SUCCESS, list);
    }
}
