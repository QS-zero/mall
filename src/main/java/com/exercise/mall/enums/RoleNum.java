package com.exercise.mall.enums;

import lombok.Getter;

/**
 * 角色0管理员，1普通用户
 */

@Getter
public enum RoleNum {
    ADMIN(0),

    CUSTOMER(1),
    ;

    Integer code;

    RoleNum(Integer code){
        this.code = code;
    }
}
