package ca.unb.mobiledev.stacks;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Map;

import ca.unb.mobiledev.stacks.utils.CategoryObject;

public class CategoryObjectAdapter extends BaseAdapter {
    private final ArrayList mData;

    public CategoryObjectAdapter(ArrayList<CategoryObject> cat){
        mData = new ArrayList();
        mData.addAll(SetupActivity.activeCategories);
    }


    public int getCount() {
        return mData.size();
    }

    @Override
    public CategoryObject getItem(int pos) {
        return (CategoryObject) mData.get(pos);
    }

    public CategoryObject getItem(CategoryObject position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView display_name;
        public TextView display_number;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.ocr_adapter_item, parent, false);
        } else {
            result = convertView;
        }

        CategoryObject item = getItem(position);

        ((TextView) result.findViewById(android.R.id.text1)).setText(item.getName());
        ((TextView) result.findViewById(android.R.id.text2)).setText(Double.toString(item.getExpense()));

        return result;
    }
}