<h2>How to check Citrus member (isCitrusMember)</h2>

To check whether the user is Citrus member or not, you can use <b><i>isCitrusMember</i></b> method. <li>If it returns True the user is already a Citrus member (display a <b>SignIn</b> screen)</li><li>If it returns False (display a <b>SignUp</b> Screen)</li>


 ```java
  citrusClient.isCitrusMember(emailId, mobileNo, new com.citrus.sdk.Callback<Boolean>() {
     @Override
     public void success(Boolean citrusMember) {}

     @Override
     public void error(CitrusError error) {}
  });
  ```
