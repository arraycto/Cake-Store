package com.cake.shop.service;

import com.cake.shop.entity.AngelCake;
import com.cake.shop.service.except.KeyWordNullException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 业务层蛋糕商品
 */
public interface IAngelCakeService {
    /**
     * 展示所有蛋糕
     *
     * @return
     */
    List<AngelCake> showAllCake();

    /**
     * @param acid
     * @return
     */
    AngelCake findByAcid(Integer acid);

    /**
     * @param amount
     * @param acid
     * @return
     */
    Integer changeAmount(@Param("amount") Integer amount, @Param("acid") Integer acid);


    /**
     * @param cakeType
     * @return
     */
    List<AngelCake> getByCakeType(String cakeType);

    /**
     * @param termChar
     * @return
     * @throws KeyWordNullException
     */
    List<AngelCake> searchByKeyWord(String termChar) throws KeyWordNullException;

    /**
     * 获取所有蛋糕种类
     *
     * @return
     */
    List<AngelCake> getDistinctCakeType();

}
