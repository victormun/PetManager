package com.github.victormun.petmanager;

import android.app.Application;

import com.github.victormun.petmanager.database.AppDatabase;
import com.github.victormun.petmanager.database.PetEntry;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<PetEntry>> pets;

    public MainViewModel(Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        pets = database.petDao().loadAllPets();
    }

    public LiveData<List<PetEntry>> getPets() {
        return pets;
    }
}
