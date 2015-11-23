<h2>How to Sign Up an User?</h2>
```java
  citrusClient.signUp(emailId, mobileNo, password, new com.citrus.sdk.Callback <CitrusResponse > () {
     @Override
    public void success(CitrusResponse citrusResponse) {}

    @Override
    public void error(CitrusError error) {}
  });
  ```
