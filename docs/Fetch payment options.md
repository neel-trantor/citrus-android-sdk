<h2>How to fetch Payment Options enabled/available for you?</h2>

<p>This is useful to find the type of <b>debit/credit</b> card & list of <b>banks</b> enabled/available to you for Transactions.</p><li>You have to use this list of banks and show in the UI.</li><li> When user selects the particular bank, you should use CID against that bank for payment.</li>

There are <b>two</b> types of Payment Options

<b>Payment Options for PG Payment</b>

Following method should be used to fetch <b>PG Payment Options</b>
```java
     CitrusClient.getInstance(getActivity()).getMerchantPaymentOptions(new Callback<MerchantPaymentOption>() {
            @Override
            public void success(MerchantPaymentOption merchantPaymentOption) {

                ArrayList<NetbankingOption> mNetbankingOptionsList = mMerchantPaymentOption.getNetbankingOptionList();//this will give you only bank list

            }

            @Override
            public void error(CitrusError error) {

            }
        });
```

