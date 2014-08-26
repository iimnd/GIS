package com.fwontain.gis;


import com.fwontain.gis.R;
import com.fwontain.gis.H9;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

@SuppressLint("NewApi")
public class Sma9 extends FragmentActivity implements ActionBar.TabListener {
	
	private ViewPager viewPager;
    private H9 mAdapter;
    private android.app.ActionBar actionBar;
    // Tab titles
    private String[] tabs = { "Profil", "Fasilitas" };


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sma9);
        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new H9(getSupportFragmentManager());
 
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
 
        // Adding Tabs
        for (String tab_name : tabs) {
        	actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener((TabListener) this));
        	
        	/**
        	 * on swiping the viewpager make respective tab selected
        	 * */
        	viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        	 
        	    @Override
        	    public void onPageSelected(int position) {
        	        // on changing the page
        	        // make respected tab selected
        	        actionBar.setSelectedNavigationItem(position);
        	    }
        	 
        	    @Override
        	    public void onPageScrolled(int arg0, float arg1, int arg2) {
        	    }
        	 
        	    @Override
        	    public void onPageScrollStateChanged(int arg0) {
        	    }
        	});


        }


	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		// on tab selected
        // show respected fragment view
       

		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		 viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
}
