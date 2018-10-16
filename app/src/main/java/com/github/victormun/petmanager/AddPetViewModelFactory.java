package com.github.victormun.petmanager;

import com.github.victormun.petmanager.database.AppDatabase;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AddPetViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final AppDatabase mDb;
    private final int mPetId;

    public AddPetViewModelFactory(AppDatabase database, int petId) {
        mDb = database;
        mPetId = petId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new AddPetViewModel(mDb, mPetId);
    }
}
