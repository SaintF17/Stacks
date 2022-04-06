package ca.unb.mobiledev.stacks.ui.charts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;

import java.util.ArrayList;
import java.util.List;

import ca.unb.mobiledev.stacks.SetupActivity;
import ca.unb.mobiledev.stacks.utils.CategoryObject;

public class ChartsViewModel extends ViewModel {

    private MutableLiveData<List<DataEntry>> mData;

    public ChartsViewModel() {

        List<DataEntry> data = new ArrayList<>();
        for(int i = 0; i < SetupActivity.activeCategories.size(); i++){
            data.add(new ValueDataEntry(SetupActivity.activeCategories.get(i).getName(),
                    SetupActivity.activeCategories.get(i).getExpense()));
        }
        mData = new MutableLiveData<>();
        mData.setValue(data);
    }

    public LiveData<List<DataEntry>> getList() {
        return mData;
    }
}