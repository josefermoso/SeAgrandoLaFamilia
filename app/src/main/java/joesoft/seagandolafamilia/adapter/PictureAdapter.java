package joesoft.seagandolafamilia.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import joesoft.seagandolafamilia.model.Picture;
import joesoft.seagandolafamilia.view.PictureFragment;


/**
 * Created by joe on 7/30/16.
 */
public class PictureAdapter extends FragmentStatePagerAdapter {



    private List<Picture> pictureList;
    private List<PictureFragment> pictureFragments;

    public PictureAdapter(FragmentManager fm) {
        super(fm);

        pictureList = new ArrayList<>();
        pictureFragments = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return  pictureFragments.get(position);
    }

    @Override
    public int getCount() {
        return pictureFragments.size();
    }

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;

        for (int i = 0; i<pictureList.size();i++){
            pictureFragments.add(PictureFragment.getNewPictureFragment(i));
        }
    }
}
