package solid.icon.english.words_by_levels.study_way;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;

import es.dmoral.toasty.Toasty;
import solid.icon.english.R;
import solid.icon.english.architecture.parents.MyFragmentActivity;


public class FragmentListen extends MyFragmentActivity implements View.OnClickListener {

    public FragmentListen(Serializable what_level, int num_of_topic) {
        this.what_level = what_level;
        this.num_of_topic = num_of_topic;
        defineArrays();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listen, container, false);
    }

    private AnimationDrawable animationDrawable = null;
    private ImageView imageView = null;

    private int i = 0;

    private int[] id = new int[]{55, 66, 77, 88, 99, 100, 110, 112, 114, 124, 1234, 124, 768, 345, 98};

    private EditText editText;

    private LinearLayout lay_write_learn = null;

    private TextView words1, words2;

    private FloatingActionButton fab;
    Drawable f;

    TextView text_check_listen;


    private int[] counter_true = new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
    private int count = 0;

    private void listen() {
        speak(getResources().getString((main_1[num_of_topic][id[i]])));
    }

    private boolean isTrueWords() {
        String eT = editText.getText().toString();
        eT = eT.trim();
        String res = (getResources().getString((main_1[num_of_topic][id[i]])));
        if ((eT.equals(res))) {
            return true;
        } else {
            return false;
        }
    }

    private void words_get_text() {
        words1.setText(main_1[num_of_topic][id[i]]);
        words2.setText(main_2[num_of_topic][id[i]]);
    }

    private void full_array() {
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
                    } else {
                        k++;
                    }
                }
                if (k == 15) {
                    isTrue = false;
                }

            } while (isTrue);
            id[iter] = rand;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        setVisibleGoneTextView();
        count = 0;
        hideSoftKeyboard(editText);
    }

    private void setVisibleGoneTextView() {
        lay_write_learn.setVisibility(View.GONE);
        editText.setBackground(f);
    }

    @Override
    public void onResume() {
        super.onResume();

        context = getActivity();

        for (int i = 0; i < 15; i++) {
            id[i] = i * -1;
        }

        for (int i = 0; i < 15; i++) {
            counter_true[i] = 5;
        }

        assert context != null;
        imageView = context.findViewById(R.id.img_listen);
        animationDrawable = (AnimationDrawable) imageView.getDrawable();

        editText = context.findViewById(R.id.wrileEdit);
        editText.setText("");

        lay_write_learn = context.findViewById(R.id.lay_write_learn);

        words1 = context.findViewById(R.id.words_by_engl);
        words2 = context.findViewById(R.id.words_by_transl);

        fab = context.findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        full_array();

        f = editText.getBackground();

        text_check_listen = context.findViewById(R.id.text_check_listen);

        imageView.setOnClickListener(this);
        fab.setOnClickListener(this);
        text_check_listen.setOnClickListener(this);

        mTTS = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(locale);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {

                    }
                } else {
                    Log.e("TTS", "Initializator failef");
                }
            }
        });

        words_get_text();

        words1.setClickable(false);
        words2.setClickable(false);
    }

    private void animationDrawable() {
        animationDrawable.start();
        listen();
        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                animationDrawable.stop();
            }
        }.start();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
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

                if (isTrueWords()) {
                    if (counter_true[i] != 0) {
                        counter_true[i] = 1;
                    }

                    editText.setBackgroundResource(R.color.back_true);
                    fab.setVisibility(View.VISIBLE);
                } else {
                    counter_true[i] = 0;
                    editText.setBackgroundResource(R.color.back_false);
                }
                break;

            case R.id.fab:
                if (i < main_1[num_of_topic].length - 1) {
                    i++;
                    lay_write_learn.setVisibility(View.GONE);
                    fab.setVisibility(View.GONE);
                    setVisibility(text_check_listen, true);
                    editText.setText("");
                    words_get_text();
                    editText.setBackground(f);
                    setVisibility(imageView, true);
                    animationDrawable();
                    showSoftKeyboard(editText);
                } else {
                    count = 0;
                    for (int c : counter_true) {
                        if (c == 1) {
                            count++;
                        }
                    }
                    Toasty.success(context, "Correct answers " + count + " of " + main_1[num_of_topic].length, Toast.LENGTH_LONG).show();

                    fab.setVisibility(View.GONE);
                    for (int i = 0; i < 15; i++) {
                        counter_true[i] = -1;
                    }
                    i = 0;
                }
                break;
        }
    }
}