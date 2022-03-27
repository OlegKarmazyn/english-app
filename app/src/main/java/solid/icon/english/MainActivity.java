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
        boolean[] isCheckArray = new boolean[3];
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        isCheckArray[0] = sharedPreferences.getBoolean(LessonsName.A2.name(), false);
        isCheckArray[1] = sharedPreferences.getBoolean(LessonsName.B1.name(), false);
        isCheckArray[2] = sharedPreferences.getBoolean(LessonsName.B2.name(), false);
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