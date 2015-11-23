<h2>How to check if the user is signed in or not?</h2>  

```java
citrusClient.isUserSignedIn(new com.citrus.sdk.Callback<Boolean>() {
     @Override
     public void success(Boolean loggedIn) {}

     @Override
     public void error(CitrusError error) {}
  });
```
You would receive a status inside callback.
