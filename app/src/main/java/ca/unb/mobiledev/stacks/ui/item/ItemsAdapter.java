package ca.unb.mobiledev.stacks.ui.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ca.unb.mobiledev.stacks.entity.Item;
import ca.unb.mobiledev.stacks.R;

public class ItemsAdapter extends ArrayAdapter<Item> {
    public ItemsAdapter(Context context, List<Item> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Item item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_row, parent, false);
        }

        // Lookup view for data population
        TextView itemName = convertView.findViewById(R.id.itemName);
        TextView itemPrice = convertView.findViewById(R.id.itemPrice);
        TextView itemCategory = convertView.findViewById(R.id.itemCategory);

        itemName.setText(item.getName());
        itemPrice.setText(String.valueOf(item.getPrice()));
        itemCategory.setText((String.valueOf(item.getPrice())));

        // Return the completed view to render on screen
        return convertView;
    }
}
