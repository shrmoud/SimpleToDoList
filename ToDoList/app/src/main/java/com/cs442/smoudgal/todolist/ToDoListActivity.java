package com.cs442.smoudgal.todolist;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ToDoListActivity extends Activity {

    static int count = 5;
    private static final String TAG = "Activity";


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate your View
        setContentView(R.layout.activity_to_do_list);

        // Get references to UI widgets
        ListView myListView = (ListView)findViewById(R.id.myListView);
        final EditText myEditText = (EditText)findViewById(R.id.myEditText);

        // Create the Array List of to do items
        final ArrayList<String> todoItems = new ArrayList<String>();
        //final ArrayList<String> todoin = new ArrayList<String>();

        // Create the Array Adapter to bind the array to the List View
        final ArrayAdapter<String> aa;

        //int count =0;
        todoItems.add(0,"Soft Drinks, Price: $5, Taste: Sweet, Ingredients: sugar or high-fructose corn syrup, Rating: ***");
        //todoin.add();
        //++count;
        todoItems.add(1,"Ice Cream, Price: $20, Taste: Sweet, Ingredients: milk teaspoon vanilla and sugar, Rating: ****");
        //++count;
        todoItems.add(2,"Milk Shake, Price: $20, Taste: Sweet, Ingredients: Blend of vanilla ice cream cup of milk teaspoon vanilla and pinch of salt, Rating: *****");
        //++count;
        todoItems.add(3,"Burrito, Price: $30, Taste: Mild, Ingredients: mexican style rice or plain rice, Rating: ***");
        //++count;
        todoItems.add(4,"Hamburger, Price: $50, Taste: Spicy, Ingredients: Ground beef Onion Cheese soy sauce egg, Rating: ****");
        //++count;


        aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                todoItems);

        // Bind the Array Adapter to the List View
        myListView.setAdapter(aa);

        myEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if ((keyCode == KeyEvent.KEYCODE_ENTER))
                    {
                        if(myEditText.getText().toString().length() > 0)
                        {
                            todoItems.add(count, myEditText.getText().toString());
                            ++count;
                            aa.notifyDataSetChanged();
                        }
                        myEditText.setText("");
                        return true;
                    }
                return false;
            }
        }
        );


        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long arg3) {

                String element = todoItems.get(position);
                Log.v(TAG, "Removing from " + position);
                todoItems.remove(position);
                aa.remove(element);
                aa.notifyDataSetChanged();
                return false;
            }

        });


        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> paramAdapterView,  View view, int position, long id) {

                String element = todoItems.get(position);
                Toast.makeText(getApplicationContext(), element, Toast.LENGTH_LONG).show();
            }
        });

    }
}