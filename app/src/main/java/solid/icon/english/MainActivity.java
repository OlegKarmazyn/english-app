package solid.icon.english;

import static android.widget.Toast.LENGTH_SHORT;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skydoves.powermenu.MenuAnimation;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;

import java.util.List;

import solid.icon.english.architecture.DividerItemDecorator;
import solid.icon.english.architecture.local_data.PreferencesOperations;
import solid.icon.english.architecture.parents.ActivityGlobal;
import solid.icon.english.architecture.room.App;
import solid.icon.english.architecture.room.TopicModel;
import solid.icon.english.architecture.room.TopicModelDao;
import solid.icon.english.dialogs.InfoDialog;
import solid.icon.english.main_adapters.AdapterLevels;
import solid.icon.english.main_adapters.AdapterUsers;
import solid.icon.english.main_adapters.MainViewModel;

public class MainActivity extends ActivityGlobal {

    // Optimize: make viewModel and organize this activity

    private RecyclerView recyclerView_levels, recyclerView_user;
    RelativeLayout loading_layout;
    public RelativeLayout mainLayout;

    private String[] levels_titlesArray,
            users_titlesArray = new String[]{"topics"};

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showActionBarWithoutTitle(true);
        viewModel = new MainViewModel(this);

        recyclerView_levels = findViewById(R.id.recycleView_levels);
        recyclerView_user = findViewById(R.id.recycleView_user);
        loading_layout = findViewById(R.id.loading_layout);
        mainLayout = findViewById(R.id.mainLayout);

        levels_titlesArray = getResources().getStringArray(R.array.lessonNames);

        fullRecycleView();
        viewModel.firstOpen();
        viewModel.checkLatestVersion();
    }

    //region shows dialogs
    public void showSiteDialog() {
        InfoDialog dialog = new InfoDialog(context);
        dialog.setPositiveButton(R.string.ride, v -> {
            new PreferencesOperations().setIsOpenedSite(true);
            Uri uri = Uri.parse("http://english-vs.web.app");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
        dialog.setNegativeButton(R.string.later, null);
        dialog.show();
    }

    public void showUpdateDialog() {
        InfoDialog dialog = new InfoDialog(context);
        dialog.setTitle("Old app version");
        dialog.setMessage("Do you want to upgrade now?");
        dialog.setPositiveButton(R.string.go, v -> {
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=solid.icon.english");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        });
        dialog.setNegativeButton(R.string.after, null);
        dialog.show();
    }
    //endregion

    public void setLoadingVisible(boolean isLoading) {
        if (isLoading)
            loading_layout.setVisibility(View.VISIBLE);
        else
            loading_layout.setVisibility(View.GONE);
    }

    // region methods for RecyclerView
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
    //endregion method for RecyclerView

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

    private void showMenu() {
        PowerMenu powerMenu = new PowerMenu.Builder(context)
                .addItem(new PowerMenuItem("Account", false))
                .addItem(new PowerMenuItem("Settings", false))
                .setAnimation(MenuAnimation.FADE) // Animation start point (TOP | LEFT).
                .setMenuRadius(10f) // sets the corner radius.
                .setMenuShadow(10f) // sets the shadow.
                .setTextColor(ContextCompat.getColor(context, R.color.ios_blue))
                .setTextGravity(Gravity.CENTER)
                .setTextTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD))
                .setSelectedTextColor(Color.WHITE)
                .setMenuColor(Color.WHITE)
                .setSelectedMenuColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
                .build();

        powerMenu.setOnMenuItemClickListener((position, item) -> {
            switch (position) {
                case 0:
                    goToAccount();
                    break;
                case 1:
                    goToSettings();
                    break;
            }
            powerMenu.dismiss();
        });
        powerMenu.showAsAnchorRightTop(mainLayout);
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dropMenu:
                showMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private long backPressedTime;

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