<h2>Pay using Citrus Cash</h2>

<li>You can perform a transaction using Citrus cash.</li>  

```java
citrusClient.payUsingCitrusCash(new PaymentType.CitrusCash(amount, BILL_URL), new Callback<TransactionResponse>() {

     @Override
     public void success(TransactionResponse transactionResponse) { }

     @Override
     public void error(CitrusError error) { }
  });
  ```
