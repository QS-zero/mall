package com.exercise.mall.enums;

import lombok.Getter;
import org.omg.PortableInterceptor.INACTIVE;

@Getter
public enum ProductStatusEnum {
    ON_SALE(1),

    OFF_SALE(2),

    DELETE(3),

    ;

    Integer code;

    ProductStatusEnum (Integer code){
        this.code = code;
    }
}
