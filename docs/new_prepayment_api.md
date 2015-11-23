##Pay using Citrus Cash

###You can perform a transaction using Citrus cash.
```java
citrusClient.prepaidPay(new PaymentType.CitrusCash(amount, Constants.BILL_URL), new Callback<PaymentResponse>() {
    @Override
    public void success(PaymentResponse paymentResponse) { }

    @Override
    public void error(CitrusError error) { }
});
```
