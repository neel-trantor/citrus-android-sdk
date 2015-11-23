/*
 *
 *    Copyright 2014 Citrus Payment Solutions Pvt. Ltd.
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 * /
 */

package com.citrus.sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.citrus.sdk.Callback;
import com.citrus.sdk.CitrusClient;
import com.citrus.sdk.classes.Amount;
import com.citrus.sdk.classes.CashoutInfo;
import com.citrus.sdk.response.CitrusError;
import com.citrus.sdk.response.CitrusResponse;
import com.citrus.sdk.response.PaymentResponse;

import static com.citrus.sample.Utils.PaymentType.CITRUS_CASH;
import static com.citrus.sample.Utils.PaymentType.LOAD_MONEY;
import static com.citrus.sample.Utils.PaymentType.PG_PAYMENT;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WalletFragmentListener} interface
 * to handle interaction events.
 * Use the {@link WalletPaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WalletPaymentFragment extends Fragment implements View.OnClickListener {

    private WalletFragmentListener mListener;
    private CitrusClient mCitrusClient = null;
    private Context mContext = null;

    private Button btnGetBalance = null;
    private Button btnLoadMoney = null;
    private Button btnPayUsingCash = null;
    private Button btnPGPayment = null;
    private Button btnGetWithdrawInfo = null;
    private Button btnWithdraw = null;
    private Button btnSendMoney = null;
    private Button btnPerformDP = null;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WalletPaymentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WalletPaymentFragment newInstance() {
        WalletPaymentFragment fragment = new WalletPaymentFragment();
        return fragment;
    }

    public WalletPaymentFragment() {
        // Required empty public constructor


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();
        mCitrusClient = CitrusClient.getInstance(mContext.getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_wallet_payment, container, false);


        btnGetBalance = (Button) rootView.findViewById(R.id.btn_get_balance);
        btnLoadMoney = (Button) rootView.findViewById(R.id.btn_load_money);
        btnPayUsingCash = (Button) rootView.findViewById(R.id.btn_pay_using_cash);
        btnPGPayment = (Button) rootView.findViewById(R.id.btn_pg_payment);
        btnWithdraw = (Button) rootView.findViewById(R.id.btn_cashout);
        btnGetWithdrawInfo = (Button) rootView.findViewById(R.id.btn_get_cashout_info);
        btnSendMoney = (Button) rootView.findViewById(R.id.btn_send_money);
        btnPerformDP = (Button) rootView.findViewById(R.id.btn_perform_dp);

        btnGetBalance.setOnClickListener(this);
        btnLoadMoney.setOnClickListener(this);
        btnPayUsingCash.setOnClickListener(this);
        btnPGPayment.setOnClickListener(this);
        btnGetWithdrawInfo.setOnClickListener(this);
        btnWithdraw.setOnClickListener(this);
        btnSendMoney.setOnClickListener(this);
        btnPerformDP.setOnClickListener(this);
        btnPerformDP.setVisibility(View.GONE);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (WalletFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement WalletFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_get_balance:
                getBalance();
                break;
            case R.id.btn_load_money:
                loadMoney();
                break;
            case R.id.btn_pay_using_cash:
                payUsingCash();
                break;
            case R.id.btn_pg_payment:
                pgPayment();
                break;
            case R.id.btn_cashout:
                cashout();
                break;
            case R.id.btn_get_cashout_info:
                getCashoutInfo();
                break;
            case R.id.btn_send_money:
                sendMoney();
                break;
            case R.id.btn_perform_dp:
                performDP();
                break;
        }
    }

    private void getBalance() {

        mCitrusClient.getBalance(new Callback<Amount>() {
            @Override
            public void success(Amount amount) {
                Utils.showToast(mContext, "Balance : " + amount.getValue());
            }

            @Override
            public void error(CitrusError error) {
                Utils.showToast(mContext, error.getMessage());
            }
        });
    }

    private void loadMoney() {
        showPrompt(LOAD_MONEY);
    }

    private void payUsingCash() {
        showPrompt(CITRUS_CASH);
    }

    private void pgPayment() {
        showPrompt(PG_PAYMENT);
    }

    private void cashout() {
        showCashoutPrompt();
    }

    private void getCashoutInfo() {
        mCitrusClient.getCashoutInfo(new Callback<CashoutInfo>() {
            @Override
            public void success(CashoutInfo cashoutInfo) {
                Utils.showToast(getActivity(), cashoutInfo.toString());
            }

            @Override
            public void error(CitrusError error) {
                Utils.showToast(getActivity(), error.getMessage());
            }
        });
    }

    private void sendMoney() {
        showSendMoneyPrompt();
    }

    private void performDP() {
        showDynamicPricingPrompt();
    }

    private void showPrompt(final Utils.PaymentType paymentType) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        String message = null;
        String positiveButtonText = null;

        switch (paymentType) {
            case LOAD_MONEY:
                message = "Please enter the amount to load.";
                positiveButtonText = "Load Money";
                break;
            case CITRUS_CASH:
                message = "Please enter the transaction amount.";
                positiveButtonText = "Pay";
                break;
            case PG_PAYMENT:
                message = "Please enter the transaction amount.";
                positiveButtonText = "Make Payment";
                break;
        }

        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        alert.setTitle("Transaction Amount?");
        alert.setMessage(message);
        // Set an EditText view to get user input
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        alert.setView(input);
        alert.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();

                mListener.onPaymentTypeSelected(paymentType, new Amount(value));

                input.clearFocus();
                // Hide the keyboard.
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });

        input.requestFocus();
        alert.show();
    }

    private void showCashoutPrompt() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        String message = "Please enter account details.";
        String positiveButtonText = "Withdraw";

        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        final TextView labelAmount = new TextView(getActivity());
        final EditText editAmount = new EditText(getActivity());
        final TextView labelAccountNo = new TextView(getActivity());
        final EditText editAccountNo = new EditText(getActivity());
        final TextView labelAccountHolderName = new TextView(getActivity());
        final EditText editAccountHolderName = new EditText(getActivity());
        final TextView labelIfscCode = new TextView(getActivity());
        final EditText editIfscCode = new EditText(getActivity());

        labelAmount.setText("Withdrawal Amount");
        labelAccountNo.setText("Account Number");
        labelAccountHolderName.setText("Account Holder Name");
        labelIfscCode.setText("IFSC Code");

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        labelAmount.setLayoutParams(layoutParams);
        labelAccountNo.setLayoutParams(layoutParams);
        labelAccountHolderName.setLayoutParams(layoutParams);
        labelIfscCode.setLayoutParams(layoutParams);
        editAmount.setLayoutParams(layoutParams);
        editAccountNo.setLayoutParams(layoutParams);
        editAccountHolderName.setLayoutParams(layoutParams);
        editIfscCode.setLayoutParams(layoutParams);

        linearLayout.addView(labelAmount);
        linearLayout.addView(editAmount);
        linearLayout.addView(labelAccountNo);
        linearLayout.addView(editAccountNo);
        linearLayout.addView(labelAccountHolderName);
        linearLayout.addView(editAccountHolderName);
        linearLayout.addView(labelIfscCode);
        linearLayout.addView(editIfscCode);

        editAmount.setInputType(InputType.TYPE_CLASS_NUMBER);

        alert.setTitle("Withdraw Money To Your Account");
        alert.setMessage(message);

        alert.setView(linearLayout);
        alert.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                String amount = editAmount.getText().toString();
                String accontNo = editAccountNo.getText().toString();
                String accountHolderName = editAccountHolderName.getText().toString();
                String ifsc = editIfscCode.getText().toString();

                CashoutInfo cashoutInfo = new CashoutInfo(new Amount(amount), accontNo, accountHolderName, ifsc);
                mListener.onCashoutSelected(cashoutInfo);

                // Hide the keyboard.
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editAmount.getWindowToken(), 0);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });

        editAmount.requestFocus();
        alert.show();
    }

    private void showSendMoneyPrompt() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        final String message = "Send Money to Friend In A Flash";
        String positiveButtonText = "Send";

        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        final TextView labelAmount = new TextView(getActivity());
        final EditText editAmount = new EditText(getActivity());
        final TextView labelMobileNo = new TextView(getActivity());
        final EditText editMobileNo = new EditText(getActivity());
        final TextView labelMessage = new TextView(getActivity());
        final EditText editMessage = new EditText(getActivity());

        labelAmount.setText("Amount");
        labelMobileNo.setText("Enter Mobile No of Friend");
        labelMessage.setText("Enter Message (Optional)");

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        labelAmount.setLayoutParams(layoutParams);
        labelMobileNo.setLayoutParams(layoutParams);
        labelMessage.setLayoutParams(layoutParams);
        editAmount.setLayoutParams(layoutParams);
        editMobileNo.setLayoutParams(layoutParams);
        editMessage.setLayoutParams(layoutParams);

        linearLayout.addView(labelAmount);
        linearLayout.addView(editAmount);
        linearLayout.addView(labelMobileNo);
        linearLayout.addView(editMobileNo);
        linearLayout.addView(labelMessage);
        linearLayout.addView(editMessage);

        editAmount.setInputType(InputType.TYPE_CLASS_NUMBER);
        editMobileNo.setInputType(InputType.TYPE_CLASS_NUMBER);
        alert.setTitle("Send Money In A Flash");
        alert.setMessage(message);

        alert.setView(linearLayout);
        alert.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                String amount = editAmount.getText().toString();
                String mobileNo = editMobileNo.getText().toString();
                String message = editMessage.getText().toString();

                mCitrusClient.sendMoneyToMoblieNo(new Amount(amount), mobileNo, message, new Callback<PaymentResponse>() {
                    @Override
                    public void success(PaymentResponse paymentResponse) {
                        Utils.showToast(getActivity(), paymentResponse.getStatus() == CitrusResponse.Status.SUCCESSFUL ? "Sent Money Successfully." : "Failed To Send the Money");
                    }

                    @Override
                    public void error(CitrusError error) {
                        Utils.showToast(getActivity(), error.getMessage());
                    }
                });
                // Hide the keyboard.
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editAmount.getWindowToken(), 0);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });

        editAmount.requestFocus();
        alert.show();
    }

    private void showDynamicPricingPrompt() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        String message = "Apply Dynamic Pricing";
        String positiveButtonText = "Apply";
        final Utils.DPRequestType[] dpRequestType = new Utils.DPRequestType[1];
        ScrollView scrollView = new ScrollView(getActivity());
        LinearLayout linearLayout = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.dynamic_pricing_input_layout, null);
        final EditText editTransactionAmount = (EditText) linearLayout.findViewById(R.id.edit_txn_amount);
        final EditText editCouponCode = (EditText) linearLayout.findViewById(R.id.edit_coupon_code);
        final EditText editAlteredAmount = (EditText) linearLayout.findViewById(R.id.edit_altered_amount);
        final LinearLayout layoutCouponCode = (LinearLayout) linearLayout.findViewById(R.id.layout_for_coupon_code);
        final LinearLayout layoutAlteredAmount = (LinearLayout) linearLayout.findViewById(R.id.layout_for_altered_amount);
        Spinner spinner = (Spinner) linearLayout.findViewById(R.id.spinner_dp_request_type);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        dpRequestType[0] = Utils.DPRequestType.SEARCH_AND_APPLY;
                        layoutCouponCode.setVisibility(View.GONE);
                        layoutAlteredAmount.setVisibility(View.GONE);
                        break;
                    case 1:
                        dpRequestType[0] = Utils.DPRequestType.CALCULATE_PRICING;
                        layoutCouponCode.setVisibility(View.VISIBLE);
                        layoutAlteredAmount.setVisibility(View.GONE);
                        break;
                    case 2:
                        dpRequestType[0] = Utils.DPRequestType.VALIDATE_RULE;
                        layoutCouponCode.setVisibility(View.VISIBLE);
                        layoutAlteredAmount.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        alert.setTitle("Perform Dynamic Pricing");
        alert.setMessage(message);

        scrollView.addView(linearLayout);
        alert.setView(scrollView);
        alert.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                String amount = editTransactionAmount.getText().toString();
                String alteredAmount = editAlteredAmount.getText().toString();
                String couponCode = editCouponCode.getText().toString();

                mListener.onPaymentTypeSelected(dpRequestType[0], new Amount(amount), couponCode, new Amount(alteredAmount));

                // Hide the keyboard.
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editTransactionAmount.getWindowToken(), 0);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });

        editTransactionAmount.requestFocus();
        alert.show();
    }
}


