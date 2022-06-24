package com.example.signuplogin;

import static com.google.common.reflect.Reflection.initialize;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        private RecyclerView rvRecsAllRec;
        AdapterRecipe adapter;
        FirebaseServices fbs;
        ArrayList<Recipe> recs;
        MyCallback myCallback;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_all_recipes);
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
            ActionBar actionBar = getSupportActionBar();



            fbs = FirebaseServices.getInstance();
            recs = new ArrayList<Recipe>();
            readData();
            if (recs.size() == 0)
                fillRecipes();
            myCallback = new MyCallback() {
                    @Override
                    public void onCallback(List<Recipe> recipeList) {
                        RecyclerView recyclerView = findViewById(R.id.rvRecsAllRec);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new AdapterRecipe(getApplicationContext(), recs);
                        recyclerView.setAdapter(adapter);
                    }
            };
            actionBar.setTitle("  A cup of yum");
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);


        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.toolbar, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                //case R.id.miSearch:
                // User chose the "Settings" item, show the app settings UI...
                //return true;

                case R.id.miSignup:
                    // User chose the "Favorite" action, mark the current item
                    // as a favorite...
                    return true;

                case R.id.miAddRecipe:

                    return true;

                default:
                    // If we got here, the user's action was not recognized.
                    // Invoke the superclass to handle it.
                    return super.onOptionsItemSelected(item);

            }
        }


        // set up the RecyclerView
        /*
        RecyclerView recyclerView = findViewById(R.id.rvRestsAllRest);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterRestaurant(this, rests);
        recyclerView.setAdapter(adapter);*/


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
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "error reading!" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        public void fillRecipes()
        {
            Recipe rp1 = new Recipe();
            //Recipe rp1 = new Recipe();
            //Recipe rp1 = new Recipe();

            recs.add(rp1);
        }
    }