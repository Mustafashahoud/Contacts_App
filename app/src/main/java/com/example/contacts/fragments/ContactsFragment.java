package com.example.contacts.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.contacts.R;
import com.example.contacts.adapters.ContactsRvAdapter;
import com.example.contacts.models.ModelContacts;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_contacts, container, false);

       recyclerView = view.findViewById(R.id.recyclerContacts);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayout.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        ContactsRvAdapter adapter = new ContactsRvAdapter(getActivity(), getContacts());
        recyclerView.setAdapter(adapter);

       return view;
    }

    private List<ModelContacts> getContacts(){
        List<ModelContacts> list = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CALL_LOG}, 1);
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, 1);
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_CONTACTS}, 1);

        }


        Cursor cursor = getContext().getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, ContactsContract.Contacts.DISPLAY_NAME);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            String number;
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            if (cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER) == -1){
                 number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER) + 1);
            }else
            number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            list.add(new ModelContacts(name, number));
        }
        return list;
    }
}
