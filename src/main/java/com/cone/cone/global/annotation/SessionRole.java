package com.cone.cone.global.annotation;

import com.cone.cone.domain.user.entity.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SessionRole {
    Role[] roles();
}
