<h2>How to reset a Password?</h2>

<li>To implement forget password feature use <b>resetPassword</b> method.</li>
```java
  citrusClient.resetPassword(emailId, new com.citrus.sdk.Callback<CitrusResponse>() {
     @Override
     public void success(CitrusResponse citrusResponse) {}

     @Override
     public void error(CitrusError error) {}
  });
  ```
