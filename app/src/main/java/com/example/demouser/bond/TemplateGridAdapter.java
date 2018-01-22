package com.example.demouser.bond;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by demouser on 1/22/18.
 */

public class TemplateGridAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList<TemplateMainItemObjects> data = new ArrayList();

    public TemplateGridAdapter(Context context, int layoutResourceId, ArrayList data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.text);
            System.out.println ("holder is null :" + holder != null);
            System.out.println ("holder is null :" + holder != null);
            holder.image = (ImageView) row.findViewById(R.id.image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        TemplateMainItemObjects item = data.get(position);
        holder.imageTitle.setText(item.getContent());
        //holder.image.setImageBitmap(item.getImageResource());
        return row;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }
}

