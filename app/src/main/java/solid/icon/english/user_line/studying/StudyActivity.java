package solid.icon.english.user_line.studying;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import es.dmoral.toasty.Toasty;
import solid.icon.english.R;
import solid.icon.english.architecture.parents.ActivityGlobal;
import solid.icon.english.architecture.room.App;
import solid.icon.english.architecture.room.WordModel;
import solid.icon.english.architecture.room.WordModelDao;
import solid.icon.english.user_line.studying.fragments.FragmentAdapter;


public class StudyActivity extends ActivityGlobal {

    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;

    List<WordModel> wordModelList;
    String topic, subTopic;
    int num_of_topic;
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
        num_of_topic = intent.getIntExtra(KeysExtra.num_of_topic.name(), 0);
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

    }

    /**
     * preparation of all data for display to the user
     */

    public void setDateToActivity() {
        /*methods before init table*/
        getWordsList();

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle(), wordModelList, topic, subTopic, num_of_topic, StudyActivity.this);
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
        WordModelDao wordModelDao = App.getInstance().getDatabase().wordModelDao();
        wordModelList = wordModelDao.getAllBySubTopicsName(subTopic, topic);
        sizeOfItems = wordModelList.size();
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
