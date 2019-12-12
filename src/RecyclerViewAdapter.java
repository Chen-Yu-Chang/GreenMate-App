package com.example.a327_prototype_1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
//recycle view adapter id for user to know what categories of recycle there are
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> myImageNames = new ArrayList<>();
    private ArrayList<String> myImages = new ArrayList<>();

    private Context myContext;

    public RecyclerViewAdapter(Context myContext, ArrayList<String> myImageNames, ArrayList<String> myImages) {
        this.myImageNames = myImageNames;
        this.myImages = myImages;
        this.myContext = myContext;

    }//each kind of recycle's format of listing

    //acting with the database activity to show the categories and the pictures
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    //format of images
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        Glide.with(myContext)
                .asBitmap()
                .load(myImages.get(position))
                .into(holder.image);
        holder.name.setText(myImageNames.get(position));
        holder.listItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + myImageNames.get(position));
                Toast.makeText(myContext, myImageNames.get(position), Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public int getItemCount() {
        return myImageNames.size();
    }
    //putting all in order
    public class ViewHolder extends RecyclerView.ViewHolder {
        CircularImageView image;
        TextView name;
        RelativeLayout listItemLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.circle_image);
            name = itemView.findViewById(R.id.name);
            listItemLayout = itemView.findViewById(R.id.list_item_layout);

        }
    }

}
