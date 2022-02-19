package com.example.signuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class RestDetailsActivity extends AppCompatActivity {

    private TextView tvName, tvDescription, tvCategory,TvIngredients, TvNutrition,TvDietartInfo,TvSteps;
    private ImageView ivPhoto;

    /*
        private String address;
    private RestCat category;
    private String photo;
    private String phone;
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_details);

        connectComponents();
        Intent i = this.getIntent();
        Recipe rec = (Recipe) i.getSerializableExtra("rec");

        tvName.setText(rec.getName());
        tvDescription.setText(rec.getDescription());
        TvIngredients.setText(rec.getIngredients());
        TvNutrition.setText(rec.getNutrition());
        tvCategory.setText(rec.getCategory().toString());
        TvDietartInfo.setText(rec.getDietartInfo());
        TvSteps.setText(rec.getSteps());
        Picasso.get().load(rec.getPhoto()).into(ivPhoto);
    }

    private void connectComponents() {
        tvName = findViewById(R.id.TvNameRecDetails);
        tvDescription = findViewById(R.id.TvDescriptionRecDetails);
        TvIngredients = findViewById(R.id.TvIngredientsRecDetails);
        tvCategory = findViewById(R.id.SrCategoryRecDetails);
        TvNutrition = findViewById(R.id.TvNutritionRecDetails);
        TvDietartInfo=findViewById(R.id.TvDietartInfoRecDetails);
        TvSteps=findViewById(R.id.TvStepsRecDetails);
        ivPhoto = findViewById(R.id.IvPhotoRecDetails);
    }
}