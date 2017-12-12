package edu.temple.labassignment10;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Stock implements Parcelable{

    String symbol;
    String name;

    public String getSymbol() {
        return symbol;
    }

    String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



    Stock(String symbol){
        symbol = this.symbol;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
