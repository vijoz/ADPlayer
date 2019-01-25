package com.cocodecat.vijoz.adplayer.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cocodecat.vijoz.adplayer.base.BasePager;

import pub.devrel.easypermissions.EasyPermissions;

public class BaseFragment extends Fragment {
    private BasePager mBasePager;

    public BasePager getBasePager() {
        return mBasePager;
    }

    public void setBasePager(BasePager basePager) {
        mBasePager = basePager;
    }

    public static BaseFragment newInstance(BasePager basePager){
        BaseFragment baseFragment=new BaseFragment();
        baseFragment.setBasePager(basePager);
        return baseFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mBasePager.getRootView();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
