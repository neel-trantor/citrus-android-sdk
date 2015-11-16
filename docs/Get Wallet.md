<h4>Get Wallet</h4>

It Fetches all Saved Cards and Bank Options of a User's account.

```java
CitrusClient.getWallet(new Callback<List<PaymentOption>>() {
            @Override
            public void success(List<PaymentOption> paymentOptions) {
                
            }

            @Override
            public void error(CitrusError error) {
               
            }
        });
```        
        
