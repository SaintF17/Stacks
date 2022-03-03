package ca.unb.mobiledev.stacks.ui.charts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChartsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ChartsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Statistics (bar and pie charts) go here later.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}