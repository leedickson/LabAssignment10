package edu.temple.labassignment10;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;


public class StockService extends IntentService {
    public StockService() {
        super("StockService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        ArrayList<Stock> newStockList = intent.getParcelableArrayListExtra("stocklist");
        UpdateStock(newStockList);
    }

    public void UpdateStock(ArrayList<Stock> stock) {
        while(true){
            if(stock.size()!=0) {
                int i;
                for (i = 0; i <= stock.size(); i++) {
                    Stock newStock = stock.get(i);
                    URL stockQuoteUrl;
                    try {

                        stockQuoteUrl = new URL("http://dev.markitondemand.com/MODApis/Api/v2/Quote/json/?symbol=" + newStock.getSymbol());

                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(
                                        stockQuoteUrl.openStream()));

                        String response = "", tmpResponse;

                        tmpResponse = reader.readLine();
                        while (tmpResponse != null) {
                            response = response + tmpResponse;
                            tmpResponse = reader.readLine();
                        }
                        JSONObject stockObject = new JSONObject(response);

                        String name = stockObject.getString("Name");
                        String price = stockObject.getString("Price");
                        newStock.setName(name);
                        newStock.setPrice(price);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}
