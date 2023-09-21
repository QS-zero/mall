package com.exercise.mall.form;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import java.security.PrivateKey;

@Data
public class OrderCreateForm {

    @NotNull
    private Integer shippingId;
}

