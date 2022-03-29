package ca.unb.mobiledev.stacks.ui.charts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.charts.Pie;

import java.util.List;

import ca.unb.mobiledev.stacks.R;
import ca.unb.mobiledev.stacks.databinding.FragmentChartsBinding;

public class ChartsFragment extends Fragment {

    private ChartsViewModel chartsViewModel;
    private FragmentChartsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        chartsViewModel = new ViewModelProvider(this).get(ChartsViewModel.class);

        binding = FragmentChartsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final AnyChartView chartView = binding.anyChartView;
        Pie pie = AnyChart.pie();
        chartsViewModel.getList().observe(getViewLifecycleOwner(), data -> {
            pie.data(data);
            chartView.setChart(pie);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}