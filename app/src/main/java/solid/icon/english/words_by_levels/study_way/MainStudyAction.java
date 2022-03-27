package solid.icon.english.words_by_levels.study_way;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;

import solid.icon.english.R;
import solid.icon.english.architecture.ActivityGlobal;


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

    private void flip_words(){
        TextView words1_1; TextView words2_1; TextView words3_1; TextView words4_1; TextView words5_1; TextView words6_1; TextView words7_1; TextView words8_1; TextView words9_1; TextView words10_1; TextView words11_1;
        TextView words12_1; TextView words13_1; TextView words14_1; TextView words15_1;

        words1_1 = findViewById(R.id.words1_1); words2_1 = findViewById(R.id.words2_1); words3_1 = findViewById(R.id.words3_1); words4_1 = findViewById(R.id.words4_1); words5_1 = findViewById(R.id.words5_1);
        words6_1 = findViewById(R.id.words6_1); words7_1 = findViewById(R.id.words7_1); words8_1 = findViewById(R.id.words8_1); words9_1 = findViewById(R.id.words9_1); words10_1 = findViewById(R.id.words10_1);
        words11_1 = findViewById(R.id.words11_1); words12_1 = findViewById(R.id.words12_1); words13_1 = findViewById(R.id.words13_1); words14_1 = findViewById(R.id.words14_1); words15_1 = findViewById(R.id.words15_1);

        words1_1.setVisibility(View.GONE);
        words2_1.setVisibility(View.GONE);
        words3_1.setVisibility(View.GONE);
        words4_1.setVisibility(View.GONE);
        words5_1.setVisibility(View.GONE);
        words6_1.setVisibility(View.GONE);
        words7_1.setVisibility(View.GONE);
        words8_1.setVisibility(View.GONE);
        words9_1.setVisibility(View.GONE);
        words10_1.setVisibility(View.GONE);
        words11_1.setVisibility(View.GONE);
        words12_1.setVisibility(View.GONE);
        words13_1.setVisibility(View.GONE);
        words14_1.setVisibility(View.GONE);
        words15_1.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
//                startActivity(new Intent(this, EnglishLevel.class));
                this.finish();
                overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
                return true;
            case R.id.replay_mipmap:
                flip_words();
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
