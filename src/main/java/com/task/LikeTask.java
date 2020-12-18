package com.task;
import com.KeyEnum;
import com.SourceEnum;
import com.XqianIllegalArgumentException;
import com.alibaba.fastjson.JSON;
import com.lz.TestService;
import com.lz.TestServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 点赞的定时任务
 */
@Slf4j
public class LikeTask extends QuartzJobBean {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");

    @Autowired
    private TestServiceImpl testService;


    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException{

        log.info("LikeTask-------- {}", sdf.format(new Date()));


        List<String> list = new ArrayList<>();
        for (int i = 10; i > 0; i--) {
            list.add("test"+i);
        }

        Map<KeyEnum,Object> map = new HashMap<>(16);
        map.put(KeyEnum.EMP_NO,"2");
        map.put(KeyEnum.JSON_BODY, JSON.toJSONString(list));
        map.put(KeyEnum.X_QIAN_SOURCE, SourceEnum.CUSTOMER_DUE_TO_DETAILS);
        map.put(KeyEnum.PAGE_NO,simpleDateFormat.format(new Date()));
        try {
            testService.TestKey(map);
        }catch (XqianIllegalArgumentException e){
            log.info("程序执行出现异常 异常的具体信息为{}",e);
            //throw e;
        }
      //  System.out.println("定时");
        //将 Redis 里的点赞信息同步到数据库里
        //    likedService.transLikedFromRedis2DB();
        // likedService.transLikedCountFromRedis2DB();
    }

}
