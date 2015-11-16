<h3>Use Cashout/withdraw money feature !</h3>

<li>You can withdraw/cashout money to your <b>card</b> account/<b>bank</b> account.</li><li>Make sure the user is <b>signed in with password</b> to be able to withdraw the money.</li>
```java
    String amount = "10";
    String accontNo = "12345678901";
    String accountHolderName = "FirstName LastName;
    String ifsc = "BANK0000123";
```
```java
CashoutInfo cashoutInfo = new CashoutInfo(new Amount(amount), accontNo, accountHolderName, ifsc);
citrusClient.cashout(cashoutInfo, new Callback<PaymentResponse>() {
        @Override
        public void success(PaymentResponse paymentResponse) { }

        @Override
        public void error(CitrusError error) {}
    });
  ```
