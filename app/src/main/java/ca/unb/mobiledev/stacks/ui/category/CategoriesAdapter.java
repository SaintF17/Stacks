package ca.unb.mobiledev.stacks.ui.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ca.unb.mobiledev.stacks.entity.Category;
import ca.unb.mobiledev.stacks.R;

public class CategoriesAdapter extends ArrayAdapter<Category> {
    public CategoriesAdapter(Context context, List<Category> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Category category = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_row_simple, parent, false);
        }

        // Lookup view for data population
        TextView categoryName = convertView.findViewById(R.id.categoryName);
        TextView categoryBudget = convertView.findViewById(R.id.budgetText);

        categoryName.setText(category.getName());
        categoryBudget.setText(String.valueOf(category.getBudgetAmount()));

        // Return the completed view to render on screen
        return convertView;
    }
}
