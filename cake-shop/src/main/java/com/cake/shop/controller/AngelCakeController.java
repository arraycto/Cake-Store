package com.cake.shop.controller;

import com.cake.shop.coolutil.ResponseResult;
import com.cake.shop.entity.AngelCake;
import com.cake.shop.service.IAngelCakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("angel_cake")
public class AngelCakeController extends BaseController {
    @Autowired
    private IAngelCakeService iacs;

    /**
     * 测试 http://localhost:8080/angel_cake/show_all_cake
     *
     * @return
     */
    @GetMapping("show_all_cake")
    public ResponseResult<List<AngelCake>> handleShowAllCake() {
        List<AngelCake> cakeList = iacs.showAllCake();
        return new ResponseResult<List<AngelCake>>(SUCCESS, cakeList);
    }

    /**
     * http://localhost:8080/angel_cake/show_cake_type
     * <p>
     * 展示蛋糕品种
     *
     * @return
     */
    @GetMapping("show_cake_type")
    public ResponseResult<List<AngelCake>> handleShowCakeType() {
        List<AngelCake> angelCakes = iacs.getDistinctCakeType();
        return new ResponseResult<List<AngelCake>>(SUCCESS, angelCakes);
    }

    /**
     * http://localhost:8080/angel_cake/千层蛋糕/show_target_cake
     *
     * @param caketype
     * @return
     */
    @GetMapping("{caketype}/show_target_cake")
    public ResponseResult<List<AngelCake>> handleShowTargetCake(@PathVariable("caketype") String caketype) {

        System.err.println("cakeTYPE:" + caketype);

        List<AngelCake> angelCakes = iacs.getByCakeType(caketype);
        return new ResponseResult<List<AngelCake>>(SUCCESS, angelCakes);
    }

    /**
     * 根据关键词搜索
     * http://localhost:8080/angel_cake/search_border?requirement=千
     *
     * @param requirement
     * @return
     */
    @PostMapping("search_border")
    public ResponseResult<List<AngelCake>> handleSearchByCondition(
            @RequestParam("requirement") String requirement) {
        System.err.println(requirement);
        List<AngelCake> angelCakes = iacs.searchByKeyWord(requirement);
        return new ResponseResult<List<AngelCake>>(SUCCESS, angelCakes);
    }

}
