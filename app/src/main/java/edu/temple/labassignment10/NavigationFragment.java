package edu.temple.labassignment10;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class NavigationFragment extends Fragment {

    ListView myList;
    ArrayAdapter<String> adapter;
    StockInterface stockInterface;
    public MainActivity myNav;
    ArrayList<String> symbolList;

    public NavigationFragment() {
        // Required empty public constructor
    }

    public static NavigationFragment newInstance(String param1, String param2) {
        NavigationFragment fragment = new NavigationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);

        myNav = (MainActivity) getActivity();
        symbolList = new ArrayList<>();
        myList = (ListView) view.findViewById(R.id.myList);
       adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, symbolList);
        myList.setAdapter(adapter);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                stockInterface.AcceptStock(position);
            }
        });

        ActionBar myActionBar = getActivity().getActionBar();
        myActionBar.setTitle(R.string.stocks);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.actionmenu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.app_bar_search){
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            alertDialog.setTitle("Enter Stock");
            final EditText editText = new EditText(getContext());
            final Button button = new Button(getContext());
            alertDialog.setView(editText);
            alertDialog.setButton(button.getId(), "Search", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String searchText = editText.getText().toString();
                    symbolList.add(searchText);
                    myNav.myStockList.add(new Stock(searchText));
                    adapter.notifyDataSetChanged();

                }
            });
            alertDialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        stockInterface = (StockInterface) context;
    }

    public interface StockInterface{
        void AcceptStock(int pos);
    }
}
