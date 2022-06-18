package com.plcoding.instagramui.saveplace.data.contactViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.plcoding.instagramui.saveplace.data.repository.ContactRepository;
import kotlin.Suppress;

@Suppress(names = "UNCHECKED_CAST")
public class ContactViewModelFactor extends ViewModelProvider.NewInstanceFactory{
    private  ContactRepository repository;
    public ContactViewModelFactor(ContactRepository rep){
        repository=rep;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        return (T) new ContactViewModel(repository);
    }
}
