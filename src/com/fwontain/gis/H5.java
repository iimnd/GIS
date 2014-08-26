package com.fwontain.gis;



import com.fwontain.gis.Profil5;

import com.fwontain.gis.Fasil5;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class H5 extends FragmentPagerAdapter {
	
 
    public H5(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
            return new Profil5();
        case 1:
            // Games fragment activity
            return new Fasil5();
        
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }
 
}

