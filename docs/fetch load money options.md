<b>Payment Options for Load Money</b>

<b>Note:</b><li>This method is only required when you are implementing <b>Load Money into Wallet</b> feature.</li>
<li>Load Money payment options <b>differes</b> from normal PG Payment.</li>
<li>These are the payment options enabled/available for you by <b>Citrus</b> while loading money</li>

Following method should be used to fetch <b>Load Money Payment Options</b>
```java
    citrusClient.getInstance(getActivity()).getLoadMoneyPaymentOptions(new Callback<MerchantPaymentOption>() {
            @Override
            public void success(MerchantPaymentOption loadMoneyPaymentOptions) {
               ArrayList<NetbankingOption> mNetbankingOptionsList = mMerchantPaymentOption.getNetbankingOptionList();//this will give you only bank list
            }

            @Override
            public void error(CitrusError error) {

            }
        });
```
