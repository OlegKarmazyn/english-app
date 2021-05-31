package solid.icon.english.words_by_levels.study_way;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.Locale;

import solid.icon.english.R;
import solid.icon.english.Res_array;
import solid.icon.english.words_by_levels.Definition;
import solid.icon.english.words_by_levels.lev_b1.Intermediate;

public class MainStudyAction extends AppCompatActivity {

    private TextToSpeech mTTS;


    private Drawable LinDraw;

    private int counter_true = 0; private Toast mess;

    TextView words1; TextView words2; TextView words3; TextView words4; TextView words5; TextView words6; TextView words7; TextView words8; TextView words9; TextView words10; TextView words11;
    TextView words12; TextView words13; TextView words14; TextView words15;

    TextView words1_1; TextView words2_1; TextView words3_1; TextView words4_1; TextView words5_1; TextView words6_1; TextView words7_1; TextView words8_1; TextView words9_1; TextView words10_1; TextView words11_1;
    TextView words12_1; TextView words13_1; TextView words14_1; TextView words15_1;

    EditText editText1; EditText editText2; EditText editText3; EditText editText4; EditText editText5; EditText editText6; EditText editText7; EditText editText8; EditText editText9; EditText editText10; EditText editText11;
    EditText editText12; EditText editText13; EditText editText14; EditText editText15;

    LinearLayout lin_1; LinearLayout lin_2; LinearLayout lin_3; LinearLayout lin_4; LinearLayout lin_5; LinearLayout lin_6; LinearLayout lin_7; LinearLayout lin_8; LinearLayout lin_9; LinearLayout lin_10; LinearLayout lin_11;
    LinearLayout lin_12; LinearLayout lin_13; LinearLayout lin_14; LinearLayout lin_15;

    private TextView check;

    private  int index = 0;
    private int i = 0;

    private String doing = "";

    private int [] counter_flip = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    private FloatingActionButton el_next;

