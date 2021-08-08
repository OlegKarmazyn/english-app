package solid.icon.english.words_by_levels.study_way;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import solid.icon.english.R;
import solid.icon.english.words_by_levels.LevelByEnglish;

public class MainStudyAction extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;

    String what_level;
    int num_of_topic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_study_action);

        what_level = LevelByEnglish.level;
        num_of_topic = getIntent().getIntExtra("num_of_topic", 0);

        tabLayout = findViewById(R.id.tab_layout_example);
        pager2 = findViewById(R.id.viewPager);

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
//                startActivity(new Intent(this, EnglishLevel.class));
                this.finish();
                overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
//        startActivity(new Intent(this, EnglishLevel.class));
        this.finish();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }
}
