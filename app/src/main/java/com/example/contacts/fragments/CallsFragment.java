package com.example.contacts.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.contacts.R;
import com.example.contacts.adapters.CallsRvAdapter;
import com.example.contacts.models.ModelCalls;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CallsFragment extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_calls, container, false);

        recyclerView = view.findViewById(R.id.recyclerCalls);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        CallsRvAdapter adapter = new CallsRvAdapter(getActivity(), getCallLogs());
        recyclerView.setAdapter(adapter);

       return view;
    }


    private List<ModelCalls> getCallLogs(){
        List<ModelCalls> list = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CALL_LOG}, 1);
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, 1);

        }
        Cursor cursor = getActivity().getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null, null, null, CallLog.Calls.DATE);

        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        int dateIndex = cursor.getColumnIndex(CallLog.Calls.DATE);

      cursor.moveToFirst();
      while (cursor.moveToNext()){
          String month, year, day;
          Date date = new Date(Long.valueOf(cursor.getString(dateIndex)));

          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE");
          month = (String) simpleDateFormat.format(date);

          simpleDateFormat = new SimpleDateFormat("MMMM");
          day = (String) simpleDateFormat.format(date);

          simpleDateFormat = new SimpleDateFormat("YYYY");
          year = (String) simpleDateFormat.format(date);



          list.add(new ModelCalls(cursor.getString(number), cursor.getString(duration),
                  day + " " + month + " " + year));
      }

        return list;
    }

}
