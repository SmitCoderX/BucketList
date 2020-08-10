package com.smitcoderx.learn.bucketlist;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ModalBottomSheet.BottomSheetListener {

    private RecyclerView recyclerView;
    private LottieAnimationView animationView;
    private TextView textTut;
    private BucketAdapter adapter;
    private ArrayList<BucketItems> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        animationView = findViewById(R.id.lottieView);
        textTut = findViewById(R.id.textTut);

        fabListener();
        ListItems();
        recyclerStuff();
    }

    public void onItemRemove(int position) {
        items.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public void recyclerStuff() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        adapter = new BucketAdapter(items);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.onBinClickListener(new BucketAdapter.onBinClickListener() {
            @Override
            public void onBinClick(int position) {
                onItemRemove(position);
                Toast.makeText(MainActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void ListItems() {
        items = new ArrayList<>();
        items.add(new BucketItems("Abhishek", "2"));
    }

    public void fabListener() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModalBottomSheet sheet = new ModalBottomSheet();
                sheet.show(getSupportFragmentManager(), "modal_sheet");
                animationView.setVisibility(View.GONE);
                textTut.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onButtonClick(CharSequence editText, CharSequence editQuantity) {

    }
}