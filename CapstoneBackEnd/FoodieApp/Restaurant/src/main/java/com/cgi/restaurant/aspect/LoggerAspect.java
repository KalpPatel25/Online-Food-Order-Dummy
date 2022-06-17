package com.cgi.restaurant.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    Logger mylog = LoggerFactory.getLogger(LoggerAspect.class);

//    @Before("addRestaurantForOwner()")
//    public void beforeSaveaddRestaurantForOwner(JoinPoint jp)
//    {
//        mylog.info("addRestaurantForOwner is called.");
//    }
//
//    @After("addRestaurantForOwner()")
//    public void afterSaveaddRestaurantForOwner(JoinPoint jp)
//    {
//        mylog.info("Executed after addRestaurantForOwner is called.");
//    }
//
//    @AfterThrowing(pointcut="addRestaurantForOwner()",throwing = "excepobj")
//    public void handleexcSaveaddRestaurantForOwner(Exception excepobj)
//    {
//        mylog.warn("Exception is raised while addRestaurantForOwner is called.");
//    }
//
//    @AfterReturning("addRestaurantForOwner()")
//    public void handleReturnaddRestaurantForOwner(JoinPoint jp){
//        mylog.warn("addRestaurant returned a response");
//    }
//
//    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.addRestaurantForOwner(..))")
//    public void addRestaurant(){
//
//    }

    @Before("addItemForOwner()")
    public void beforeSaveaddItemForOwner(JoinPoint jp)
    {
        mylog.info("addItemForOwner is called.");
    }

    @After("addItemForOwner()")
    public void afterSaveaddItemForOwner(JoinPoint jp)
    {
        mylog.info("Executed after addItemForOwner is called.");
    }

    @AfterThrowing(pointcut="addItemForOwner()",throwing = "excepobj")
    public void handleexcSaveaddItemForOwner(Exception excepobj)
    {
        mylog.warn("Exception is raised while addItemForOwner is called.");
    }

    @AfterReturning("addItemForOwner()")
    public void handleReturnaddItemForOwner(JoinPoint jp){
        mylog.warn("addItemForOwner returned a response");
    }

    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.addItemForOwner(..))")
    public void addItemForOwner(){

    }

    @Before("getRestaurantById()")
    public void beforeSavegetRestaurantById(JoinPoint jp)
    {
        mylog.info("getRestaurantById is called.");
    }

    @After("getRestaurantById()")
    public void afterSavegetRestaurantById(JoinPoint jp)
    {
        mylog.info("Executed after getRestaurantById is called.");
    }

    @AfterThrowing(pointcut="getRestaurantById()",throwing = "excepobj")
    public void handleexcSavegetRestaurantById(Exception excepobj)
    {
        mylog.warn("Exception is raised while getRestaurantById is called.");
    }

    @AfterReturning("getRestaurantById()")
    public void handleReturngetRestaurantById(JoinPoint jp){
        mylog.warn("getRestaurantById returned a response");
    }

    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.getRestaurantById(..))")
    public void getRestaurantById(){

    }

    @Before("getRestaurantId()")
    public void beforeSavegetRestaurantId(JoinPoint jp)
    {
        mylog.info("getRestaurantId is called.");
    }

    @After("getRestaurantId()")
    public void afterSavegetRestaurantId(JoinPoint jp)
    {
        mylog.info("Executed after getRestaurantId is called.");
    }

    @AfterThrowing(pointcut="getRestaurantId()",throwing = "excepobj")
    public void handleexcSavegetRestaurantId(Exception excepobj)
    {
        mylog.warn("Exception is raised while getRestaurantId is called.");
    }

    @AfterReturning("getRestaurantId()")
    public void handleReturngetRestaurantId(JoinPoint jp){
        mylog.warn("getRestaurantId returned a response");
    }

    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.getRestaurantId(..))")
    public void getRestaurantId(){

    }

    @Before("viewRestaurantByType()")
    public void beforeSaveviewRestaurantByType(JoinPoint jp)
    {
        mylog.info("viewRestaurantByType is called.");
    }

    @After("viewRestaurantByType()")
    public void afterSaveviewRestaurantByType(JoinPoint jp)
    {
        mylog.info("Executed after viewRestaurantByType is called.");
    }

    @AfterThrowing(pointcut="viewRestaurantByType()",throwing = "excepobj")
    public void handleexcSaveviewRestaurantByType(Exception excepobj)
    {
        mylog.warn("Exception is raised while viewRestaurantByType is called.");
    }

    @AfterReturning("viewRestaurantByType()")
    public void handleReturnviewRestaurantByType(JoinPoint jp){
        mylog.warn("viewRestaurantByType returned a response");
    }

    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.viewRestaurantByType(..))")
    public void viewRestaurantByType(){

    }

    @Before("viewRestaurantByItemName()")
    public void beforeSaveviewRestaurantByItemName(JoinPoint jp)
    {
        mylog.info("viewRestaurantByItemName is called.");
    }

    @After("viewRestaurantByItemName()")
    public void afterSaveviewRestaurantByItemName(JoinPoint jp)
    {
        mylog.info("Executed after viewRestaurantByItemName is called.");
    }

    @AfterThrowing(pointcut="viewRestaurantByItemName()",throwing = "excepobj")
    public void handleexcSaveviewRestaurantByItemName(Exception excepobj)
    {
        mylog.warn("Exception is raised while viewRestaurantByItemName is called.");
    }

    @AfterReturning("viewRestaurantByItemName()")
    public void handleReturnviewRestaurantByItemName(JoinPoint jp){
        mylog.warn("viewRestaurantByItemName returned a response");
    }

    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.viewRestaurantByItemName(..))")
    public void viewRestaurantByItemName(){

    }

    @Before("viewAllRestaurants()")
    public void beforeSaveviewAllRestaurants(JoinPoint jp)
    {
        mylog.info("viewAllRestaurants is called.");
    }

    @After("viewAllRestaurants()")
    public void afterSaveviewAllRestaurants(JoinPoint jp)
    {
        mylog.info("Executed after viewAllRestaurants is called.");
    }

    @AfterThrowing(pointcut="viewAllRestaurants()",throwing = "excepobj")
    public void handleexcSaveviewAllRestaurants(Exception excepobj)
    {
        mylog.warn("Exception is raised while viewAllRestaurants is called.");
    }

    @AfterReturning("viewAllRestaurants()")
    public void handleReturnviewAllRestaurants(JoinPoint jp){
        mylog.warn("viewAllRestaurants returned a response");
    }

    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.viewAllRestaurants(..))")
    public void viewAllRestaurants(){

    }

    @Before("viewAllRestaurantsAdmin()")
    public void beforeSaveviewAllRestaurantsAdmin(JoinPoint jp)
    {
        mylog.info("viewAllRestaurantsAdmin is called.");
    }

    @After("viewAllRestaurantsAdmin()")
    public void afterSaveviewAllRestaurantsAdmin(JoinPoint jp)
    {
        mylog.info("Executed after viewAllRestaurantsAdmin is called.");
    }

    @AfterThrowing(pointcut="viewAllRestaurantsAdmin()",throwing = "excepobj")
    public void handleexcSaveviewAllRestaurantsAdmin(Exception excepobj)
    {
        mylog.warn("Exception is raised while viewAllRestaurantsAdmin is called.");
    }

    @AfterReturning("viewAllRestaurantsAdmin()")
    public void handleReturnviewAllRestaurantsAdmin(JoinPoint jp){
        mylog.warn("viewAllRestaurantsAdmin returned a response");
    }

    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.viewAllRestaurantsAdmin(..))")
    public void viewAllRestaurantsAdmin(){

    }

    @Before("viewAllItemsRestaurantOwner()")
    public void beforeSaveviewAllItemsRestaurantOwner(JoinPoint jp)
    {
        mylog.info("viewAllItemsRestaurantOwner is called.");
    }

    @After("viewAllItemsRestaurantOwner()")
    public void afterSaveviewAllItemsRestaurantOwner(JoinPoint jp)
    {
        mylog.info("Executed after viewAllItemsRestaurantOwner is called.");
    }

    @AfterThrowing(pointcut="viewAllItemsRestaurantOwner()",throwing = "excepobj")
    public void handleexcSaveviewAllItemsRestaurantOwner(Exception excepobj)
    {
        mylog.warn("Exception is raised while viewAllItemsRestaurantOwner is called.");
    }

    @AfterReturning("viewAllItemsRestaurantOwner()")
    public void handleReturnviewAllItemsRestaurantOwner(JoinPoint jp){
        mylog.warn("viewAllItemsRestaurantOwner returned a response");
    }

    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.viewAllItemsRestaurantOwner(..))")
    public void viewAllItemsRestaurantOwner(){

    }

    @Before("viewAllItemsRestaurantUser()")
    public void beforeSaveviewAllItemsRestaurantUser(JoinPoint jp)
    {
        mylog.info("viewAllItemsRestaurantUser is called.");
    }

    @After("viewAllItemsRestaurantUser()")
    public void afterSaveviewAllItemsRestaurantUser(JoinPoint jp)
    {
        mylog.info("Executed after viewAllItemsRestaurantUser is called.");
    }

    @AfterThrowing(pointcut="viewAllItemsRestaurantUser()",throwing = "excepobj")
    public void handleexcSaveviewAllItemsRestaurantUser(Exception excepobj)
    {
        mylog.warn("Exception is raised while viewAllItemsRestaurantUser is called.");
    }

    @AfterReturning("viewAllItemsRestaurantUser()")
    public void handleReturnviewAllItemsRestaurantUser(JoinPoint jp){
        mylog.warn("viewAllItemsRestaurantUser returned a response");
    }

    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.viewAllItemsRestaurantUser(..))")
    public void viewAllItemsRestaurantUser(){

    }

    @Before("viewAllItemsByCategoryOwner()")
    public void beforeSaveviewAllItemsByCategoryOwner(JoinPoint jp)
    {
        mylog.info("viewAllItemsByCategoryOwner is called.");
    }

    @After("viewAllItemsByCategoryOwner()")
    public void afterSaveviewAllItemsByCategoryOwner(JoinPoint jp)
    {
        mylog.info("Executed after viewAllItemsByCategoryOwner is called.");
    }

    @AfterThrowing(pointcut="viewAllItemsByCategoryOwner()",throwing = "excepobj")
    public void handleexcSaveviewAllItemsByCategoryOwner(Exception excepobj)
    {
        mylog.warn("Exception is raised while viewAllItemsByCategoryOwner is called.");
    }

    @AfterReturning("viewAllItemsByCategoryOwner()")
    public void handleReturnviewAllItemsByCategoryOwner(JoinPoint jp){
        mylog.warn("viewAllItemsByCategoryOwner returned a response");
    }

    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.viewAllItemsByCategoryOwner(..))")
    public void viewAllItemsByCategoryOwner(){

    }

    @Before("viewAllItemsByCategoryUser()")
    public void beforeSaveviewAllItemsByCategoryUser(JoinPoint jp)
    {
        mylog.info("viewAllItemsByCategoryUser is called.");
    }

    @After("viewAllItemsByCategoryUser()")
    public void afterSaveviewAllItemsByCategoryUser(JoinPoint jp)
    {
        mylog.info("Executed after viewAllItemsByCategoryUser is called.");
    }

    @AfterThrowing(pointcut="viewAllItemsByCategoryUser()",throwing = "excepobj")
    public void handleexcSaveviewAllItemsByCategoryUser(Exception excepobj)
    {
        mylog.warn("Exception is raised while viewAllItemsByCategoryUser is called.");
    }

    @AfterReturning("viewAllItemsByCategoryUser()")
    public void handleReturnviewAllItemsByCategoryUser(JoinPoint jp){
        mylog.warn("viewAllItemsByCategoryUser returned a response");
    }

    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.viewAllItemsByCategoryUser(..))")
    public void viewAllItemsByCategoryUser(){

    }
    @Before("viewAnItemByItemId()")
    public void beforeSaveviewAnItemByItemId(JoinPoint jp)
    {
        mylog.info("viewAnItemByItemId is called.");
    }

    @After("viewAnItemByItemId()")
    public void afterSaveviewAnItemByItemId(JoinPoint jp)
    {
        mylog.info("Executed after viewAnItemByItemId is called.");
    }

    @AfterThrowing(pointcut="viewAnItemByItemId()",throwing = "excepobj")
    public void handleexcSaveviewAnItemByItemId(Exception excepobj)
    {
        mylog.warn("Exception is raised while viewAnItemByItemId is called.");
    }

    @AfterReturning("viewAnItemByItemId()")
    public void handleReturnviewAnItemByItemId(JoinPoint jp){
        mylog.warn("viewAnItemByItemId returned a response");
    }

    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.viewAnItemByItemId(..))")
    public void viewAnItemByItemId(){

    }

    @Before("updateItemRestaurantOwner()")
    public void beforeSaveupdateItemRestaurantOwner(JoinPoint jp)
    {
        mylog.info("updateItemRestaurantOwner is called.");
    }

    @After("updateItemRestaurantOwner()")
    public void afterSaveupdateItemRestaurantOwner(JoinPoint jp)
    {
        mylog.info("Executed after updateItemRestaurantOwner is called.");
    }

    @AfterThrowing(pointcut="updateItemRestaurantOwner()",throwing = "excepobj")
    public void handleexcSaveupdateItemRestaurantOwner(Exception excepobj)
    {
        mylog.warn("Exception is raised while updateItemRestaurantOwner is called.");
    }

    @AfterReturning("updateItemRestaurantOwner()")
    public void handleReturnupdateItemRestaurantOwner(JoinPoint jp){
        mylog.warn("updateItemRestaurantOwner returned a response");
    }

    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.updateItemRestaurantOwner(..))")
    public void updateItemRestaurantOwner(){

    }

    @Before("updateRestaurantInfoRestaurantOwner()")
    public void beforeSaveupdateRestaurantInfoRestaurantOwner(JoinPoint jp)
    {
        mylog.info("updateRestaurantInfoRestaurantOwner is called.");
    }

    @After("updateRestaurantInfoRestaurantOwner()")
    public void afterSaveupdateRestaurantInfoRestaurantOwner(JoinPoint jp)
    {
        mylog.info("Executed after updateRestaurantInfoRestaurantOwner is called.");
    }

    @AfterThrowing(pointcut="updateRestaurantInfoRestaurantOwner()",throwing = "excepobj")
    public void handleexcSaveupdateRestaurantInfoRestaurantOwner(Exception excepobj)
    {
        mylog.warn("Exception is raised while updateRestaurantInfoRestaurantOwner is called.");
    }

    @AfterReturning("updateRestaurantInfoRestaurantOwner()")
    public void handleReturnupdateRestaurantInfoRestaurantOwner(JoinPoint jp){
        mylog.warn("updateRestaurantInfoRestaurantOwner returned a response");
    }

    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.updateRestaurantInfoRestaurantOwner(..))")
    public void updateRestaurantInfoRestaurantOwner(){

    }

    @Before("updateItemStatusRestaurantOwner()")
    public void beforeSaveupdateItemStatusRestaurantOwner(JoinPoint jp)
    {
        mylog.info("updateItemStatusRestaurantOwner is called.");
    }

    @After("updateItemStatusRestaurantOwner()")
    public void afterSaveupdateItemStatusRestaurantOwner(JoinPoint jp)
    {
        mylog.info("Executed after updateItemStatusRestaurantOwner is called.");
    }

    @AfterThrowing(pointcut="updateItemStatusRestaurantOwner()",throwing = "excepobj")
    public void handleexcSaveupdateItemStatusRestaurantOwner(Exception excepobj)
    {
        mylog.warn("Exception is raised while updateItemStatusRestaurantOwner is called.");
    }

    @AfterReturning("updateItemStatusRestaurantOwner()")
    public void handleReturnupdateItemStatusRestaurantOwner(JoinPoint jp){
        mylog.warn("updateItemStatusRestaurantOwner returned a response");
    }

    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.updateItemStatusRestaurantOwner(..))")
    public void updateItemStatusRestaurantOwner(){

    }

    @Before("updateRestaurantStatusRestaurantOwner()")
    public void beforeSaveupdateRestaurantStatusRestaurantOwner(JoinPoint jp)
    {
        mylog.info("updateRestaurantStatusRestaurantOwner is called.");
    }

    @After("updateRestaurantStatusRestaurantOwner()")
    public void afterSaveupdateRestaurantStatusRestaurantOwner(JoinPoint jp)
    {
        mylog.info("Executed after updateRestaurantStatusRestaurantOwner is called.");
    }

    @AfterThrowing(pointcut="updateRestaurantStatusRestaurantOwner()",throwing = "excepobj")
    public void handleexcSaveupdateRestaurantStatusRestaurantOwner(Exception excepobj)
    {
        mylog.warn("Exception is raised while updateRestaurantStatusRestaurantOwner is called.");
    }

    @AfterReturning("updateRestaurantStatusRestaurantOwner()")
    public void handleReturnupdateRestaurantStatusRestaurantOwner(JoinPoint jp){
        mylog.warn("updateRestaurantStatusRestaurantOwner returned a response");
    }

    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.updateRestaurantStatusRestaurantOwner(..))")
    public void updateRestaurantStatusRestaurantOwner(){

    }

    @Before("deleteRestaurantRestaurantOwner()")
    public void beforeSavedeleteRestaurantRestaurantOwner(JoinPoint jp)
    {
        mylog.info("deleteRestaurantRestaurantOwner is called.");
    }

    @After("deleteRestaurantRestaurantOwner()")
    public void afterSavedeleteRestaurantRestaurantOwner(JoinPoint jp)
    {
        mylog.info("Executed after deleteRestaurantRestaurantOwner is called.");
    }

    @AfterThrowing(pointcut="deleteRestaurantRestaurantOwner()",throwing = "excepobj")
    public void handleexcSavedeleteRestaurantRestaurantOwner(Exception excepobj)
    {
        mylog.warn("Exception is raised while deleteRestaurantRestaurantOwner is called.");
    }

    @AfterReturning("deleteRestaurantRestaurantOwner()")
    public void handleReturndeleteRestaurantRestaurantOwner(JoinPoint jp){
        mylog.warn("deleteRestaurantRestaurantOwner returned a response");
    }

    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.deleteRestaurantRestaurantOwner(..))")
    public void deleteRestaurantRestaurantOwner(){

    }

    @Before("deleteAllRestaurantsAdmin()")
    public void beforeSavedeleteAllRestaurantsAdmin(JoinPoint jp)
    {
        mylog.info("deleteAllRestaurantsAdmin is called.");
    }

    @After("deleteAllRestaurantsAdmin()")
    public void afterSavedeleteAllRestaurantsAdmin(JoinPoint jp)
    {
        mylog.info("Executed after deleteAllRestaurantsAdmin is called.");
    }

    @AfterThrowing(pointcut="deleteAllRestaurantsAdmin()",throwing = "excepobj")
    public void handleexcSavedeleteAllRestaurantsAdmin(Exception excepobj)
    {
        mylog.warn("Exception is raised while deleteAllRestaurantsAdmin is called.");
    }

    @AfterReturning("deleteAllRestaurantsAdmin()")
    public void handleReturndeleteAllRestaurantsAdmin(JoinPoint jp){
        mylog.warn("deleteAllRestaurantsAdmin returned a response");
    }

    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.deleteAllRestaurantsAdmin(..))")
    public void deleteAllRestaurantsAdmin(){

    }

    @Before("deleteItemRestaurantOwner()")
    public void beforeSavedeleteItemRestaurantOwner(JoinPoint jp)
    {
        mylog.info("deleteItemRestaurantOwner is called.");
    }

    @After("deleteItemRestaurantOwner()")
    public void afterSavedeleteItemRestaurantOwner(JoinPoint jp)
    {
        mylog.info("Executed after deleteItemRestaurantOwner is called.");
    }

    @AfterThrowing(pointcut="deleteItemRestaurantOwner()",throwing = "excepobj")
    public void handleexcSavedeleteItemRestaurantOwner(Exception excepobj)
    {
        mylog.warn("Exception is raised while deleteItemRestaurantOwner is called.");
    }

    @AfterReturning("deleteItemRestaurantOwner()")
    public void handleReturndeleteItemRestaurantOwner(JoinPoint jp){
        mylog.warn("deleteItemRestaurantOwner returned a response");
    }

    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.deleteItemRestaurantOwner(..))")
    public void deleteItemRestaurantOwner(){

    }

    @Before("deleteAllItemsByRestaurantId()")
    public void beforeSavedeleteAllItemsByRestaurantId(JoinPoint jp)
    {
        mylog.info("deleteAllItemsByRestaurantId is called.");
    }

    @After("deleteAllItemsByRestaurantId()")
    public void afterSavedeleteAllItemsByRestaurantId(JoinPoint jp)
    {
        mylog.info("Executed after deleteAllItemsByRestaurantId is called.");
    }

    @AfterThrowing(pointcut="deleteAllItemsByRestaurantId()",throwing = "excepobj")
    public void handleexcSavedeleteAllItemsByRestaurantId(Exception excepobj)
    {
        mylog.warn("Exception is raised while deleteAllItemsByRestaurantId is called.");
    }

    @AfterReturning("deleteAllItemsByRestaurantId()")
    public void handleReturndeleteAllItemsByRestaurantId(JoinPoint jp){
        mylog.warn("deleteAllItemsByRestaurantId returned a response");
    }

    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.deleteAllItemsByRestaurantId(..))")
    public void deleteAllItemsByRestaurantId(){

    }

    @Before("deleteAllItemsByCategory()")
    public void beforeSavedeleteAllItemsByCategory(JoinPoint jp)
    {
        mylog.info("deleteAllItemsByCategory is called.");
    }

    @After("deleteAllItemsByCategory()")
    public void afterSavedeleteAllItemsByCategory(JoinPoint jp)
    {
        mylog.info("Executed after deleteAllItemsByCategory is called.");
    }

    @AfterThrowing(pointcut="deleteAllItemsByCategory()",throwing = "excepobj")
    public void handleexcSavedeleteAllItemsByCategory(Exception excepobj)
    {
        mylog.warn("Exception is raised while deleteAllItemsByCategory is called.");
    }

    @AfterReturning("deleteAllItemsByCategory()")
    public void handleReturndeleteAllItemsByCategory(JoinPoint jp){
        mylog.warn("deleteAllItemsByCategory returned a response");
    }

    @Pointcut("execution (* com.cgi.restaurant.controller.RestaurantController.deleteAllItemsByCategory(..))")
    public void deleteAllItemsByCategory(){

    }

}
