package main.java.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Aspect
public class MethodLogger {


  @Around("execution(* *(..)) && @annotation(LogTime)")
  public Object around(ProceedingJoinPoint point) throws Throwable {
    long start = System.currentTimeMillis();
    Object result = point.proceed();

    String sisend = Arrays.toString(point.getArgs());
    String v2ljund = result.toString();

    String aeg = Long.toString(System.currentTimeMillis() - start);

    System.out.println("INFO[]  Sisend:"+sisend+" V2ljund: "+v2ljund+" Aeg: "+aeg);

    return result;
  }
}