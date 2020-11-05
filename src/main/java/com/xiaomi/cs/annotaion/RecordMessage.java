package com.xiaomi.cs.annotaion;

import java.lang.annotation.*;

/**
 * @author l
 * @create 2020-10-29-11:34
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface RecordMessage {
}
