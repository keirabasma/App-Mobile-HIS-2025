package com.example.tp04exo02;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class customAdapter extends BaseAdapter {
    ArrayList<Persone> listIt;

    customAdapter(ArrayList<Persone> listIt) {
        this.listIt = listIt;
    }

    @Override
    public int getCount() {
        return listIt.size(); // عدد العناصر
    }

    @Override
    public Object getItem(int position) {
        return listIt.get(position); // العنصر في هذا الموضع
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // أهم دالة: تحدد شكل العنصر في القائمة
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // إذا كانت convertView فارغة، نقوم بإنشائها
        if (convertView == null) {
            LayoutInflater inflat = LayoutInflater.from(parent.getContext());
            convertView = inflat.inflate(R.layout.personeview, parent, false); // تصميم العنصر

            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.textViewname);
            holder.phone = convertView.findViewById(R.id.textViewphone);

            // حفظ الـ ViewHolder في convertView
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag(); // استرجاع الـ ViewHolder
        }

        // الحصول على العنصر المناسب في القائمة
        Persone c = listIt.get(position);
        holder.name.setText(c.name + " " + c.Lastname);
        holder.phone.setText(c.Phone);

        return convertView;
    }

    // ViewHolder لتحسين الأداء
    static class ViewHolder {
        TextView name;
        TextView phone;
    }
}
