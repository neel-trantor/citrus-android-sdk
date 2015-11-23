<h4>Load/Add Money using Debit Card Token</h4>
```java
  CitrusClient citrusClient = CitrusClient.getInstance(context); // Activity Context
  // No need to call init on CitrusClient if already done.

  DebitCardOption debitCardOption = new DebitCardOption("94a4def03fdac35749bfd2746e5cd6f9", "123");
  //Note: The Token for sandbox and production will be different

  // Init PaymentType     
  PaymentType.LoadMoney loadMoney = new PaymentType.LoadMoney(amount, LOAD_MONEY_RETURN_URL, debitCardOption);

  citrusClient.LoadMoney(loadMoney, new Callback<TransactionResponse>() {

     @Override
     public void success(TransactionResponse transactionResponse) { }

     @Override
     public void error(CitrusError error) { }
  });
  ```
<h4>Load/Add Money using Credit Card Token</h4>
```java
   CitrusClient citrusClient = CitrusClient.getInstance(context); // Activity Context
  // No need to call init on CitrusClient if already done.

  CreditCardOption creditCardOption = new CreditCardOption("d7505f22bca20a97f8d8f305530e88a9", "123");
  //Note: The Token for sandbox and production will be different

  // Init PaymentType     
  PaymentType.LoadMoney loadMoney = new PaymentType.LoadMoney(amount, LOAD_MONEY_RETURN_URL, creditCardOption);
  // Call LoadMoney
  citrusClient.LoadMoney(loadMoney, new Callback<TransactionResponse>() {

     @Override
     public void success(TransactionResponse transactionResponse) { }

     @Override
     public void error(CitrusError error) { }
  });
  ```
<h4>Load/Add Money using Bank Token</h4>
```java
  CitrusClient citrusClient = CitrusClient.getInstance(context); // Activity Context
  // No need to call init on CitrusClient if already done.

  NetbankingOption netbankingOption = new NetbankingOption("b66352b2d465699d6fa7cfb520ba27b5");
  //Note: The Token for sandbox and production will be different

  // Init PaymentType     
  PaymentType.LoadMoney loadMoney = new PaymentType.LoadMoney(amount, LOAD_MONEY_RETURN_URL, netbankingOption, new CitrusUser("developercitrus@gmail.com","9876543210"));
  // Call LoadMoney
  citrusClient.LoadMoney(loadMoney, new Callback<TransactionResponse>() {

     @Override
     public void success(TransactionResponse transactionResponse) { }

     @Override
     public void error(CitrusError error) { }
  });
  ```
