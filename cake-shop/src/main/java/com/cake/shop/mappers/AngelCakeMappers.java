package com.cake.shop.mappers;

import com.cake.shop.entity.AngelCake;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 持久层蛋糕商品
 */
public interface AngelCakeMappers {
    /**
     * 查找全部蛋糕
     *
     * @return
     */
    List<AngelCake> selectAll();

    /**
     * @param acid
     * @return
     */
    AngelCake selectByAcid(@Param("acid") Integer acid);

    /**
     * @param amount
     * @param Acid
     * @return
     */
    Integer modifyAmountByAcid(@Param("amount") Integer amount,
                               @Param("acid") Integer Acid);

    /**
     * @return
     */
    List<AngelCake> selectDistinctCakeType();

    /**
     * @param cakeType
     * @return
     */
    List<AngelCake> foundByCakeType(@Param("cakeType") String cakeType);

    /**
     * @param term
     * @return
     */
    List<AngelCake> selectBlurry(@Param("term") String term);

    /**
     * @param cakename
     * @return
     */
    AngelCake selectByCakeName(@Param("cakename") String cakename);

    /**
     * @param acids
     * @return
     */
    List<AngelCake> multiSelectByAcid(Integer[] acids);

    /**
     * @param cakes
     * @return
     */
    Integer volumeUpdateAmountByAcid(List<AngelCake> cakes);

    /**
     *
     * @param cakename
     * @return
     */
    Integer countIdByCakename(@Param("cakename") String cakename);
}
