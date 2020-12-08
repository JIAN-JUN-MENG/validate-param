package com.github.validate.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;


/**
 * 注解RemoveEmptyOrNullParamter aop
 *
 * @author MENG
 * @version 2017/7/13
 * @see
 */
@Aspect
@Component("removeEmptyOrNullParameterAspectThird")
public class RemoveEmptyOrNullParameterAspect
{
    /**
     * 切入点
     */
    @Pointcut("@annotation(com.github.validate.annotation.PrintParameter)")
    public void pointCutMethod()
    {
    }

    /**
     * 方法执行之前
     *
     * @param joinPoint 参数
     */
    @Before("pointCutMethod()")
    public void before(JoinPoint joinPoint)
    {
        Object[] objects = joinPoint.getArgs();

        for (Object obj : objects)
        {
            if (obj != null)
            {
                if (obj instanceof Map)
                {
                    @SuppressWarnings("unchecked")
                    Map map = (Map<String, Object>)obj;

                    @SuppressWarnings("unckecked")
                    Iterator iterator = map.keySet().iterator();

                    while (iterator.hasNext())
                    {
                        Object key = iterator.next();

                        if (map.get(key) == null || ("").equals(map.get(key).toString().trim()))
                        {
                            iterator.remove();
                        }
                    }
                }

            }
        }
    }

}
