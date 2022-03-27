package solid.icon.english;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import solid.icon.english.architecture.ActivityGlobal;
import solid.icon.english.architecture.DividerItemDecorator;

public class MainActivity extends ActivityGlobal {

    private Context context = this;

    private RecyclerView recyclerView_levels, recyclerView_your;

    private String[] levels_titlesArray,
                    your_titlesArray;
    private boolean[] isCheckArray;
    private LessonsName[] lessonsNames = LessonsName.values();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showActionBar(false, "");

        recyclerView_levels = findViewById(R.id.recycleView_levels);
        recyclerView_your = findViewById(R.id.recycleView_your);

        levels_titlesArray = getResources().getStringArray(R.array.lessonNames);

        setAdaptersTo_recyclerView_levels();
    }

    private void getIsCheckArray(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean[] isCheckArray = new boolean[lessonsNames.length];
        for (int i = 0; i < lessonsNames.length; i++) {
            isCheckArray[i] = sharedPreferences.getBoolean(lessonsNames[i].name(), false);
        }
        this.isCheckArray = isCheckArray;
    }

    private void setAdaptersTo_recyclerView_levels(){
        getIsCheckArray();
        setSettingsToRecyclerView(recyclerView_levels);
        setAdapterFor_recyclerView_levels();

    }

    private void setSettingsToRecyclerView(RecyclerView recyclerView){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecorator(ContextCompat.getDrawable(context, R.drawable.divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void setAdapterFor_recyclerView_levels() {
        AdapterMainActivity adapterMainActivity = new AdapterMainActivity(context, levels_titlesArray, isCheckArray, MainActivity.this);
        recyclerView_levels.setAdapter(adapterMainActivity);
    }

    private void setAdapterFor_recyclerView_your() {
        AdapterMainActivity adapterMainActivity = new AdapterMainActivity(context, your_titlesArray, isCheckArray, MainActivity.this);
        recyclerView_your.setAdapter(adapterMainActivity);
    }
}