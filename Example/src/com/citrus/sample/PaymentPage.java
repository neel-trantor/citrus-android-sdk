/*
   Copyright 2014 Citrus Payment Solutions Pvt. Ltd.
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
     http://www.apache.org/licenses/LICENSE-2.0
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package com.citrus.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.citrus.sdk.CitrusActivity;
import com.citrus.sdk.Constants;
import com.citrus.sdk.PaymentParams;
import com.citrus.sdk.TransactionResponse;
import com.citrus.sdk.classes.Amount;
import com.citrus.sdk.classes.CitrusException;
import com.citrus.sdk.classes.Month;
import com.citrus.sdk.classes.Year;
import com.citrus.sdk.payment.CreditCardOption;
import com.citrus.sdk.payment.NetbankingOption;
import com.citrus.sdk.payment.PaymentType;

import org.json.JSONObject;

public class PaymentPage extends Activity {
    public static final String BILL_URL = "https://salty-plateau-1529.herokuapp.com/billGenerator.sandbox.php?";

    Button cardpayment, tokenpayment, bankpay, walletpay, signin, getbalance;

    JSONObject customer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_page);

        cardpayment = (Button) this.findViewById(R.id.cardpayment);

        tokenpayment = (Button) this.findViewById(R.id.tokenpayment);

        bankpay = (Button) this.findViewById(R.id.bankpay);

        walletpay = (Button) this.findViewById(R.id.walletpay);

        signin = (Button) this.findViewById(R.id.signin);

        getbalance = (Button) this.findViewById(R.id.getbalance);

        customer = new JSONObject();

        cardpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Amount amount = new Amount("2.5");
                PaymentType paymentType = null;
                try {
                    paymentType = new PaymentType.PGPayment(amount, BILL_URL + "amount=" + amount.getValue());
                } catch (CitrusException e) {
                    e.printStackTrace();

                    Utils.showToast(PaymentPage.this, e.getMessage());
                }
                CreditCardOption creditCardOption = new CreditCardOption("My Debit Card", "6759649826438453", "123", Month.getMonth("04"), Year.getYear("19"));

                //Card card = new Card("6759649826438453", "", "", "", "Bruce Banner", "debit");

                PaymentParams paymentParams = PaymentParams.builder(amount, paymentType, creditCardOption)
                        .environment(PaymentParams.Environment.SANDBOX)
                        .build();
                startCitrusActivity(paymentParams);
            }
        });

        tokenpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amount amount = new Amount("2.5");
                PaymentType paymentType = null;
                try {
                    paymentType = new PaymentType.PGPayment(amount, BILL_URL + "amount=" + amount.getValue());
                } catch (CitrusException e) {
                    e.printStackTrace();

                    Utils.showToast(PaymentPage.this, e.getMessage());
                }
                CreditCardOption creditCardOption = new CreditCardOption("f1b2508e360c345285d7917d4f4eb112", "123");

                PaymentParams paymentParams = PaymentParams.builder(amount, paymentType, creditCardOption)
                        .environment(PaymentParams.Environment.SANDBOX)
                        .build();
                startCitrusActivity(paymentParams);
            }
        });

        bankpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amount amount = new Amount("2.5");
                PaymentType paymentType = null;
                try {
                    paymentType = new PaymentType.PGPayment(amount, BILL_URL + "amount=" + amount.getValue());
                } catch (CitrusException e) {
                    e.printStackTrace();

                    Utils.showToast(PaymentPage.this, e.getMessage());
                }
                NetbankingOption netbankingOption = new NetbankingOption("ICICI Bank", "CID001");

                PaymentParams paymentParams = PaymentParams.builder(amount, paymentType, netbankingOption)
                        .environment(PaymentParams.Environment.SANDBOX)
                        .build();
                startCitrusActivity(paymentParams);
            }
        });
    }

    private void startCitrusActivity(PaymentParams paymentParams) {
        Intent intent = new Intent(PaymentPage.this, CitrusActivity.class);
        intent.putExtra(Constants.INTENT_EXTRA_PAYMENT_PARAMS, paymentParams);
        startActivityForResult(intent, Constants.REQUEST_CODE_PAYMENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TransactionResponse transactionResponse = data.getParcelableExtra(Constants.INTENT_EXTRA_TRANSACTION_RESPONSE);
        if (transactionResponse != null) {
            //Log.d("Citrus", "transactionResponse :: " + transactionResponse.toString());
            //    Logger.d("transactionResponse ::" +transactionResponse.toString());
        }
    }
}