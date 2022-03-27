package solid.icon.english.words_by_levels.universal_topic_level;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import solid.icon.english.R;
import solid.icon.english.architecture.ActivityGlobal;
import solid.icon.english.architecture.DividerItemDecorator;

public class EnglishLevel extends ActivityGlobal {

    RecyclerView recyclerView;
    String name_topic [];
    LessonsName level;
    boolean[] key_topics = new boolean[51];
    final Context context = this;

    @SuppressLint("RestrictedApi")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topics_layout);

        recyclerView = findViewById(R.id.recycleView);

        level = (LessonsName) getIntent().getSerializableExtra(String.valueOf(KeysExtra.level));

        try {
            String[] lessonNames = getResources().getStringArray(R.array.lessonNames);
            if (level == LessonsName.A2) {
                name_topic = getResources().getStringArray(R.array.topics_name_a2);
                showActionBar(true, lessonNames[0]);
            } else if (level == LessonsName.B1) {
                name_topic = getResources().getStringArray(R.array.topics_name_b1);
                showActionBar(true, lessonNames[1]);
            }  else if (level == LessonsName.B2) {
                name_topic = getResources().getStringArray(R.array.topics_name_b2);
                showActionBar(true, lessonNames[2]);
            }
        } catch (Exception e){/*empty*/}

        goDateBack();
        setAdapter();
    }


    private void setAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecorator(ContextCompat.getDrawable(context, R.drawable.divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        RecycleAdapter recycleAdapter = new RecycleAdapter(context, name_topic, key_topics, EnglishLevel.this);
        recyclerView.setAdapter(recycleAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public boolean[] goDateBack(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        for(int i = 0; i < key_topics.length; i++){
            String mod_key = level.name() + i;
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
        this.finish();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }
}
