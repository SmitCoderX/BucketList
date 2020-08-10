package com.smitcoderx.learn.bucketlist;

public class BucketItems {

    private String Item;
    private String Quantity;

    public BucketItems(String item, String quantity) {
        Item = item;
        Quantity = quantity;
    }

    public String getItem() {
        return Item;
    }

    public String getQuantity() {
        return Quantity;
    }
}
