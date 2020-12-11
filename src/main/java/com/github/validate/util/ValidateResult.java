package com.github.validate.util;

import java.io.Serializable;


/**
 * 验证返回model
 *
 * @author MENG
 * @version 2019/3/27
 */
public class ValidateResult<T> implements Serializable
{
    private static final long serialVersionUID = -6139677251863746424L;

    /**
     * 返回代码 00000 为OK  其他 error
     */
    private String code = ValidateConstants.RESULT_SUCCESS_CODE;

    /**
     * 返回信息 SUCCESS
     */
    private String message = ValidateConstants.RESULT_SUCCESS_MESSAGE;

    /**
     * 返回数据
     */
    private T data;

    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return this.message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public T getData()
    {
        return this.data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public boolean isSuccess()
    {
        if(this.getCode().equals(ValidateConstants.RESULT_SUCCESS_CODE))
        {
            return true;
        }

        return false;
    }

    @Override
    public String toString()
    {
        return "ValidateResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

