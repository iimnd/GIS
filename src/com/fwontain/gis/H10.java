package com.fwontain.gis;



import com.fwontain.gis.Profil10;

import com.fwontain.gis.Fasil10;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class H10 extends FragmentPagerAdapter {
	
 
    public H10(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
            return new Profil10();
        case 1:
            // Games fragment activity
            return new Fasil10();
        
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }
 
}

