package gipuzkoa.hondakinak;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class hMotaAdapter extends ArrayAdapter<hMota>{

    Context context; 
    hMota data[] = null;
	int layoutResourceId;
    
    public hMotaAdapter(Context context, int layoutResourceId,  hMota[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        hondakinHolder holder = null;
        
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new hondakinHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);  
            holder.txtOrdua = (TextView)row.findViewById(R.id.txtOrdua);
            holder.txtEguna = (TextView)row.findViewById(R.id.txtEguna);
            holder.dena = (LinearLayout)row.findViewById(R.id.dena);
            
            row.setTag(holder);
        }
        else
        {
            holder = (hondakinHolder)row.getTag();
        }
        
        hMota hm = data[position];
        holder.txtTitle.setText(hm._name);
        holder.imgIcon.setImageResource(hm._irudia);
        holder.txtEguna.setText(hm._eguna);
        holder.txtOrdua.setText(hm._ordua);
   


        final OnClickListener denaListener = new OnClickListener() {
            public void onClick(View v) {
            	Intent i = new Intent(context,hMotaHautatua.class);
				hMota hon = (hMota) data[position];
				i.putExtra("izena", (String) hon.get_name());
				i.putExtra("ordua", (String) hon.get_ordua());
				i.putExtra("eguna", (String) hon.get_eguna());
				i.putExtra("irudia", (int) hon.get_irudia());
				context.startActivity(i);
            }
        };
        holder.dena.setOnClickListener(denaListener); 
        
        final OnClickListener titListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent i = new Intent(context,hMotaHautatua.class);
        				hMota hon = (hMota) data[position];
        				i.putExtra("izena", (String) hon.get_name());
        				i.putExtra("ordua", (String) hon.get_ordua());
        				i.putExtra("eguna", (String) hon.get_eguna());
        				i.putExtra("irudia", (int) hon.get_irudia());
        				context.startActivity(i);
                    }
        };
        holder.txtTitle.setOnClickListener(titListener); 
        
     final OnClickListener egunaListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
         	Intent i = new Intent(context,hMotaHautatua.class);
				hMota hon = (hMota) data[position];
				i.putExtra("izena", (String) hon.get_name());
				i.putExtra("ordua", (String) hon.get_ordua());
				i.putExtra("eguna", (String) hon.get_eguna());
				i.putExtra("irudia", (int) hon.get_irudia());
				context.startActivity(i);
            }
        };
        holder.txtEguna.setOnClickListener(egunaListener);

        
        final OnClickListener orduaListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
             	Intent i = new Intent(context,hMotaHautatua.class);
				hMota hon = (hMota) data[position];
				i.putExtra("izena", (String) hon.get_name());
				i.putExtra("ordua", (String) hon.get_ordua());
				i.putExtra("eguna", (String) hon.get_eguna());
				i.putExtra("irudia", (int) hon.get_irudia());
				context.startActivity(i);
            }
       };        
        holder.txtOrdua.setOnClickListener(orduaListener);

        final OnClickListener assetListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
             	Intent i = new Intent(context,hMotaHautatua.class);
				hMota hon = (hMota) data[position];
				i.putExtra("izena", (String) hon.get_name());
				i.putExtra("ordua", (String) hon.get_ordua());
				i.putExtra("eguna", (String) hon.get_eguna());
				i.putExtra("irudia", (int) hon.get_irudia());
				context.startActivity(i);
            }
        };
        holder.imgIcon.setOnClickListener(assetListener);        
        return row;
        
    }

	static class hondakinHolder
    {
        public LinearLayout dena;
		ImageView imgIcon;
        TextView txtTitle;
        TextView txtEguna;
        TextView txtOrdua;
    }
}