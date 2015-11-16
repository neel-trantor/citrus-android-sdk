<h4>Load Money using Debit Card</h4>
```java
  // If you have already initiated the CitrusClient, no need to initialize again.
  // Just get the reference to theCitrusClient object

  CitrusClient citrusClient = CitrusClient.getInstance(context); // Activity Context
  DebitCardOption debitCardOption 
        = new DebitCardOption("Card Holder Name", "4111111111111111", "123", Month.getMonth("12"), Year.getYear("18"));

  Amount amount = new Amount("5"); 

  // Init Load Money PaymentType     
  PaymentType.LoadMoney loadMoney = new PaymentType.LoadMoney(amount, LOAD_MONEY_RETURN_URL,            debitCardOption);

  // Call Load Money
  citrusClient.loadMoney(loadMoney, new Callback<TransactionResponse>() {

     @Override
     public void success(TransactionResponse transactionResponse) {}

     @Override
     public void error(CitrusError error) {}
  });
  ```
  <h4>Load Money using Credit Card</h4>
  ```java
   CitrusClient citrusClient = CitrusClient.getInstance(context); // Activity Context
  // No need to call init on CitrusClient if already done.
  CreditCardOption creditCardOption = new CreditCardOption("Card Holder Name", "4111111111111111", "123", Month.getMonth("12"), Year.getYear("18"));

  Amount amount = new Amount("5");
  // Init Load Money PaymentType     
  PaymentType.LoadMoney loadMoney = new PaymentType.LoadMoney(amount, LOAD_MONEY_RETURN_URL, creditCardOption);

  citrusClient.loadMoney(loadMoney, new Callback<TransactionResponse>() {

     @Override
     public void success(TransactionResponse transactionResponse) {}

     @Override
     public void error(CitrusError error) {}
  });
  ```
  <h4>Load Money using Net Banking option</h4>
  ```java
   CitrusClient citrusClient = CitrusClient.getInstance(context); // Activity Context
  // No need to call init on CitrusClient if already done.

  NetbankingOption netbankingOptio = new NetbankingOption(“ICICI Bank” ,”CID001”);

  // Init Net Banking PaymentType     
  PaymentType.LoadMoney loadMoney = new PaymentType.LoadMoney(amount, LOAD_MONEY_RETURN_URL, netbankingOption);

  citrusClient.loadMoney(loadMoney, new Callback<TransactionResponse>() {

     @Override
     public void success(TransactionResponse transactionResponse) { }

     @Override
     public void error(CitrusError error) { }
  });
  
  ```