    public int[][] main_1 = new int[][]{};
    public int[][] main_2 = new int[][]{};
    private int [] id = new int[]{55, 66, 77, 88, 99, 100, 110, 112, 114, 124, 1234, 124, 768, 345, 98};
    private String [] back_words = new String[15];
    private boolean [] isRight = new boolean[]{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_words_tab_layout);
        ActionBar actionBar = getSupportActionBar();



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
                int position = 0;
                position = tab.getPosition();
                viewPager.setCurrentItem(position);
                doing = "learn";
                init_learn_fragment();
                switch (position){
                    case 0:
                        doing = "learn";
                        init_learn_fragment();
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        doing = "test";
                        init_learn_fragment();
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


    private void init_learn_fragment(){
        for(int i = 0; i < 15;i++){
            id[i]= i * -1;
        }
        main_1 = new Res_array().main_1.clone();
        main_2 = new Res_array().main_2.clone();
        words1 = findViewById(R.id.words1); words2 = findViewById(R.id.words2); words3 = findViewById(R.id.words3); words4 = findViewById(R.id.words4); words5 = findViewById(R.id.words5);
        words6 = findViewById(R.id.words6); words7 = findViewById(R.id.words7); words8 = findViewById(R.id.words8); words9 = findViewById(R.id.words9); words10 = findViewById(R.id.words10);
        words11 = findViewById(R.id.words11); words12 = findViewById(R.id.words12); words13 = findViewById(R.id.words13); words14 = findViewById(R.id.words14); words15 = findViewById(R.id.words15);

        words1_1 = findViewById(R.id.words1_1); words2_1 = findViewById(R.id.words2_1); words3_1 = findViewById(R.id.words3_1); words4_1 = findViewById(R.id.words4_1); words5_1 = findViewById(R.id.words5_1);
        words6_1 = findViewById(R.id.words6_1); words7_1 = findViewById(R.id.words7_1); words8_1 = findViewById(R.id.words8_1); words9_1 = findViewById(R.id.words9_1); words10_1 = findViewById(R.id.words10_1);
        words11_1 = findViewById(R.id.words11_1); words12_1 = findViewById(R.id.words12_1); words13_1 = findViewById(R.id.words13_1); words14_1 = findViewById(R.id.words14_1); words15_1 = findViewById(R.id.words15_1);

        editText1 = findViewById(R.id.editText1); editText2 = findViewById(R.id.editText2); editText3 = findViewById(R.id.editText3); editText4 = findViewById(R.id.editText4); editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6); editText7 = findViewById(R.id.editText7); editText8 = findViewById(R.id.editText8); editText9 = findViewById(R.id.editText9); editText10 = findViewById(R.id.editText10);
        editText11 = findViewById(R.id.editText11); editText12 = findViewById(R.id.editText12); editText13 = findViewById(R.id.editText13); editText14 = findViewById(R.id.editText14); editText15 = findViewById(R.id.editText15);

        lin_1 = findViewById(R.id.lin_1); lin_2 = findViewById(R.id.lin_2); lin_3 = findViewById(R.id.lin_3); lin_4 = findViewById(R.id.lin_4); lin_5 = findViewById(R.id.lin_5);
        lin_6 = findViewById(R.id.lin_6); lin_7 = findViewById(R.id.lin_7); lin_8 = findViewById(R.id.lin_8); lin_9 = findViewById(R.id.lin_9); lin_10 = findViewById(R.id.lin_10);
        lin_11 = findViewById(R.id.lin_11); lin_12 = findViewById(R.id.lin_12); lin_13 = findViewById(R.id.lin_13); lin_14 = findViewById(R.id.lin_14); lin_15 = findViewById(R.id.lin_15);

        check = findViewById(R.id.check);
        el_next = findViewById(R.id.el_next);

        LinDraw = lin_1.getBackground();

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result =  mTTS.setLanguage(Locale.US);

                    if(result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS", "Language not supported");
                    } else {

                    }
                }else{
                    Log.e("TTS", "Initializator failef");
                }
            }
        });


        if(doing.equals("learn")){

                full_array();
                index = Intermediate.abs;
                change_test();
                editText_visible_gone();
                check_visible_gone();



        } else if (doing.equals("test")){

                text1_1_visibel_vis();
                text1_visibel_gone();
                full_array();
                index = Intermediate.abs;
                change_test();
            }



    }

    private void full_array(){
        int rand = 0;
        boolean isTrue = false;
        int k = 0;


        for (int iter = 0; iter < 15; iter++) {
            do {
                k = 0;
                rand = (int) (Math.random() * 15);
                for (int j = 0; j < 15; j++) {
                    if (rand == id[j]) {
                        isTrue = true;
                    }else{
                        k++;
                    }
                }
                if(k == 15){
                    isTrue = false;
                }

            } while (isTrue);
            id[iter] = rand;
        }
    }


    private void change_test(){

        words1.setText(main_1[index][id[i++]]);
        words2.setText(main_1[index][id[i++]]);
        words3.setText(main_1[index][id[i++]]);
        words4.setText(main_1[index][id[i++]]);
        words5.setText(main_1[index][id[i++]]);
        words6.setText(main_1[index][id[i++]]);
        words7.setText(main_1[index][id[i++]]);
        words8.setText(main_1[index][id[i++]]);
        words9.setText(main_1[index][id[i++]]);
        words10.setText(main_1[index][id[i++]]);
        words11.setText(main_1[index][id[i++]]);
        words12.setText(main_1[index][id[i++]]);
        words13.setText(main_1[index][id[i++]]);
        words14.setText(main_1[index][id[i++]]);
        words15.setText(main_1[index][id[i]]);
        i = 0;

        words1_1.setText(main_2[index][id[i++]]);
        words2_1.setText(main_2[index][id[i++]]);
        words3_1.setText(main_2[index][id[i++]]);
        words4_1.setText(main_2[index][id[i++]]);
        words5_1.setText(main_2[index][id[i++]]);
        words6_1.setText(main_2[index][id[i++]]);
        words7_1.setText(main_2[index][id[i++]]);
        words8_1.setText(main_2[index][id[i++]]);
        words9_1.setText(main_2[index][id[i++]]);
        words10_1.setText(main_2[index][id[i++]]);
        words11_1.setText(main_2[index][id[i++]]);
        words12_1.setText(main_2[index][id[i++]]);
        words13_1.setText(main_2[index][id[i++]]);
        words14_1.setText(main_2[index][id[i++]]);
        words15_1.setText(main_2[index][id[i]]);
        i = 0;
    }

    private void editText_visible_gone(){

        editText1.setVisibility(View.GONE);
        editText2.setVisibility(View.GONE);
        editText3.setVisibility(View.GONE);
        editText4.setVisibility(View.GONE);
        editText5.setVisibility(View.GONE);
        editText6.setVisibility(View.GONE);
        editText7.setVisibility(View.GONE);
        editText8.setVisibility(View.GONE);
        editText9.setVisibility(View.GONE);
        editText10.setVisibility(View.GONE);
        editText11.setVisibility(View.GONE);
        editText12.setVisibility(View.GONE);
        editText13.setVisibility(View.GONE);
        editText14.setVisibility(View.GONE);
        editText15.setVisibility(View.GONE);

    }

    private void text1_1_visibel_vis(){

        words1_1.setVisibility(View.VISIBLE);
        words2_1.setVisibility(View.VISIBLE);
        words3_1.setVisibility(View.VISIBLE);
        words4_1.setVisibility(View.VISIBLE);
        words5_1.setVisibility(View.VISIBLE);
        words6_1.setVisibility(View.VISIBLE);
        words7_1.setVisibility(View.VISIBLE);
        words8_1.setVisibility(View.VISIBLE);
        words9_1.setVisibility(View.VISIBLE);
        words10_1.setVisibility(View.VISIBLE);
        words11_1.setVisibility(View.VISIBLE);
        words12_1.setVisibility(View.VISIBLE);
        words13_1.setVisibility(View.VISIBLE);
        words14_1.setVisibility(View.VISIBLE);
        words15_1.setVisibility(View.VISIBLE);

    }

    private void text1_visibel_gone(){

        words1.setVisibility(View.GONE);
        words2.setVisibility(View.GONE);
        words3.setVisibility(View.GONE);
        words4.setVisibility(View.GONE);
        words5.setVisibility(View.GONE);
        words6.setVisibility(View.GONE);
        words7.setVisibility(View.GONE);
        words8.setVisibility(View.GONE);
        words9.setVisibility(View.GONE);
        words10.setVisibility(View.GONE);
        words11.setVisibility(View.GONE);
        words12.setVisibility(View.GONE);
        words13.setVisibility(View.GONE);
        words14.setVisibility(View.GONE);
        words15.setVisibility(View.GONE);

    }

    private void text1_visibel_vis(){

        words1.setVisibility(View.VISIBLE);
        words2.setVisibility(View.VISIBLE);
        words3.setVisibility(View.VISIBLE);
        words4.setVisibility(View.VISIBLE);
        words5.setVisibility(View.VISIBLE);
        words6.setVisibility(View.VISIBLE);
        words7.setVisibility(View.VISIBLE);
        words8.setVisibility(View.VISIBLE);
        words9.setVisibility(View.VISIBLE);
        words10.setVisibility(View.VISIBLE);
        words11.setVisibility(View.VISIBLE);
        words12.setVisibility(View.VISIBLE);
        words13.setVisibility(View.VISIBLE);
        words14.setVisibility(View.VISIBLE);
        words15.setVisibility(View.VISIBLE);

    }

    private void check_visible_gone(){
        check.setVisibility(View.GONE);
    }

    private void back_inf_from_editText(){
        int t = 0;
        back_words[t++] = editText1.getText().toString(); // приводим к типу String
        back_words[t++] = editText2.getText().toString();
        back_words[t++] = editText3.getText().toString();
        back_words[t++] = editText4.getText().toString();
        back_words[t++] = editText5.getText().toString();
        back_words[t++] = editText6.getText().toString();
        back_words[t++] = editText7.getText().toString();
        back_words[t++] = editText8.getText().toString();
        back_words[t++] = editText9.getText().toString();
        back_words[t++] = editText10.getText().toString();
        back_words[t++] = editText11.getText().toString();
        back_words[t++] = editText12.getText().toString();
        back_words[t++] = editText13.getText().toString();
        back_words[t++] = editText14.getText().toString();
        back_words[t++] = editText15.getText().toString();
        t = 0;
    }

    private void equals_back_with_true(){
        String word = "";
        for (int j = 0; j < 15; j++){
            word = getResources().getString((main_1[index][id[j]]));
            back_words[j] = back_words[j].trim();
            isRight[j] = back_words[j].equals(word);
            //System.out.println("что " + back_words[j] + " с чем " + word + " " + isRight[j]);
        }
    }

    @SuppressLint("ResourceAsColor")
    private void setBackgraund_to_lin(LinearLayout lin, int k){
        //System.out.println(k + " " + " " +isRight[k]);
        if (isRight[k]){
            lin.setBackgroundResource(R.color.back_true);
            counter_true++;
            //System.out.println("true");
        }else {
            lin.setBackgroundResource(R.color.back_false);
            //System.out.println("false");
        }

    }



    private void set_all_back_to_lin(){
        int k = 0;
        setBackgraund_to_lin(lin_1, k); k++;
        setBackgraund_to_lin(lin_2, k); k++;
        setBackgraund_to_lin(lin_3, k); k++;
        setBackgraund_to_lin(lin_4, k); k++;
        setBackgraund_to_lin(lin_5, k); k++;
        setBackgraund_to_lin(lin_6, k); k++;
        setBackgraund_to_lin(lin_7, k); k++;
        setBackgraund_to_lin(lin_8, k); k++;
        setBackgraund_to_lin(lin_9, k); k++;
        setBackgraund_to_lin(lin_10, k); k++;
        setBackgraund_to_lin(lin_11, k); k++;
        setBackgraund_to_lin(lin_12, k); k++;
        setBackgraund_to_lin(lin_13, k); k++;
        setBackgraund_to_lin(lin_14, k); k++;
        setBackgraund_to_lin(lin_15, k); k++;
    }

    private void speak(TextView textView){
        String text = textView.getText().toString();
        float pitch = 0.5f;
        float speed = 0.5f;
        Log.e("TTS", "123");
        //mTTS.setPitch(pitch);
        //mTTS.setSpeechRate(speed);

        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {

        if(mTTS != null){
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }

    public void learn_click(View v) {
        switch (v.getId()) {
            case R.id.words1:
                if(words1_1.getVisibility() == View.GONE){
                    words1_1.setVisibility(View.VISIBLE);
                    counter_flip[0] = 1;
                    calculate_flip();
                }
                speak(words1);
                break;

            case R.id.words2:
                if(words2_1.getVisibility() == View.GONE){
                    words2_1.setVisibility(View.VISIBLE);
                    counter_flip[1] = 1;
                    calculate_flip();
                }
                speak(words2);
                break;

            case R.id.words3:
                if(words3_1.getVisibility() == View.GONE){
                    words3_1.setVisibility(View.VISIBLE);
                    counter_flip[2] = 1;
                    calculate_flip();
                }
                speak(words3);
                break;

            case R.id.words4:
                if(words4_1.getVisibility() == View.GONE){
                    words4_1.setVisibility(View.VISIBLE);
                    counter_flip[3] = 1;
                    calculate_flip();
                }
                speak(words4);
                break;

            case R.id.words5:
                if(words5_1.getVisibility() == View.GONE){
                    words5_1.setVisibility(View.VISIBLE);
                    counter_flip[4] = 1;
                    calculate_flip();
                }
                speak(words5);
                break;

            case R.id.words6:
                if(words6_1.getVisibility() == View.GONE){
                    words6_1.setVisibility(View.VISIBLE);
                    counter_flip[5] = 1;
                    calculate_flip();
                }
                speak(words6);
                break;

            case R.id.words7:
                if(words7_1.getVisibility() == View.GONE){
                    words7_1.setVisibility(View.VISIBLE);
                    counter_flip[6] = 1;
                    calculate_flip();
                }
                speak(words7);
                break;

            case R.id.words8:
                if(words8_1.getVisibility() == View.GONE){
                    words8_1.setVisibility(View.VISIBLE);
                    counter_flip[7] = 1;
                    calculate_flip();
                }
                speak(words8);
                break;

            case R.id.words9:
                if(words9_1.getVisibility() == View.GONE){
                    words9_1.setVisibility(View.VISIBLE);
                    counter_flip[8] = 1;
                    calculate_flip();
                }
                speak(words9);
                break;

            case R.id.words10:
                if(words10_1.getVisibility() == View.GONE){
                    words10_1.setVisibility(View.VISIBLE);
                    counter_flip[9] = 1;
                    calculate_flip();
                }
                speak(words10);
                break;

            case R.id.words11:
                if(words11_1.getVisibility() == View.GONE){
                    words11_1.setVisibility(View.VISIBLE);
                    counter_flip[10] = 1;
                    calculate_flip();
                }
                speak(words11);
                break;

            case R.id.words12:
                if(words12_1.getVisibility() == View.GONE){
                    words12_1.setVisibility(View.VISIBLE);
                    counter_flip[11] = 1;
                    calculate_flip();
                }
                speak(words12);
                break;

            case R.id.words13:
                if(words13_1.getVisibility() == View.GONE){
                    words13_1.setVisibility(View.VISIBLE);
                    counter_flip[12] = 1;
                    calculate_flip();
                }
                speak(words13);
                break;

            case R.id.words14:
                if(words14_1.getVisibility() == View.GONE){
                    words14_1.setVisibility(View.VISIBLE);
                    counter_flip[13] = 1;
                    calculate_flip();
                }
                speak(words14);
                break;

            case R.id.words15:
                if(words15_1.getVisibility() == View.GONE){
                    words15_1.setVisibility(View.VISIBLE);
                    counter_flip[14] = 1;
                    calculate_flip();
                }
                speak(words15);
                break;

            case R.id.check:
                text1_visibel_vis();
                back_inf_from_editText();
                equals_back_with_true();
                set_all_back_to_lin();
                check_visible_gone();
                mess = Toast.makeText(this, "Correct answers " + counter_true + " of " + 15, Toast.LENGTH_LONG);
                mess.show();

                break;
        }
    }

    public void calculate_flip(){
        int ma = 0;
        for (int g:
                counter_flip) {
            if (g == 1){
                ma++;
            }
        }
        if (ma == 15){
            el_next.setVisibility(View.VISIBLE);
        }
    }


    public void next_learn(View v) {
        switch (v.getId()) {
            case R.id.el_next:
                if(doing.equals("learn")){
                    Intent intent = new Intent(this, Definition.class);
                    startActivity(intent);
                    this.finish();
                }
                break;
        }
    }

    private void text1_1_visibel_gone(){
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

    @SuppressLint("ResourceAsColor")
    private void setBackgraund_to_lin_reply(){
        lin_1.setBackground(LinDraw);
        lin_2.setBackground(LinDraw);
        lin_3.setBackground(LinDraw);
        lin_4.setBackground(LinDraw);
        lin_5.setBackground(LinDraw);
        lin_6.setBackground(LinDraw);
        lin_7.setBackground(LinDraw);
        lin_8.setBackground(LinDraw);
        lin_9.setBackground(LinDraw);
        lin_10.setBackground(LinDraw);
        lin_11.setBackground(LinDraw);
        lin_12.setBackground(LinDraw);
        lin_13.setBackground(LinDraw);
        lin_14.setBackground(LinDraw);
        lin_15.setBackground(LinDraw);
    }

    private void set_null_editText(){
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");
        editText5.setText("");
        editText6.setText("");
        editText7.setText("");
        editText8.setText("");
        editText9.setText("");
        editText10.setText("");
        editText11.setText("");
        editText12.setText("");
        editText13.setText("");
        editText14.setText("");
        editText15.setText("");
    }

    private void reply__text(){
        int temp = 1000;
        for (int j = 0; j < 15; j++) {
            id[j] = temp;
            temp++;
        }
        full_array();

        if(doing.equals("learn")){

            text1_1_visibel_gone();
            change_test();

        } else if (doing.equals("test")) {
            counter_true = 0;
            setBackgraund_to_lin_reply();
            text1_visibel_gone();
            check.setVisibility(View.VISIBLE);
            change_test();
            set_null_editText();
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
                Intent intent = new Intent(this, Intermediate.class);
                startActivity(intent);
                this.finish();
                return true;

            case R.id.replay_mipmap:
                doing = "learn";
                init_learn_fragment();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Intermediate.class);
        startActivity(intent);
        this.finish();
    }
}
