package solid.icon.english;

import android.content.Context;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import solid.icon.english.architecture.ActivityGlobal;
import solid.icon.english.architecture.DividerItemDecorator;

public class MainActivity extends ActivityGlobal {

    private Context context = this;

    private RecyclerView recyclerView_levels, recyclerView_your;

    private String[] levels_titlesArray = new String[]{"Pre-Intermediate", "Intermediate", "Upper-Intermediate"},
                    your_titlesArray = new String[]{
                            "мои слова 1",
                            "мои слова 2",
                            "мои слова 3",
                            "мои слова 4",
                            "мои слова 5",
                            "мои слова 6",
                            "мои слова 7",
                            "мои слова 8",
                            "мои слова 9",
                            "мои слова 10",
                            "мои слова 11"
                    },
            keysArray, checkingArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        recyclerView_levels = findViewById(R.id.recycleView_levels);
        recyclerView_your = findViewById(R.id.recycleView_your);

        setAdapters();
    }

    private void setAdapters(){
        setSettingsToRecyclerView(recyclerView_levels);
        setSettingsToRecyclerView(recyclerView_your);
        setAdapterFor_recyclerView_levels();
        setAdapterFor_recyclerView_your();
    }

    private void setSettingsToRecyclerView(RecyclerView recyclerView){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecorator(ContextCompat.getDrawable(context, R.drawable.divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void setAdapterFor_recyclerView_levels() {
        AdapterMainActivity adapterMainActivity = new AdapterMainActivity(context, levels_titlesArray, keysArray, checkingArray, MainActivity.this);
        recyclerView_levels.setAdapter(adapterMainActivity);
    }

    private void setAdapterFor_recyclerView_your() {
        AdapterMainActivity adapterMainActivity = new AdapterMainActivity(context, your_titlesArray, keysArray, checkingArray, MainActivity.this);
        recyclerView_your.setAdapter(adapterMainActivity);
    }
}