package com.dteam.momentree.application.config.auth;

import io.swagger.v3.oas.annotations.Parameter;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(PARAMETER)
@Retention(RUNTIME)
@Parameter(hidden = true)
public @interface Auth {
}
