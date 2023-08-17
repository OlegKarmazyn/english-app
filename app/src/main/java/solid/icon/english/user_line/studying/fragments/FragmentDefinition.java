package solid.icon.english.user_line.studying.fragments;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import solid.icon.english.R;
import solid.icon.english.architecture.parents.UserFragmentActivity;
import solid.icon.english.architecture.room.WordModel;
import solid.icon.english.databinding.FragmentDefinitionBinding;
import solid.icon.english.user_line.studying.StudyActivity;

public class FragmentDefinition extends UserFragmentActivity implements View.OnClickListener {

    protected FragmentDefinition(List<WordModel> wordModelList, String topic, String subTopic, StudyActivity studyActivity) {
        super(wordModelList, topic, subTopic, studyActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDefinitionBinding.inflate(inflater);
        return binding.getRoot();
    }

    FragmentDefinitionBinding binding;

    Drawable f;

    private int i = 0;

    private boolean isCreate = false;

    @Override
    public void onResume() {
        super.onResume();
        context = getActivity();

        assert context != null;
        binding.meaning.setClickable(false);

        f = binding.writeEdit.getBackground();

        binding.fab.setOnClickListener(this);
        binding.checkButton.setOnClickListener(this);
        binding.englishWord.setOnClickListener(this);

        mTTS = new TextToSpeech(getActivity(), status -> {
            if (status == TextToSpeech.SUCCESS) {
                Locale locale = new Locale(topicModel.country);
                int result = mTTS.setLanguage(locale);

                if (result == TextToSpeech.LANG_MISSING_DATA
                        || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Language not supported");
                }  //empty else

            } else {
                Log.e("TTS", "Initialization failed");
            }
        });

        binding.englishWord.setClickable(false);
        words_get_text();

        if (!isCreate) {
            isCreate = true;
            f = binding.writeEdit.getBackground();
            Arrays.fill(counter_flip, 2); //must be neither 0 nor 1
        }

        setNotVisibleItem(0);

        binding.writeEdit.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                clickCheckButton();
                return true;
            }
            return false;
        });
    }

    private void words_get_text() {
        try {
            binding.englishWord.setText(englishTranslArr[id[i]]);
            binding.translateWord.setText(rusTranslArr[id[i]]);
            binding.meaning.setText(definitionArr[id[i]]);
        } catch (ArrayIndexOutOfBoundsException e) {
            binding.englishWord.setClickable(false);
            binding.translateWord.setClickable(false);
            binding.checkButton.setClickable(false);
            Toasty.warning(context, getString(R.string.list_definition_is_empty)).show();
        }
    }

    private boolean isTrueWords() {
        try {
            String eT = binding.writeEdit.getText().toString();
            eT = eT.trim();
            String res = englishTranslArr[id[i]];
            return eT.equals(res);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private void clickCheckButton() {
        setVisibility(binding.definitionLayout, true);
        if (isTrueWords()) {
            if (counter_flip[i] != 0)
                counter_flip[i] = 1;

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
            case R.id.checkButton:
                clickCheckButton();
                break;

            case R.id.fab:
                if (i < size - 1) { // todo CHECK
                    i++;
                    binding.definitionLayout.setVisibility(View.GONE);
                    binding.fab.setVisibility(View.GONE);
                    setVisibility(binding.checkButton, true);
                    binding.writeEdit.setText("");
                    words_get_text();
                    binding.writeEdit.setBackground(f);
                    showSoftKeyboard(binding.writeEdit);
                } else {
                    int count = 0;
                    for (int c : counter_flip) {
                        if (c == 1) {
                            count++;
                        }
                    }
                    Toasty.success(context, "Correct answers " + count + " of " + englishTranslArr.length, Toast.LENGTH_LONG).show();
                    setVisibility(binding.fab, false);
                }
                break;
            case R.id.englishWord:
                speak(englishTranslArr[id[i]]);
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        hideSoftKeyboard(binding.writeEdit);
    }
}
