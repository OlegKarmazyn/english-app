package solid.icon.english;

import android.content.Context;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import solid.icon.english.architecture.ActivityGlobal;
import solid.icon.english.architecture.DividerItemDecorator;
import solid.icon.english.main_adapters.AdapterLevels;
import solid.icon.english.main_adapters.AdapterUsers;

public class MainActivity extends ActivityGlobal {

    private Context context = this;

    private RecyclerView recyclerView_levels, recyclerView_user;

    private String[] levels_titlesArray,
                    users_titlesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showActionBar(false, "");

        recyclerView_levels = findViewById(R.id.recycleView_levels);
        recyclerView_user = findViewById(R.id.recycleView_user);

        levels_titlesArray = getResources().getStringArray(R.array.lessonNames);

        fullRecycleView();
    }
    //----------------method for RecyclerView--------------------------------// start

    private void fullRecycleView(){
        setSettingsToRecyclerView(recyclerView_levels);
        setSettingsToRecyclerView(recyclerView_user);
        setAdapters();

    }

    private void setSettingsToRecyclerView(RecyclerView recyclerView){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecorator(ContextCompat.getDrawable(context, R.drawable.divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void setAdapters() {
        AdapterLevels adapterLevels = new AdapterLevels(context, levels_titlesArray, MainActivity.this);
        recyclerView_levels.setAdapter(adapterLevels);

        AdapterUsers adapterUsers = new AdapterUsers(context, users_titlesArray, MainActivity.this);
        recyclerView_user.setAdapter(adapterUsers);
    }

    //----------------method for RecyclerView--------------------------------// end


}