package ca.unb.mobiledev.stacks;

import  android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Map;

public class OCRAdapter extends BaseAdapter {
    private final ArrayList mData;

    public OCRAdapter(Map<String, Double> map){
        mData = new ArrayList();
        mData.addAll(map.entrySet());
    }

    @Override
    public int getCount(){
        return mData.size();
    }

    @Override
    public Map.Entry<String, Double> getItem(int pos){
        return (Map.Entry) mData.get(pos);
    }

    @Override public long getItemId(int pos){
        //do
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_adapter_item, parent, false);
        } else {
            result = convertView;
        }

        Map.Entry<String, Double> item = getItem(position);

        ((TextView) result.findViewById(android.R.id.text1)).setText(item.getKey());
        ((TextView) result.findViewById(android.R.id.text2)).setText(Double.toString(item.getValue()));

        return result;
    }
}
