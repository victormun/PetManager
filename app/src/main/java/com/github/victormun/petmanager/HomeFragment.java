package com.github.victormun.petmanager;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.github.victormun.petmanager.RegistrationActivity.BIRTHDATE_KEY;
import static com.github.victormun.petmanager.RegistrationActivity.BREED_KEY;
import static com.github.victormun.petmanager.RegistrationActivity.IMAGE_KEY;
import static com.github.victormun.petmanager.RegistrationActivity.NAME_KEY;
import static com.github.victormun.petmanager.RegistrationActivity.TYPE_KEY;

public class HomeFragment extends Fragment {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.home_fragment, container,    false);
        ButterKnife.bind(this, rootView);

        Bundle incomingBundle = getArguments();
        if (incomingBundle!=null){
            petImageUri = Uri.parse(incomingBundle.getString(IMAGE_KEY));
            petName = incomingBundle.getString(NAME_KEY);
            petType = incomingBundle.getString(TYPE_KEY);
            petBreed = incomingBundle.getString(BREED_KEY);
            petBirthday = (Date) incomingBundle.getSerializable(BIRTHDATE_KEY);

            initViews();
        }
        return rootView;
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
