package org.example.springtest.aspect;

import org.example.springtest.service.LoggerServiceImpl;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface LoggerMarker {
}
