package ca.unb.mobiledev.stacks.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.Random;

import ca.unb.mobiledev.stacks.HomeActivity;
import ca.unb.mobiledev.stacks.R;
import ca.unb.mobiledev.stacks.databinding.FragmentHomeBinding;
import ca.unb.mobiledev.stacks.utils.CategoryObject;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        LinearLayout layout = root.findViewById(R.id.lin_layout);

        for(CategoryObject o: HomeActivity.categoryObjects){
            if(o.getText().getVisibility() == View.VISIBLE){
                TextView category = new TextView(getContext());
                TextView budget = new TextView(getContext());
                ProgressBar progressBar = new ProgressBar(getContext(), null,
                        android.R.attr.progressBarStyleHorizontal);
                progressBar.setMax(Integer.parseInt(o.getText().getText().toString()));
                Random random = new Random();
                progressBar.setProgress(random.nextInt(progressBar.getMax()));
                category.setTextSize(22);
                budget.setTextSize(18);
                category.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                budget.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                category.setText(o.getName());
                budget.setText("$"+progressBar.getProgress()+ " used out of $"+ o.getText().getText().toString());
                layout.addView(category);
                layout.addView(budget);
                progressBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                layout.addView(progressBar);
            }
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}