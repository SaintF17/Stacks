package ca.unb.mobiledev.stacks.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ca.unb.mobiledev.stacks.HomeActivity;
import ca.unb.mobiledev.stacks.MyAdapter;
import ca.unb.mobiledev.stacks.R;
import ca.unb.mobiledev.stacks.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Implemented recyclerview instead of manually creating view. See Lab 4
        MyAdapter adapter = new MyAdapter(HomeActivity.activeCategories, getContext());
        RecyclerView recyclerView = root.findViewById(R.id.rvCategory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}