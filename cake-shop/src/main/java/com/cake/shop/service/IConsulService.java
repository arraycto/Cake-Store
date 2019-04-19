package com.cake.shop.service;

import com.cake.shop.entity.AngelCake;
import com.cake.shop.entity.Consul;
import com.cake.shop.entity.Customer;
import com.cake.shop.service.except.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 管理员业务接口
 * <p>
 * 注册:关于管理员注册,其不能在页面上直接注册,只能在后台测试方法上注册
 * <p>
 * 每当管理删除一款蛋糕时,其他会员篮子中相应蛋糕亦应当被删除,
 * 然后以某种形式的消息告知顾客.
 */
public interface IConsulService {
    /**
     * @param account
     * @param password
     * @return
     * @throws UsernameNotExistException
     * @throws PasswordNotMatchException
     */
    Consul login(String account, String password) throws UsernameNotExistException,
            PasswordNotMatchException;

    /**
     * 管理员新增一款蛋糕
     *
     * @param angelCake
     * @param csid
     * @return
     * @throws PropertyNotNullException
     * @throws FormatMistakeException
     * @throws CakeNameDuplicateException
     * @throws UserMustBeforeLoginException
     */
    Integer addNewOneCake(AngelCake angelCake, Integer csid) throws PropertyNotNullException,
            FormatMistakeException, CakeNameDuplicateException, UserMustBeforeLoginException;

    /**
     * @param acid
     * @param csid
     * @return
     * @throws UserMustBeforeLoginException
     */
    Integer deleteOneCake(Integer acid, Integer csid) throws UserMustBeforeLoginException;

    /**
     * 转发(transmit)旧数据(obsolete)给新编辑页面
     *
     * @param acid
     * @param csid
     * @return
     * @throws UserMustBeforeLoginException
     */
    AngelCake transmitsObsolete(Integer acid, Integer csid) throws UserMustBeforeLoginException;

    /**
     * 领事修改蛋糕数据
     *
     * @param csid
     * @param angelCake
     * @return
     * @throws CakeNullException            蛋糕不出在
     * @throws CakeNameDuplicateException   蛋糕名已重复
     * @throws UserMustBeforeLoginException 必须先登录
     * @throws PropertyNotNullException     任一项属性不准为空
     */
    Integer editAngelaCake(AngelCake angelCake, Integer csid) throws CakeNullException, CakeNameDuplicateException,
            UserMustBeforeLoginException, PropertyNotNullException;

    /**
     * @param csid
     * @param amount
     * @param acid
     * @return
     * @throws UserMustBeforeLoginException
     * @throws CakeAmountZeroException
     */
    Integer changeCakeAmount(Integer csid, Integer amount, Integer acid)
            throws UserMustBeforeLoginException, CakeAmountZeroException;

    /**
     * 检视所有会员
     *
     * @param csid
     * @return
     * @throws UserMustBeforeLoginException
     */
    List<Customer> InspectionEachAccount(Integer csid) throws UserMustBeforeLoginException;

    /**
     * @param id
     * @param csid
     * @return
     * @throws UserMustBeforeLoginException
     * @throws RepetitiveOperationException
     */
    Integer letItCancel(Integer id, Integer csid) throws UserMustBeforeLoginException,
            RepetitiveOperationException;

    /**
     * 激活用户账号
     *
     * @param id
     * @param csid
     * @return
     * @throws UserMustBeforeLoginException
     * @throws RepetitiveOperationException
     */
    Integer doActive(Integer id, Integer csid) throws UserMustBeforeLoginException,
            RepetitiveOperationException;

    /**
     * 删除一名会员账户
     *
     * @param id
     * @param csid
     * @return
     * @throws UserMustBeforeLoginException
     */
    Integer doDeleteSingleCustomer(Integer id, Integer csid) throws UserMustBeforeLoginException;

    /**
     * @param ids    用户们
     * @param csid   领事
     * @param status 状态
     * @return
     * @throws UserMustBeforeLoginException
     * @throws RepetitiveOperationException
     */
    Integer batchSetStatus(Integer[] ids, Integer csid, Integer status) throws UserMustBeforeLoginException,
            RepetitiveOperationException;

    /**
     * 管理者删除多名用户
     *
     * @param ids
     * @param csid
     * @return
     * @throws UserMustBeforeLoginException
     */
    Integer deleteByVolumeIds(Integer[] ids, Integer csid)
            throws UserMustBeforeLoginException;

    /**
     * 管员据acids删除多款蛋糕
     *
     * @param csid
     * @param acids
     * @return
     * @throws UserMustBeforeLoginException
     */
    Integer banMultipleAngelCake(Integer csid, Integer[] acids)
            throws UserMustBeforeLoginException;

    /**
     * 为管员展示蛋糕列表
     *
     * @param csid
     * @return
     * @throws UserMustBeforeLoginException
     */
    List<AngelCake> showCakeListForArchon(Integer csid) throws UserMustBeforeLoginException;
}
