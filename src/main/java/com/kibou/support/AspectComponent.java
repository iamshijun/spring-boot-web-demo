package com.kibou.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Aspect
@Component
public @interface AspectComponent {
	
//	@AliasFor(annotation = Aspect.class,attribute = "value")
	String value() default "";
}
