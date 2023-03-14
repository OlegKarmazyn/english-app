package solid.icon.english.user_line.studying;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.dmoral.toasty.Toasty;
import solid.icon.english.R;
import solid.icon.english.architecture.local_data.PreferencesOperations;
import solid.icon.english.architecture.parents.ActivityGlobal;
import solid.icon.english.architecture.room.App;
import solid.icon.english.architecture.room.SubTopicDao;
import solid.icon.english.architecture.room.SubTopicModel;
import solid.icon.english.architecture.room.WordModel;
import solid.icon.english.architecture.room.WordModelDao;
import solid.icon.english.user_line.studying.fragments.FragmentAdapter;


public class StudyActivity extends ActivityGlobal {

    PreferencesOperations preferencesOperations = new PreferencesOperations();

    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;

    List<WordModel> wordModelList;
    String topic, subTopic;
    boolean isSubTest;
    int sizeOfItems = 0;

    public boolean isReplaced = false;
    public Menu menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_study_action);

        Intent intent = getIntent();
        topic = intent.getStringExtra(KeysExtra.level.name());
        subTopic = intent.getStringExtra(KeysExtra.title.name());
        isSubTest = intent.getBooleanExtra(KeysExtra.isSubTest.name(), false);
        showActionBar(true, subTopic);

        tabLayout = findViewById(R.id.tab_layout_example);
        pager2 = findViewById(R.id.viewPager);

        setDateToActivity();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (sizeOfItems != 0) {
                    pager2.setCurrentItem(tab.getPosition());
                } else {
                    tabLayout.selectTab(tabLayout.getTabAt(0));
                    Toasty.warning(context, "List of words is empty").show();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        displayGptNotification();
    }

    //------------showing alert dialog to notify about gpt helper---------------///
    private void displayGptNotification() {
        if (!preferencesOperations.getGptDescription()) {
            final LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setPadding(50, 0, 0, 0);

            final CheckBox checkBox = new CheckBox(context);
            int id = View.generateViewId();
            checkBox.setId(id);
            linearLayout.addView(checkBox);
            final TextView textView = new TextView(context);
            textView.setText("don't show me again");
            linearLayout.addView(textView);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("GPT-bot can help");
            builder.setMessage("Just click \"DEFINITION\" and bot will give it");

            builder.setView(linearLayout);

            builder.setPositiveButton("Okay", (dialog, which) -> {
                if (checkBox.isChecked()) {
                    preferencesOperations.putGptDescription();
                }
            });

            builder.show();
        }
    }

    /**
     * preparation of all data for display to the user
     */

    public void setDateToActivity() {
        /*methods before init table*/
        getWordsList();

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle(), wordModelList, topic, subTopic, isSubTest, StudyActivity.this);
        int cur = pager2.getCurrentItem();

        pager2.animate().alpha(0).setDuration(600);

        new Handler().postDelayed(() -> {
            pager2.setAdapter(adapter);
            pager2.setCurrentItem(cur);
        }, 650);

        new Handler().postDelayed(() -> pager2.animate().alpha(1).setDuration(1000), 700);
    }

    /**
     * getting all english and translating words from list in database
     */

    private void getWordsList() {
        if (!isSubTest) {
            WordModelDao wordModelDao = App.getInstance().getDatabase().wordModelDao();
            wordModelList = wordModelDao.getAllBySubTopicsName(subTopic, topic);
            sizeOfItems = wordModelList.size();
        } else {
            getTestingWords();
            sizeOfItems = wordModelList.size();
            defineWords();
        }
    }

    private void defineWords() {
        if (sizeOfItems == 0) {
            String message = "List of words is empty. Complete at least one SubTopic";
            Toasty.warning(context, message, Toasty.LENGTH_LONG).show();
        } else {
            Collections.shuffle(wordModelList);
            List<WordModel> _wordModelList = new ArrayList<>();
            for (int i = 0; i < Math.min(wordModelList.size(), 10); i++) {
                _wordModelList.add(wordModelList.get(i));
            }
            wordModelList = _wordModelList;
        }
    }

    private void getTestingWords() {
        SubTopicDao subTopicDao = App.getInstance().getDatabase().subTopicDao();
        WordModelDao wordModelDao = App.getInstance().getDatabase().wordModelDao();
        List<SubTopicModel> subTopicModelList = subTopicDao.getAllByTopicsName(topic);
        int size = subTopicModelList.size();
        wordModelList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String subTopic = subTopicModelList.get(i).subTopicsName;
            String mod_key = topic + subTopic;
            boolean isChecked = preferencesOperations.getCheckingSubTopics(mod_key);

            if (isChecked) {
                wordModelList.addAll(wordModelDao.getAllBySubTopicsName(subTopic, topic));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        this.menu = menu;
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
                return true;

            case R.id.replay_mipmap:
                setDateToActivity();
                return true;

            case R.id.replaced:
                isReplaced = !isReplaced;
                setDateToActivity();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
