package com.plcoding.instagramui.saveplace.data.contactViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.plcoding.instagramui.saveplace.data.db.entities.ContactItem;
import com.plcoding.instagramui.saveplace.data.repository.ContactRepository;

import java.util.List;



public class ContactViewModel extends ViewModel {

    private  ContactRepository repository;

    public ContactViewModel(ContactRepository rep){
        repository = rep ;
    }
    public void upsert(ContactItem item) {

        repository.upsert(item);
    }

    public void delete(ContactItem item) {
        repository.delete(item);
    }

    public void update(ContactItem item) {
        repository.update(item);
    }

    public LiveData<List<ContactItem>> getAllContactItems() {
        return repository.getAllContactItems();
    }

    public int getIdByName (String name ) {
        return repository.getIdByName(name);
    }

    public List<String> getContactPhoneNumber(){
        return repository.getContactPhoneNumber();
    }

    public String getItemNameById(int id ){
        return repository.getItemNameById(id);
    }

    public String getItemPhoneNumberById(int id){
        return repository.getItemPhoneNumberById(id);
    }


}
