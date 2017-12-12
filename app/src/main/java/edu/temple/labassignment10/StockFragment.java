package edu.temple.labassignment10;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


public class StockFragment extends Fragment {

    TextView stockName;
    TextView stockSymbol;
    TextView stockPrice;
    public MainActivity myNav;

    public StockFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stock, container, false);

        myNav = (MainActivity) getActivity();

        stockName = (TextView) view.findViewById(R.id.stockName);
        stockSymbol = (TextView) view.findViewById(R.id.stockSymbol);
        stockPrice = (TextView) view.findViewById(R.id.stockPrice);

        return view;
    }

    public void setStock(int pos){
        Stock stock = myNav.myStockList.get(pos);
        stockName.setText(stock.getName());
        stockSymbol.setText(stock.getSymbol());
        stockPrice.setText(stock.getPrice());
    }


}
