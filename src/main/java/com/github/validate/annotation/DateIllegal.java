package com.github.validate.annotation;

import java.lang.annotation.*;


/**
 * 邮箱验证
 *
 * @author MENG
 * @version 2019/1/21
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DateIllegal
{
    /**
     * 用于 描述字段的名称 也可用于提示用户
     *
     * @return 自定义描述
     */
    String description() default "";

    /**
     * dateformat 格式
     * @return 日期格式
     */
    String format() default "yyyy-MM-dd";

}
