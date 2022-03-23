package ca.unb.mobiledev.ocrapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.unb.mobiledev.ocrapp.ui.MyAdapter;

public class OCR_Activity extends AppCompatActivity {
    private MyAdapter adapter;
    private ListView listView;
    Button button_capture , button_copy;
    Bitmap bitmap;
    private static final int REQUEST_CAMERA_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_capture = findViewById(R.id.button_capture);
        button_copy = findViewById(R.id.button_copy);
        listView = findViewById(R.id.listview);

        if(ContextCompat.checkSelfPermission(OCR_Activity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(OCR_Activity.this, new String[]{
                Manifest.permission.CAMERA
            },REQUEST_CAMERA_CODE);
        }

        button_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(OCR_Activity.this);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode == RESULT_OK){
                Uri resultUir = result.getUri();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),resultUir);
                    Map<String, Double> cinemas = getTextFromImage(bitmap);
                    MyAdapter adapter = new MyAdapter(cinemas);
                    listView.setAdapter(adapter);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static boolean isNumeric(String strNum){
        if(strNum == null){
            return false;
        }
        try{
            double d = Double.parseDouble(strNum);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    private List<Double> getPriceList(List<String> list){
        List<Double> pList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            if(isNumeric(list.get(i))){
                pList.add(Double.parseDouble(list.get(i)));
            }
        }
        return pList;
    }

    private List<String> getProductList(List<String> list){
        List<String> pList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            if(!isNumeric(list.get(i))){
                pList.add(list.get(i));
            }
        }
        return pList;
    }

    public static Map<String, Double> listToMap(List<String> keys, List<Double> values){
        if(keys.size() != values.size()){
            throw new IllegalArgumentException("Lists must be equal");
        }
        Map<String,Double> map = new HashMap<>();
        for(int i = 0; i < keys.size(); i++){
            map.put(keys.get(i), values.get(i));
        }
        return map;
    }

    private Map<String,Double> getTextFromImage(Bitmap bitmap){
        String[] strArr = null;
        List<String> list = new ArrayList<>();
        Map<String, Double> map = new HashMap<>();
        TextRecognizer recognizer = new TextRecognizer.Builder(this).build();
        if(!recognizer.isOperational()){
            Toast.makeText(OCR_Activity.this,"Error Occured", Toast.LENGTH_SHORT).show();
        }
        else{
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> textBlockSparseArray = recognizer.detect(frame);
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 0; i < textBlockSparseArray.size();i++){
                TextBlock textBlock = textBlockSparseArray.valueAt(i);
                Log.d("textBlock", textBlock.toString());
                stringBuilder.append(textBlock.getValue());
                stringBuilder.append("\n");
                Log.d("Helloooo", String.valueOf(i));
            }
            strArr = stringBuilder.toString().split("[\\$\\n]");
            list = new ArrayList<String>(Arrays.asList(strArr));
            list.removeAll(Arrays.asList("",null));
            //Collections.sort(list);
            List<Double> price_List = getPriceList(list);
            List<String> product_List = getProductList(list);
            Log.d("PRICE LIST", price_List.toString());
            Log.d("PRODUCT LIST", product_List.toString());
            map = listToMap(product_List,price_List);

            button_capture.setText("Retake");
            button_copy.setVisibility(View.VISIBLE);
        }
        Log.d("My arr", list.toString());
        Log.d("My arr size", String.valueOf(list.size()));

        return map;
    }
}