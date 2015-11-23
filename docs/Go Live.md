

<h4>Changes required to go Live</h4>

* Thorough testing should be done on Sandbox.User created on Sandbox environment will not be available on production environment. You have to explicitly create account for both environments.
* Once your app is <b>working fine </b>under<b> Sandbox environment</b>, you can switch environment to Production.
* Please make sure you are using correct set of keys and have set environment to Production before releasing your app to Play-Store. 
* Pass your environment as <b>PRODUCTION</b> to citrusClient.init method.
* Replace your signup and signin keys from your new admin account provided by Citrus
* Replace your vanity

<b>Pass merchant parameters in init</b>
```java
    citrusClient.init(
              "test-signup", 
              "c78ec84e389814a05d3ae46546d16d2e", 
              "test-signin", 
              "52f7e15efd4208cf5345dd554443fd99", 
              "testing", 
              Environment.PRODUCTION ); // Make sure change the environment to PRODUCTION while going live.
```
  For both the enviroments Citrus PG Prerequisites key sets are different. keys from one enviroment wont work on other.
  <ul>
  <li> First Parameter –  SignUp Key </li>
  <li>Second Parameter –  SignUp Secret</li>
  <li>Third Parameter  –  SignIn Key</li>
  <li>Fourth Parameter -  SignIn Secret</li>
  <li>Fifth Parameter  -  Vanity</li>
  <li>Sixth Parameter  -  Environment.</li>
  </ul>
