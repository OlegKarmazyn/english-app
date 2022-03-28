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
import solid.icon.english.main_adapters.AdapterLevels;

public class MainActivity extends ActivityGlobal {

    private Context context = this;

    private RecyclerView recyclerView_levels, recyclerView_user;

    private String[] levels_titlesArray,
                    users_titlesArray;
    private boolean[] isCheckArray;
    private LessonsName[] lessonsNames = LessonsName.values();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showActionBar(false, "");

        recyclerView_levels = findViewById(R.id.recycleView_levels);
        recyclerView_user = findViewById(R.id.recycleView_user);

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
        AdapterLevels adapterLevels = new AdapterLevels(context, levels_titlesArray, isCheckArray, MainActivity.this);
        recyclerView_levels.setAdapter(adapterLevels);
    }

    private void setAdapterFor_recyclerView_user() {
        AdapterLevels adapterLevels = new AdapterLevels(context, users_titlesArray, isCheckArray, MainActivity.this);
        recyclerView_user.setAdapter(adapterLevels);
    }
}