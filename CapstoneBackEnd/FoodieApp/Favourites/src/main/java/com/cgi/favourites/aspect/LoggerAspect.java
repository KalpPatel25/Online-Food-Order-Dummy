package com.cgi.favourites.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
    
    Logger mylog = LoggerFactory.getLogger(LoggerAspect.class);

    @Before("addRestaurant()")
    public void beforeSaveaddRestaurant(JoinPoint jp)
    {
        mylog.info("addRestaurant is called.");
    }

    @After("addRestaurant()")
    public void afterSaveaddRestaurant(JoinPoint jp)
    {
        mylog.info("Executed after addRestaurant is called.");
    }

    @AfterThrowing(pointcut="addRestaurant()",throwing = "excepobj")
    public void handleexcSaveaddRestaurant(Exception excepobj)
    {
        mylog.warn("Exception is raised while addRestaurant is called.");
    }

    @AfterReturning("addRestaurant()")
    public void handleReturnaddRestaurant(JoinPoint jp){
        mylog.warn("addRestaurant returned a response");
    }

    @Pointcut("execution (* com.cgi.favourites.controller.FavouriteController.addRestaurant(..))")
    public void addRestaurant(){

    }

    @Before("getByUserId()")
    public void beforeSavegetByUserId(JoinPoint jp)
    {
        mylog.info("getByUserId is called.");
    }

    @After("getByUserId()")
    public void afterSavegetByUserId(JoinPoint jp)
    {
        mylog.info("Executed after getByUserId is called.");
    }

    @AfterThrowing(pointcut="getByUserId()",throwing = "excepobj")
    public void handleexcSavegetByUserId(Exception excepobj)
    {
        mylog.warn("Exception is raised while getByUserId is called.");
    }

    @AfterReturning("getByUserId()")
    public void handleReturngetByUserId(JoinPoint jp){
        mylog.warn("getByUserId returned a response");
    }

    @Pointcut("execution (* com.cgi.favourites.controller.FavouriteController.getByUserId(..))")
    public void getByUserId(){

    }

    @Before("deleteRestaurant()")
    public void beforeSavedeleteRestaurant(JoinPoint jp)
    {
        mylog.info("deleteRestaurant is called.");
    }

    @After("deleteRestaurant()")
    public void afterSavedeleteRestaurant(JoinPoint jp)
    {
        mylog.info("Executed after deleteRestaurant is called.");
    }

    @AfterThrowing(pointcut="deleteRestaurant()",throwing = "excepobj")
    public void handleexcSavedeleteRestaurant(Exception excepobj)
    {
        mylog.warn("Exception is raised while deleteRestaurant is called.");
    }

    @AfterReturning("deleteRestaurant()")
    public void handleReturndeleteRestaurant(JoinPoint jp){
        mylog.warn("deleteRestaurant returned a response");
    }

    @Pointcut("execution (* com.cgi.favourites.controller.FavouriteController.deleteRestaurant(..))")
    public void deleteRestaurant(){

    }



}
