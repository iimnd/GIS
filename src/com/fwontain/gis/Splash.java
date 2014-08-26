package com.fwontain.gis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class Splash extends Activity{
   
   private static int progress = 0;
   private int status = 0;
   ProgressBar progressBar;
   Handler handler = new Handler();
   
   public void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      requestWindowFeature(Window.FEATURE_NO_TITLE);
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                              WindowManager.LayoutParams.FLAG_FULLSCREEN);

      setContentView(R.layout.splash);
      
      progressBar = (ProgressBar) findViewById(R.id.Progress); 
    		  //findViewById(R.id.progg);
      
      new Thread(new Runnable() {
         
         @Override
         public void run() {
            while(status < 100){
               status = loading();
               handler.post(new Runnable() {
                  
                  @Override
                  public void run() {
                  progressBar.setProgress(status);   
                  }
               });
               
            }
            handler.post(new Runnable() {
               
               @Override
               public void run() {
                  Intent intent = new Intent(Splash.this, MainActivity.class);
                  Splash.this.startActivity(intent);
                  Splash.this.finish();
                  
                  
               }

               
            });
         }
         
         public int loading(){
            try{
               Thread.sleep(50);
            }catch(InterruptedException ie){
               ie.printStackTrace();
            }
            return ++progress;
         }
      }).start();
      
   }

}

