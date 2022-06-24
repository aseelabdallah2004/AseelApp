package com.example.signuplogin;

import static com.example.signuplogin.RecipeType.breakfast;
import static com.example.signuplogin.RecipeType.lunch;
import static com.example.signuplogin.RecipeType.vegan;
import static com.google.common.reflect.Reflection.initialize;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
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
            ActionBar actionBar = getSupportActionBar();

            actionBar.setTitle("  RestApp");
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

                case R.id.miAddRecipe:
                    gotoAddRec();
                    return true;


                case R.id.miSignup:
                    showDialog1();
                    fbs.getAuth().signOut();
                    return true;

                default:

                    return super.onOptionsItemSelected(item);

            }
        }

        private void showDialog1() {
            new AlertDialog.Builder(this)
                    .setTitle("Alert!")
                    .setMessage("Are you sure you want to logout!?")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        private void gotoAddRec() {
            Intent i = new Intent(this, AddRecActivity.class);
            startActivity(i);
        }

        private void fillRecipes() {
            Recipe rp1 = new Recipe("Strawberry Nice Cream","This luscious strawberry nice cream is a wonderful healthy ice cream alternative. It's all fruit, dairy-free, vegan and contains no added sugar, but is bursting with sweet berry flavor. And it takes just minutes to whip up this easy healthy dessert in a food processor if you freeze the fruit ahead. While you'll get the best flavor from fresh berries, if you don't have them on hand, feel free to use store-bought frozen fruit in this recipe. Serve the nice cream on its own or top it with more fresh berries for a refreshing summer treat.","1 pound fresh strawberries,2 medium bananas,1 tablespoon fresh lemon juice,¼ cup ice-cold water, as needed","Serving Size: 1 cup\n" +
                    "Per Serving: 191 calories; protein 1.4g; carbohydrates 22.5g; dietary fiber 3.8g; sugars 12.9g; fat 0.5g; saturated fat 0.1g; vitamin a iu 51.6IU; vitamin c 73.3mg; folate 39.8mcg; calcium 21.8mg; iron 0.6mg; magnesium 31mg; potassium 388.8mg; sodium 2.2mg; thiamin 0.1mg.","350cal","step1:Hull and coarsely chop strawberries. Peel and coarsely chop bananas. Spread the strawberries and bananas on separate sides of one baking sheet or on two sheets. Freeze until solid, at least 12 hours.step2:Let the strawberries thaw at room temperature for 15 minutes. Transfer to a food processor; pulse until finely chopped, about 10 pulses. Add the frozen bananas and lemon juice; process until smooth, 1 to 1 1/2 minutes, adding up to 1/4 cup cold water if needed to achieve desired consistency, stopping to scrape down sides of bowl as needed. Serve immediately or, for a firmer texture, transfer to a freezer-safe container and freeze for up to 30 minutes.",breakfast,"");
            Recipe rp2 = new Recipe("Salmon Rice Bowl","Inspired by the viral TikTok trend, this tasty bowl makes for a satisfying lunch or dinner. With a handful of healthy ingredients, like instant brown rice, heart-healthy salmon and lots of crunchy veggies, you'll have a filling and flavorful meal in just 25 minutes. Looking to cut down on carbs? Try swapping in riced cauliflower in place of the brown rice.","1 cup instant brown rice\n" +
                    "1 cup water\n" +
                    "4 ounces salmon, preferably wild\n" +
                    "1 teaspoon avocado oil\n" +
                    "⅛ teaspoon kosher salt\n" +
                    "2 tablespoons mayonnaise\n" +
                    "1 ½ teaspoons Sriracha\n" +
                    "1 ½ teaspoons 50%-less-sodium tamari\n" +
                    "1 teaspoon mirin\n" +
                    "½ teaspoon freshly grated ginger\n" +
                    "¼ teaspoon crushed red pepper\n" +
                    "⅛ teaspoon kosher salt\n" +
                    "½ ripe avocado, chopped\n" +
                    "½ cup chopped cucumber\n" +
                    "¼ cup spicy kimchi\n" +
                    "12 (4-inch) sheets nori (roasted seaweed)","Serving Size: 1 bowl\n" +
                    "Per Serving: 481 calories; protein 18g; carbohydrates 47g; dietary fiber 6g; sugars 3g; added sugar 1g; fat 25g; saturated fat 4g; mono fat 10g; poly fat 9g; cholesterol 37mg; vitamin a iu 1177IU; vitamin b3 niacin 8mg; vitamin b12 2mcg; vitamin c 14mg; vitamin d iu 1IU; vitamin e iu 3IU; folate 94mg; vitamin k 45mg; sodium 687mg; calcium 41mg; iron 2mg; magnesium 101mg; phosphorus 166mg; potassium 747mg; zinc 2mg; omega 3 fatty acid 2g; omega 6 fatty acid 7g; niacin equivalents 10mg; selenium 22mcg.","481cal","Step 1\n" +
                    "Preheat oven to 400ºF. Line a small rimmed baking sheet with foil. Place salmon on the prepared pan. Drizzle with oil; season with salt. Bake until an instant-read thermometer inserted in the thickest part registers 125ºF, 8 to 10 minutes.\n" +
                    "\n" +
                    "Step 2\n" +
                    "Meanwhile, cook rice according to package directions. Mix mayonnaise and Sriracha in a small bowl; set aside. Whisk tamari, mirin, ginger, crushed red pepper and salt in another small bowl; set aside.\n" +
                    "\n" +
                    "Step 3\n" +
                    "Divide the rice between 2 bowls. Top with salmon, avocado, cucumber and kimchi. Drizzle with the tamari mixture and the mayonnaise mixture. Mix the bowls, if desired, and serve with nori.",lunch,"");
            Recipe rp3 = new Recipe("banana bread","The best banana bread ever, and it's vegan! Perfectly sweet, moist, and topped with a brown sugar crust, this delicious banana bread is super easy to make and customizable with your favorite stir-ins.","Overripe bananas,brown sugar,canola oil,vanilla extract,all-purpose wheat flour,Baking soda,Cinnamon,salt,walnuts","amount ber serving (1 slice) contains 332 cal ,15g fats ,6g protein","vegan ,lactose free,dairy-free","Mash your bananas up in a large bowl using a fork or potato masher.\n" +
                    "Add some oil, sugar, brown sugar, and vanilla to your mashed banana, then stir everything up.","Preheat the oven to 350°.\n" +
                    "\n" +
                    "Lightly oil a 9-inch loaf pan and arrange a strip of parchment paper width-wise along the center, with just a bit hanging out over each side.\n" +
                    "\n" +
                    "Peel the bananas and place them into a large mixing bowl. Mash them well with a fork or potato masher.\n" +
                    "\n" +
                    "Add sugar, brown sugar, oil, and vanilla to the bowl. Stir until well-mixed.\n" +
                    "\n" +
                    "\n" +
                    "Add the flour to the bowl, then sprinkle the baking soda, cinnamon and salt on top of the flour.\n" +
                    "\n" +
                    "Stir everything together just until mixed. Don't overmix. The batter will be thick.\n" +
                    "\n" +
                    "Fold in the walnuts.\n" +
                    "\n" +
                    "Spoon the batter into the prepared loaf pan and smooth out the top with the back of a spoon.\n" +
                    "\n" +
                    "Sprinkle the top with brown sugar.\n" +
                    "\n" +
                    "Bake for 50 minutes, or until a toothpick inserted into the center comes out clean.\n" +
                    "\n" +
                    "\n" +
                    "Remove the pan from the oven and transfer it to a wire rack. Allow the loaf to cool for at least 15 minutes before removing it from the pan.\n" +
                    "\n" +
                    "Slice and serve.",vegan,"");



            recs.add(rp1);
            recs.add(rp2);
            recs.add(rp3);
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
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "error reading!" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }



