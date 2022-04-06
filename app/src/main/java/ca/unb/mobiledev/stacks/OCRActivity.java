package ca.unb.mobiledev.stacks;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OCRActivity extends AppCompatActivity {
    private ListView listView;
    Button button_capture;
    Bitmap bitmap;
    private static final int REQUEST_CAMERA_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);

        button_capture = findViewById(R.id.button_capture);
        listView = findViewById(R.id.listview);

        if(ContextCompat.checkSelfPermission(OCRActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(OCRActivity.this, new String[]{
                Manifest.permission.CAMERA
            },REQUEST_CAMERA_CODE);
        }

        button_capture.setOnClickListener(view -> CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(OCRActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode == RESULT_OK){
                assert result != null;
                Uri resultUir = result.getUri();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),resultUir);
                    Map<String, Double> cinemas = getTextFromImage(bitmap);
                    OCRAdapter adapter = new OCRAdapter(cinemas);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Map.Entry<String, Double> item = (Map.Entry<String, Double>) adapterView.getItemAtPosition(i);
                            Intent intent = new Intent(OCRActivity.this, InputActivityOCR.class);
                            intent.putExtra("item",item.getKey());
                            intent.putExtra("price", item.getValue());
                            adapter.removeItem(i);
                            adapter.notifyDataSetChanged();
                            if(adapter.isEmpty()){
                                Intent resume = new Intent(OCRActivity.this, NavigationActivity.class);
                                startActivity(resume);
                            }
                            startActivity(intent);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static boolean isNumeric(String strNum){
        try{
            Double.parseDouble(strNum);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
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

    @SuppressLint("SetTextI18n")
    private Map<String,Double> getTextFromImage(Bitmap bitmap){
        String[] strArr;
        List<String> list;
        Map<String, Double> map = new HashMap<>();
        TextRecognizer recognizer = new TextRecognizer.Builder(this).build();
        if(!recognizer.isOperational()){
            Toast.makeText(OCRActivity.this,"Error Occurred", Toast.LENGTH_SHORT).show();
        }
        else{
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> textBlockSparseArray = recognizer.detect(frame);
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 0; i < textBlockSparseArray.size();i++){
                TextBlock textBlock = textBlockSparseArray.valueAt(i);
                stringBuilder.append(textBlock.getValue());
                stringBuilder.append("\n");
            }
            strArr = stringBuilder.toString().split("[$\\n]");
            list = new ArrayList<>(Arrays.asList(strArr));
            list.removeAll(Arrays.asList("",null));
            List<Double> price_List = getPriceList(list);
            List<String> product_List = getProductList(list);
            map = listToMap(product_List,price_List);

            button_capture.setText("Retake");
        }
        return map;
    }
}