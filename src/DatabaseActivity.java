package com.example.a327_prototype_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

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

    private void initImageBitmap() {
        Log.d(TAG, "initImageBitmap: preparing bitmaps");
        myImageUrls.add("https://previews.123rf.com/images/artzzz/artzzz1806/artzzz180600303/104801419-bunch-of-different-empty-soda-aluminium-cans.jpg");
        myNames.add("Aluminum can");

        myImageUrls.add("https://static.nashvillewraps.com/images/sku/MP0KR-XLARGE.jpg");
        myNames.add("Paper bag");

        myImageUrls.add("https://www.niagaraproduce.com/content//20180226_191041.jpg");
        myNames.add("Glass milk bottle");

        myImageUrls.add("https://i.pinimg.com/originals/bc/da/f9/bcdaf98a142add259be780283ac82914.jpg");
        myNames.add("Plastic milk jug");

        myImageUrls.add("https://i.pinimg.com/originals/f5/1d/08/f51d08be05919290355ac004cdd5c2d6.png");
        myNames.add("Pikachu");

        myImageUrls.add("https://thehappypuppysite.com/wp-content/uploads/2015/09/The-Siberian-Husky-HP-long.jpg");
        myNames.add("Husky");

        myImageUrls.add("https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Irelia_17.jpg");
        myNames.add("Irelia");

        initRecyclerView();


    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: initialize recyclerView.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, myNames,myImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
