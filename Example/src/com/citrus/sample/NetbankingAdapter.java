package com.citrus.sample;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.citrus.sdk.payment.NetbankingOption;

import java.util.ArrayList;

/**
 * Created by salil on 27/2/15.
 */
final class NetbankingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<NetbankingOption> mNetbankingOptionList = null;
    Activity mActivity = null;

    public NetbankingAdapter(Activity activity, ArrayList<NetbankingOption> netbankingOptionList) {
        this.mActivity = activity;
        this.mNetbankingOptionList = netbankingOptionList;
    }

    public void setNetbankingOptionList(ArrayList<NetbankingOption> netbankingOptionList) {
        this.mNetbankingOptionList = netbankingOptionList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(android.R.layout.simple_list_item_1, viewGroup, false);

        return new NetbankingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int index) {

        NetbankingViewHolder itemHolder = (NetbankingViewHolder) viewHolder;
        NetbankingOption netbankingOption = getItem(index);

        if (netbankingOption != null) {
            itemHolder.txtBankName.setText(netbankingOption.getBankName());
            Drawable mBankIconDrawable = netbankingOption.getOptionIcon(mActivity);
            itemHolder.txtBankName.setCompoundDrawablePadding(25);
            itemHolder.txtBankName.setCompoundDrawablesWithIntrinsicBounds(mBankIconDrawable,null,null,null);
        }
    }

    @Override
    public int getItemCount() {
        if (mNetbankingOptionList != null) {
            return mNetbankingOptionList.size();
        } else {
            return 0;
        }
    }

    /**
     * Called by RecyclerView when it stops observing this Adapter.
     *
     * @param recyclerView The RecyclerView instance which stopped observing this adapter.
     * @see #onAttachedToRecyclerView(android.support.v7.widget.RecyclerView)
     */
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);

        if (mNetbankingOptionList != null) {
            mNetbankingOptionList.clear();
        }

        mNetbankingOptionList = null;
    }

    private NetbankingOption getItem(int position) {
        if (mNetbankingOptionList != null && position >= 0 && position < mNetbankingOptionList.size()) {
            return mNetbankingOptionList.get(position);
        }

        return null;
    }

    public static class NetbankingViewHolder extends RecyclerView.ViewHolder {
        final TextView txtBankName;

        public NetbankingViewHolder(View v) {
            super(v);
            txtBankName = (TextView) v.findViewById(android.R.id.text1);
        }
    }
}