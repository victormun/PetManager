package com.github.victormun.petmanager;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.victormun.petmanager.database.AppDatabase;
import com.github.victormun.petmanager.database.PetEntry;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    private static final String TAG = HomeFragment.class.getSimpleName();

    @BindView(R.id.home_image)
    CircularImageView petImageView;

    @BindView(R.id.home_title)
    TextView petNameTextView;

    @BindView(R.id.home_information_birthday)
    TextView petBirthdayView;

    @BindView(R.id.home_information_breed)
    TextView petBreedView;

    @BindView(R.id.home_information_type)
    TextView petTypeView;

    private Uri petImageUri;
    private String petName;
    private String petType;
    private Date petBirthday;
    private String petBreed;
    private View rootView;

    private AppDatabase mDb;

    private PetEntry currentPet;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, rootView);
        mDb = AppDatabase.getInstance(rootView.getContext().getApplicationContext());
        setupViewModel();
        return rootView;
    }

    private void setupViewModel() {
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getPets().observe(this, new Observer<List<PetEntry>>() {
            @Override
            public void onChanged(@Nullable List<PetEntry> petEntries) {
                if (petEntries == null || petEntries.isEmpty()) {
                    Log.e(TAG, "Couldn't find any pets");
                } else {
                    currentPet = petEntries.get(0);
                    extractData(currentPet);
                    initViews();
                }
            }
        });
    }

    private void extractData(PetEntry currentPet) {
        petName = currentPet.getName();
        petType = currentPet.getType();
        petBreed = currentPet.getBreed();
        petImageUri = Uri.parse(currentPet.getUrl());
        petBirthday = currentPet.getBirthdate();
    }

    private void initViews() {
        petImageView.setImageURI(petImageUri);
        petNameTextView.setText(petName);
        petTypeView.setText(petType);
        petBreedView.setText(petBreed);

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy", rootView.getContext().getResources().getConfiguration().getLocales().get(0));
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        petBirthdayView.setText(reportDate);
    }
}
