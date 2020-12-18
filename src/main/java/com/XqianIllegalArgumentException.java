package com;

import lombok.NoArgsConstructor;

/**
 * Date: 2020/4/2
 * Time: 9:36
 * @author: yangkai
 * EMail: yangkai01@chtwm.com
 * @author Kayle
 */
@NoArgsConstructor
public class XqianIllegalArgumentException extends Exception {

    public XqianIllegalArgumentException(String exception) {
        super(exception);
    }

}

