package com.cake.shop.controller;

import com.cake.shop.coolutil.ResponseResult;
import com.cake.shop.entity.CakeCart;
import com.cake.shop.service.ICakeCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("cake_cart")
public class CakeCartController extends BaseController {
    @Autowired
    private ICakeCartService iccs;

    /**
     * 展示一位会员的购物车中的列表
     * <p>
     * http://localhost:8080/cake_cart/cuid=3
     *
     * @param session
     * @return
     */
    @GetMapping("/")
    public ResponseResult<List<CakeCart>> handlerShow(HttpSession session) {
        Object id = session.getAttribute("id");
        Integer cuid = objCastToInteger(id);
        List<CakeCart> cakeCarts = iccs.foundByCuid(cuid);
        return new ResponseResult<List<CakeCart>>(SUCCESS, cakeCarts);
    }

    /**
     * 添入购物车
     * http://localhost:8080/cake_cart/2/add
     *
     * @param acid
     * @param session
     * @return
     */
    @PostMapping("{acid}/add")
    public ResponseResult<Void> handlerAddCart(@PathVariable("acid") Integer acid,
                                               HttpSession session) {
        System.err.println("acid: " + acid);

        Object id = session.getAttribute("id");
        Integer cuid = objCastToInteger(id);

        Integer integer = iccs.addMyCart(acid, cuid);
        System.err.println(integer);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * http://localhost:8080/cake_cart/delete?ccid=8
     *
     * @param session
     * @param ccid
     * @return
     */
    @PostMapping("{ccid}/delete")
    public ResponseResult<Void> handlerDeleted(HttpSession session,
                                               @PathVariable("ccid") Integer ccid) {
        Integer id = getUidFromSession(session);
        Integer integer = iccs.deleteByCcid(id, ccid);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * volume批量 increase增加
     * http://localhost:8080/cake_cart/volume_increase?acids=1&acids=2&cuid=13
     *
     * @param acids
     * @param session
     * @return
     */
    @PostMapping("volume_increase")
    public ResponseResult<Void> handleVolumeIncrease(@RequestParam("acids") Integer[] acids,
                                                     HttpSession session) {
        Object id = session.getAttribute("id");
        Integer cuid = objCastToInteger(id);

        Integer rows = iccs.addMultiCake(acids, cuid);
        System.err.println("increase: " + rows);
        return new ResponseResult<Void>(SUCCESS);
    }

    /**
     * http://localhost:8080/cake_cart/volume_del?ccids=43&ccids=92&cuid=9
     *
     * @param ccids
     * @param session
     * @return
     */
    @PostMapping("volume_del")
    public ResponseResult<Integer> handleVolumeDel(@RequestParam("ccids") Integer[] ccids,
                                                   HttpSession session) {
        for (int i = 0; i < ccids.length; i++) {
            System.err.print("("+ccids[i]+")");
        }
        Integer cuid = objCastToInteger(session.getAttribute("id"));

        Integer rows = iccs.delMultiCake(ccids, cuid);
        return new ResponseResult<Integer>(SUCCESS, rows);
    }
}
