<h2> Dynamic Pricing, offer coupons, surcharge. </h2>
Using dynamic pricing you can apply surcharge or discount or even issue a coupon to the user.

Dynamic pricing happens in 2 stages
<ul>
<li> Apply rule </li>
<li> Process Payments with DP result </li>
<b> The payment is processed only if the dynamic pricing is applied. </b>
</ul>

There are three ways in which rule can be applied

##SearchAndApply
Search and apply will search for the possible rule depending upon the input data. 
The rule can be applied on the payment mode or card bin range or user details etc.
The system will check for matching rule using the input data and apply the rule and return the altered amount if proper rule was found. Once the request is successful the user can make transaction for the altered amount.
The input parameters will be transaction amount, payment details, userDetails , valid bill url .

<h4> Apply the rule to Debit Card </h4>
```java
  //Payment details
  CitrusUser user = new CitrusUser("email@domain.com", "9999999999", "First Name", "Last Name", null);
  DebitCardOption cardOption = new DebitCardOption(cardHolderName, cardNumber, cardCVV, month, year);
  DynamicPricingRequestType dynamicPricingRequestType = new DynamicPricingRequestType.SearchAndApplyRule(amount, cardOption, user);
  
  citrusClient.performDynamicPricing(dynamicPricingRequestType, Constants.BILL_URL, new Callback<DynamicPricingResponse>() {
      @Override
      public void success(DynamicPricingResponse dynamicPricingResponse) {
            // Show the user with altered amount and status of the request.
            // If user aggress then pay using the dynamicPricingResponse.
            citrusClient.pgPayment(dynamicPricingResponse, new Callback<TransactionResponse>() {
                @Override
                public void success(TransactionResponse transactionResponse) { }

                @Override
                public void error(CitrusError error) { }
            });
      }
  
      @Override
      public void error(CitrusError error) { }
    });
  
```

<h4> Apply the rule to Credit Card </h4>
```java
  //Payment details
  CitrusUser user = new CitrusUser("email@domain.com", "9999999999", "First Name", "Last Name", null);
  CreditCardOption cardOption = new CreditCardOption(cardHolderName, cardNumber, cardCVV, month, year);
  DynamicPricingRequestType dynamicPricingRequestType = new DynamicPricingRequestType.SearchAndApplyRule(amount, cardOption, user);
  
  citrusClient.performDynamicPricing(dynamicPricingRequestType, Constants.BILL_URL, new Callback<DynamicPricingResponse>() {
      @Override
      public void success(DynamicPricingResponse dynamicPricingResponse) {
            // Show the user with altered amount and status of the request.
            // If user aggress then pay using the dynamicPricingResponse.
            citrusClient.pgPayment(dynamicPricingResponse, new Callback<TransactionResponse>() {
                @Override
                public void success(TransactionResponse transactionResponse) { }

                @Override
                public void error(CitrusError error) { }
            });
      }
  
      @Override
      public void error(CitrusError error) { }
    });
  
```

<h4> Apply the rule to NetBank </h4>
```java
  //Payment details
  CitrusUser user = new CitrusUser("email@domain.com", "9999999999", "First Name", "Last Name", null);
  NetbankingOption netbankingOption = new NetbankingOption("Bank Name", "CID Code");
  DynamicPricingRequestType dynamicPricingRequestType = new DynamicPricingRequestType.SearchAndApplyRule(amount, netbankingOption, user);
  
  citrusClient.performDynamicPricing(dynamicPricingRequestType, Constants.BILL_URL, new Callback<DynamicPricingResponse>() {
      @Override
      public void success(DynamicPricingResponse dynamicPricingResponse) {
            // Show the user with altered amount along with status of the request.
            // If user aggress then pay using the dynamicPricingResponse.
            citrusClient.pgPayment(dynamicPricingResponse, new Callback<TransactionResponse>() {
                @Override
                public void success(TransactionResponse transactionResponse) { }

                @Override
                public void error(CitrusError error) { }
            });
      }
  
      @Override
      public void error(CitrusError error) { }
    });
  
```

##CalculatePricing
Calculate pricing will search for the given rule name and apply the rule, if rule with given name is found, it will return the altered amount. Once the request is successful the user can make transaction for altered amount.
The input parameters will be transaction amount, payment details, user details and rule name, valid bill url

<h4> Calculate price by applying rule using Debit Card </h4>
```java
  //Payment details
  CitrusUser user = new CitrusUser("email@domain.com", "9999999999", "First Name", "Last Name", null);
  DebitCardOption cardOption = new DebitCardOption(cardHolderName, cardNumber, cardCVV, month, year);
  DynamicPricingRequestType dynamicPricingRequestType = new DynamicPricingRequestType.CalculatePrice(amount, cardOption, couponCode, user);
  
  citrusClient.performDynamicPricing(dynamicPricingRequestType, Constants.BILL_URL, new Callback<DynamicPricingResponse>() {
      @Override
      public void success(DynamicPricingResponse dynamicPricingResponse) {
            // Show the user with altered amount and status of the request.
            // If user aggress then pay using the dynamicPricingResponse.
            citrusClient.pgPayment(dynamicPricingResponse, new Callback<TransactionResponse>() {
                @Override
                public void success(TransactionResponse transactionResponse) { }

                @Override
                public void error(CitrusError error) { }
            });
      }
  
      @Override
      public void error(CitrusError error) { }
    });
  
```

