package com.example.resume.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.resume.R;
import com.example.resume.shop.models.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> list;

    public MyAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Product> getList() {
        return list;
    }

    public void setList(ArrayList<Product> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View result;
        LayoutInflater inflater=LayoutInflater.from(context);
        result = inflater.inflate(R.layout.listviewitems,null,false);
        TextView tvtitle=result.findViewById(R.id.tvproducttitle);
        TextView tvprice=result.findViewById(R.id.tvproductprice);
        ImageView img=result.findViewById(R.id.productimage);

        tvtitle.setText(list.get(position).getTitle());
        tvprice.setText(String.valueOf(list.get(position).getPrice()));
        Picasso.get().load(list.get(position).getImgresource()).into(img);
        return result;
    }
}
