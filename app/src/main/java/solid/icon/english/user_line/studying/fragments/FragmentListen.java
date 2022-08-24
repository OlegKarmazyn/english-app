package solid.icon.english.user_line.studying.fragments;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Locale;

import solid.icon.english.R;
import solid.icon.english.architecture.UserFragmentActivity;
import solid.icon.english.architecture.room.WordModel;
import solid.icon.english.user_line.studying.StudyActivity;


public class FragmentListen extends UserFragmentActivity implements View.OnClickListener {

    public FragmentListen(List<WordModel> wordModelList, String topic, String subTopic, StudyActivity studyActivity) {
        super(wordModelList, topic, subTopic, studyActivity);

        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listen, container, false);
    }

    private AnimationDrawable animationDrawable = null;
    private ImageView imageView = null;

    private TextToSpeech mTTS;

    private int i = 0;
    private int count = 0;

    private LinearLayout lay_write_learn = null;
    private EditText editText;
    private TextView words1, words2;
    private TextView text_check_listen;

    private FloatingActionButton fab;
    Drawable f;
    private FloatingActionButton el_next;

    @Override
    public void onResume() {
        super.onResume();
        context = getActivity();

        imageView = getActivity().findViewById(R.id.img_listen);
        animationDrawable = (AnimationDrawable) imageView.getDrawable();


        editText = getActivity().findViewById(R.id.wrileEdit);
        editText.setText("");

        lay_write_learn = getActivity().findViewById(R.id.lay_write_learn);

        words1 = getActivity().findViewById(R.id.words_by_engl);
        words2 = getActivity().findViewById(R.id.words_by_transl);

        fab = getActivity().findViewById(R.id.fab);

        f = editText.getBackground();

        el_next = getActivity().findViewById(R.id.el_next);

        text_check_listen = getActivity().findViewById(R.id.text_check_listen);

        imageView.setOnClickListener(this);
        fab.setOnClickListener(this);
        el_next.setOnClickListener(this);
        text_check_listen.setOnClickListener(this);

        mTTS = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {

                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        words_get_text();

        words1.setClickable(false);
        words2.setClickable(false);

        setNotVisibleItem(0);
    }

    private void listen() {
        speak(englishTranslArr[id[i]]);
        outLog("englishTranslArr " + englishTranslArr[id[i]]);
    }

    private boolean isTrueWords() {
        String eT = editText.getText().toString();
        eT = eT.trim();
        String res = (englishTranslArr[id[i]]);
        if ((eT.equals(res))) {
            return true;
        } else {
            return false;
        }
    }

    private void words_get_text() {
        words1.setText(englishTranslArr[id[i]]);
        words2.setText(rusTranslArr[id[i]]);
    }

    @Override
    public void onPause() {
        super.onPause();
        setVisibleGoneTextView();
        count = 0;
    }

    private void setVisibleGoneTextView() {
        lay_write_learn.setVisibility(View.GONE);
        editText.setBackground(f);
    }

    private void speak(String text) {
        //float pitch = 0.5f;
        //float speed = 0.5f;

        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void hideSoftKeyboard(EditText input) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
    }

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
                    if (counter_flip[i] != 0) {
                        counter_flip[i] = 1;
                    }

                    editText.setBackgroundResource(R.color.back_true);
                    fab.setVisibility(View.VISIBLE);
                    hideSoftKeyboard(editText);
                } else {
                    counter_flip[i] = 0;
                    editText.setBackgroundResource(R.color.back_false);
                }

                break;

            case R.id.fab:
                if (i < englishTranslArr.length - 1) {
                    i++;
                    //editText.setBackgroundResource(R.color.colorPrimary);
                    lay_write_learn.setVisibility(View.GONE);
                    fab.setVisibility(View.GONE);
                    editText.setText("");
                    words_get_text();
                    editText.setBackground(f);
                } else {
                    count = 0;
                    for (int c : counter_flip) {
                        if (c == 1) {
                            count++;
                        }
                    }
                    Toast.makeText(getActivity(), "Correct answers " + count + " of " + englishTranslArr.length, Toast.LENGTH_LONG).show();

//                    el_next.setVisibility(View.VISIBLE);
                    fab.setVisibility(View.GONE);
                    for (int i = 0; i < size; i++) {
                        counter_flip[i] = -1;
                    }
                    i = 0;
                }
                break;
        }
    }
}