package com.example.notepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class noteadapter extends ArrayAdapter<notes> {


    public noteadapter(Context context, int resource, ArrayList<notes> objects) {
        super(context, resource, objects);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemss,null);

        }
        notes note = getItem(position);
        if(note!=null){
            TextView title = convertView.findViewById(R.id.list_title);
            TextView date = convertView.findViewById(R.id.list_date);
            TextView content = convertView.findViewById(R.id.list_content);
            title.setText(note.getmTitle());
            date.setText(note.dateandtime(getContext()));
            if(note.getmContent().length()>50) {
                content.setText(note.getmContent().substring(0,50));
            }
            else{
                content.setText(note.getmContent());
            }
        }
        return convertView;
    }
}
