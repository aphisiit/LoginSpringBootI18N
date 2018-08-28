package com.guy.login.constant;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConstantVariable {
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
    public static String APPLICATION_CURRENT_VERSION = "v.0.99." + simpleDateFormat.format(new Date());
}
