package com.citrus.sample;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.citrus.sdk.payment.CardOption;
import com.citrus.sdk.payment.CitrusCash;
import com.citrus.sdk.payment.NetbankingOption;
import com.citrus.sdk.payment.PaymentOption;

import java.util.ArrayList;

/**
 * Created by salil on 27/2/15.
 */
final class SavedOptionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<PaymentOption> mWalletList = null;
    Activity mActivity = null;

    public SavedOptionsAdapter(Activity activity,ArrayList<PaymentOption> walletList) {
        this.mActivity = activity;
        this.mWalletList = walletList;
    }

    public void setWalletList(ArrayList<PaymentOption> mWalletList) {
        this.mWalletList = mWalletList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(android.R.layout.simple_list_item_2, viewGroup, false);

        return new SavedOptionsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int index) {

        SavedOptionsViewHolder itemHolder = (SavedOptionsViewHolder) viewHolder;
        PaymentOption paymentOption = getItem(index);

        if (paymentOption != null) {

            if (paymentOption instanceof NetbankingOption) {
                itemHolder.paymentOptionName.setText(((NetbankingOption) paymentOption).getBankName());
                Drawable mNetBankIconDrawable = paymentOption.getOptionIcon(mActivity);
                itemHolder.paymentOptionName.setCompoundDrawablesWithIntrinsicBounds(mNetBankIconDrawable, null, null, null);

            } else if (paymentOption instanceof CardOption) {
                itemHolder.paymentOptionName.setText(((CardOption) paymentOption).getCardNumber());
                Drawable mCardIconDrawable = paymentOption.getOptionIcon(mActivity);
                itemHolder.paymentOptionName.setCompoundDrawablesWithIntrinsicBounds(mCardIconDrawable, null, null, null);
                itemHolder.paymentOptionBankName.setText(paymentOption.getName());
            }
            if (paymentOption instanceof CitrusCash) {
                itemHolder.paymentOptionName.setText(paymentOption.getName());
                itemHolder.paymentOptionBankName.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mWalletList != null) {
            return mWalletList.size();
        } else {
            return 0;
        }
    }

    /**
     * Called by RecyclerView when it stops observing this Adapter.
     *
     * @param recyclerView The RecyclerView instance which stopped observing this adapter.
     * @see #onAttachedToRecyclerView(RecyclerView)
     */
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);

        if (mWalletList != null) {
            mWalletList.clear();
        }

        mWalletList = null;
    }

    private PaymentOption getItem(int position) {
        if (mWalletList != null && position >= 0 && position < mWalletList.size()) {
            return mWalletList.get(position);
        }

        return null;
    }

    public static class SavedOptionsViewHolder extends RecyclerView.ViewHolder {
        final TextView paymentOptionName;
        final TextView paymentOptionBankName;

        public SavedOptionsViewHolder(View v) {
            super(v);
            paymentOptionName = (TextView) v.findViewById(android.R.id.text1);
            paymentOptionBankName = (TextView) v.findViewById(android.R.id.text2);
        }
    }
}