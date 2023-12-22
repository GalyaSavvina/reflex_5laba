package org.example;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
    public @interface AutoInjectable {
        boolean value() default true;
    }
