package com.example.admin.myapplication.fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.adapter.PaymentListAdapter;
import com.example.admin.myapplication.database.DatabaseAccess;
import com.example.admin.myapplication.objects.payment;

import java.util.ArrayList;


public class Fragment_payMent extends Fragment {

    View viewStore;
    Context mContext;
    RecyclerView recyclerPayment;
    PaymentListAdapter adapter;
    ArrayList<payment> arrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewStore = inflater.inflate(R.layout.activity_fragment_payments, container, false);
        mContext = container.getContext();
        arrayList = new ArrayList<>();

        recyclerPayment = viewStore.findViewById(R.id.recyclerPayment);
        addView();
        return viewStore;
    }

    public void addView(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(mContext);

        databaseAccess.open();

        Cursor cursor = databaseAccess.getPaymentData();

        if(cursor.getCount() == 0){
            Toast.makeText(mContext, "No data to show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                arrayList.add(new payment(cursor.getString(0), cursor.getInt(1), cursor.getInt(2),cursor.getString(3), cursor.getFloat(4)));
            }

            adapter = new PaymentListAdapter(mContext , arrayList);

            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            recyclerPayment.setLayoutManager(layoutManager);
            recyclerPayment.setAdapter(adapter);
        }
    }


}
