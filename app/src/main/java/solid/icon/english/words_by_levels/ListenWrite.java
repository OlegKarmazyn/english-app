package solid.icon.english.words_by_levels;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

import solid.icon.english.R;
import solid.icon.english.words_by_levels.Lev_a2.Level_A2;
import solid.icon.english.words_by_levels.Lev_a2.PreIntermediate;
import solid.icon.english.words_by_levels.lev_b1.Intermediate;
import solid.icon.english.words_by_levels.lev_b1.Level_B1;

public class ListenWrite extends AppCompatActivity {

    private AnimationDrawable animationDrawable = null;
    Level_A2 level_a2 = null;
    Level_B1 level_b1 = null;
    private ImageView imageView = null;
    private int[][] main_1 = new int[][]{};
    private int[][] main_2 = new int[][]{};

    private int index = 0;
    private int i = 0;

    private TextToSpeech mTTS;

    private int [] id = new int[]{55, 66, 77, 88, 99, 100, 110, 112, 114, 124, 1234, 124, 768, 345, 98};

    private EditText editText;

    private LinearLayout lay_write_learn = null;

    private TextView words1, words2;

    private FloatingActionButton fab; Drawable f;


    private int [] counter_true = new int[]{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5}; private int count = 0;

    private FloatingActionButton el_next;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listen_write);

        imageView = findViewById(R.id.img_listen);
        animationDrawable = (AnimationDrawable) imageView.getDrawable();


        editText = findViewById(R.id.wrileEdit);

        lay_write_learn = findViewById(R.id.lay_write_learn);

        words1 = findViewById(R.id.words_by_engl);
        words2 = findViewById(R.id.words_by_transl);

        fab = findViewById(R.id.fab);

        full_array();

        f = editText.getBackground();

        el_next = findViewById(R.id.el_next);

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
        if (PreIntermediate.lev.equals("a2")){
            index = PreIntermediate.abs;
            level_a2 = new Level_A2();
            main_1 = level_a2.main_1.clone();
            main_2 = level_a2.main_2.clone();
            words_get_text();
            //actionBar.setTitle(topic_by_a2[tem]);
        } else if (Intermediate.lev.equals("b1")){
            index = Intermediate.abs;
            level_b1 = new Level_B1();
            main_1 = level_b1.main_1.clone();
            main_2 = level_b1.main_2.clone();
            words_get_text();
            //actionBar.setTitle(topic_by_b1[tem]);
        }

        words1.setClickable(false);
        words2.setClickable(false);

    }

    private void listen(){ speak(getResources().getString((main_1[index][id[i]]))); }

    private boolean isTrueWords(){
        String eT = editText.getText().toString();
        eT = eT.trim();
        String res = (getResources().getString((main_1[index][id[i]])));
        if((eT.equals(res)) ){
            return true;
        } else {
            return false;
        }
    }

    private void words_get_text(){
        words1.setText(main_1[index][id[i]]);
        words2.setText(main_2[index][id[i]]);
    }

    @SuppressLint("NonConstantResourceId")
    public void click(View v) {
        switch (v.getId()) {
            case R.id.img_listen:
                animationDrawable.start();
                listen();
                new CountDownTimer(1000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        //mTextField.setText("done!");
                        animationDrawable.stop();
                    }
                }.start();

                break;
            case R.id.text_check_listen:
                lay_write_learn.setVisibility(View.VISIBLE);

                if(isTrueWords()){
                    if (counter_true[i] != 0){
                        counter_true[i] = 1;
                    }

                    editText.setBackgroundResource(R.color.back_true);
                    fab.setVisibility(View.VISIBLE);
                } else{
                    counter_true[i] = 0;
                    editText.setBackgroundResource(R.color.back_false);
                }
                break;

            case R.id.fab:
                    if (i < main_1[index].length - 1){
                        i++;
                        //editText.setBackgroundResource(R.color.colorPrimary);
                        lay_write_learn.setVisibility(View.GONE);
                        fab.setVisibility(View.GONE);
                        editText.setText("");
                        words_get_text();
                        editText.setBackground(f);
                    } else {
                        count = 0;
                        for (int c: counter_true) {
                            if (c == 1){
                                count++;
                            }
                        }
                       Toast mess = Toast.makeText(this, "Correct answers " + count + " of " + main_1[index].length, Toast.LENGTH_LONG);
                        mess.show();
                        el_next.setVisibility(View.VISIBLE);
                        fab.setVisibility(View.GONE);
                    }
                break;
            case R.id.el_next:
                Intent intent = null;
                if (PreIntermediate.lev.equals("a2")){
                    TestOrLearn.doing = "test";
                    intent = new Intent(this, Level_A2.class);

                } else if (Intermediate.lev.equals("b1")){
                    TestOrLearn.doing = "test";
                    intent = new Intent(this, Level_B1.class);

                }
                startActivity(intent);
                this.finish();
                break;
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

    private void speak(String text){
        float pitch = 0.5f;
        float speed = 0.5f;
        Log.e("TTS", "123");
        //mTTS.setPitch(pitch);
        //mTTS.setSpeechRate(speed);

        mTTS.speak(text,TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {

        if(mTTS != null){
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }



    @Override
    public void onBackPressed() {
        Intent intent;
        if (PreIntermediate.lev.equals("a2")){
             intent = new Intent(this, PreIntermediate.class);
            //actionBar.setTitle(topic_by_a2[tem]);
        } else if (Intermediate.lev.equals("b1")){
             intent = new Intent(this, Intermediate.class);
            //actionBar.setTitle(topic_by_b1[tem]);
        } else {
             intent = new Intent(this, ListenWrite.class);
        }
        startActivity(intent);
        this.finish();
    }
}
