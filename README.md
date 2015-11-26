# Citrus Payments Android SDK

Welcome to the open-source Android SDK Documentation of Citrus Payments Solution!

### Download
#### Gradle

    compile 'com.citrus.sdk:payment-sdk:3.0.33'
      
#### Eclipse
    
    * Get the code git clone https://github.com/citruspay/citrus-android-sdk.git
    * Import citrus-library project in your workspace
    * Copy files from Example/libs into your apps libs folder
    * You are good to go!

___
<h5>Introduction</h5>
___
* This document details the merchant's <b>Android App</b> integration with Citrus Payment gateway.There is a difference between <b>Normal(PG) Payment</b> and <b>Prepaid Payment</b>!
* <b>Normal</b> payment requires only <b>email</b> and <b>mobile</b>. Citrus User account will be created by only using email and mobile combination. We call it as <b><i>Bind</i></b>. Once user is Bind, card can be saved against his account, saved cards can be fetched against his account.
* <b>Prepaid</b> Payment requires <b>email</b> and <b>password</b>. Citrus User account will be created by using email and password combination. We call it prepaid account.

open-android-v3 is enhanced [version](docs/Version%20History.md) of SDK V2 wherein you can implement your App with features such as:

* Pay using Credit Card/Debit Card & Netbanking 
* Pay Using Citrus Cash - user can make a payment using Citrus Cash account if he has sufficient amount for payment. 
* Get Citrus Cash balance
* Load Money – money can be loaded to user’s account using CC/DC/NB option.
* Save Cards and Bank options to user account for faster checkout
* Fetch Saved cards of user
* Delete Cards
* Send Money
* Logout your user from App
* Withdraw your Money

<h5>Few more points on SDK V3</h5>
___
   * SDK response time optimization-  over 5x faster response compared to V2
   * Everything is Object now. No more JSON parsing required.
   * Improved WebView. WebView is handled by SDK.  
   * Uniform responses from SDK across different API’s
   * Support for Citrus Cash.
   * Added support to gradle/Android Studio
   * Less Integration Time Required.
   * Zero click payment using Citrus Cash.

<h2> Prerequisites </h2>
* <a href="docs/Prerequisite.md" target="_blank">Before you Start</a>

<h2>Lets Start...</h2>

<b>Initiation</b>
* <a href="docs/InitSDK.md" target="_blank">Initiate Citrus SDK</a>
* <a href="docs/enable%20logs%20.md" target="_blank">How to enable logs </a>

<b> Implementation for User creation and management </b>

* <a href="docs/isUserSignedIn.md" target="_blank">See if the User is logged/Signed in   </a>
* <a href="docs/isCitrusMember.md" target="_blank">To check if the User is a Citrus member or not? </a>
* <a href="docs/Sign%20Up%20User.md" target="_blank">SignUp User </a>
* <a href="docs/Sign%20In%20User.md" target="_blank">SignIn User </a>
* <a href="docs/Reset%20Password.md" target="_blank">Reset Password </a>
* <a href="docs/Logout.md" target="_blank">Logout the User </a>


<b> Get Payment Options</b>
 
* <a href="docs/Fetch%20payment%20options.md" target="_blank">Fetch Normal(PG) Payment Options </a>(Required while making a <b>Normal</b> payment)
* <a href="docs/fetch%20load%20money%20options.md" target="_blank">Fetch Load Money Payment Options </a>(Required during <b>Adding/Loading</b> Money to Wallet)

<b>Wallet</b>

* <a href="docs/Get%20Balance.md" target="_blank">Get Citrus Cash balance </a>(<b>Note:</b> User should be <b>Signed In</b> for this Implementation)
* <a href="docs/Load%20Money.md" target="_blank">Add Money/Load Money into Citrus Account </a>
* <a href="docs/Load%20using%20Saved%20Card%20&%20Net%20bank.md" target="_blank">Add Money/Load Money into Citrus Account using Saved cards </a>


<b>Payment</b>

* <a href="docs/CC%20%2CDC%20%2CNB%20Direct%20Payment.md" target="_blank">Pay using Credit/Debit Card & Net Banking</a>
* <a href="docs/Get%20Wallet.md" target="_blank">Get Wallet</a> (It fetches all Saved Cards and Bank options of the User's account)
* <a href="docs/Pay%20Using%20Saved%20Cards%20and%20Bank.md" target="_blank">Pay using Saved Cards and Net banking</a>
* <a href="docs/Pay%20using%20Citrus%20Cash.md" target="_blank">Pay using Citrus Cash</a> (<b>Note:</b> User should be <b>Signed In</b> for this Implementation)

<b>How to save Cards option and Banks</b>
* <a href="docs/Save%20payment%20option.md" target="_blank">Save Debit/Credit Card and Net banking</a>

<b>Others...</b>
* <a href="docs/Send%20Money.md" target="_blank">Send Money To Your Friend</a>
* <a href="docs/Withdraw.md" target="_blank">Withdraw Money to Your Account</a>

___
<h5>How to Go Live...</h5>

* <a href="docs/Backend%20Files%20Changes.md" target="_blank">Changes inside Bill Generator and Returl Url Files</a>
* <a href="docs/Go%20Live.md" target="_blank">Keys and Environment inside citrusClient.init</a>
* <a href="docs/Progaurd%20changes.md" target="_blank">Progaurd changes</a>(If required)

___


