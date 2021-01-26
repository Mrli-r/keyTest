import com.KeyEnum;
import com.SourceEnum;
import com.XqianIllegalArgumentException;
import com.alibaba.fastjson.JSON;
import com.lz.TestServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import com.alibaba.fastjson.JSONObject;
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





    @Test
    public  void  TestDATA(){
       /* List<Object>  list = new ArrayList<>();
        list.add("wangwu");
        list.add("lisi");
        list.add("zhangsan");
        list.add("sdadsa");

            Object dataDeal = list;

            if(dataDeal !=null){

                System.out.println("list不是null");
            }else{
                System.out.println("list是null");
            }*/

        long milliSecond = 1610085491122L;
        Date date = new Date();
        date.setTime(milliSecond);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date));


        long milliSecond1 = 1610085497681L;
        Date date1 = new Date();
        date1.setTime(milliSecond1);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date1));

    }





    @Test
    public  void  TestJson(){
        System.out.println(12345);
        String  data =  "大啊啊";
        JSONObject a  = new JSONObject();


    }



}
