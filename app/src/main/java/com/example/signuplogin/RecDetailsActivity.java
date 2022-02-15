package com.example.signuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RecDetailsActivity extends AppCompatActivity {
private TextView TvName,TvDescription,TvIngredients,TvNutritions,TvDietartInfo,TvSteps;
private ImageView IvPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_details);

        connectComponents();
        Intent i = this.getIntent();
        Recipe rec = (Recipe) i.getSerializableExtra("rec");
        TvName.setText(rec.getName());
        TvDescription.setText(rec.getDescription());
        TvIngredients.setText(rec.getIngredients());
        TvNutritions.setText(rec.getNutrition());
        TvDietartInfo.setText(rec.getDietartInfo());
        TvSteps.setText(rec.getSteps());
        picasso.get().load(rec.getPhoto()).into(IvPhoto);
    }

    private void connectComponents() {
        TvName=findViewById(R.id.TvNameRecDetails);
        TvDescription=findViewById(R.id.TvDescriptionRecDetails);
        TvIngredients=findViewById(R.id.TvIngredientsRecDetails);
        TvNutritions=findViewById(R.id.TvNutritionRecDetails);
       TvDietartInfo=findViewById(R.id.TvDietartInfoRecDetails);
        TvSteps=findViewById(R.id.TvStepsRecDetails);
    }

}