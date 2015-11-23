package com.citrus.sample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.citrus.mobile.CType;
import com.citrus.sdk.Callback;
import com.citrus.sdk.CitrusClient;
import com.citrus.sdk.CitrusUser;
import com.citrus.sdk.TransactionResponse;
import com.citrus.sdk.classes.Amount;
import com.citrus.sdk.classes.CitrusException;
import com.citrus.sdk.classes.Month;
import com.citrus.sdk.classes.Year;
import com.citrus.sdk.dynamicPricing.DynamicPricingRequestType;
import com.citrus.sdk.dynamicPricing.DynamicPricingResponse;
import com.citrus.sdk.payment.CardOption;
import com.citrus.sdk.payment.CreditCardOption;
import com.citrus.sdk.payment.DebitCardOption;
import com.citrus.sdk.payment.PaymentOption;
import com.citrus.sdk.payment.PaymentType;
import com.citrus.sdk.response.CitrusError;
import com.citrus.sdk.response.CitrusResponse;
import com.citrus.widgets.CardNumberEditText;
import com.citrus.widgets.ExpiryDate;

public class CreditDebitCardFragment extends Fragment implements View.OnClickListener {

    private CardNumberEditText editCardNumber = null;
    private ExpiryDate editExpiryDate = null;
    private EditText editCVV = null, editCardHolderName = null, cardHolderNickName = null, cardHolderNumber = null;
    private TextView submitButton = null;
    private CheckBox checkBoxSaveCard = null;
    private CType cType = CType.DEBIT;
    private Utils.PaymentType paymentType = null;
    private Utils.DPRequestType dpRequestType = null;
    private Amount amount = null;
    private String couponCode = null;
    private Amount alteredAmount = null;
    private CitrusClient citrusClient = null;

    public CreditDebitCardFragment() {
    }

    public static CreditDebitCardFragment newInstance(Utils.PaymentType paymentType, CType cType, Amount amount) {
        CreditDebitCardFragment fragment = new CreditDebitCardFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("paymentType", paymentType);
        bundle.putSerializable("cType", cType);
        bundle.putParcelable("amount", amount);
        fragment.setArguments(bundle);

        return fragment;
    }

    public static CreditDebitCardFragment newInstance(Utils.DPRequestType dpRequestType, CType cType, Amount originalAmount, String couponCode, Amount alteredAmount) {
        CreditDebitCardFragment fragment = new CreditDebitCardFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("paymentType", Utils.PaymentType.DYNAMIC_PRICING);
        bundle.putSerializable("dpRequestType", dpRequestType);
        bundle.putSerializable("cType", cType);
        bundle.putParcelable("amount", originalAmount);
        bundle.putParcelable("alteredAmount", alteredAmount);
        bundle.putString("couponCode", couponCode);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            Bundle bundle = getArguments();
            paymentType = (Utils.PaymentType) bundle.getSerializable("paymentType");
            cType = (CType) bundle.getSerializable("cType");
            dpRequestType = (Utils.DPRequestType) bundle.getSerializable("dpRequestType");
            amount = bundle.getParcelable("amount");
            alteredAmount = bundle.getParcelable("alteredAmount");
            couponCode = bundle.getString("couponCode");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View returnView = inflater.inflate(R.layout.fragment_credit_debit_card, container,
                false);

        citrusClient = CitrusClient.getInstance(getActivity());
        editCardNumber = (CardNumberEditText) returnView
                .findViewById(R.id.cardHolderNumber);
        editExpiryDate = (ExpiryDate) returnView.findViewById(R.id.cardExpiry);
        editCardHolderName = (EditText) returnView.findViewById(R.id.cardHolderName);
        cardHolderNickName = (EditText) returnView.findViewById(R.id.cardHolderNickName);
        editCVV = (EditText) returnView.findViewById(R.id.cardCvv);
        submitButton = (TextView) returnView.findViewById(R.id.load);
        checkBoxSaveCard = (CheckBox) returnView.findViewById(R.id.checkboxSaveCard);
        submitButton.setOnClickListener(this);

        switch (paymentType) {
            case LOAD_MONEY:
                submitButton.setText("Load");
                break;
            case CITRUS_CASH:
            case PG_PAYMENT:
            case DYNAMIC_PRICING:
                submitButton.setText("Pay");
                break;
        }

        return returnView;
    }

