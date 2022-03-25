package solid.icon.english.words_by_levels.universal_topic_level;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;

import solid.icon.english.MainActivity;
import solid.icon.english.R;
import solid.icon.english.architecture.ActivityGlobal;

public class EnglishLevel extends ActivityGlobal {

    RecyclerView recyclerView;
    String name_topic [];
    Serializable level;
    boolean[] key_topics = new boolean[51];
    public static String which_KNOW_TOPIC;
    final Context context = this;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topics_layout);
        recyclerView = findViewById(R.id.recycleView);
        showActionBar(true,"rgevfuhahniku");

        level = getIntent().getSerializableExtra(String.valueOf(KeysExtra.level));
        try {


            if (level == LessonsName.A2) {
                name_topic = getResources().getStringArray(R.array.topics_name_a2);
            } else if (level == LessonsName.B1) {
                name_topic = getResources().getStringArray(R.array.topics_name_b1);
            }  else if (level == LessonsName.B2) {
                name_topic = getResources().getStringArray(R.array.topics_name_b2);
            }
        } catch (Exception e){/*empty*/}
    }


    private void setAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        RecycleAdapter recycleAdapter = new RecycleAdapter(context, name_topic, key_topics, EnglishLevel.this);
        recyclerView.setAdapter(recycleAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            goDateBack();
        }catch (Exception e){

        }

        setAdapter();
    }

    public boolean[] goDateBack(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        for(int i = 0; i < key_topics.length; i++){
            String mod_key = String.valueOf(level) + i;
            key_topics[i] = preferences.getBoolean(mod_key, false);
            Log.d("goDateBack", "key_topics[" + i + "]" + key_topics[i]);
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
        startActivity(new Intent(context, MainActivity.class));
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
        this.finish();
    }
}
