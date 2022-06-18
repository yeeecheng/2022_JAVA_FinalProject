package com.plcoding.instagramui.saveplace.fragment.contactList;

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

public class ContactItemAdapter extends RecyclerView.Adapter<ContactItemAdapter.ContactViewHolder> {

    private SwitchCompat Switch;
    public List<ContactItem> items;
    private ContactViewModel viewModel;
    private  onItemClickListener mListener;
    public Boolean mode ;

    public ContactItemAdapter(SwitchCompat mSwitch , List<ContactItem> Items, ContactViewModel viewmodel){

        Switch =mSwitch;
        items = Items;
        viewModel =viewmodel;
        mode = false;
    }

    public interface onItemClickListener{
        void onItemClick(int id);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        mListener = listener;
    }


    @NonNull
    @Override
    public ContactItemAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item,parent,false);
        return new ContactViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactItemAdapter.ContactViewHolder holder, int position) {
        mode = Switch.isChecked() == true;

        ContactItem curContactItem = items.get(position);
        TextView tvId =holder.itemView.findViewById(R.id.tv_Id);
        TextView tvName =holder.itemView.findViewById(R.id.tv_Name);
        TextView tvPhone =holder.itemView.findViewById(R.id.tv_Phone);
        TextView ivDelete =holder.itemView.findViewById(R.id.iv_Delete);


        tvId.setText((position+1));
        tvName.setText(curContactItem.name);
        tvPhone.setText(curContactItem.phoneNumber);
        ivDelete.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                viewModel.delete(curContactItem);

            }
        }));


    }


    @Override
    public int getItemCount() {
        return items.size();
    }
    public class ContactViewHolder extends RecyclerView.ViewHolder{
        public ContactViewHolder(View itemView, ContactItemAdapter.onItemClickListener listener) {
            super(itemView);
            TextView contactName = itemView.findViewById(R.id.tv_Name);


            itemView.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                public final void onClick(View it) {

                    String name = contactName.getText().toString();
                    int contactId = viewModel.getIdByName(name);
                    listener.onItemClick(contactId);
                }
            }));


        }
    }


}
