package com.example.lab2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewDebug;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText inputName;
    EditText inputDescrption;
    Button addProduct;
    Button saveProducts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = (ListView)findViewById(R.id.listView);
        final TextView textView = (TextView)findViewById(R.id.ListItemText);
        final ArrayList<Product> list = new ArrayList<>();
        saveProducts = findViewById(R.id.SaveButton);
        Product p1 = new Product("Car","Mercedes",40000);
        Product p2 = new Product( "Phone", "Iphone", 1000);
        Product p3 = new Product( "Laptop", "Dell" , 1400);

        list.add(p1);
        list.add(p2);
        list.add(p3);

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(list.get(position).toString());
            }
        });
        if(savedInstanceState != null) {
            String text = savedInstanceState.getString("text");
            textView.setText(String.valueOf(text));

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Product");
        builder.setIcon(R.drawable.ic_launcher_foreground);
        builder.setMessage("New Product");
        inputName = new EditText(this);
       // inputDescrption = new EditText( this);
        builder.setView(inputName);
        //builder.setView(inputDescrption);

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name =  inputName.getText().toString();
            //    String description = inputDescrption.getText().toString();
                if(name.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please enter a name", Toast.LENGTH_LONG).show();
                }
                else {
                    Product product = new Product(name, "", 0);
                    arrayAdapter.add(product);
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final AlertDialog alertDialog = builder.create();
        addProduct = findViewById(R.id.AddButton);
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });



        saveProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    saveData("key",textView.getText().toString());
                }

        });

        }
        private void saveData(String key, String value)
        {
            SharedPreferences sharedPreferences = getSharedPreferences("myPref",0);
            SharedPreferences.Editor mEditor = sharedPreferences.edit();
            mEditor.putString(key,value);
            mEditor.apply();
            mEditor.commit();
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_new)
        {
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
            startActivity(intent);
            return  false;
        }
        if (id == R.id.action_prefference)
        {
            Intent intent = new Intent( MainActivity.this, Prefferences.class);
            startActivity(intent);
            return  false;
        }
        if (id == R.id.action_sensor)
        {
            Intent intent = new Intent(MainActivity.this, SensorActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_gps)
        {
            Intent intent = new Intent(MainActivity.this, GPS.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onStart(){
        Log.d("lifecycle ","start");
        super.onStart();
    }

    @Override
    public void onResume(){
        Log.d("lifecycle ","resume");
        super.onResume();
    }

    @Override
    public void onPause(){
        Log.d("lifecycle ","pause");
        super.onPause();
    }

    @Override
    public void onStop(){
        Log.d("lifecycle ","stop");
        super.onStop();
    }

    @Override
    public void onDestroy(){
        Log.d("lifecycle ","destroy");
        super.onDestroy();
    }

    @Override
    public void onRestart(){
        Log.d("lifecycle ","restart");
        super.onRestart();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
             TextView textView = (TextView)findViewById(R.id.ListItemText);
            String text = textView.getText().toString();
            outState.putString("text", text);
        }

    }
