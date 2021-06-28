package solid.icon.english.words_by_levels.lev_b1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import solid.icon.english.R;

public class Intermidiate_v2 extends AppCompatActivity {

    RecyclerView recyclerView;

    String name_topic [];

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intermidiate_lay);

        name_topic = getResources().getStringArray(R.array.topics_name_b1);

        recyclerView = findViewById(R.id.recycleView);

        RecycleAdapter recycleAdapter = new RecycleAdapter(this, name_topic);
        recyclerView.setAdapter(recycleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
