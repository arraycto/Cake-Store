package com.cake.shop.service;

import com.cake.shop.entity.CakeCart;
import com.cake.shop.service.except.*;

import java.util.List;

public interface ICakeCartService {
    /**
     * @param acid
     * @param cuid
     * @return
     * @throws CakeAmountZeroException
     * @throws CakeNullException
     * @throws InsertIntoException
     * @throws UserMustBeforeLoginException
     */
    Integer addMyCart(Integer acid, Integer cuid)
            throws CakeAmountZeroException, CakeNullException, InsertIntoException, UserMustBeforeLoginException;

    /**
     * @return
     */
    List<CakeCart> showAllCakeCart();

    /**
     * @param ccid
     * @return
     */
    Integer deleteByCcid(Integer id, Integer ccid) throws JurisdictionException;

    /**
     * @param cuid
     * @return
     */
    List<CakeCart> foundByCuid(Integer cuid);

    /**
     * @param ccid
     * @return
     */
    CakeCart foundByCcid(Integer ccid);

    /**
     * 多糕入车
     * 必选一件:在前端页面示意
     *
     * @param acids
     * @param cuid
     * @return
     * @throws UserMustBeforeLoginException
     * @throws CakeAmountZeroException
     */
    Integer addMultiCake(Integer[] acids, Integer cuid)
            throws UserMustBeforeLoginException, CakeAmountZeroException;

    /**
     * 一名会员多糕删除
     *
     * @param ccids
     * @param cuid
     * @return
     * @throws LoginStatusFailureException
     */
    Integer delMultiCake(Integer[] ccids, Integer cuid) throws LoginStatusFailureException;
}
