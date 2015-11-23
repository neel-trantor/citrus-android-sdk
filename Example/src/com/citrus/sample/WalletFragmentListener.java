package com.citrus.sample;

import com.citrus.sdk.TransactionResponse;
import com.citrus.sdk.classes.Amount;
import com.citrus.sdk.classes.CashoutInfo;

/**
 * Created by salil on 3/6/15.
 */
public interface WalletFragmentListener {
    void onPaymentComplete(TransactionResponse transactionResponse);

    void onPaymentTypeSelected(Utils.PaymentType paymentType, Amount amount);

    void onPaymentTypeSelected(Utils.DPRequestType dpRequestType, Amount originalAmount, String couponCode, Amount alteredAmount);

    void onCashoutSelected(CashoutInfo cashoutInfo);

}
