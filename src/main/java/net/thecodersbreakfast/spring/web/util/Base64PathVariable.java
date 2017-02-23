package net.thecodersbreakfast.spring.web.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface Base64PathVariable {
    String value();
    String charset() default "UTF-8";
    Decoder decoder() default Decoder.BASIC;
    boolean required() default false;

    enum Decoder {
        BASIC,
        URL
    }
}
