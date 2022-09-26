package com.example.presensimanualapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SampleAdaptor extends FragmentStateAdapter {
    public SampleAdaptor(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    //menampilkan tab
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            FirstFragment fragment1 = new FirstFragment();
            return fragment1;
        }else{
            SecondFragment fragment2 = new SecondFragment();
            return fragment2;
        }
    }

    //jumlah fragment yang digunakan
    @Override
    public int getItemCount() {
        return 2;
    }
}
