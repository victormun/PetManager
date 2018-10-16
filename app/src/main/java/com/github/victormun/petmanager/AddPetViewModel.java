package com.github.victormun.petmanager;
import com.github.victormun.petmanager.database.AppDatabase;
import com.github.victormun.petmanager.database.PetEntry;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class AddPetViewModel extends ViewModel {

    private LiveData<PetEntry> pet;

    public AddPetViewModel(AppDatabase database, int petId) {
        pet = database.petDao().loadPetById(petId);
    }

    public LiveData<PetEntry> getPet() {
        return pet;
    }
}
