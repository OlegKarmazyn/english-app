package solid.icon.english.words_by_levels.lev_b1;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import solid.icon.english.R;
import solid.icon.english.db_pac.DBHelper;
import solid.icon.english.db_pac.DBmoveINFO;

public class Intermediate_v2 extends AppCompatActivity {

    RecyclerView recyclerView;
    String name_topic [];
    int[] key_topics = new int[51];

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intermidiate_lay);

        name_topic = getResources().getStringArray(R.array.topics_name_b1);

        recyclerView = findViewById(R.id.recycleView);
        goDateBack();
        RecycleAdapter recycleAdapter = new RecycleAdapter(this, name_topic, key_topics);
        recyclerView.setAdapter(recycleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public int [] goDateBack(){
        DBmoveINFO dBmoveINFO = new DBmoveINFO(this);
        for (int i = 0; i < 51; i++) {
            key_topics[i] = dBmoveINFO.back_check_info(i, DBHelper.KNOW_TOPIC_B1);
            Log.d("Intermediate_v2", i + "goDateBack =" + key_topics[i]);
        }
        return key_topics;
    }
}
