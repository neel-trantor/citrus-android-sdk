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


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.citrus.sdk.Callback;
import com.citrus.sdk.CitrusClient;
import com.citrus.sdk.response.CitrusError;
import com.citrus.sdk.response.CitrusResponse;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserManagementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserManagementFragment extends Fragment implements View.OnClickListener {


    private UserManagementInteractionListener mListener = null;
    private Button btnLinkUser = null;
    private Button btnSignUp = null;
    private Button btnSignIn = null;
    private Button btnResetPassword = null;

    private EditText editEmailId = null;
    private EditText editMobileNo = null;
    private EditText editPassword = null;
    private TextView textMessage = null;

    private CitrusClient citrusClient = null;
    private Context context = null;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment UserManagementFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserManagementFragment newInstance(Context context) {
        return new UserManagementFragment();
    }

    public UserManagementFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user_management, container, false);

        context = getActivity();

        editEmailId = (EditText) rootView.findViewById(R.id.edit_email_id);
        editMobileNo = (EditText) rootView.findViewById(R.id.edit_mobile_no);
        editPassword = (EditText) rootView.findViewById(R.id.edit_password);
        textMessage = (TextView) rootView.findViewById(R.id.txt_user_mgmt_message);

        btnLinkUser = (Button) rootView.findViewById(R.id.btn_link_user);
        btnSignIn = (Button) rootView.findViewById(R.id.btn_signin);
        btnSignUp = (Button) rootView.findViewById(R.id.btn_signup);

        btnResetPassword = (Button) rootView.findViewById(R.id.btn_reset_password);

        btnLinkUser.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
        btnResetPassword.setOnClickListener(this);


        citrusClient = CitrusClient.getInstance(context.getApplicationContext());

        return rootView;
    }

    private void linkUser() {
        String emailId = editEmailId.getText().toString();
        String mobileNo = editMobileNo.getText().toString();

        citrusClient.isCitrusMember(emailId, mobileNo, new Callback<Boolean>() {
            @Override
            public void success(Boolean isMember) {
                if (isMember) {
                    btnSignIn.setVisibility(View.VISIBLE);
                    btnSignUp.setVisibility(View.GONE);
                    textMessage.setText("User is already a Citrus Member. Please Sign In the user.");
                } else {
                    btnSignUp.setVisibility(View.VISIBLE);
                    btnSignIn.setVisibility(View.GONE);
                    textMessage.setText("User is not a Citrus Member. Please Sign Up the user.");
                }

                btnLinkUser.setVisibility(View.GONE);
                editPassword.setVisibility(View.VISIBLE);
            }

            @Override
            public void error(CitrusError error) {
                textMessage.setText(error.getMessage());
            }
        });
    }

    private void signIn() {
        String emailId = editEmailId.getText().toString();
        String password = editPassword.getText().toString();

        citrusClient.signIn(emailId, password, new Callback<CitrusResponse>() {
            @Override
            public void success(CitrusResponse citrusResponse) {
                Utils.showToast(context, citrusResponse.getMessage());
                textMessage.setText(citrusResponse.getMessage());

                mListener.onShowWalletScreen();
            }

            @Override
            public void error(CitrusError error) {
                Utils.showToast(context, error.getMessage());
                textMessage.setText(error.getMessage());
            }
        });
    }

    private void signUp() {
        String emailId = editEmailId.getText().toString();
        String mobileNo = editMobileNo.getText().toString();
        String password = editPassword.getText().toString();

        citrusClient.signUp(emailId, mobileNo, password, new Callback<CitrusResponse>() {
            @Override
            public void success(CitrusResponse citrusResponse) {
                Utils.showToast(context, citrusResponse.getMessage());
                textMessage.setText(citrusResponse.getMessage());

                btnSignUp.setVisibility(View.GONE);
                btnSignIn.setVisibility(View.VISIBLE);

                mListener.onShowWalletScreen();
            }

            @Override
            public void error(CitrusError error) {
                Utils.showToast(context, error.getMessage());
                textMessage.setText(error.getMessage());
            }
        });
    }

    private void resetPassword() {
        String emailId = editEmailId.getText().toString();

        citrusClient.resetPassword(emailId, new Callback<CitrusResponse>() {
            @Override
            public void success(CitrusResponse citrusResponse) {
                Utils.showToast(context, citrusResponse.getMessage());
                textMessage.setText(citrusResponse.getMessage());
            }

            @Override
            public void error(CitrusError error) {
                Utils.showToast(context, error.getMessage());
                textMessage.setText(error.getMessage());
            }
        });
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (UserManagementInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement UserManagementInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onClick(View v) {

        textMessage.setText("");

        switch (v.getId()) {
            case R.id.btn_link_user:
                linkUser();
                break;
            case R.id.btn_signin:
                signIn();
                break;
            case R.id.btn_signup:
                signUp();
                break;
            case R.id.btn_reset_password:
                resetPassword();
                break;
        }
    }

    public interface UserManagementInteractionListener {
        void onShowWalletScreen();
    }
}
