package com.example.resume.download.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.resume.R;
import com.example.resume.download.models.Music;

import java.util.ArrayList;

public class MusicItemsAdapter extends BaseAdapter {
    ArrayList<Music> list;
    Context context;

    public ArrayList<Music> getList() {
        return list;
    }

    public void setList(ArrayList<Music> list) {
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
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
        LayoutInflater inflater= LayoutInflater.from(this.context);
        result = inflater.inflate(R.layout.music_info,parent,false);

        TextView tvname = result.findViewById(R.id.tvMusicName);
        ImageView img = result.findViewById(R.id.musicImage);

        tvname.setText(list.get(position).getName());
        img.setImageResource(list.get(position).getImageResource());

        return result;



    }
}
