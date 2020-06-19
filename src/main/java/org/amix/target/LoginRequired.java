package org.amix.target;

import java.lang.annotation.*;

@Target({ElementType.PACKAGE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {
	
}