package com.plcoding.instagramui.saveplace.fragment.contactList;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.plcoding.instagramui.saveplace.R;
import com.plcoding.instagramui.saveplace.data.contactViewModel.ContactViewModel;
import com.plcoding.instagramui.saveplace.data.db.entities.ContactItem;

import java.util.List;

public class CallContactItemAdapter extends RecyclerView.Adapter<CallContactItemAdapter.ContactViewHolder> {

    private SwitchCompat Switch;
    public List<ContactItem> items;
    private ContactViewModel viewModel;
    private onItemClickListener mListener;
    public Boolean mode ;

    public CallContactItemAdapter(SwitchCompat mSwitch , List<ContactItem> Items, ContactViewModel viewmodel){

        Switch =mSwitch;
        items = Items;
        viewModel =viewmodel;
        mode = false;
    }

    public interface onItemClickListener{
        void onItemClick(String phoneNumber);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        mListener = listener;
    }




    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.call_contact_item,parent,false);
        return new ContactViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        mode = Switch.isChecked() == true;

        ContactItem curContactItem = items.get(position);
        TextView tvId =holder.itemView.findViewById(R.id.tv_Id);
        TextView tvName =holder.itemView.findViewById(R.id.tv_Name);
        TextView tvPhone =holder.itemView.findViewById(R.id.tv_Phone);

        tvId.setText((position+1));
        tvName.setText(curContactItem.name);
        tvPhone.setText(curContactItem.phoneNumber);


        if(mode) {
            tvName.setTextColor(Color.parseColor("#000000"));
            tvPhone.setTextColor(Color.parseColor("#000000"));
        }else{
            tvName.setTextColor(Color.parseColor("#ffffff"));
            tvPhone.setTextColor(Color.parseColor("#ffffff"));
        }

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{

        public ContactViewHolder(View itemView, CallContactItemAdapter.onItemClickListener listener) {
            super(itemView);
            TextView contactName = itemView.findViewById(R.id.tv_Name);


            itemView.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                public final void onClick(View it) {

                    String name = contactName.getText().toString();
                    int contactId = viewModel.getIdByName(name);
                    String contactPhoneNumber =viewModel.getItemPhoneNumberById(contactId);
                    listener.onItemClick(contactPhoneNumber);
                }
            }));


        }
    }


}
