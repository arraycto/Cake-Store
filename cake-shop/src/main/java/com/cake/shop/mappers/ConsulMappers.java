package com.cake.shop.mappers;

import com.cake.shop.entity.AngelCake;
import com.cake.shop.entity.Consul;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsulMappers {
    /**
     * @param consul
     * @return
     */
    Integer insertAnAdmin(Consul consul);

    /**
     * @return
     */
    List<Consul> foundAllAdmin();

    /**
     * @param account
     * @return
     */
    Consul selectAdminByAccount(@Param("account") String account);

    /**
     * @param angelCake
     * @return
     */
    Integer adminInsertOneCake(AngelCake angelCake);

    /**
     * @param angelCake
     * @return
     */
    Integer updateAngelCakeByAcid(AngelCake angelCake);

    /**
     * @param acid
     * @return
     */
    Integer deleByAcid(@Param("acid") Integer acid);

    /**
     * @param angelCake
     * @return
     */
    Integer updateAngelaCakeByAcid(AngelCake angelCake);

    /**
     * @param amount
     * @param acid
     * @return
     */
    Integer updateAmountByAcid(@Param("amount") Integer amount,
                               @Param("acid") Integer acid);

    /**
     * @param cancelStatus
     * @param id
     * @return
     */
    Integer setCancelStatusById(@Param("cancelStatus") Integer cancelStatus,
                                @Param("id") Integer id);

    /**
     * 根据多个id将用户表中多行`cancel_status`设为0或1
     *
     * @param cancelStatus
     * @param ids
     * @return
     */
    Integer setCancelByVolumeId(@Param("cancelStatus") Integer cancelStatus,
                                @Param("array") Integer[] ids);

    /**
     * 根据多个id删除多个用户
     *
     * @param ids
     * @return
     */
    Integer volumesDeleteByIds(@Param("array") Integer[] ids);

    /**
     * 管员据acid数组删除多款蛋糕
     *
     * @param acids
     * @return
     */
    Integer multipleDeleteCakeByAcids(@Param("array") Integer[] acids);

    /**
     *
     * @param ccnames
     * @return
     */
    Integer multipleDeleteCakeByCcnames(@Param("list") List<String> ccnames);
}
