package com.cgi.userservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    Logger mylog = LoggerFactory.getLogger(LoggerAspect.class);

    @Before("registerUser()")
    public void beforeSaveregisterUser(JoinPoint jp)
    {
        mylog.info("registerUser is called.");
    }

    @After("registerUser()")
    public void afterSaveregisterUser(JoinPoint jp)
    {
        mylog.info("Executed after registerUser is called.");
    }

    @AfterThrowing(pointcut="registerUser()",throwing = "excepobj")
    public void handleexcSaveregisterUser(Exception excepobj)
    {
        mylog.warn("Exception is raised while registerUser is called.");
    }

    @AfterReturning("registerUser()")
    public void handleReturnregisterUser(JoinPoint jp){
        mylog.warn("registerUser returned a response");
    }

    @Pointcut("execution (* com.cgi.userservice.controller.UserController.registerUser(..))")
    public void registerUser(){

    }

    @Before("loginUser()")
    public void beforeSaveloginUser(JoinPoint jp)
    {
        mylog.info("loginUser is called.");
    }

    @After("loginUser()")
    public void afterSaveloginUser(JoinPoint jp)
    {
        mylog.info("Executed after loginUser is called.");
    }

    @AfterThrowing(pointcut="loginUser()",throwing = "excepobj")
    public void handleexcSaveloginUser(Exception excepobj)
    {
        mylog.warn("Exception is raised while loginUser is called.");
    }

    @AfterReturning("loginUser()")
    public void handleReturnloginUser(JoinPoint jp){
        mylog.warn("loginUser returned a response");
    }

    @Pointcut("execution (* com.cgi.userservice.controller.UserController.loginUser(..))")
    public void loginUser(){

    }

    @Before("updateUser()")
    public void beforeSaveupdateUser(JoinPoint jp)
    {
        mylog.info("updateUser is called.");
    }

    @After("updateUser()")
    public void afterSaveupdateUser(JoinPoint jp)
    {
        mylog.info("Executed after updateUser is called.");
    }

    @AfterThrowing(pointcut="updateUser()",throwing = "excepobj")
    public void handleexcSaveupdateUser(Exception excepobj)
    {
        mylog.warn("Exception is raised while updateUser is called.");
    }

    @AfterReturning("updateUser()")
    public void handleReturnupdateUser(JoinPoint jp){
        mylog.warn("updateUser returned a response");
    }

    @Pointcut("execution (* com.cgi.userservice.controller.UserController.updateUser(..))")
    public void updateUser(){

    }

    @Before("deleteUser()")
    public void beforeSavedeleteUser(JoinPoint jp)
    {
        mylog.info("deleteUser is called.");
    }

    @After("deleteUser()")
    public void afterSavedeleteUser(JoinPoint jp)
    {
        mylog.info("Executed after deleteUser is called.");
    }

    @AfterThrowing(pointcut="deleteUser()",throwing = "excepobj")
    public void handleexcSavedeleteUser(Exception excepobj)
    {
        mylog.warn("Exception is raised while deleteUser is called.");
    }

    @AfterReturning("deleteUser()")
    public void handleReturndeleteUser(JoinPoint jp){
        mylog.warn("deleteUser returned a response");
    }

    @Pointcut("execution (* com.cgi.userservice.controller.UserController.deleteUser(..))")
    public void deleteUser(){

    }

    @Before("getUserType()")
    public void beforeSavegetUserType(JoinPoint jp)
    {
        mylog.info("getUserType is called.");
    }

    @After("getUserType()")
    public void afterSavegetUserType(JoinPoint jp)
    {
        mylog.info("Executed after getUserType is called.");
    }

    @AfterThrowing(pointcut="getUserType()",throwing = "excepobj")
    public void handleexcSavegetUserType(Exception excepobj)
    {
        mylog.warn("Exception is raised while getUserType is called.");
    }

    @AfterReturning("getUserType()")
    public void handleReturngetUserType(JoinPoint jp){
        mylog.warn("getUserType returned a response");
    }

    @Pointcut("execution (* com.cgi.userservice.controller.UserController.getUserType(..))")
    public void getUserType(){

    }


    @Before("getAllUser()")
    public void beforeSavegetAllUser(JoinPoint jp)
    {
        mylog.info("getAllUser is called.");
    }

    @After("getAllUser()")
    public void afterSavegetAllUser(JoinPoint jp)
    {
        mylog.info("Executed after getAllUser is called.");
    }

    @AfterThrowing(pointcut="getAllUser()",throwing = "excepobj")
    public void handleexcSavegetAllUser(Exception excepobj)
    {
        mylog.warn("Exception is raised while getAllUser is called.");
    }

    @AfterReturning("getAllUser()")
    public void handleReturngetAllUser(JoinPoint jp){
        mylog.warn("getAllUser returned a response");
    }

    @Pointcut("execution (* com.cgi.userservice.controller.UserController.getAllUser(..))")
    public void getAllUser(){

    }

    

}
