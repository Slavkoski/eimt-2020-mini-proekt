package com.finki.eimt.hotel.model.price;

import lombok.AllArgsConstructor;

import javax.persistence.Embeddable;
import java.util.HashMap;
import java.util.Map;

@Embeddable
@AllArgsConstructor
public class Price {

    private final Currency currency;

    private final Double amount;

    public Price(){
        currency=Currency.MKD;
        amount=0.0;
    }

    public Price add(Price price){
        return new Price(this.currency,this.amount+price.getAmountWithCurrency(this.currency));
    }

    public Double getAmountWithCurrency(Currency currency){
        Map<Currency,Double> currencyMap=new HashMap<>();
        currencyMap.put(Currency.EUR,61.5);
        currencyMap.put(Currency.USD,54.1);
        currencyMap.put(Currency.MKD,1.0);
        double amountInMKD=this.amount*currencyMap.get(this.currency);
        return amountInMKD*(1/currencyMap.get(currency));
    }

}