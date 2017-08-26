package com.acadgild.gridviewapplication;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //creating objects
GridView gridView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing
        gridView= (GridView) findViewById(R.id.mygridview);
        textView= (TextView) findViewById(R.id.textView);
        gridView.setAdapter(new MyAdapter(this));

    }


    class MyAdapter extends BaseAdapter{
        //Arraylist
      ArrayList<MyClass> list;
        Context context;

        MyAdapter(Context context){
            this.context=context;
            list= new ArrayList<MyClass>();
          Resources res= context.getResources();
         String[] tempversion=  res.getStringArray(R.array.versions);
            int[] images={R.drawable.gingerbread,R.drawable.honeycomb,R.drawable.icecream,R.drawable.jellybean,R.drawable.kitkat,R.drawable.lolipop};
         //Now putting it in main arraylist using for loop
            for(int i=0;i<6;i++){
               MyClass myClass= new MyClass(images[i],tempversion[i]);
                list.add(myClass);
            }

        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        class ViewHolder{
            ImageView myimage;
            TextView mytext;
            ViewHolder(View view){
                myimage= (ImageView) view.findViewById(R.id.imageView);
                mytext= (TextView) view.findViewById(R.id.textView);
            }
        }

          //this method is responsible for creating views
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            View row= view;
            ViewHolder holder=null;
            if(row==null) {
                LayoutInflater inflater= (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                row= inflater.inflate(R.layout.single_view,parent,false);
                holder =new ViewHolder(row);
                row.setTag(holder);
            }else{
              holder= (ViewHolder) row.getTag();

            }
           MyClass temp= list.get(position);
            MyClass temp1=list.get(position);
           holder.myimage.setImageResource(temp.versionImage);
            holder.mytext.setText(temp.versionName);
            return row;
        }
    }
}
