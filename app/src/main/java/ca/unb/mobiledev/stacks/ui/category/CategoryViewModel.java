package ca.unb.mobiledev.stacks.ui.category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CategoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CategoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Users will be able to modify, delete, and create custom categories here.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}