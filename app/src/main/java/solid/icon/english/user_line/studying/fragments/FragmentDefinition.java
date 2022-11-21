package solid.icon.english.user_line.studying.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import solid.icon.english.R;
import solid.icon.english.architecture.UserFragmentActivity;
import solid.icon.english.architecture.room.WordModel;
import solid.icon.english.user_line.studying.StudyActivity;

public class FragmentDefinition extends UserFragmentActivity implements View.OnClickListener {

    protected FragmentDefinition(List<WordModel> wordModelList, String topic, String subTopic, StudyActivity studyActivity) {
        super(wordModelList, topic, subTopic, studyActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_definition, container, false);
    }

    private EditText editText = null;

    private LinearLayout lay_definition_transl = null;

    private TextView words1, words2;

    private FloatingActionButton fab;
    Drawable f;

    private TextView text_check;

    private TextView meaning = null;

    private int i = 0;

    private boolean isCreate = false;

    @Override
    public void onResume() {
        super.onResume();
        context = getActivity();

        editText = getActivity().findViewById(R.id.writeEdit);
        meaning = getActivity().findViewById(R.id.meaning);
        meaning.setClickable(false);

        words1 = getActivity().findViewById(R.id.words_by_engl);
        words2 = getActivity().findViewById(R.id.words_by_transl);

        lay_definition_transl = getActivity().findViewById(R.id.lay_definition_transl);

        fab = getActivity().findViewById(R.id.fab);

        text_check = getActivity().findViewById(R.id.text_check);

        fab.setOnClickListener(this);
        text_check.setOnClickListener(this);
        words1.setOnClickListener(this);

        mTTS = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    Locale locale = new Locale(topicModel.country);
                    int result = mTTS.setLanguage(locale);

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

        words2.setClickable(false);
        words_get_text();

        if (!isCreate) {
            isCreate = true;
            f = editText.getBackground();
            Arrays.fill(counter_flip, 2); //must be neither 0 or 1
        }
    }

    private void words_get_text() {
        meaning.setText(definitionArr[id[i]]);
        words1.setText(englishTranslArr[id[i]]);
        words2.setText(rusTranslArr[id[i]]);
    }

    private boolean isTrueWords() {
        String eT = editText.getText().toString();
        eT = eT.trim();
        String res = englishTranslArr[id[i]];
        if ((eT.equals(res))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_check:
                lay_definition_transl.setVisibility(View.VISIBLE);

                if (isTrueWords()) {
                    if (counter_flip[i] != 0) {
                        counter_flip[i] = 1;
                    }

                    editText.setBackgroundResource(R.color.back_true);
                    fab.setVisibility(View.VISIBLE);
                } else {
                    counter_flip[i] = 0;
                    editText.setBackgroundResource(R.color.back_false);
                }
                break;

            case R.id.fab:
                if (i < size - 1) { // todo CHECK
                    i++;
                    //editText.setBackgroundResource(R.color.colorPrimary);
                    lay_definition_transl.setVisibility(View.GONE);
                    fab.setVisibility(View.GONE);
                    editText.setText("");
                    words_get_text();
                    editText.setBackground(f);
                } else {
                    int count = 0;
                    for (int c : counter_flip) {
                        if (c == 1) {
                            count++;
                        }
                    }
                    Toast mess = Toast.makeText(getActivity(), "Correct answers " + count + " of " + size, Toast.LENGTH_LONG);
                    mess.show();
                    fab.setVisibility(View.GONE);
                }
                break;
//            case R.id.el_next:
//                Intent intent = new Intent(getActivity(), ListenWrite.class);
//                startActivity(intent);
//                getActivity().finish();
//                break;
            case R.id.words_by_engl:
                speak(englishTranslArr[id[i]]);
                break;
        }
    }
}
