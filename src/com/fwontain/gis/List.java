package com.fwontain.gis;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class List extends Activity {
	protected static final ItemDetails ItemDetails = null;
	protected Object Object;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftarsekolah);
      
        
        ArrayList<ItemDetails> image_details = GetSearchResults();
        
        final ListView lv1 = (ListView) findViewById(R.id.listV_main);
        lv1.setAdapter(new ItemListBaseAdapter(this, image_details));
        
        lv1.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) { 
        	 
        		Intent i = null; 
        		if (position == 0){ 
        			i = new Intent(List.this,Sma1.class);
        			} 
        		else if(position == 1){ 
        			i = new Intent(List.this,Sma2.class); 
        			}
        		else if(position == 2){ 
        			i = new Intent(List.this,Sma3.class);
        			} 		
        		else if(position == 3){ 
        			i = new Intent(List.this,Sma4.class);
        			} 		
        		else if(position == 4){ 
        			i = new Intent(List.this,Sma5.class);
        			} 		
        		else if(position == 5){ 
        			i = new Intent(List.this,Sma6.class);
        			}
        		else if(position == 6){ 
        			i = new Intent(List.this,Sma7.class);		
        				} 
        		else if(position == 7){ 
        			i = new Intent(List.this,Sma8.class);		
        				} 
        		else if(position == 8){ 
        			i = new Intent(List.this,Sma9.class);		
        				} 
        		else if(position == 9){ 
        			i = new Intent(List.this,Sma10.class);		
        				} 
        		else
        		
        		{
        			finish();
        			} 
        		startActivity(i);      
        	}
        });
    }
    
    private ArrayList<ItemDetails> GetSearchResults(){
    	ArrayList<ItemDetails> results = new ArrayList<ItemDetails>();
    	
    	ItemDetails item_details = new ItemDetails();
    	item_details.setName("SMAN 1 BOGOR");
    	item_details.setItemDescription("Smansa");
    	item_details.setImageNumber(1);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("SMAN 2 BOGOR");
    	item_details.setItemDescription("Smanda");
    	item_details.setImageNumber(2);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("SMAN 3 BOGOR");
    	item_details.setItemDescription("Smanti");
    	item_details.setImageNumber(3);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("SMAN 4 BOGOR");
    	item_details.setItemDescription("Smanpat");
    	item_details.setImageNumber(4);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("SMAN 5 BOGOR");
    	item_details.setItemDescription("Smanli");
    	item_details.setImageNumber(5);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("SMAN 6 BOGOR");
    	item_details.setItemDescription("Smaugue");
    	item_details.setImageNumber(6);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("SMAN 7 BOGOR");
    	item_details.setItemDescription("Smunjuh");
    	item_details.setImageNumber(7);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("SMAN 8 BOGOR");
    	item_details.setItemDescription("Smanlapan");
    	item_details.setImageNumber(8);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("SMAN 9 BOGOR");
    	item_details.setItemDescription("Smunlan");
    	item_details.setImageNumber(9);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName("SMAN 10 BOGOR");
    	item_details.setItemDescription("Smauluh");
    	item_details.setImageNumber(10);
    	results.add(item_details);
    	
   

	return results;
	
	
}
}