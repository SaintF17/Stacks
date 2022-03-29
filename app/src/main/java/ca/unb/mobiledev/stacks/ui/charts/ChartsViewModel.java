package ca.unb.mobiledev.stacks.ui.charts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;

import java.util.ArrayList;
import java.util.List;

public class ChartsViewModel extends ViewModel {

    private MutableLiveData<List<DataEntry>> mData;

    public ChartsViewModel() {

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("John", 10000));
        data.add(new ValueDataEntry("Jake", 12000));
        data.add(new ValueDataEntry("Peter", 18000));

        mData = new MutableLiveData<>();
        mData.setValue(data);
    }

    public LiveData<List<DataEntry>> getList() {
        return mData;
    }
}