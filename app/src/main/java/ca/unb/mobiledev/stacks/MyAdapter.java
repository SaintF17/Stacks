package ca.unb.mobiledev.stacks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

import ca.unb.mobiledev.stacks.utils.CategoryObject;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<CategoryObject> categories;
    private final Context context;

    public MyAdapter(ArrayList<CategoryObject> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryObject object = categories.get(position);
        holder.setDetails(object);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView category;
        public TextView budget;
        public ProgressBar progressBar;
        public ViewHolder(View v) {
            super(v);
            category = v.findViewById(R.id.categoryName);
            budget = v.findViewById(R.id.budgetText);
            progressBar = v.findViewById(R.id.progressBar);
        }
        public void setDetails(CategoryObject o){
            category.setText(o.getName());
            progressBar.setMax(Integer.parseInt(o.getText().getText().toString()));
            Random random = new Random();
            progressBar.setProgress(random.nextInt(progressBar.getMax()));
            budget.setText("$" + progressBar.getProgress() + " used out of $" + o.getText().getText().toString());
        }
    }
}
