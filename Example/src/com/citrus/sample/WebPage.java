/*
   Copyright 2014 Citrus Payment Solutions Pvt. Ltd.
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
     http://www.apache.org/licenses/LICENSE-2.0
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package com.citrus.sample;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.citrus.mobile.Callback;
import com.citrus.mobile.CitrusWebViewClient;

public class WebPage extends Activity {
    WebView webView;
    
    BroadcastReceiver receiver;
    
    Button submit;
    
    String js, otp;
    
    TextView otpview;
	Callback loadmoneycb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page);
        
//        initReceiver();
        
        initwebview();
        
        String url = getIntent().getStringExtra("url");
        
        webView.loadUrl(url);

    }
        
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
	private void initwebview() {
    	
    	loadmoneycb = new Callback() {
			
			@Override
			public void onTaskexecuted(String success, String error) {
				Toast.makeText(getApplicationContext(), success + " : " + error, Toast.LENGTH_LONG).show();
			}
		};
    	
    	webView = (WebView) this.findViewById(R.id.webview);
        
        submit = (Button) this.findViewById(R.id.submitButton);
        
        otpview = (TextView) this.findViewById(R.id.otpText);

        webView.getSettings().setJavaScriptEnabled(true);
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        
        webView.addJavascriptInterface(new JsInterface(), "CitrusResponse");

        webView.setWebViewClient(new CitrusWebViewClient(loadmoneycb));
		
		webView.setWebChromeClient(new WebChromeClient());
	}
    
    private class JsInterface {

        @JavascriptInterface
        public void pgResponse(String response) {
            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
        }

        @JavascriptInterface
        public void loadWalletResponse(String response) {

               if (response.contains(":")) {
                String decodeResp[] = response.split(":");
                if (decodeResp.length > 0) {
                    if (TextUtils.equals(decodeResp[0], "#SUCCESSFUL")) {
                        Toast.makeText(getApplicationContext(), "Your wallet is loaded Successfully.", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        //transaction was fail
                        Toast.makeText(getApplicationContext(), "Wallet Load Failed.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }

        }


    }
}