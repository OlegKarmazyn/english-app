package solid.icon.english.user_line.studying.fragments;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import solid.icon.english.R;
import solid.icon.english.architecture.parents.UserFragmentActivity;
import solid.icon.english.architecture.room.WordModel;
import solid.icon.english.databinding.FragmentListenBinding;
import solid.icon.english.user_line.studying.StudyActivity;


public class FragmentListen extends UserFragmentActivity implements View.OnClickListener {

    public FragmentListen(List<WordModel> wordModelList, String topic, String subTopic, StudyActivity studyActivity) {
        super(wordModelList, topic, subTopic, studyActivity);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListenBinding.inflate(inflater);
        return binding.getRoot();
    }

    private FragmentListenBinding binding;

    private AnimationDrawable animationDrawable = null;
    private ImageView imageView = null;

    private int i = 0;
    private int count = 0;

    Drawable f;

    @Override
    public void onResume() {
        super.onResume();
        context = getActivity();

        animationDrawable = (AnimationDrawable) imageView.getDrawable();

        binding.writeEdit.setText("");

        f = binding.writeEdit.getBackground();

        imageView.setOnClickListener(this);
        binding.fab.setOnClickListener(this);
        binding.checkButton.setOnClickListener(this);

        mTTS = new TextToSpeech(getActivity(), status -> {
            if (status == TextToSpeech.SUCCESS) {
                Locale locale = new Locale(topicModel.country);
                int result = mTTS.setLanguage(locale);

                if (result == TextToSpeech.LANG_MISSING_DATA
                        || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Language not supported");
                } //empty else

            } else {
                Log.e("TTS", "Initialization failed");
            }
        });

        words_get_text();

        binding.englishWord.setClickable(false);
        binding.translationWord.setClickable(false);

        setNotVisibleItem(0);
        firstSettingVisibility();
    }

    private void firstSettingVisibility() {
        imageView.setVisibility(View.VISIBLE);
        binding.checkButton.setVisibility(View.VISIBLE);
        binding.writeEdit.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                clickCheckButton();
                return true;
            }
            return false;
        });
    }

    private void listen() {
        speak(englishTranslArr[id[i]]);
        outLog("englishTranslArr " + englishTranslArr[id[i]]);
    }

    private boolean isTrueWords() {
        String eT = binding.writeEdit.getText().toString();
        eT = eT.trim();
        String res = (englishTranslArr[id[i]]);
        return eT.equals(res);
    }

    private void words_get_text() {
        binding.englishWord.setText(englishTranslArr[id[i]]);
        binding.translationWord.setText(rusTranslArr[id[i]]);
    }

    @Override
    public void onPause() {
        super.onPause();
        setVisibleGoneTextView();
        count = 0;
        hideSoftKeyboard(binding.writeEdit);
    }

    private void setVisibleGoneTextView() {
        binding.layoutWrite.setVisibility(View.GONE);
        binding.writeEdit.setBackground(f);
        binding.fab.setVisibility(View.GONE);
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

    private void clickCheckButton() {
        binding.layoutWrite.setVisibility(View.VISIBLE);

        if (isTrueWords()) {
            setVisibility(imageView, false);
            if (counter_flip[i] != 0) {
                counter_flip[i] = 1;
            }

            binding.writeEdit.setBackgroundResource(R.color.back_true);
            setVisibility(binding.fab, true);
            setVisibility(binding.checkButton, false);
            hideSoftKeyboard(binding.writeEdit);
        } else {
            counter_flip[i] = 0;
            binding.writeEdit.setBackgroundResource(R.color.back_false);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgListen:
                animationDrawable();
                break;

            case R.id.checkButton:
                clickCheckButton();
                break;

            case R.id.fab:
                if (i < englishTranslArr.length - 1) {
                    i++;
                    binding.layoutWrite.setVisibility(View.GONE);
                    binding.fab.setVisibility(View.GONE);
                    setVisibility(binding.checkButton, true);
                    binding.writeEdit.setText("");
                    words_get_text();
                    binding.writeEdit.setBackground(f);
                    setVisibility(imageView, true);
                    animationDrawable();
                    showSoftKeyboard(binding.writeEdit);
                } else {
                    count = 0;
                    for (int c : counter_flip) {
                        if (c == 1) {
                            count++;
                        }
                    }
                    Toasty.success(context, "Correct answers " + count + " of " + englishTranslArr.length, Toast.LENGTH_LONG).show();

                    binding.fab.setVisibility(View.GONE);
                    for (int i = 0; i < size; i++) {
                        counter_flip[i] = -1;
                    }
                    i = 0;
                }
                break;
        }
    }
}