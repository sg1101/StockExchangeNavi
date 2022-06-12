package com.stockexchange;

import java.util.ArrayList;
import java.util.Collections;

public class Stock {
    String stockName;
    ArrayList<Order> buyOrders;
    ArrayList<Order> sellOrders;
    Stock(String name){
        this.stockName = name;
    }

    String getName(){
        return stockName;
    }


    //Order(String order_id, String time, String buy, int price, int quantity, String stockName)
    //Output format: <buy-order-id> <sell-price> <qty> <sell-order-id>

    void stockMatch(Order order){
        String orderId = order.getOrderId();
        String time = order.getTime();
        boolean buy = order.isBuy();
        double price = order.getPrice();
        int quantity = order.getQuantity();
        String stockName = order.getStockName();
        if(buy){
            if(sellOrders.isEmpty()){
                buyOrders.add(order);
            }else{
                int countsellorders = sellOrders.size();

                Collections.sort(buyOrders, new NameComparator());
                Collections.sort(sellOrders, new NameComparator());

                for(int i =0; i<countsellorders; i++){
                    
                    if(quantity!=0 && price>sellOrders.get(i).getPrice() ){
                        int quantityMatched = Math.min(quantity, sellOrders.get(i).getQuantity());
                        quantity = quantity-quantityMatched;
                        sellOrders.get(i).setQuantity(sellOrders.get(i).getQuantity() - quantityMatched);
                        System.out.println(orderId+" " + String.valueOf(sellOrders.get(i).getPrice())+" "+ String.valueOf(quantityMatched)+" "+ sellOrders.get(i).getOrderId());
                    }
                    
                }
                
                int index = 0;
                while(index<sellOrders.size()){
                    if(sellOrders.get(index).getQuantity()==0){
                        sellOrders.remove(index);
                    }else{
                        break;
                    }
                }
            }
        }else{


            if(buyOrders.isEmpty()){
                sellOrders.add(order);
            }else{
                int countbuyorders = buyOrders.size();

                Collections.sort(buyOrders, new NameComparator());
                Collections.sort(sellOrders, new NameComparator());

                for(int i =0; i<countbuyorders; i++){
                    
                    if(quantity!=0 && price>buyOrders.get(i).getPrice() ){
                        int quantityMatched = Math.min(quantity, buyOrders.get(i).getQuantity());
                        quantity = quantity-quantityMatched;
                        buyOrders.get(i).setQuantity(buyOrders.get(i).getQuantity() - quantityMatched);
                        System.out.println(orderId+" " + String.valueOf(buyOrders.get(i).getPrice())+" "+ String.valueOf(quantityMatched)+" "+ buyOrders.get(i).getOrderId());
                    }
                    
                }
                
                int index = 0;
                while(index<buyOrders.size()){
                    if(buyOrders.get(index).getQuantity()==0){
                        buyOrders.remove(index);
                    }else{
                        break;
                    }
                }

            }


        }
    }

}
