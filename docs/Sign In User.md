<h2>How to Sign In an User?</h2>
```java
  citrusClient.signIn(emailId, password, new com.citrus.sdk.Callback<CitrusResponse>() {
      @Override
      public void success(CitrusResponse citrusResponse) {}

      @Override
      public void error(CitrusError error) {}
  });
 ```
