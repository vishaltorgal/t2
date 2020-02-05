package com.androidaura.t2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomList extends BaseAdapter {

    private final Activity context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;


    static class ViewHolder {

        TextView me;

    }




    public CustomList(Activity context,
                      ArrayList<HashMap<String, String>> alist) {
        this.context = context;
        data = alist;


    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = context.getLayoutInflater();

Log.d("vt","value os "+data);
        if (view == null) {
            view= inflater.inflate(R.layout.layout_customlist, null);
            holder = new ViewHolder();
            holder.me = (TextView) view.findViewById(R.id.ename);


            view.setTag(holder);
            Log.i("vt", "new view");
        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
            holder = (ViewHolder) view.getTag();
            Log.i("vt", "recycle view");
        }

        HashMap<String, String> obj = data.get(position);

        holder.me.setText(obj.get("nm"));



        return view;


    }



}