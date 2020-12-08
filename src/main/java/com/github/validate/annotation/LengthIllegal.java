package com.github.validate.annotation;

import java.lang.annotation.*;


/**
 * 字段长度验证
 *
 * @author MENG
 * @version 2019/1/21
 * @see
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LengthIllegal
{
    /**
     * 长度开始范围
     *
     * @return
     */
    int start();

    /**
     * 长度结束范围
     *
     * @return
     */
    int end();

    /**
     * 用于 描述字段的名称 也可用于提示用户
     *
     * @return
     */
    String description() default "";

}
