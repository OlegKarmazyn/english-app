package solid.icon.english.user_line.studying;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import solid.icon.english.R;
import solid.icon.english.architecture.ActivityGlobal;
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
    String[] englishTranslArr, rusTranslArr;


     //tod!o:  1 - зашло проверило кол листа
     //todo:  2 - количестово = количество кнопок ( как создавать кнопки ) + лист или масив кнопок по обьектам что бы слушатели были
     //todo:  3 - кнопка додавания нового слова
     //todo:  4 - ф-ционал кнопки додавания



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_study_action);

        Intent intent = getIntent();
        topic = intent.getStringExtra(KeysExtra.level.name());
        subTopic = intent.getStringExtra(KeysExtra.title.name());
        showActionBar(true, subTopic);

        tabLayout = findViewById(R.id.tab_layout_example);
        pager2 = findViewById(R.id.viewPager);

        setDateToActivity();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
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

    public void setDateToActivity(){
        /*methods before init table*/
        getWordsList();

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle(), wordModelList, topic, subTopic, englishTranslArr, rusTranslArr );
        pager2.setAdapter(adapter);
    }

    /**
     * getting all english and rus words from list from database
     */

    private void getWordsList(){
        WordModelDao wordModelDao = App.getInstance().getDatabase().wordModelDao();
        wordModelList = wordModelDao.getAllBySubTopicsName(subTopic, topic);

        englishTranslArr = new String[wordModelList.size()];
        rusTranslArr = new String[wordModelList.size()];
        int i = 0;
        for (WordModel w : wordModelList) {
            englishTranslArr[i] = w.englishWord;
            rusTranslArr[i] = w.rusWord;
            Log.e(TAG, englishTranslArr[i]);
            Log.e(TAG, rusTranslArr[i]);
            i++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
                return true;
            case R.id.replay_mipmap:
                setDateToActivity();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
