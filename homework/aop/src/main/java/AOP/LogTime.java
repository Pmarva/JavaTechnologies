package main.java.AOP;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by marva on 20.05.16.
 */

@Target(ElementType.METHOD)
public @interface LogTime {
}
