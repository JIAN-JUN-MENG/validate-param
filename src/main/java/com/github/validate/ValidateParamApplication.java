package com.github.validate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



//@ComponentScan({"com.github.**"})
@SpringBootApplication
public class ValidateParamApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(ValidateParamApplication.class, args);
	}
}
