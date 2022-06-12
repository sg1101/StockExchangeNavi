package com.stockexchange;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class Matching {
    static ArrayList<String> inputOrders = new ArrayList<String>();

    static HashMap<String, Stock> stocks = new HashMap<String, Stock>(); //stockname -> Stock(object)
    static HashMap<String, Order> orders = new HashMap<String, Order>(); //orderid -> Order(object)


    // static void fileRead() throws IOException{

    private InputStream getFileFromResourceAsStream(String fileName) {

            // The class loader that loaded the class
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(fileName);
    
            // the stream holding the file content
            if (inputStream == null) {
                throw new IllegalArgumentException("file not found! " + fileName);
            } else {
                return inputStream;
            }
    
    }

    

    // static void fileRead() throws IOException{



    //     File file = new File();

        

    //     BufferedReader br
    //         = new BufferedReader(new FileReader(file));
 
    //     String st;

    //     inputOrders = new ArrayList<String> ();
        
    //     while ((st = br.readLine()) != null){
    //         inputOrders.add(st);
    //     }
            
    //     br.close();
    // }


    // INPUT: inputOrders
    // <order-id> <time> <stock> <buy/sell> <price> <qty>
    //Stock(String name), Order(String order_id, String time, String buy, int price, int quantity, String stockName)
    // #1 09:45 BAC sell 240.12 100
    // #2 09:46 BAC sell 237.45 90
    // #3 09:47 BAC buy 238.10 110
    // #4 09:48 BAC buy 237.80 10
    // #5 09:49 BAC buy 237.80 40
    // #6 09:50 BAC sell 236.00 50
    

    public static void main(String[] args) throws IOException{

        String fileName = "data/test.txt";

        Matching match = new Matching();

        // System.out.println("getResourceAsStream : " + fileName);
        InputStream is = match.getFileFromResourceAsStream(fileName);
        // printInputStream(is);

        // System.out.println("\ngetResource : " + fileName);
        // File file = match.getFileFromResource(fileName);
        // printFile(file);

        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(streamReader);
        String st;

        inputOrders = new ArrayList<String> ();
        
        while ((st = br.readLine()) != null){
            inputOrders.add(st);
        }
            
        br.close();


        //fileRead();
        int countInputStrings = inputOrders.size();

        for(int i =0; i<countInputStrings; i++){
            
            String orderString = inputOrders.get(i);

            String[] orderList = orderString.split("\\s+");;

            String orderId = orderList[0];
            String time = orderList[1];
            String stockname = orderList[2];
            String buy = orderList[3];
            String price = orderList[4];
            String quantity = orderList[5];

            if(!stocks.containsKey(stockname)){
                Stock stock = new Stock(stockname);
                stocks.put(stockname, stock);
            }

            Stock stock = stocks.get(stockname);

            Order order = new Order(orderId, time, buy, Double.parseDouble(price), Integer.parseInt(quantity), stockname);

            orders.put(orderId, order);

            stock.stockMatch(order);

        }

    }
}
