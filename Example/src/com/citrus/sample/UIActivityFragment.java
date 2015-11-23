/*
 *
 *    Copyright 2014 Citrus Payment Solutions Pvt. Ltd.
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 * /
 */

package com.citrus.sample;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.citrus.sdk.Callback;
import com.citrus.sdk.CitrusClient;
import com.citrus.sdk.response.CitrusError;
import com.citrus.sdk.response.CitrusResponse;


/**
 * A placeholder fragment containing a simple view.
 */
public class UIActivityFragment extends Fragment implements View.OnClickListener {

    private TextView textMessage = null;
    private CitrusClient citrusClient = null;
    private Button btnSignout = null;
    private Button btnUserManagement = null;
    private Button btnWalletPayment = null;

    public UIActivityFragment() {
    }

    public static UIActivityFragment newInstance() {
        return new UIActivityFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ui, container, false);
        textMessage = (TextView) rootView.findViewById(R.id.txt_message);

        btnUserManagement = (Button) rootView.findViewById(R.id.btn_user_management);
        btnWalletPayment = (Button) rootView.findViewById(R.id.btn_user_wallet);
        btnSignout = (Button) rootView.findViewById(R.id.btn_logout);
        btnSignout.setOnClickListener(this);

        citrusClient.isUserSignedIn(new Callback<Boolean>() {
            @Override
            public void success(Boolean isUserLoggedIn) {
                if (isUserLoggedIn) {
                    textMessage.setText("Welcome " + citrusClient.getUserEmailId());
                    btnWalletPayment.setVisibility(View.VISIBLE);
                    btnUserManagement.setVisibility(View.GONE);
                    btnSignout.setVisibility(View.VISIBLE);
                } else {
                    textMessage.setText("Please Sign In or Sign Up the user.");
                    btnWalletPayment.setVisibility(View.GONE);
                    btnUserManagement.setVisibility(View.VISIBLE);
                    btnSignout.setVisibility(View.GONE);
                }
            }

            @Override
            public void error(CitrusError error) {
                textMessage.setText(error.getMessage());
            }
        });
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        citrusClient = CitrusClient.getInstance(getActivity().getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        citrusClient.signOut(new Callback<CitrusResponse>() {
            @Override
            public void success(CitrusResponse citrusResponse) {
                Utils.showToast(getActivity(), citrusResponse.getMessage());

                textMessage.setText("Please Sign In or Sign Up the user.");
                btnSignout.setVisibility(View.GONE);
                btnUserManagement.setVisibility(View.VISIBLE);
                btnWalletPayment.setVisibility(View.GONE);
            }

            @Override
            public void error(CitrusError error) {
                Utils.showToast(getActivity(), error.getMessage());
            }
        });
    }
}
