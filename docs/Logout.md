<h3>How to implement Logout feature</h2>

<li><b>signOut</b> method is used to Logout the user</li>

```java
 citrusClient.signOut(new Callback<CitrusResponse>() {

     @Override
     public void success(CitrusResponse citrusResponse) { }

     @Override
     public void error(CitrusError error) { }
  });
```
