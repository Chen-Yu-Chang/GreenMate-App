package com.example.a327_prototype_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
//database activity
public class DatabaseActivity extends AppCompatActivity {

    private static final String TAG = "DatabaseActivity";
    // variables
    private ArrayList<String> myNames = new ArrayList<>();
    private ArrayList<String> myImageUrls = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        Log.d(TAG, "onCreate: started.");
        initImageBitmap();
    }

    private void initImageBitmap() {//showing the user what kinds of recycle there are
        Log.d(TAG, "initImageBitmap: preparing bitmaps");
        myImageUrls.add("https://previews.123rf.com/images/artzzz/artzzz1806/artzzz180600303/104801419-bunch-of-different-empty-soda-aluminium-cans.jpg");
        myNames.add("Aluminum can");//Aluminum

        myImageUrls.add("https://static.nashvillewraps.com/images/sku/MP0KR-XLARGE.jpg");
        myNames.add("Paper bag");//Paper Bag

        myImageUrls.add("https://www.niagaraproduce.com/content//20180226_191041.jpg");
        myNames.add("Glass milk bottle");//Bottle

        myImageUrls.add("https://i.pinimg.com/originals/bc/da/f9/bcdaf98a142add259be780283ac82914.jpg");
        myNames.add("Plastic milk jug");//Jug



        initRecyclerView();


    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: initialize recyclerView.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, myNames,myImageUrls);
        //link to recycle view adapter
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}