<h4> Calculate price by applying rule using Credit Card </h4>
```java
  //Payment details
  CitrusUser user = new CitrusUser("email@domain.com", "9999999999", "First Name", "Last Name", null);
  CreditCardOption cardOption = new CreditCardOption(cardHolderName, cardNumber, cardCVV, month, year);
  DynamicPricingRequestType dynamicPricingRequestType = new DynamicPricingRequestType.CalculatePrice(amount, cardOption, couponCode, user);
  
  citrusClient.performDynamicPricing(dynamicPricingRequestType, Constants.BILL_URL, new Callback<DynamicPricingResponse>() {
      @Override
      public void success(DynamicPricingResponse dynamicPricingResponse) {
            // Show the user with altered amount and status of the request.
            // If user aggress then pay using the dynamicPricingResponse.
            citrusClient.pgPayment(dynamicPricingResponse, new Callback<TransactionResponse>() {
                @Override
                public void success(TransactionResponse transactionResponse) { }

                @Override
                public void error(CitrusError error) { }
            });
      }
  
      @Override
      public void error(CitrusError error) { }
    });
  
```

<h4> Calculate price by applying rule using NetBank </h4>
```java
  //Payment details
  CitrusUser user = new CitrusUser("email@domain.com", "9999999999", "First Name", "Last Name", null);
  NetbankingOption netbankingOption = new NetbankingOption("Bank Name", "CID Code");
  DynamicPricingRequestType dynamicPricingRequestType = new DynamicPricingRequestType.CalculatePrice(amount, netbankingOption, couponCode, user);
  
  citrusClient.performDynamicPricing(dynamicPricingRequestType, Constants.BILL_URL, new Callback<DynamicPricingResponse>() {
      @Override
      public void success(DynamicPricingResponse dynamicPricingResponse) {
            // Show the user with altered amount along with status of the request.
            // If user aggress then pay using the dynamicPricingResponse.
            citrusClient.pgPayment(dynamicPricingResponse, new Callback<TransactionResponse>() {
                @Override
                public void success(TransactionResponse transactionResponse) { }

                @Override
                public void error(CitrusError error) { }
            });
      }
  
      @Override
      public void error(CitrusError error) { }
    });
  
```

##ValidateRule
Validate rule will be used to validate the whether the rule is properly applied or not. 
The input values will be transaction amount, payment details, user details, rule name and altered amount.
System will search for the given rule and apply the rule and check whether the given altered amount matches with the generated altered amount. Once the rule is validated you can proceed for the transaction with altered amount.

<h4> Validate rule using Debit Card </h4>
```java
  //Payment details
  CitrusUser user = new CitrusUser("email@domain.com", "9999999999", "First Name", "Last Name", null);
  DebitCardOption cardOption = new DebitCardOption(cardHolderName, cardNumber, cardCVV, month, year);
  DynamicPricingRequestType dynamicPricingRequestType = new DynamicPricingRequestType.ValidateRule(amount, cardOption, couponCode, alteredAmount, user);
  
  citrusClient.performDynamicPricing(dynamicPricingRequestType, Constants.BILL_URL, new Callback<DynamicPricingResponse>() {
      @Override
      public void success(DynamicPricingResponse dynamicPricingResponse) {
            // Show the user with altered amount and status of the request.
            // If user aggress then pay using the dynamicPricingResponse.
            citrusClient.pgPayment(dynamicPricingResponse, new Callback<TransactionResponse>() {
                @Override
                public void success(TransactionResponse transactionResponse) { }

                @Override
                public void error(CitrusError error) { }
            });
      }
  
      @Override
      public void error(CitrusError error) { }
    });
  
```

<h4> Calculate price by applying rule using Credit Card </h4>
```java
  //Payment details
  CitrusUser user = new CitrusUser("email@domain.com", "9999999999", "First Name", "Last Name", null);
  CreditCardOption cardOption = new CreditCardOption(cardHolderName, cardNumber, cardCVV, month, year);
  DynamicPricingRequestType dynamicPricingRequestType = new DynamicPricingRequestType.ValidateRule(amount, cardOption, couponCode, alteredAmount, user);
  
  citrusClient.performDynamicPricing(dynamicPricingRequestType, Constants.BILL_URL, new Callback<DynamicPricingResponse>() {
      @Override
      public void success(DynamicPricingResponse dynamicPricingResponse) {
            // Show the user with altered amount and status of the request.
            // If user aggress then pay using the dynamicPricingResponse.
            citrusClient.pgPayment(dynamicPricingResponse, new Callback<TransactionResponse>() {
                @Override
                public void success(TransactionResponse transactionResponse) { }

                @Override
                public void error(CitrusError error) { }
            });
      }
  
      @Override
      public void error(CitrusError error) { }
    });
  
```

<h4> Calculate price by applying rule using NetBank </h4>
```java
  //Payment details
  CitrusUser user = new CitrusUser("email@domain.com", "9999999999", "First Name", "Last Name", null);
  NetbankingOption netbankingOption = new NetbankingOption("Bank Name", "CID Code");
  DynamicPricingRequestType dynamicPricingRequestType = new DynamicPricingRequestType.ValidateRule(amount, netbankingOption, couponCode, alteredAmount, user);
  
  citrusClient.performDynamicPricing(dynamicPricingRequestType, Constants.BILL_URL, new Callback<DynamicPricingResponse>() {
      @Override
      public void success(DynamicPricingResponse dynamicPricingResponse) {
            // Show the user with altered amount along with status of the request.
            // If user aggress then pay using the dynamicPricingResponse.
            citrusClient.pgPayment(dynamicPricingResponse, new Callback<TransactionResponse>() {
                @Override
                public void success(TransactionResponse transactionResponse) { }

                @Override
                public void error(CitrusError error) { }
            });
      }
  
      @Override
      public void error(CitrusError error) { }
    });
  
```
