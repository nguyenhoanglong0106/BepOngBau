package com.example.bepongbau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bepongbau.Model.ItemMenu;
import com.example.bepongbau.R;

import java.util.List;

public class MenuApdater extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ItemMenu> list;

    public MenuApdater(Context context, int layout, List<ItemMenu> list) {
        this.context = context;
        this.layout = layout;
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
        return 0;
    }
    public class  ViewHolder{
        TextView txtView;
        ImageView img;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout,null);
            viewHolder = new ViewHolder();
            viewHolder.txtView = convertView.findViewById(R.id.txtTextViewMenu);
            viewHolder.img = convertView.findViewById(R.id.imgItemMenu);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtView.setText(list.get(position).itemName);
        viewHolder.img.setImageResource(list.get(position).icon);
        return convertView;
    }
}
