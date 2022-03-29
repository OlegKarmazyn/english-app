package solid.icon.english.user_line;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import solid.icon.english.R;
import solid.icon.english.architecture.ActivityGlobal;
import solid.icon.english.architecture.DividerItemDecorator;

public class UserLevel extends ActivityGlobal {

    RecyclerView recyclerView;
    String name_topic [];
    String level;
    boolean[] key_topics = new boolean[10];
    final Context context = this;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topics_layout);

        recyclerView = findViewById(R.id.recycleView);

        level = getIntent().getStringExtra(String.valueOf(KeysExtra.level));

        //todo name_topic

        goDateBack();
        setAdapter();
    }


    private void setAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecorator(ContextCompat.getDrawable(context, R.drawable.divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        UserAdapter userAdapter = new UserAdapter(context, name_topic, key_topics, UserLevel.this);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public boolean[] goDateBack(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        for(int i = 0; i < key_topics.length; i++){
            String mod_key = level + i;
            key_topics[i] = preferences.getBoolean(mod_key, false);
            Log.d("goDateBack", "key_topics[" + i + "]" + key_topics[i]);
        }

        return key_topics;
    }
}
