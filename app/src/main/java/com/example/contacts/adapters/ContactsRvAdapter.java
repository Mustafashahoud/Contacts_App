package com.example.contacts.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.contacts.R;
import com.example.contacts.models.ModelContacts;

import java.util.List;

public class ContactsRvAdapter extends RecyclerView.Adapter<ContactsRvAdapter.MyViewHolder> {

    LayoutInflater inflater;
    Context mContext;
    List<ModelContacts> modelContactsList;

    public ContactsRvAdapter(Context mContext, List<ModelContacts> modelContactsList) {
        this.inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        this.mContext = mContext;
        this.modelContactsList = modelContactsList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.item_contatc, viewGroup, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        ModelContacts modelContacts = modelContactsList.get(i);
        myViewHolder.setData(modelContacts);

    }



    @Override
    public int getItemCount() {
        return modelContactsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, number;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.contactName);
            number = itemView.findViewById(R.id.contactNumber);
        }
        public void setData(ModelContacts modelContacts) {
            name.setText(modelContacts.getName());
            number.setText(modelContacts.getNumber());
        }
    }
}
