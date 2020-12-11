package com.github.validate.annotation;

import java.lang.annotation.*;


/**
 * 必填字段
 *
 * @author MENG
 * @version 2019/1/21
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IntegerIllegal
{
    /**
     * 用于 描述字段的名称 也可用于提示用户
     *
     * @return 自定义描述
     */
    String description() default "";

    /**
     * 开始范围
     *
     * @return 最小值
     */
    int start() default Integer.MIN_VALUE;

    /**
     * 结束返回
     *
     * @return 最大值
     */
    int end() default Integer.MAX_VALUE;
}
