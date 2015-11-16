<h2>How to fetch Citrus Cash balance?</h2>

<li>You can get user’s <b>Citrus Cash</b> balance after you have <b>Signed In</b> the user.</i>
```java
  citrusClient.getBalance(new com.citrus.sdk.Callback<Amount>() {
     @Override
     public void success(Amount amount) {}

     @Override
     public void error(CitrusError error) {}
  });
  ```
