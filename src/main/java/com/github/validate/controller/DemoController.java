package com.github.validate.controller;

import com.github.validate.annotation.PrintParameter;
import com.github.validate.annotation.ValidateParameter;
import com.github.validate.dto.ParamTestDto;
import com.github.validate.util.ValidateResult;
import com.github.validate.util.ValidateUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * ${DESCRIPTION}
 *
 * @author MENG
 * @version 2020/3/28
 * @see
 */
@RestController
@RequestMapping("demo")
public class DemoController
{
    /**
     * 测试
     *
     * @return
     */
    @PostMapping(value = "/paramTest")
    @ValidateParameter
    @PrintParameter(description = "参数测试")
    public ValidateResult paramTest(HttpServletRequest request, String a)
    {
        //TODO SOMETHING

        return ValidateUtil.returnSuccess();
    }
}
