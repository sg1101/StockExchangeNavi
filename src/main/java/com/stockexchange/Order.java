package com.stockexchange;

public class Order {
    String order_id;
    String time;
    boolean buyFlag = false;
    boolean sellFlag = false;
    double price;
    Integer quantity;
    String stockName;

    Order(){
        this.order_id = "";
        this.time = "";
        this.price = 0;
        this.quantity = 0;
        this.stockName = "";
    }

    Order(String order_id, String time, String buy, double price, int quantity, String stockName){
        this.order_id = order_id;
        this.time = time;
        if(buy=="buy"){
            buyFlag = true;
        }else{
            sellFlag = true;
        }
        this.price = price;
        this.quantity = quantity;
        this.stockName = stockName;
    }

    String getOrderId(){
        return order_id;
    }

    String getTime(){
        return time;
    }

    boolean isBuy(){
        if(buyFlag)return true;
        return false;
    }

    double getPrice(){
        return price;
    }

    int getQuantity(){
        return quantity;
    }

    String getStockName(){
        return stockName;
    }

    void setQuantity(int quantity){
        this.quantity = quantity;
    }

}
