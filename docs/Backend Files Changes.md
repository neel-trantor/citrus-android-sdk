<h5>Changes inside Bill Generator and Returl Url Files</h5>

<b>1. Bill Generator</b>

* You need to replace your <b>Secret Key, Access Key</b>.
* Verify your returnUrl

```
  String accessKey = "MERCHANT_ACCESS_KEY";
  String secretKey = "MERCHANT_SECRET_KEY";
  String returnUrl = "http://www.yourwebsite.com/returnData.jsp";
```

<b>2. Return Url</b>

* You only need to replace your <b>Secret Key</b>.

```
String secretKey = "MERCHANT_SECRET_KEY";
```
