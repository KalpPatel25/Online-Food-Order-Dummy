package com.cgi.order.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class LoggerAspect {

    Logger mylog = LoggerFactory.getLogger(LoggerAspect.class);


    @Before("addToCart()")
    public void beforeSaveaddToCart(JoinPoint jp)
    {
        mylog.info("addToCart is called.");
    }

    @After("addToCart()")
    public void afterSaveaddToCart(JoinPoint jp)
    {
        mylog.info("Executed after addToCart is called.");
    }

    @AfterThrowing(pointcut="addToCart()",throwing = "excepobj")
    public void handleexcSaveaddToCart(Exception excepobj)
    {
        mylog.warn("Exception is raised while addToCart is called.");
    }

    @AfterReturning("addToCart()")
    public void handleReturnaddToCart(JoinPoint jp){
        mylog.warn("addToCart returned a response");
    }

    @Pointcut("execution (* com.cgi.order.controller.OrderController.addToCart(..))")
    public void addToCart(){

    }


    @Before("deleteFromCart()")
    public void beforeSavedeleteFromCart(JoinPoint jp)
    {
        mylog.info("deleteFromCart is called.");
    }

    @After("deleteFromCart()")
    public void afterSavedeleteFromCart(JoinPoint jp)
    {
        mylog.info("Executed after deleteFromCart is called.");
    }

    @AfterThrowing(pointcut="deleteFromCart()",throwing = "excepobj")
    public void handleexcSavedeleteFromCart(Exception excepobj)
    {
        mylog.warn("Exception is raised while deleteFromCart is called.");
    }

    @AfterReturning("deleteFromCart()")
    public void handleReturndeleteFromCart(JoinPoint jp){
        mylog.warn("deleteFromCart returned a response");
    }

    @Pointcut("execution (* com.cgi.order.controller.OrderController.deleteFromCart(..))")
    public void deleteFromCart(){

    }

    @Before("showItemsInCart()")
    public void beforeSaveshowItemsInCart(JoinPoint jp)
    {
        mylog.info("showItemsInCart is called.");
    }

    @After("showItemsInCart()")
    public void afterSaveshowItemsInCart(JoinPoint jp)
    {
        mylog.info("Executed after showItemsInCart is called.");
    }

    @AfterThrowing(pointcut="showItemsInCart()",throwing = "excepobj")
    public void handleexcSaveshowItemsInCart(Exception excepobj)
    {
        mylog.warn("Exception is raised while showItemsInCart is called.");
    }

    @AfterReturning("showItemsInCart()")
    public void handleReturnshowItemsInCart(JoinPoint jp){
        mylog.warn("showItemsInCart returned a response");
    }

    @Pointcut("execution (* com.cgi.order.controller.OrderController.showItemsInCart(..))")
    public void showItemsInCart(){

    }

    @Before("updateItemQty()")
    public void beforeSaveupdateItemQty(JoinPoint jp)
    {
        mylog.info("updateItemQty is called.");
    }

    @After("updateItemQty()")
    public void afterSaveupdateItemQty(JoinPoint jp)
    {
        mylog.info("Executed after updateItemQty is called.");
    }

    @AfterThrowing(pointcut="updateItemQty()",throwing = "excepobj")
    public void handleexcSaveupdateItemQty(Exception excepobj)
    {
        mylog.warn("Exception is raised while updateItemQty is called.");
    }

    @AfterReturning("updateItemQty()")
    public void handleReturnupdateItemQty(JoinPoint jp){
        mylog.warn("updateItemQty returned a response");
    }

    @Pointcut("execution (* com.cgi.order.controller.OrderController.updateItemQty(..))")
    public void updateItemQty(){

    }

    @Before("placeOrder()")
    public void beforeSaveplaceOrder(JoinPoint jp)
    {
        mylog.info("placeOrder is called.");
    }

    @After("placeOrder()")
    public void afterSaveplaceOrder(JoinPoint jp)
    {
        mylog.info("Executed after placeOrder is called.");
    }

    @AfterThrowing(pointcut="placeOrder()",throwing = "excepobj")
    public void handleexcSaveplaceOrder(Exception excepobj)
    {
        mylog.warn("Exception is raised while placeOrder is called.");
    }

    @AfterReturning("placeOrder()")
    public void handleReturnplaceOrder(JoinPoint jp){
        mylog.warn("placeOrder returned a response");
    }

    @Pointcut("execution (* com.cgi.order.controller.OrderController.placeOrder(..))")
    public void placeOrder(){

    }

    @Before("viewOrderByOrderId()")
    public void beforeSaveviewOrderByOrderId(JoinPoint jp)
    {
        mylog.info("viewOrderByOrderId is called.");
    }

    @After("viewOrderByOrderId()")
    public void afterSaveviewOrderByOrderId(JoinPoint jp)
    {
        mylog.info("Executed after viewOrderByOrderId is called.");
    }

    @AfterThrowing(pointcut="viewOrderByOrderId()",throwing = "excepobj")
    public void handleexcSaveviewOrderByOrderId(Exception excepobj)
    {
        mylog.warn("Exception is raised while viewOrderByOrderId is called.");
    }

    @AfterReturning("viewOrderByOrderId()")
    public void handleReturnviewOrderByOrderId(JoinPoint jp){
        mylog.warn("viewOrderByOrderId returned a response");
    }

    @Pointcut("execution (* com.cgi.order.controller.OrderController.viewOrderByOrderId(..))")
    public void viewOrderByOrderId(){

    }

    @Before("viewAllOrdersByRestaurantId()")
    public void beforeSaveviewAllOrdersByRestaurantId(JoinPoint jp)
    {
        mylog.info("viewAllOrdersByRestaurantId is called.");
    }

    @After("viewAllOrdersByRestaurantId()")
    public void afterSaveviewAllOrdersByRestaurantId(JoinPoint jp)
    {
        mylog.info("Executed after viewAllOrdersByRestaurantId is called.");
    }

    @AfterThrowing(pointcut="viewAllOrdersByRestaurantId()",throwing = "excepobj")
    public void handleexcSaveviewAllOrdersByRestaurantId(Exception excepobj)
    {
        mylog.warn("Exception is raised while viewAllOrdersByRestaurantId is called.");
    }

    @AfterReturning("viewAllOrdersByRestaurantId()")
    public void handleReturnviewAllOrdersByRestaurantId(JoinPoint jp){
        mylog.warn("viewAllOrdersByRestaurantId returned a response");
    }

    @Pointcut("execution (* com.cgi.order.controller.OrderController.viewAllOrdersByRestaurantId(..))")
    public void viewAllOrdersByRestaurantId(){

    }

    @Before("viewAllOrdersByUsername()")
    public void beforeSaveviewAllOrdersByUsername(JoinPoint jp)
    {
        mylog.info("viewAllOrdersByUsername is called.");
    }

    @After("viewAllOrdersByUsername()")
    public void afterSaveviewAllOrdersByUsername(JoinPoint jp)
    {
        mylog.info("Executed after viewAllOrdersByUsername is called.");
    }

    @AfterThrowing(pointcut="viewAllOrdersByUsername()",throwing = "excepobj")
    public void handleexcSaveviewAllOrdersByUsername(Exception excepobj)
    {
        mylog.warn("Exception is raised while viewAllOrdersByUsername is called.");
    }

    @AfterReturning("viewAllOrdersByUsername()")
    public void handleReturnviewAllOrdersByUsername(JoinPoint jp){
        mylog.warn("viewAllOrdersByUsername returned a response");
    }

    @Pointcut("execution (* com.cgi.order.controller.OrderController.viewAllOrdersByUsername(..))")
    public void viewAllOrdersByUsername(){

    }

    @Before("viewAllOrders()")
    public void beforeSaveviewAllOrders(JoinPoint jp)
    {
        mylog.info("viewAllOrders is called.");
    }

    @After("viewAllOrders()")
    public void afterSaveviewAllOrders(JoinPoint jp)
    {
        mylog.info("Executed after viewAllOrders is called.");
    }

    @AfterThrowing(pointcut="viewAllOrders()",throwing = "excepobj")
    public void handleexcSaveviewAllOrders(Exception excepobj)
    {
        mylog.warn("Exception is raised while viewAllOrders is called.");
    }

    @AfterReturning("viewAllOrders()")
    public void handleReturnviewAllOrders(JoinPoint jp){
        mylog.warn("viewAllOrders returned a response");
    }

    @Pointcut("execution (* com.cgi.order.controller.OrderController.viewAllOrders(..))")
    public void viewAllOrders(){

    }

    @Before("updateOrderStatus()")
    public void beforeSaveupdateOrderStatus(JoinPoint jp)
    {
        mylog.info("updateOrderStatus is called.");
    }

    @After("updateOrderStatus()")
    public void afterSaveupdateOrderStatus(JoinPoint jp)
    {
        mylog.info("Executed after updateOrderStatus is called.");
    }

    @AfterThrowing(pointcut="updateOrderStatus()",throwing = "excepobj")
    public void handleexcSaveupdateOrderStatus(Exception excepobj)
    {
        mylog.warn("Exception is raised while updateOrderStatus is called.");
    }

    @AfterReturning("updateOrderStatus()")
    public void handleReturnupdateOrderStatus(JoinPoint jp){
        mylog.warn("updateOrderStatus returned a response");
    }

    @Pointcut("execution (* com.cgi.order.controller.OrderController.updateOrderStatus(..))")
    public void updateOrderStatus(){

    }

    @Before("deleteOrder()")
    public void beforeSavedeleteOrder(JoinPoint jp)
    {
        mylog.info("deleteOrder is called.");
    }

    @After("deleteOrder()")
    public void afterSavedeleteOrder(JoinPoint jp)
    {
        mylog.info("Executed after deleteOrder is called.");
    }

    @AfterThrowing(pointcut="deleteOrder()",throwing = "excepobj")
    public void handleexcSavedeleteOrder(Exception excepobj)
    {
        mylog.warn("Exception is raised while deleteOrder is called.");
    }

    @AfterReturning("deleteOrder()")
    public void handleReturndeleteOrder(JoinPoint jp){
        mylog.warn("deleteOrder returned a response");
    }

    @Pointcut("execution (* com.cgi.order.controller.OrderController.deleteOrder(..))")
    public void deleteOrder(){

    }



}
