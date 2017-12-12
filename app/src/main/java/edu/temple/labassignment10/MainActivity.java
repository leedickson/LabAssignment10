package edu.temple.labassignment10;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;


public class MainActivity extends Activity implements NavigationFragment.StockInterface {

    StockFragment myStock;
    NavigationFragment myNav;
    FragmentTransaction transaction;
    boolean twoPanes;
    public ArrayList<Stock> myStockList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myStockList = new ArrayList<>();
        final Intent myIntent = new Intent(MainActivity.this, StockService.class);
        myIntent.putParcelableArrayListExtra("stocklist", myStockList);
        startService(myIntent);

        twoPanes = (findViewById(R.id.stockFragment)!=null);

        FragmentManager fragMan = getFragmentManager();
        transaction = fragMan.beginTransaction();
        myStock = new StockFragment();
        myNav = new NavigationFragment();
        transaction.add(R.id.navFragment, myNav);

        if(twoPanes){
            transaction.add(R.id.stockFragment, myStock);
        }

        transaction.commit();


    }

    @Override
    public void AcceptStock(int pos) {
        if(!twoPanes) {
            transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.navFragment, myStock).addToBackStack(null).commit();
            getFragmentManager().executePendingTransactions();
        }
        myStock.setStock(pos);
    }
}
