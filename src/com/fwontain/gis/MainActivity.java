package com.fwontain.gis;


import com.fwontain.gis.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.os.Build;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 
		ImageButton info= (ImageButton) findViewById(R.id.imageButton2);
        info.setOnClickListener(this);
        ImageButton list= (ImageButton) findViewById(R.id.imageButton4);
        list.setOnClickListener(this);
        ImageButton ib= (ImageButton) findViewById(R.id.imageButton1);
        ib.setOnClickListener(this);
        ImageButton exit= (ImageButton) findViewById(R.id.imageButton3);
        exit.setOnClickListener(this);

      
		
	}        


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()){
		
		case R.id.imageButton1:
			Intent message=new Intent(this,Maps.class);
			startActivity(message);
			break;
		case R.id.imageButton2:
			 new AlertDialog.Builder(this)  
			    .setTitle("Tentang Aplikasi")  
			    .setMessage("Aplikasi Google Maps untuk meberi informasi SMA NEGERI di kota Bogor")  
			    .setNeutralButton("Close", new DialogInterface.OnClickListener() {  
			       
			     @Override  
			     public void onClick(DialogInterface dlg, int sumthin) {  
			      // TODO Auto-generated method stub        
			     }  
			    })  
			    .show();  
			break;
			
		case R.id.imageButton3:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
	           builder.setMessage("Apakah Anda Benar-Benar ingin keluar?")
	           .setCancelable(false)
	           .setPositiveButton("Tidak",
	           new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog,
	           int id) {
	        	   dialog.cancel();	
	        	   }
	           })
	           .setNegativeButton("Ya",new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog,
	           int id) {
	           finish();

	           }
	           }).show();
			break;
		
		case R.id.imageButton4:
			Intent list=new Intent(this,List.class);
			startActivity(list);
			break;
	}

}
}