package com.lz;

import com.KeyEnum;
import com.XqianIllegalArgumentException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TestServiceImpl implements TestService{


    @Override
    public String TestKey(Map<KeyEnum,Object> map) throws XqianIllegalArgumentException {


        String key = KeyUtils.getKeyString(map,true);

        System.out.println(key);
        return key;
    }
}
