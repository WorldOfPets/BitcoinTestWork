package com.example.bitcoinminertestwork.ui.withdrawals;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bitcoinminertestwork.R;

public class WithdrawlsFragment extends Fragment {

    private WithdrawlsViewModel mViewModel;

    public static WithdrawlsFragment newInstance() {
        return new WithdrawlsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_withdrawls, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WithdrawlsViewModel.class);
        // TODO: Use the ViewModel
    }

}