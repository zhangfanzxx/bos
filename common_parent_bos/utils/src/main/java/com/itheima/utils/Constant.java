package com.itheima.utils;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/9 12:33 <br/>
 * Author zzff
 */
public interface Constant {
    /****************** Order 常量  *********************/
    // 分单类型 1 自动分单 2 人工分单
    String AUTO_DISTRIBUTION="1";
    String MANUAL_SORTING="2";
    // 订单状态 1 待取件 2 运输中 3 已签收 4 异常
    String ORDER_STATUS_STANDBY="1";
    String ORDER_STATUS_TRANSPORTATION="2";
    String ORDER_STATUS_ALREADY_SIGNED="3";
    String ORDER_STATUS_EXCEPTION="4";
    // 支付类型编号：1寄付日结、2寄付月结、3到付
    String ORDER_PAY_TODAY="1";
    String ORDER_PAY_MONTHLY="2";
    String ORDER_TO_PAY="3";
    // 快递产品类型编号：1速运当日、2速运次日、3速运隔日
    String ORDER_EXPRESS_DAY="1";
    String ORDER_EXPRESS_NEXT_DAY="2";
    String ORDER_EXPRESS_SECOND_DAY="3";
}
