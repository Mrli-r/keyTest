import com.KeyEnum;
import com.SourceEnum;
import com.XqianIllegalArgumentException;
import com.alibaba.fastjson.JSON;
import com.lz.TestServiceImpl;
import javafx.application.Application;
import jdk.nashorn.internal.ir.annotations.Reference;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class TestController {

    TestServiceImpl testService =  new TestServiceImpl();

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");

    @Test
    public void testKey() throws XqianIllegalArgumentException{

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
            throw e;
        }

    }


}
