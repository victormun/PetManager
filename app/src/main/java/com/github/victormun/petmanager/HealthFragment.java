package com.github.victormun.petmanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HealthFragment extends Fragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.health_fragment, container, false);
        //ButterKnife.bind(this, rootView);
        //mDb = AppDatabase.getInstance(rootView.getContext().getApplicationContext());
        //setupViewModel();
        return rootView;
    }
}
