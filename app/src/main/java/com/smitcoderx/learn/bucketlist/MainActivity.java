package com.smitcoderx.learn.bucketlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.appus.splash.Splash;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Declarations
    private String editItem;
    private String editQuantity;
    private RecyclerView recyclerView;
    private LottieAnimationView animationView;
    private TextView textTut;
    private BucketAdapter adapter;
    private ArrayList<BucketItems> items;
    private RelativeLayout layout;
    private FloatingActionButton fab;
    private ImageView imageView;
    private EditText editTextItem;
    private EditText editTextQuantity;
    private Button buttonInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Id Stuff
        recyclerView = findViewById(R.id.recyclerView);
        animationView = findViewById(R.id.lottieView);
        textTut = findViewById(R.id.textTut);
        layout = findViewById(R.id.bottom_relative);
        imageView = findViewById(R.id.SheetInvisibler);
        fab = findViewById(R.id.fab);
        editTextItem = findViewById(R.id.editTextItem);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        buttonInsert = findViewById(R.id.buttonInsert);

        editItem = editTextItem.getText().toString();
        editQuantity = editTextQuantity.getText().toString();

        Splash.Builder builder = new Splash.Builder(this, getSupportActionBar());
        builder.perform();

        if (adapter != null) {
            recyclerView.setVisibility(View.VISIBLE);
            animationView.setVisibility(View.GONE);
            textTut.setVisibility(View.GONE);
        }

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonInsert();
                onItemInsert(0);
            }
        });

        items = new ArrayList<>();
        fabListener();
        recyclerStuff();

    }

    //For Menu Icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteAll:
                adapter.clear();
                animationBack();
                Toast.makeText(this, "Bucket Cleared", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about_dev:
                Intent intent = new Intent(MainActivity.this, DevActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    // Menu Stuff ends here

    //For Bringing back the animations
    public void animationBack() {
        animationView.setVisibility(View.VISIBLE);
        textTut.setVisibility(View.VISIBLE);
    }

    //The stuff happens when insert button is clicked
    public void buttonInsert() {
        Toast.makeText(this, "Text Inserted", Toast.LENGTH_SHORT).show();
        layout.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
        fab.setVisibility(View.VISIBLE);
        animationView.setVisibility(View.GONE);
        textTut.setVisibility(View.GONE);
    }

    //adding items when insert clicked but somehow its not showing text in list... sed lyf..
    public void onItemInsert(int position) {
        items.add(new BucketItems(editItem, editQuantity));
        adapter.notifyItemInserted(position);
    }

    //the stuff happens when bin is clicked..
    public void onItemRemove(int position) {
        items.remove(position);
        adapter.notifyItemRemoved(position);
    }

    // Recycler Stuff
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

    //Stuff that works when fab is clicked.
    public void fabListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.VISIBLE);
                layout.setVisibility(View.VISIBLE);
                fab.setVisibility(View.GONE);
            }
        });
    }

    //Basically a dummy ImageView's onClick Function..
    public void sheetInvisible(View view) {
        layout.setVisibility(View.GONE);
        fab.setVisibility(View.VISIBLE);
    }
}