package personcom.yl.contract.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by changlianjiuzhou on 17/2/16.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment>fragments;

    public FragmentAdapter(FragmentManager fm,List<Fragment>fragments) {
        super(fm);
        this.fragments=fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


}
