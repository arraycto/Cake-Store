package com.cake.shop.mappers;

import com.cake.shop.entity.AngelCake;
import com.cake.shop.entity.CakeCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CakeCartMapper {
    /**
     * @return
     */
    List<CakeCart> foundAll();

    /**
     * @param cakeCart
     * @return
     */
    Integer insertIntoOneCake(CakeCart cakeCart);

    /**
     * @param ccid
     * @return
     */
    Integer deleByCcid(Integer ccid);

    /**
     * @param cuid
     * @return
     */
    List<CakeCart> seleByCuid(Integer cuid);

    /**
     * @param ccid
     * @return
     */
    CakeCart seleByCcid(Integer ccid);

    /**
     * @param cakes
     * @return
     */
    Integer bulkInsertCart(@Param("cakes") List<CakeCart> cakes);

    /**
     * @param ccname
     * @return
     */
    Integer deleteByCcName(@Param("ccname") String ccname);

    /**
     *
     * @param ccids
     * @param cuid
     * @return
     */
    Integer delByOneCuidAndMultiCcid(@Param("ccids") List<Integer> ccids,@Param("cuid") Integer cuid);

    /**
     *
     * @param cuids
     * @return
     */
    Integer deleteCartByUids(@Param("array") Integer[] cuids);
}
