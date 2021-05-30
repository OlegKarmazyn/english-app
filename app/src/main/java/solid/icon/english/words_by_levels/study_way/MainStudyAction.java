package solid.icon.english.words_by_levels.study_way;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import solid.icon.english.R;

public class MainStudyAction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_words_tab_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        TabLayout tabLayout     = (TabLayout) findViewById(R.id.tab_layout_choose_type_of_study);
        TabItem item_learn      = (TabItem) findViewById(R.id.item_learn);
        TabItem item_definition = (TabItem) findViewById(R.id.item_definition);
        TabItem item_listen     = (TabItem) findViewById(R.id.item_listen);
        TabItem item_test       = (TabItem) findViewById(R.id.item_test);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),
                                                     tabLayout.getTabCount());

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




    }

    public void Oclick(View view)
    {
        // выводим сообщение
        Toast.makeText(this, "Зачем вы нажали?", Toast.LENGTH_SHORT).show();

    }


}
