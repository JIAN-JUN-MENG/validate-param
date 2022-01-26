package com.github.validate.controller;

import com.github.validate.annotation.PrintParameter;
import com.github.validate.annotation.RequireField;
import com.github.validate.annotation.ValidateParameter;
import com.github.validate.dto.ParamTestDto;
import com.github.validate.util.ValidateResult;
import com.github.validate.util.ValidateUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 测试CONTROLLER
 *
 * @author MENG
 * @version 2020/3/28
 */
@RestController
@RequestMapping("demo")
public class DemoController
{
    /**
     * 参数测试
     *
     * @param request HttpServletRequest
     * @param paramTestDto 测试参数
     * @return ValidateResult
     */
    @PostMapping(value = "/paramTest")
    @ValidateParameter
    @PrintParameter(description = "参数测试")
    public ValidateResult paramTest(HttpServletRequest request, @RequestBody ParamTestDto paramTestDto)
    {
        //TODO SOMETHING
        System.out.println("FIELD ="+paramTestDto.getRequireField());
        System.out.println("FIELD ="+paramTestDto.getRemoveSpace());
        return ValidateUtil.returnSuccess();
    }
}
