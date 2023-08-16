package solid.icon.english.words_by_levels.study_way;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;

import solid.icon.english.R;
import solid.icon.english.architecture.parents.ActivityGlobal;


public class MainStudyAction extends ActivityGlobal {

    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;

    Serializable what_level;
    int num_of_topic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_study_action);

        Intent intent = getIntent();
        what_level = intent.getSerializableExtra(String.valueOf(KeysExtra.level));
        num_of_topic = intent.getIntExtra(String.valueOf(KeysExtra.num_of_topic), 0);
        showActionBar(true, intent.getStringExtra("title"));

        tabLayout = findViewById(R.id.tab_layout_example);
        pager2 = findViewById(R.id.viewPager);

        setDataAdapter();
    }

    private void setDataAdapter() {
        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle(), what_level, num_of_topic);
        pager2.setAdapter(adapter);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.getItem(0).setVisible(false); //so far without an element that flips words
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.replay_mipmap:
                setDataAdapter();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
