package com.example.bitcoinminertestwork.ui.referrals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReferralsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ReferralsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is referrals fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}