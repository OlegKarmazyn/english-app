package solid.icon.english.words_by_levels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import solid.icon.english.MainActivity;
import solid.icon.english.R;
import solid.icon.english.words_by_levels.Lev_a2.PreIntermediate;
import solid.icon.english.words_by_levels.lev_b1.Intermediate;

public class LevelByEnglish extends AppCompatActivity {

    private Intent a2;
    private Intent b1;

    private Button pre_intermediate;
    private Button intermediate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lavel_by_english);
        ActionBar actionBar = getSupportActionBar();

        a2 = new Intent(this, PreIntermediate.class);
        b1 = new Intent(this, Intermediate.class);

        pre_intermediate = findViewById(R.id.pre_intermediate);
        intermediate = findViewById(R.id.intermediate);

        actionBar.setTitle("Have fun");

        pre_intermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(a2);
                finish();
            }
        });


        intermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(b1);
                finish();
            }
        });



    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}