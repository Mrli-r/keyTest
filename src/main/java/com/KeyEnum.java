package com;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * key声明枚举
 *
 * attention!!! 需要保证该枚举中举key在枚举中唯一
 *
 * 仅适用于续签服务内部
 *
 * @author fanyuexing
 * @date 2020/11/12 19:13
 */
@Getter
@AllArgsConstructor
public enum KeyEnum {


    /**
     * 单页条数
     */
    PAGE_SIZE("pageSize","单页条数"),

    /**
     * 指定页数
     */
    PAGE_NO("pageNo","指定页数"),

    /**
     * 理顾编号
     */
    EMP_NO("empNo","理顾编号"),

    /**
     * json体
     */
    JSON_BODY("jsonBody","json体"),

    /**
     * 营业部
     */
    TRADE_DEPARTMENT("tradeDepartment","营业部"),

    /**
     * 团队编号
     */
    TEAM_NO("teamNo","团队编号"),

    /**
     * 事业部编号
     */
    BUSINESS_DEPARTMENT("businessDepartment","事业部编号"),

    /**
     * 事业群编号
     */
    BUSINESS_GROUP("businessGroup","事业群编号"),

    /**
     * 查询客户类型
     * 私享客户  财富客户
     */
    CUSTOMER_TYPE("customerType","查询客户类型"),

    /**
     * 月份
     */
    MONTH("month","月份"),

    /**
     * 客户等级
     */
    CUSTOMER_TIER("customerTier","客户等级"),

    /**
     * 客户等级名称
     */
    TIRE_NAME("tireName","客户等级名称"),

    /**
     * 筛选时间
     */
    SCREEN_TIME("screenTime","筛选时间"),

    /**
     * 组织架构名称
     */
    ORG_NAME("orgName","组织架构名称"),

    /**
     * 组织架构编号
     */
    ORGANIZATION("organization","组织架构编号"),

    /**
     * 部门层级
     */
    DEPARTMENT_TIRE("departmentTire","部门层级"),

    /**
     * 来源 调用接口的身份声明
     */
    X_QIAN_SOURCE("source","来源 调用接口的身份声明"),

    /**
     * 业务编号
     *  预留项  调用方自行传入 保证保存和读取时传入的值相同即可
     */
    BUSINESS_NO("businessNO","业务编号"),

    /**
     * 客户编号
     */
    CUSTOMER_NO("customerNo","客户编号"),

    ;

    /**
     * key声明
     */
    private final String key;

    /**
     * key 中文释义
     */
    private final String message;


    /**
     * 按照指定规则排序后的枚举类
     */
    private static final List<String> IMMUTABLE_LIST;

    /**
     * id enum map
     */
    private static final ImmutableMap<String, KeyEnum> INT_ENUM_MAP;

    static {
        //ImmutableList 初始化集合
        final List<String> list = new ArrayList<>();
        //初始化集合
        final ImmutableMap.Builder<String, KeyEnum> builder = new ImmutableMap.Builder<>();

        for (final KeyEnum value : KeyEnum.values()) {
            list.add(value.getKey());
            builder.put(value.getKey(), value);
        }
        //将list进行排序
        Collections.sort(list);
        IMMUTABLE_LIST = list;

        //初始化map
        INT_ENUM_MAP = builder.build();
    }

    /**
     * 获取初始化后  排序号的list
     * @return
     */
    public static List<String> getSortList(){
        return IMMUTABLE_LIST;
    }

    /**
     * 获取指定编号的枚举
     * @param key 编号
     * @return 未查询到匹配key编号情况返回null
     */
    public static KeyEnum of(String key){
        if (!INT_ENUM_MAP.containsKey(key)) {
            return null;
        }
        return INT_ENUM_MAP.get(key);
    }

    /**
     * 获取指定编码对应的message
     * @param key key
     * @return 返回指定key编号对应的枚举中文释义  未匹配情况返回null
     */
    public static String getMessage(String key){
        KeyEnum keyEnum = of(key);
        if (Objects.isNull(keyEnum)){
            return null;
        }
        return keyEnum.getMessage();
    }

}
