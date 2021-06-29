package solid.icon.english.words_by_levels.lev_b1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import solid.icon.english.R;
import solid.icon.english.db_pac.DBHelper;
import solid.icon.english.db_pac.DBmoveINFO;
import solid.icon.english.words_by_levels.LevelByEnglish;

public class EnglishLevel extends AppCompatActivity {

    RecyclerView recyclerView;
    String name_topic [];
    String level;
    int[] key_topics = new int[51];

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intermidiate_lay);

        level = LevelByEnglish.level;
        if(level.equals("a2")) {
            name_topic = getResources().getStringArray(R.array.topics_name_a2);
        } else if(level.equals("b1")){
            name_topic = getResources().getStringArray(R.array.topics_name_b1);
        }

        recyclerView = findViewById(R.id.recycleView);
        goDateBack();
        RecycleAdapter recycleAdapter = new RecycleAdapter(this, name_topic, key_topics);
        recyclerView.setAdapter(recycleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public int [] goDateBack(){
        DBmoveINFO dBmoveINFO = new DBmoveINFO(this);
        String which_KNOW_TOPIC;
        which_KNOW_TOPIC = (level.equals("a2")) ? DBHelper.KNOW_TOPIC_A2 : ((level.equals("b1")) ? DBHelper.KNOW_TOPIC_B1 : null);
        for (int i = 0; i < 51; i++) {
            key_topics[i] = dBmoveINFO.back_check_info(i, which_KNOW_TOPIC);
            Log.d("Intermediate_v2", i + " goDateBack = " + key_topics[i]);
        }
        return key_topics;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, LevelByEnglish.class));
        this.finish();
    }
}
