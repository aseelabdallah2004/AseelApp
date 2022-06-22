package com.example.signuplogin;

import static com.google.common.reflect.Reflection.initialize;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


    public class AllRecipesActivity extends AppCompatActivity {

        private RecyclerView rvAllRec;
        AdapterRecipe adapter;
        FirebaseServices fbs;
        ArrayList<Recipe> recs;
        MyCallback myCallback;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_all_recipes);


            fbs = FirebaseServices.getInstance();
            recs = new ArrayList<Recipe>();
            readData();
            myCallback = new MyCallback() {
                @Override
                public void onCallback(List<Recipe> restsList) {
                    RecyclerView recyclerView = findViewById(R.id.rvRecipesAllRec);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter = new AdapterRecipe(getApplicationContext(), recs);
                    recyclerView.setAdapter(adapter);
                }
            };


            // set up the RecyclerView
        /*
        RecyclerView recyclerView = findViewById(R.id.rvRestsAllRest);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterRestaurant(this, rests);
        recyclerView.setAdapter(adapter);*/
        }

        private void readData() {
            try {

                fbs.getFirestore().collection("recipes")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        recs.add(document.toObject(Recipe.class));
                                    }

                                    myCallback.onCallback(recs);
                                } else {
                                    Log.e("AllRestActivity: readData()", "Error getting documents.", task.getException());
                                }
                            }
                        });
            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(), "error reading!" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }