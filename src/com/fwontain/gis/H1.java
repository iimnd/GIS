package com.fwontain.gis;



import com.fwontain.gis.Profil1;
import com.fwontain.gis.Fasil1;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class H1 extends FragmentPagerAdapter {
	
 
    public H1(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
            return new Profil1();
        case 1:
            // Games fragment activity
            return new Fasil1();
        
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }
 
}

