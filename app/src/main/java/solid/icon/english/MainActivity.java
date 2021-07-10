package solid.icon.english;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import solid.icon.english.db_pac.DBmoveINFO;
import solid.icon.english.words_by_levels.LevelByEnglish;

public class MainActivity extends AppCompatActivity {

    private Intent intent_words_by_levels;
    private Intent intent_add_your_words;

    private Button words_by_levels;
    private Button add_your_words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();

        intent_words_by_levels = new Intent(this, LevelByEnglish.class);
        intent_add_your_words = new Intent(this, LevelByEnglish.class);

        words_by_levels = findViewById(R.id.words_by_levels);
        add_your_words = findViewById(R.id.add_your_words);

        actionBar.setTitle("Welcome");


        new DBmoveINFO(this).create(this);

        words_by_levels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_words_by_levels);
                finish();
            }
        });

        add_your_words.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_add_your_words);
                finish();
            }
        });
    }
}