
<h4>Pay using Debit Card</h4>

```java
  CitrusClient citrusClient = CitrusClient.getInstance(context); // Activity Context
  // No need to call init on CitrusClient if already done.

  DebitCardOption debitCardOption = new DebitCardOption("Card Holder Name", "4111111111111111", "123", Month.getMonth("12"), Year.getYear("18"));

  Amount amount = new Amount("5");
  // Init PaymentType     
  PaymentType.PGPayment pgPayment = new PaymentType.PGPayment(amount, BILL_URL, debitCardOption, new CitrusUser("developercitrus@gmail.com","9876543210"));

  citrusClient.pgPayment(pgPayment, new Callback<TransactionResponse>() {

     @Override
     public void success(TransactionResponse transactionResponse) { }

     @Override
     public void error(CitrusError error) { }
  });
  ```
<h4>Pay using Credit Card</h4>

```java
  CitrusClient citrusClient = CitrusClient.getInstance(context); // Activity Context
  // No need to call init on CitrusClient if already done.

  CreditCardOption creditCardOption = new CreditCardOption("Card Holder Name", "4111111111111111", "123", Month.getMonth("12"), Year.getYear("18"));

  Amount amount = new Amount("5");
  // Init PaymentType     
  PaymentType.PGPayment pgPayment = new PaymentType.PGPayment(amount, BILL_URL, creditCardOption, new CitrusUser("developercitrus@gmail.com","9876543210"));

  citrusClient.pgPayment(pgPayment, new Callback<TransactionResponse>() {

     @Override
     public void success(TransactionResponse transactionResponse) { }

     @Override
     public void error(CitrusError error) { }
  });
  ```
  
  <h4>Pay using Net Banking Option</h4>
  
  ```java
  CitrusClient citrusClient = CitrusClient.getInstance(context); // Activity Context
  // No need to call init on CitrusClient if already done.

  NetbankingOption netbankingOption = new NetbankingOption("ICICI Bank","CID001");

  // Init Net Banking PaymentType     
  PaymentType.PGPayment pgPayment = new PaymentType.PGPayment(amount, BILL_URL, netbankingOption, new CitrusUser("developercitrus@gmail.com","9876543210"));

  citrusClient.pgPayment(pgPayment, new Callback<TransactionResponse>() {

     @Override
     public void success(TransactionResponse transactionResponse) { }

     @Override
     public void error(CitrusError error) { }
  });
  ```
