package com.fwontain.gis;

import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemListBaseAdapter extends BaseAdapter {
	private static ArrayList<ItemDetails> itemDetailsrrayList;
	
	private Integer[] imgid = {
			R.drawable.sma1,
			R.drawable.sma2,
			R.drawable.sma3,
			R.drawable.sma4,
			R.drawable.sma5,
			R.drawable.sma6,
			R.drawable.sma7,
			R.drawable.sma8,
			R.drawable.sma9,
			R.drawable.sma10
			};
	
	private LayoutInflater l_Inflater;

	public ItemListBaseAdapter(Context context, ArrayList<ItemDetails> results) {
		itemDetailsrrayList = results;
		l_Inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return itemDetailsrrayList.size();
	}

	public Object getItem(int position) {
		return itemDetailsrrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.item, null);
			holder = new ViewHolder();
			holder.txt_itemName = (TextView) convertView.findViewById(R.id.item_nama);
			holder.txt_itemDescription = (TextView) convertView.findViewById(R.id.item_keterangan);
			holder.itemImage = (ImageView) convertView.findViewById(R.id.item_icon);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.txt_itemName.setText(itemDetailsrrayList.get(position).getName());
		holder.txt_itemDescription.setText(itemDetailsrrayList.get(position).getItemDescription());
		holder.itemImage.setImageResource(imgid[itemDetailsrrayList.get(position).getImageNumber() - 1]);
//		imageLoader.DisplayImage("http://192.168.1.28:8082/ANDROID/images/BEVE.jpeg", holder.itemImage);

		return convertView;
	}

	static class ViewHolder {
		TextView txt_itemName;
		TextView txt_itemDescription;
		TextView txt_itemPrice;
		ImageView itemImage;
	}
}
