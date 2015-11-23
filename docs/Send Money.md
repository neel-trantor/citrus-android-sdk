<h3>Send Money</h3>

<li>You can send money to your friend using Mobile No. Please refer below code snippet.</li>

```java
citrusClient.sendMoneyToMoblieNo(new Amount("10"), "9999999999", "My contribution", new Callback<PaymentResponse>() {
    @Override
    public void success(PaymentResponse paymentResponse) { }

    @Override
    public void error(CitrusError error) { }
});
```
