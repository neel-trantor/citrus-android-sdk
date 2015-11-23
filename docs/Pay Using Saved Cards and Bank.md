<h4>Payment using Debit Card Token</h4>
```java
  CitrusClient citrusClient = CitrusClient.getInstance(context); // Activity Context
  // No need to call init on CitrusClient if already done.

  DebitCardOption debitCardOption = new DebitCardOption("94a4def03fdac35749bfd2746e5cd6f9", "123");

  // Init Net Banking PaymentType     
  PaymentType.PGPayment pgPayment = new PaymentType.PGPayment(amount, BILL_URL, debitCardOption, new CitrusUser("developercitrus@gmail.com","9876543210"));

  citrusClient.pgPayment(pgPayment, new Callback<TransactionResponse>() {

     @Override
     public void success(TransactionResponse transactionResponse) { }

     @Override
     public void error(CitrusError error) { }
  });
  ```
  
 <h4>Payment using Credit Card Token</h4>
```java
  CitrusClient citrusClient = CitrusClient.getInstance(context); // Activity Context
  // No need to call init on CitrusClient if already done.

  CreditCardOption creditCardOption = new CreditCardOption("d7505f22bca20a97f8d8f305530e88a9", "123");

  // Init Net Banking PaymentType     
  PaymentType.PGPayment pgPayment = new PaymentType.PGPayment(amount, BILL_URL, creditCardOption, new CitrusUser("developercitrus@gmail.com","9876543210"));

  citrusClient.pgPayment(pgPayment, new Callback<TransactionResponse>() {

     @Override
     public void success(TransactionResponse transactionResponse) { }

     @Override
     public void error(CitrusError error) { }
  });
  ```
  
<h4>Payment using Bank Token</h4>
```java
   CitrusClient citrusClient = CitrusClient.getInstance(context); // Activity Context
  // No need to call init on CitrusClient if already done.

  NetbankingOption netbankingOption = new NetbankingOption("b66352b2d465699d6fa7cfb520ba27b5");

  // Init Net Banking PaymentType     
  PaymentType.PGPayment pgPayment = new PaymentType.PGPayment(amount, BILL_URL, ne, netbankingOption CitrusUser("developercitrus@gmail.com","9876543210"));

  citrusClient.pgPayment(pgPayment, new Callback<TransactionResponse>() {

     @Override
     public void success(TransactionResponse transactionResponse) { }

     @Override
     public void error(CitrusError error) { }
  });
  ```
