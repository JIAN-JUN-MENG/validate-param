package com.github.validate.annotation;

import java.lang.annotation.*;


/**
 * 必填字段
 *
 * @author MENG
 * @version 2019/1/21
 * @see
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LongIllegal
{
    /**
     * 用于 描述字段的名称 也可用于提示用户
     *
     * @return
     */
    String description() default "";

    /**
     * 开始范围
     * @return
     */
    long start() default Long.MIN_VALUE;

    /**
     * 结束返回
     * @return
     */
    long end() default Long.MAX_VALUE;
}
