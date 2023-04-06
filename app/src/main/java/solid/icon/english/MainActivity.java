package solid.icon.english;

import static android.widget.Toast.LENGTH_SHORT;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import solid.icon.english.architecture.DividerItemDecorator;
import solid.icon.english.architecture.local_data.PreferencesOperations;
import solid.icon.english.architecture.parents.ActivityGlobal;
import solid.icon.english.architecture.room.App;
import solid.icon.english.architecture.room.TopicModel;
import solid.icon.english.architecture.room.TopicModelDao;
import solid.icon.english.main_adapters.AdapterLevels;
import solid.icon.english.main_adapters.AdapterUsers;

public class MainActivity extends ActivityGlobal {

    // Optimize: make viewModel and organize this activity

    private RecyclerView recyclerView_levels, recyclerView_user;
    RelativeLayout loading_layout;

    private String[] levels_titlesArray,
            users_titlesArray = new String[]{"topics"};
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showActionBarWithoutTitle(true);

        recyclerView_levels = findViewById(R.id.recycleView_levels);
        recyclerView_user = findViewById(R.id.recycleView_user);
        loading_layout = findViewById(R.id.loading_layout);

        levels_titlesArray = getResources().getStringArray(R.array.lessonNames);

        fullRecycleView();
        firstOpen();
    }

    private void firstOpen() {
        PreferencesOperations pref = new PreferencesOperations();
        pref.firstOpen();
        if (pref.getNumberOfVisits() < 4 && !pref.getIsOpenedSite())
            showSiteDialog();
    }

    private void showSiteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Школа чекає на тебе!");
        builder.setMessage("Скоріше реєструйся на безкоштовне індивідуальне заняття");
        builder.setPositiveButton("Поїхали", (dialog, which) -> {
            new PreferencesOperations().setIsOpenedSite(true);
            Uri uri = Uri.parse("http://english-vs.web.app");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
        builder.setNegativeButton("Пізніше", null);
        builder.show();
    }

    public void setLoadingVisible(boolean isLoading) {
        if (isLoading)
            loading_layout.setVisibility(View.VISIBLE);
        else
            loading_layout.setVisibility(View.GONE);
    }

    //----------------method for RecyclerView--------------------------------// start

    private void fullRecycleView() {
        setSettingsToRecyclerView(recyclerView_levels);
        setSettingsToRecyclerView(recyclerView_user);
        setAdapters();
    }

    private void setSettingsToRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecorator(ContextCompat.getDrawable(context, R.drawable.divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void setAdapters() {
        recyclerView_levels.setAlpha(0f);
        AdapterLevels adapterLevels = new AdapterLevels(context, levels_titlesArray, MainActivity.this);
        recyclerView_levels.setAdapter(adapterLevels);
        recyclerView_levels.animate().alpha(1f);

        setDataToUserAdapter();
    }

    public void setDataToUserAdapter() {
        getUsers_titlesArray();

        recyclerView_user.setAlpha(0f);

        AdapterUsers adapterUsers = new AdapterUsers(context, users_titlesArray, MainActivity.this);
        recyclerView_user.setAdapter(adapterUsers);

        recyclerView_user.animate().alpha(1f);
    }

    //----------------method for RecyclerView--------------------------------// end

    private void getUsers_titlesArray() {
        TopicModelDao topicModelDao = App.getInstance().getDatabase().topicModelDao();
        assert topicModelDao != null;
        List<TopicModel> topicModelList = topicModelDao.getAll();
        for (TopicModel t : topicModelList) {
            if (t.topicsName == null)
                topicModelDao.delete(t);
        }
        users_titlesArray = new String[topicModelList.size()];
        for (int i = 0; i < users_titlesArray.length; i++) {
            if (topicModelList.get(i).topicsName != null) {
                users_titlesArray[i] = topicModelList.get(i).topicsName;
            }
            Log.e("getUsers_titlesArray", users_titlesArray[i]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        Toast backToast = Toast.makeText(getBaseContext(), "Click again to exit", LENGTH_SHORT);
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}