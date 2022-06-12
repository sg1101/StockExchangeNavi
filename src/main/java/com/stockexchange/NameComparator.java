package com.stockexchange;

import java.util.Comparator;

public class NameComparator implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        if(o1.isBuy()){
            if(o2.getPrice()<o1.getPrice())return 1;
            return 0;
        }else{
            if(o2.getPrice()>o1.getPrice())return 1;
            return 0;
        }
    }
}
