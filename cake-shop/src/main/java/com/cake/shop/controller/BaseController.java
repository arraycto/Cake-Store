package com.cake.shop.controller;

import com.cake.shop.coolutil.ResponseResult;
import com.cake.shop.service.except.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseController {
    /**
     * 处理请求正確碼
     */
    public static final int SUCCESS = 200;

    /**
     * 从Session中获取当前登录的用户的id
     *
     * @param session
     * @return 当前登录的用户的id
     */
    protected Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("id").toString());
    }

    /**
     * 将object转换为Integer
     *
     * @param obj
     * @return
     */
    protected Integer objCastToInteger(Object obj) {
        Integer integer = null;
        if (obj != null) {
            if (obj instanceof Integer) {
                integer = (Integer) obj;
            }
        }
        return integer;
    }

    /**
     * 将字符串转化为年月日期类型格式
     *
     * @param str
     * @return
     */
    public Date strCastToDate(String str) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            date = sdf.parse(str);
            System.err.println("str date: " + date);
        } catch (Exception e) {
            try {
                date = sdf.parse("1500-01-01");
                System.err.println("default date: " + date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return date;
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseResult<Void> handleException(Exception e) {
        // 声明返回对象
        ResponseResult<Void> rr = new ResponseResult<Void>();
        // 在返回对象封装错误提示的文字
        rr.setMessage(e.getMessage());

        // 根据异常的不同，返回的错误代码也不同
        if (e instanceof UsernameNotExistException) {
            rr.setState(400);
        } else if (e instanceof CakeAmountZeroException) {
            rr.setState(401);
        } else if (e instanceof PasswordNotMatchException) {
            // 402-验证用户密码失败的异常
            rr.setState(402);
        } else if (e instanceof CakeNullException) {
            rr.setState(403);
        } else if (e instanceof InsertIntoException) {
            rr.setState(406);
        } else if (e instanceof UserMustBeforeLoginException) {
            rr.setState(407);
        } else if (e instanceof UsernameConflictException) {
            rr.setState(408);
        } else if (e instanceof JurisdictionException) {
            rr.setState(409);
        } else if (e instanceof KeyWordNullException) {
            rr.setState(410);
        } else if (e instanceof PropertyNotNullException) {
            rr.setState(411);
        } else if (e instanceof FormatMistakeException) {
            rr.setState(412);
        } else if (e instanceof LoginStatusFailureException) {
            rr.setState(414);
        } else if (e instanceof AccountHasBeenCancelException) {
            rr.setState(415);
        } else if (e instanceof PhoneBindOverrunException) {
            rr.setState(416);
        }else if (e instanceof RepetitiveOperationException){
            rr.setState(417);
        }

        // 返回
        return rr;
    }
}
