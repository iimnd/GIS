package com.fwontain.gis;



import com.fwontain.gis.Profil9;

import com.fwontain.gis.Fasil9;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class H9 extends FragmentPagerAdapter {
	
 
    public H9(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
            return new Profil9();
        case 1:
            // Games fragment activity
            return new Fasil9();
        
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }
 
}

