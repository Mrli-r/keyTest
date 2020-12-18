package com.lz;

import com.KeyEnum;
import com.XqianIllegalArgumentException;

import java.util.Map;

public interface TestService {

    String TestKey (Map<KeyEnum,Object> map)throws XqianIllegalArgumentException;

}
