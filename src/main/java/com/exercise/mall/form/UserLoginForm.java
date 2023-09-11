package com.exercise.mall.form;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserLoginForm {

    //@NotBlank 用于String 判断空格
    //@NotNull 判断是否是NULL
    //@NotEmpty 判断集合是否为空

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
