package com.fwontain.gis;



import com.fwontain.gis.Profil4;

import com.fwontain.gis.Fasil4;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class H4 extends FragmentPagerAdapter {
	
 
    public H4(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
            return new Profil4();
        case 1:
            // Games fragment activity
            return new Fasil4();
        
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }
 
}

