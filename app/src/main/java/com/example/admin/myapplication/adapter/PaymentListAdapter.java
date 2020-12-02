package com.example.admin.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.model.payment;

import java.util.ArrayList;

public class PaymentListAdapter extends RecyclerView.Adapter<PaymentListAdapter.ViewHolder> {
    private ArrayList<payment> arrayList;
    private Context context;

    public PaymentListAdapter(Context context, ArrayList<payment> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_payment_recyclerview, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        payment payment = arrayList.get(position);
        holder.ID_HD.setText(payment.getPayment_id());
        holder.ID_KH.setText(Integer.toString(payment.getCustomer_id()));
        holder.ID_BOOK.setText(Integer.toString(payment.getBook_id()));
        holder.TIME_CREATE.setText(payment.getTime_create());
        holder.PAY_TOTAL.setText(Float.toString(payment.getPayment_total()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView ID_HD, ID_KH, ID_BOOK, TIME_CREATE, PAY_TOTAL;
        public ViewHolder(View itemView) {
            super(itemView);

            ID_HD = itemView.findViewById(R.id.ID_HD);
            ID_KH = itemView.findViewById(R.id.ID_KH);
            ID_BOOK = itemView.findViewById(R.id.ID_BOOK);
            TIME_CREATE = itemView.findViewById(R.id.TIME_CREATE);
            PAY_TOTAL = itemView.findViewById(R.id.PAY_TOTAL);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
