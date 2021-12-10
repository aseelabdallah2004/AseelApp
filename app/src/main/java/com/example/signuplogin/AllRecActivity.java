package com.example.signuplogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AllRecActivity extends AppCompatActivity {
    private RecyclerView rvAllRest;
    AdapterRecipe adapter;
    FirebaseServices fbs;
    ArrayList<Recipe> rests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_rec);
        fbs = FirebaseServices.getInstance();
        rests = new ArrayList<Recipe>();
        readData();

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvRestsAllRec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterRecipe(this, rests);
        recyclerView.setAdapter(adapter);
    }

    private void readData() {
        fbs.getFirestore().collection("recipes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                rests.add(document.toObject(Recipe.class));
                            }
                        } else {
                            Log.e("AllRecActivity: readData()", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}
