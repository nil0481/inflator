package com.example.layoutinflator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final List<String> list=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list.add("1st");
        list.add("2nd");
        list.add("3rd");
        list.add("4th");
        list.add("5th");
        StringBuilder builder=new StringBuilder();
        for(String x:list){
            builder.append(x).append("\n");
        }
        TextView textView2=(TextView)findViewById(R.id.textView2);
        textView2.setText(builder);

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        ListView listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String item=String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MainActivity.this,item+" selected",Toast.LENGTH_SHORT).show();
                    }
                }
        );

        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        remove(position);
                        return true;
                    }
                }
        );

    }
    private void remove(final int position){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Do you want to delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

               list.remove(position);
               adapter.notifyDataSetChanged();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    public void clk(View view) {
        LayoutInflater layoutInflater=getLayoutInflater();
        View view1=layoutInflater.inflate(R.layout.inflator,null);
        TextView textView=(TextView)view1.findViewById(R.id.textView);
        Toast toast=new Toast(getApplicationContext());
        toast.setView(view1);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CLIP_HORIZONTAL,0,0);
        toast.show();


    }
}
