package com.citrus.sample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.citrus.mobile.CType;
import com.citrus.sdk.classes.Amount;
import com.viewpagerindicator.TabPageIndicator;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WalletPaymentFragment} interface
 * to handle interaction events.
 * Use the {@link CardPaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardPaymentFragment extends Fragment {

    private static final String[] OPTIONS = new String[]{"SAVED\nACCOUNTS", "CREDIT\nCARD", "DEBIT\nCARD", "NET\nBANKING"};
    private Utils.PaymentType paymentType = null;
    private Utils.DPRequestType dpRequestType = null;
    private Amount amount = null;
    private String couponCode = null;
    private Amount alteredAmount = null;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CardPaymentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CardPaymentFragment newInstance(Utils.PaymentType paymentType, Amount amount) {
        CardPaymentFragment fragment = new CardPaymentFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("paymentType", paymentType);
        bundle.putParcelable("amount", amount);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static CardPaymentFragment newInstance(Utils.DPRequestType dpRequestType, Amount originalAmount, String couponCode, Amount alteredAmount) {
        CardPaymentFragment fragment = new CardPaymentFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("paymentType", Utils.PaymentType.DYNAMIC_PRICING);
        bundle.putSerializable("dpRequestType", dpRequestType);
        bundle.putParcelable("amount", originalAmount);
        bundle.putParcelable("alteredAmount", alteredAmount);
        bundle.putString("couponCode", couponCode);
        fragment.setArguments(bundle);
        return fragment;
    }

    public CardPaymentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            Bundle bundle = getArguments();
            paymentType = (Utils.PaymentType) bundle.getSerializable("paymentType");
            dpRequestType = (Utils.DPRequestType) bundle.getSerializable("dpRequestType");
            amount = bundle.getParcelable("amount");
            alteredAmount = bundle.getParcelable("alteredAmount");
            couponCode = bundle.getString("couponCode");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // create ContextThemeWrapper from the original Activity Context with the custom theme
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.PagerIndicatorDefaultNewWithDivider);

        // clone the inflater using the ContextThemeWrapper
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);

        // Inflate the layout for this fragment
        View rootView = localInflater.inflate(R.layout.fragment_card_payment, container, false);

        FragmentStatePagerAdapter adapter = new SavePayAdapter(getChildFragmentManager());
        ViewPager pager = (ViewPager) rootView.findViewById(R.id.pager);
        pager.setOffscreenPageLimit(4);
        pager.setAdapter(adapter);
        TabPageIndicator indicator = (TabPageIndicator) rootView.findViewById(R.id.indicator);
        indicator.setViewPager(pager);

        return rootView;
    }

    public class SavePayAdapter extends FragmentStatePagerAdapter {
        public SavePayAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                if (paymentType == Utils.PaymentType.DYNAMIC_PRICING) {
                    return SavedOptionsFragment.newInstance(dpRequestType, amount, couponCode, alteredAmount);
                } else {
                    return SavedOptionsFragment.newInstance(paymentType, amount);
                }
            } else if (position == 1) {
                if (paymentType == Utils.PaymentType.DYNAMIC_PRICING) {
                    return CreditDebitCardFragment.newInstance(dpRequestType, CType.CREDIT, amount, couponCode, alteredAmount);
                } else {
                    return CreditDebitCardFragment.newInstance(paymentType, CType.CREDIT, amount);
                }
            }
            if (position == 2) {
                if (paymentType == Utils.PaymentType.DYNAMIC_PRICING) {
                    return CreditDebitCardFragment.newInstance(dpRequestType, CType.DEBIT, amount, couponCode, alteredAmount);
                } else {
                    return CreditDebitCardFragment.newInstance(paymentType, CType.DEBIT, amount);
                }
            } else {
                if (paymentType == Utils.PaymentType.DYNAMIC_PRICING) {
                    return NetbankingFragment.newInstance(dpRequestType, amount, couponCode, alteredAmount);
                } else {
                    return NetbankingFragment.newInstance(paymentType, amount);
                }
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return OPTIONS[position];
        }

        @Override
        public int getCount() {
            return OPTIONS.length;
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
