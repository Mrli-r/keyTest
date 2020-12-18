package com.lz;

import com.KeyEnum;
import com.SourceEnum;
import com.XqianIllegalArgumentException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class KeyUtils {


    private static final String STITCHING_RULES = "_";

    /**
     * key 限制的最大长度
     */
    private static final int MAX_LENGTH = 200;



    public static void verifyJson(String json)throws XqianIllegalArgumentException{

        if (StringUtils.isEmpty(json)){
            log.info("待校验是否为json的数据为空");
            throw new XqianIllegalArgumentException("待添加的数据主体(jsonBody)不能转换为json格式 请确认参数（传入的json为空）");
        }

        try{
            try{
                //校验是否可以转换为json
                JSON.parseObject(json);
            }catch (Exception e){
                //校验是否可以转换为jsonArray
                JSONArray.parseArray(json);
            }
        }catch (Exception e){
            log.info("待添加的数据主体(jsonBody)不能转换为json格式 校验的json体为{}",json);
            throw new XqianIllegalArgumentException("待添加的数据主体(jsonBody)不能转换为json格式 请确认参数");
        }
    }


    public static String getKeyString(Map<KeyEnum, Object> map, boolean isSave) throws XqianIllegalArgumentException {

        //key校验
        verifyParameterForKey(map,isSave);

        //返回一个指定key排序号的list
        List<String> sortList = KeyEnum.getSortList();

        //待拼接集合   返回一个不会触发扩容的ArrayList
        List<Object> valueList = Lists.newArrayListWithExpectedSize(map.size());

        sortList.forEach(str ->{
            //获取指定key所在的枚举
            KeyEnum keyEnum = KeyEnum.of(str);
            if (Objects.isNull(keyEnum) || keyEnum.equals(KeyEnum.JSON_BODY)){
                //跳出本次循环 执行下次循环  不会拼接jsonBody作为key返回
                return;

            }

            //将符合条件的数据加入到待拼接的集合中   map中的value要求不能为空
            if (map.containsKey(keyEnum) && Objects.nonNull(map.get(keyEnum))){
                valueList.add(map.get(keyEnum));
            }
        });

        if (CollectionUtils.isEmpty(valueList)){
            log.warn("所有待添加的value 值均为空");
            throw new  XqianIllegalArgumentException("所有待添加的value 值均为空 请确认入参参数");
        }

        //生成后的key
        String key = StringUtils.join(valueList,STITCHING_RULES);
        if (key.length() >= MAX_LENGTH){
            log.warn("转换后的key 超过指定长度 指定的长度为{}",MAX_LENGTH);
            throw new  XqianIllegalArgumentException("转换后的key 超过指定长度 限制的长度应不大于 "+MAX_LENGTH);
        }

        return key;
    }


    public static void verifyParameterForKey(Map<KeyEnum,Object> map,boolean isSearch) throws XqianIllegalArgumentException{
        //通用校验方法
        verifyCommonData(map);

        //入库获取key  校验json_body是否存在
        if (isSearch){
            //是否包含数据主体校验
            if (!map.containsKey(KeyEnum.JSON_BODY)){
                log.info("待添加的数据主体(jsonBody)为空");
                throw new XqianIllegalArgumentException("待添加的数据主体(jsonBody)为空 请确认参数");
            }
            String jsonBody = map.get(KeyEnum.JSON_BODY).toString();
            //校验jsonBody是否为json
            verifyJson(jsonBody);
        }

    }



    private static void verifyCommonData(Map<KeyEnum,Object> map) throws XqianIllegalArgumentException{
        //实体是否为空的判断
        if (CollectionUtils.isEmpty(map)){
            log.info("待校验的主体（map）为空");
            throw new XqianIllegalArgumentException("待校验的主体（map）为空 请确认入参参数");
        }

        //调用方来源验证
        if (!map.containsKey(KeyEnum.X_QIAN_SOURCE)){
            log.info("来源不能为空(source)为空");
            throw new XqianIllegalArgumentException("来源不能为空(source)为空 请确认参数");
        }
        try{
            SourceEnum sourceEnum = (SourceEnum)map.get(KeyEnum.X_QIAN_SOURCE);
            if (Objects.isNull(sourceEnum)){
                throw new Exception();
            }
        }catch (Exception e){
            log.info("来源不能为空(source)不符合指定要求");
            throw new XqianIllegalArgumentException("来源不能为空(source)不符合指定要求 请确认参数");
        }
    }

}
