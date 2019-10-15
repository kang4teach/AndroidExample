package com.example.listviewexample;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class NameAdapter extends ArrayAdapter {
    private int layout;
    public NameAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Log.d("NameAdapter",String.valueOf(position));
        long startTime = System.nanoTime();// ns
        View view;
        // 1、 重复利用convertView
        if (convertView != null){
            view = convertView;
        }else {
            view = LayoutInflater.from(getContext()).inflate(this.layout,parent,false);
        }
        TextView firstTv = view.findViewById(R.id.textView1);
        TextView secondTv = view.findViewById(R.id.textView2);

        // view.setTag();
        NameClass nc = (NameClass)getItem(position);
        firstTv.setText(nc.getFirst());
        secondTv.setText(nc.getSecond());
        long endTime = System.nanoTime();
        Log.d("NameAdapter",String.valueOf((endTime-startTime)/(position + 1)));
        return view;
    }
}
