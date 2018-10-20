package com.team.bulkan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.team.bulkan.AppController;
import com.team.bulkan.R;
import com.team.bulkan.font.HelveticaTextView;
import com.team.bulkan.model.Volcano;

public class VolcanoInfoActivity extends AppCompatActivity {

    public static final String VOLCANO_ID = "VOLCANO_ID";

    private HelveticaTextView tvVolcanoInfo, tvLocation;
    private FloatingActionButton fabEvacuate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volcano_info);

        int volcanoId = getIntent().getIntExtra(VOLCANO_ID, 26);
        Volcano volcano = AppController.getInstance().getDbAccess().getVolcanoById(volcanoId);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(volcano.getVolcanoName());

        loadBackdrop("https://volcano.si.edu/photos/full/017077.jpg");

        tvVolcanoInfo = findViewById(R.id.tv_volcano_info);
        tvLocation = findViewById(R.id.tv_geo_location);
        tvVolcanoInfo.setText(volcano.getVolcanoInfo());
        tvLocation.setText(volcano.getGeoLat() + "," + volcano.getGeoLong());

        fabEvacuate = findViewById(R.id.fab_evacuate);
        fabEvacuate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(VolcanoInfoActivity.this, "EVACUATE", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(VolcanoInfoActivity.this, EvacActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

    }

    private void loadBackdrop(String volcanoImage) {
        final ImageView imageView = findViewById(R.id.backdrop);
        Glide.with(this).load(volcanoImage).apply(RequestOptions.centerCropTransform()).into(imageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