    @Override
    public void onClick(View v) {

        String cardHolderName = editCardHolderName.getText().toString();
        String cardNumber = editCardNumber.getText().toString();
        String cardCVV = editCVV.getText().toString();
        Month month = editExpiryDate.getMonth();
        Year year = editExpiryDate.getYear();

        CardOption cardOption;
        if (cType == CType.DEBIT) {
            cardOption = new DebitCardOption(cardHolderName, cardNumber, cardCVV, month, year);
        } else {
            cardOption = new CreditCardOption(cardHolderName, cardNumber, cardCVV, month, year);
        }

        // Save the card if checkbox is checked.
        if (checkBoxSaveCard.isChecked()) {
            savePaymentOption(cardOption);
        }

        if (paymentType == Utils.PaymentType.DYNAMIC_PRICING) {
            DynamicPricingRequestType dynamicPricingRequestType = null;

            if (dpRequestType == Utils.DPRequestType.SEARCH_AND_APPLY) {
                dynamicPricingRequestType = new DynamicPricingRequestType.SearchAndApplyRule(amount, cardOption, null);
            } else if (dpRequestType == Utils.DPRequestType.CALCULATE_PRICING) {
                dynamicPricingRequestType = new DynamicPricingRequestType.CalculatePrice(amount, cardOption, couponCode, null);
            } else if (dpRequestType == Utils.DPRequestType.VALIDATE_RULE) {
                dynamicPricingRequestType = new DynamicPricingRequestType.ValidateRule(amount, cardOption, couponCode, alteredAmount, null);
            }

//            citrusClient.performDynamicPricing(dynamicPricingRequestType, Constants.BILL_URL, new Callback<DynamicPricingResponse>() {
//                @Override
//                public void success(DynamicPricingResponse dynamicPricingResponse) {
//                    showPrompt(dynamicPricingResponse);
//                }
//
//                @Override
//                public void error(CitrusError error) {
//                    Utils.showToast(getActivity(), error.getMessage());
//                }
//            });
        } else {
            PaymentType paymentType;

            Callback<TransactionResponse> callback = new Callback<TransactionResponse>() {
                @Override
                public void success(TransactionResponse transactionResponse) {
                    ((UIActivity) getActivity()).onPaymentComplete(transactionResponse);
                }

                @Override
                public void error(CitrusError error) {
                    Utils.showToast(getActivity(), error.getMessage());
                }
            };

            try {
                if (this.paymentType == Utils.PaymentType.LOAD_MONEY) {
                    paymentType = new PaymentType.LoadMoney(amount, Constants.RETURN_URL_LOAD_MONEY, cardOption);
                    citrusClient.loadMoney((PaymentType.LoadMoney) paymentType, callback);
                } else if (this.paymentType == Utils.PaymentType.PG_PAYMENT) {
                    paymentType = new PaymentType.PGPayment(amount, Constants.BILL_URL, cardOption, new CitrusUser(citrusClient.getUserEmailId(), citrusClient.getUserMobileNumber()));
                    citrusClient.pgPayment((PaymentType.PGPayment) paymentType, callback);
                } else if (this.paymentType == Utils.PaymentType.DYNAMIC_PRICING) {
                    DynamicPricingRequestType dynamicPricingRequestType = new DynamicPricingRequestType.SearchAndApplyRule(amount, cardOption, null);

//                    citrusClient.performDynamicPricing(dynamicPricingRequestType, Constants.BILL_URL, new Callback<DynamicPricingResponse>() {
//                        @Override
//                        public void success(DynamicPricingResponse dynamicPricingResponse) {
//                            showPrompt(dynamicPricingResponse);
//                        }
//
//                        @Override
//                        public void error(CitrusError error) {
//                            Utils.showToast(getActivity(), error.getMessage());
//                        }
//                    });
                }
            } catch (CitrusException e) {
                e.printStackTrace();

                Utils.showToast(getActivity(), e.getMessage());
            }
        }
    }

    private void savePaymentOption(PaymentOption paymentOption) {
        citrusClient.savePaymentOption(paymentOption, new Callback<CitrusResponse>() {
            @Override
            public void success(CitrusResponse citrusResponse) {
                Utils.showToast(getActivity(), citrusResponse.getMessage());
            }

            @Override
            public void error(CitrusError error) {
                Utils.showToast(getActivity(), error.getMessage());
            }
        });
    }

    private void showPrompt(final DynamicPricingResponse dynamicPricingResponse) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        String message = dynamicPricingResponse.getMessage();
        String positiveButtonText = "Pay";

        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        final TextView originalAmount = new TextView(getActivity());
        final TextView alteredAmount = new TextView(getActivity());
        final TextView txtMessage = new TextView(getActivity());
        final TextView txtConsumerMessage = new TextView(getActivity());

        linearLayout.addView(originalAmount);
        linearLayout.addView(alteredAmount);
        linearLayout.addView(txtMessage);
        linearLayout.addView(txtConsumerMessage);

        originalAmount.setText("Original Amount : " + (dynamicPricingResponse.getOriginalAmount() != null ? dynamicPricingResponse.getOriginalAmount().getValue() : ""));
        alteredAmount.setText("Altered Amount : " + (dynamicPricingResponse.getAlteredAmount() != null ? dynamicPricingResponse.getAlteredAmount().getValue() : ""));
        txtMessage.setText("Message : " + dynamicPricingResponse.getMessage());
        txtMessage.setText("Consumer Message : " + dynamicPricingResponse.getConsumerMessage());

        alert.setTitle("Dynamic Pricing Response");
        alert.setMessage(message);
        alert.setView(linearLayout);
        if (dynamicPricingResponse.getStatus() == DynamicPricingResponse.Status.SUCCESS) {
            alert.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    CitrusClient.getInstance(getActivity()).pgPayment(dynamicPricingResponse, new Callback<TransactionResponse>() {
                        @Override
                        public void success(TransactionResponse transactionResponse) {
                            Utils.showToast(getActivity(), transactionResponse.getMessage());
                        }

                        @Override
                        public void error(CitrusError error) {
                            Utils.showToast(getActivity(), error.getMessage());
                        }
                    });

                    dialog.dismiss();
                }
            });
        }

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });

        alert.show();
    }
}
