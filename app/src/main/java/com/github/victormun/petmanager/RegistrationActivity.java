package com.github.victormun.petmanager;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity {

    // Calendar constants
    private static final Calendar CALENDAR = Calendar.getInstance();
    private static final int CURRENT_MONTH = CALENDAR.get(Calendar.MONTH);
    private static final int CURRENT_DAY = CALENDAR.get(Calendar.DAY_OF_MONTH);
    private static final int CURRENT_YEAR = CALENDAR.get(Calendar.YEAR);

    private static final int PET_IMAGES_NUMBER = 3;
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST = 12;
    private static final int OPEN_DOCUMENT_CODE = 2;

    @BindView(R.id.register_image)
    CircularImageView petImageView;

    @BindView(R.id.register_name_entry)
    TextInputEditText petNameEditText;

    @BindView(R.id.register_type_entry)
    Spinner petTypeSpinner;

    @BindView(R.id.register_breed_entry)
    TextInputEditText petBreedEditText;

    @BindView(R.id.register_birthday_entry)
    MaterialButton petBirthdayButton;

    @BindView(R.id.register_register_pet_button)
    FloatingActionButton petRegisterButton;
    private Date mChosenDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        setRandomPetImage();
        initListeners();

    }

    // Initializes all the listeners in the activity
    private void initListeners() {
        petBirthdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate();
            }
        });
        petRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIfFieldsComply()) {
                    Toast.makeText(RegistrationActivity.this, "All correct!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        petImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    checkPermissions();
            }
        });
    }

    private boolean checkIfFieldsComply() {
        boolean complies = true;
        if (!checkNameField()) {
            petNameEditText.setError("Please enter your Pet Name");
            complies = false;
        }
        if (!checkBreedField()) {
            petBreedEditText.setError("Please enter your Pet Breed");
            complies = false;
        }
        if (!checkBirthDate()) {
            Toast.makeText(this, "Please enter a valid BirthDate", Toast.LENGTH_SHORT).show();
            complies = false;
        }
        return complies;
    }

    private boolean checkBirthDate() {
        return mChosenDate != null;
    }

    private boolean checkBreedField() {
        return petBreedEditText.getText() != null && !petBreedEditText.getText().toString().isEmpty();
    }

    private boolean checkNameField() {
        return petNameEditText.getText() != null && !petNameEditText.getText().toString().isEmpty();
    }

    private void getDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar c = Calendar.getInstance();
                c.set(year, month, dayOfMonth, 0, 0);
                mChosenDate = c.getTime();
                petBirthdayButton.setText(formattedDate(mChosenDate));
            }
        }, CURRENT_YEAR, CURRENT_MONTH, CURRENT_DAY);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private String formattedDate(Date date) {
        SimpleDateFormat formatter;
        Locale locale = getResources().getConfiguration().locale;
        switch (locale.getLanguage()) {
            case "en-rus":
                formatter = new SimpleDateFormat("MM/dd/yyyy", locale);
                break;
            case "es":
                formatter = new SimpleDateFormat("dd/MM/yyyy", locale);
                break;
            default:
                formatter = new SimpleDateFormat("dd/MM/yyyy", locale);
                break;
        }
        return formatter.format(date);
    }

    // Sets a random image for the registration
    private void setRandomPetImage() {
        Random random = new Random();
        int randomNum = random.nextInt(PET_IMAGES_NUMBER) + 1;
        switch (randomNum) {
            case 1:
                petImageView.setImageResource(R.drawable.registration_cat);
                break;
            case 2:
                petImageView.setImageResource(R.drawable.registration_dog_01);
                break;
            case 3:
                petImageView.setImageResource(R.drawable.registration_dog_02);
                break;
        }
    }

    // Permissions
    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PermissionChecker.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setMessage(getResources().getString(R.string.gallery_permissions_message))
                        .setTitle(getResources().getString(R.string.permissions_title));
                builder.setPositiveButton(getResources().getString(R.string.permissions_ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ActivityCompat.requestPermissions(RegistrationActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                WRITE_EXTERNAL_STORAGE_REQUEST);
                    }
                });
                builder.setNegativeButton(getResources().getString(R.string.permissions_deny), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        WRITE_EXTERNAL_STORAGE_REQUEST);
            }

        } else {
            getImageFromGallery();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int result : grantResults) {
            if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    && requestCode == WRITE_EXTERNAL_STORAGE_REQUEST
                    && !(result == PermissionChecker.PERMISSION_GRANTED)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setMessage(getResources().getString(R.string.gallery_permissions_always_message))
                        .setTitle(getResources().getString(R.string.permissions_always_title));
                builder.setPositiveButton(getResources().getString(R.string.permissions_always_ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        RegistrationActivity.this.startActivity(intent);
                    }
                });
                builder.setNegativeButton(getResources().getString(R.string.permissions_deny), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

    }

    private void getImageFromGallery(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, OPEN_DOCUMENT_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == OPEN_DOCUMENT_CODE && resultCode == RESULT_OK) {
            if (resultData != null) {
                petImageView.setImageURI(resultData.getData());
            }
        }
    }
}
