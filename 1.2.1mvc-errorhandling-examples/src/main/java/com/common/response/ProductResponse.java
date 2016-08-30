package com.common.response;

import com.common.response.BaseResponse;
import com.common.response.Message;

/**
 * Created by fsubasi on 15.02.2016.
 */
public class ProductResponse extends BaseResponse {
    private double price;

    public ProductResponse(Message message){
        super(false);
        this.get_messages().add(message);
    }

    public ProductResponse(double price){
        super(true); // the operation is successful, i.e. we have the price
        this.setPrice(price);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
