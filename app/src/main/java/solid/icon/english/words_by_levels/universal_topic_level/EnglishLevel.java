package solid.icon.english.words_by_levels.universal_topic_level;

import android.content.Context;
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
    public static String which_KNOW_TOPIC;
    final Context context = this;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topics_layout);

        level = LevelByEnglish.level;
        try {


            if (level.equals("a2")) {
                name_topic = getResources().getStringArray(R.array.topics_name_a2);
            } else if (level.equals("b1")) {
                name_topic = getResources().getStringArray(R.array.topics_name_b1);
            }  else if (level.equals("b2")) {
                name_topic = getResources().getStringArray(R.array.topics_name_b2);
            }
        } catch (Exception e){

        }
        recyclerView = findViewById(R.id.recycleView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

        recyclerView.setLayoutManager(layoutManager);

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                layoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);



    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            goDateBack();
        }catch (Exception e){

        }

        RecycleAdapter recycleAdapter = new RecycleAdapter(context, name_topic, key_topics, EnglishLevel.this);
        recyclerView.setAdapter(recycleAdapter);
    }

    public int [] goDateBack(){
        DBmoveINFO dBmoveINFO = new DBmoveINFO(context);

        which_KNOW_TOPIC = (level.equals("a2")) ? DBHelper.KNOW_TOPIC_A2 : ((level.equals("b1")) ? DBHelper.KNOW_TOPIC_B1 : ((level.equals("b2")) ? DBHelper.KNOW_TOPIC_B2 : null));
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
        startActivity(new Intent(context, LevelByEnglish.class));
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
        this.finish();
    }
}
