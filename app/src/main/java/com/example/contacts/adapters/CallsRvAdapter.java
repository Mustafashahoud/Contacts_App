package com.example.contacts.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.contacts.R;
import com.example.contacts.models.ModelCalls;

import java.util.List;

public class CallsRvAdapter extends RecyclerView.Adapter<CallsRvAdapter.MyViewHolder> {
    LayoutInflater inflater;
    List<ModelCalls> mCallModelList;
    Context mContext;

    public CallsRvAdapter(Context context, List<ModelCalls> list) {
       mContext = context;
        mCallModelList = list;
        inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_calls, viewGroup,  false);
        MyViewHolder vh = new MyViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        ModelCalls mModelCalls = mCallModelList.get(i);
        holder.setData(mModelCalls, i);
    }

    @Override
    public int getItemCount() {
        return mCallModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvDuration, tvDate;
        Button btnName, btnCall;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.contactNumber);
            tvDuration = itemView.findViewById(R.id.callDuration);
            tvDate = itemView.findViewById(R.id.callDate);
            btnName = itemView.findViewById(R.id.btnName);
            btnCall = itemView.findViewById(R.id.btnCall);

        }

        public void setData(ModelCalls mCall, int position) {
            this.tvName.setText(mCall.getName());
            this.tvDuration.setText(mCall.getDuration());
            this.tvDate.setText(mCall.getDate());
        }
    }
}
