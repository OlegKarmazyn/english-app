package solid.icon.english.words_by_levels.study_way;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.Serializable;

import es.dmoral.toasty.Toasty;
import solid.icon.english.R;
import solid.icon.english.architecture.parents.MyFragmentActivity;
import solid.icon.english.databinding.FragmentTestBinding;
import solid.icon.english.dialogs.TitleDialog;

public class FragmentTest extends MyFragmentActivity {

    public FragmentTest(Serializable what_level, int num_of_topic) {
        this.what_level = what_level;
        this.num_of_topic = num_of_topic;
        defineArrays();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTestBinding.inflate(inflater);
        return binding.getRoot();
    }

    private FragmentTestBinding binding;

    private int[] id = new int[]{55, 66, 77, 88, 99, 100, 110, 112, 114, 124, 1234, 124, 768, 345, 98};

    private int counter_true = 0;

    private final String TAG = "FragmentLearn";
    private Drawable LinDraw;
    private int i = 0;

    private final View.OnClickListener speakListener = v -> {
        speak(((TextView) v).getText().toString());
    };

    private String[] back_words = new String[15];
    private boolean[] isRight = new boolean[]{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};

    @Override
    public void onResume() {
        super.onResume();

        context = getActivity();

        for (int i = 0; i < 15; i++) {
            id[i] = i * -1;
        }

        assert context != null;
        LinDraw = binding.linearLay1.getBackground();

        binding.englishWord1.setOnClickListener(speakListener);
        binding.englishWord2.setOnClickListener(speakListener);
        binding.englishWord3.setOnClickListener(speakListener);
        binding.englishWord4.setOnClickListener(speakListener);
        binding.englishWord5.setOnClickListener(speakListener);
        binding.englishWord6.setOnClickListener(speakListener);
        binding.englishWord7.setOnClickListener(speakListener);
        binding.englishWord8.setOnClickListener(speakListener);
        binding.englishWord9.setOnClickListener(speakListener);
        binding.englishWord10.setOnClickListener(speakListener);
        binding.englishWord11.setOnClickListener(speakListener);
        binding.englishWord12.setOnClickListener(speakListener);
        binding.englishWord13.setOnClickListener(speakListener);
        binding.englishWord14.setOnClickListener(speakListener);
        binding.englishWord15.setOnClickListener(speakListener);

        binding.check.setOnClickListener(v -> {
            text1_visible_vis();
            back_inf_from_editText();
            equals_back_with_true();
            set_all_back_to_lin();
            check_visible_gone();
            Toasty.info(context, "Correct answers " + counter_true + " of " + 15, Toast.LENGTH_LONG).show();
            for (int i = 0; i < 15; i++) {
                isRight[i] = false;
            }
            if (counter_true > 12) {
                TitleDialog dialog = new TitleDialog(context);
                dialog.setTitle("Do you want to mark this topic as done?");
                dialog.setPositiveButton("OK", pos -> {
                    mark_topic_as_done();
                });

                dialog.setNegativeButton("Cancel", (neg) -> {
                });
                dialog.show();
            }
            counter_true = 0;
        });

        mTTS = new TextToSpeech(context, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = mTTS.setLanguage(locale);

                if (result == TextToSpeech.LANG_MISSING_DATA
                        || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Language not supported");
                } else {/*empty*/}

            } else {
                Log.e("TTS", "Initialization failed");
            }
        });

        binding.check.setVisibility(View.VISIBLE);
        text1_1_visible_vis();
        text1_visible_gone();
        full_array();
        change_test();
    }

    @Override
    public void onPause() {
        super.onPause();
        setVisibleGoneTextView();
    }

    private void setVisibleGoneTextView() {
        binding.linearLay1.setBackground(LinDraw);
        binding.linearLay2.setBackground(LinDraw);
        binding.linearLay3.setBackground(LinDraw);
        binding.linearLay4.setBackground(LinDraw);
        binding.linearLay5.setBackground(LinDraw);
        binding.linearLay6.setBackground(LinDraw);
        binding.linearLay7.setBackground(LinDraw);
        binding.linearLay8.setBackground(LinDraw);
        binding.linearLay9.setBackground(LinDraw);
        binding.linearLay10.setBackground(LinDraw);
        binding.linearLay11.setBackground(LinDraw);
        binding.linearLay12.setBackground(LinDraw);
        binding.linearLay13.setBackground(LinDraw);
        binding.linearLay14.setBackground(LinDraw);
        binding.linearLay15.setBackground(LinDraw);

        binding.editText1.setText("");
        binding.editText2.setText("");
        binding.editText3.setText("");
        binding.editText4.setText("");
        binding.editText5.setText("");
        binding.editText6.setText("");
        binding.editText7.setText("");
        binding.editText8.setText("");
        binding.editText9.setText("");
        binding.editText10.setText("");
        binding.editText11.setText("");
        binding.editText12.setText("");
        binding.editText13.setText("");
        binding.editText14.setText("");
        binding.editText15.setText("");
    }

    private void full_array() {
        int rand;
        boolean isTrue = false;
        int k;

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

    private void change_test() {

        binding.englishWord1.setText(main_1[num_of_topic][id[i++]]);
        binding.englishWord2.setText(main_1[num_of_topic][id[i++]]);
        binding.englishWord3.setText(main_1[num_of_topic][id[i++]]);
        binding.englishWord4.setText(main_1[num_of_topic][id[i++]]);
        binding.englishWord5.setText(main_1[num_of_topic][id[i++]]);
        binding.englishWord6.setText(main_1[num_of_topic][id[i++]]);
        binding.englishWord7.setText(main_1[num_of_topic][id[i++]]);
        binding.englishWord8.setText(main_1[num_of_topic][id[i++]]);
        binding.englishWord9.setText(main_1[num_of_topic][id[i++]]);
        binding.englishWord10.setText(main_1[num_of_topic][id[i++]]);
        binding.englishWord11.setText(main_1[num_of_topic][id[i++]]);
        binding.englishWord12.setText(main_1[num_of_topic][id[i++]]);
        binding.englishWord13.setText(main_1[num_of_topic][id[i++]]);
        binding.englishWord14.setText(main_1[num_of_topic][id[i++]]);
        binding.englishWord15.setText(main_1[num_of_topic][id[i]]);
        i = 0;

        binding.translateWord1.setText(main_2[num_of_topic][id[i++]]);
        binding.translateWord2.setText(main_2[num_of_topic][id[i++]]);
        binding.translateWord3.setText(main_2[num_of_topic][id[i++]]);
        binding.translateWord4.setText(main_2[num_of_topic][id[i++]]);
        binding.translateWord5.setText(main_2[num_of_topic][id[i++]]);
        binding.translateWord6.setText(main_2[num_of_topic][id[i++]]);
        binding.translateWord7.setText(main_2[num_of_topic][id[i++]]);
        binding.translateWord8.setText(main_2[num_of_topic][id[i++]]);
        binding.translateWord9.setText(main_2[num_of_topic][id[i++]]);
        binding.translateWord10.setText(main_2[num_of_topic][id[i++]]);
        binding.translateWord11.setText(main_2[num_of_topic][id[i++]]);
        binding.translateWord12.setText(main_2[num_of_topic][id[i++]]);
        binding.translateWord13.setText(main_2[num_of_topic][id[i++]]);
        binding.translateWord14.setText(main_2[num_of_topic][id[i++]]);
        binding.translateWord15.setText(main_2[num_of_topic][id[i]]);
        i = 0;
    }

    private void check_visible_gone() {
        binding.check.setVisibility(View.GONE);
    }

    private void text1_1_visible_vis() {

        binding.translateWord1.setVisibility(View.VISIBLE);
        binding.translateWord2.setVisibility(View.VISIBLE);
        binding.translateWord3.setVisibility(View.VISIBLE);
        binding.translateWord4.setVisibility(View.VISIBLE);
        binding.translateWord5.setVisibility(View.VISIBLE);
        binding.translateWord6.setVisibility(View.VISIBLE);
        binding.translateWord7.setVisibility(View.VISIBLE);
        binding.translateWord8.setVisibility(View.VISIBLE);
        binding.translateWord9.setVisibility(View.VISIBLE);
        binding.translateWord10.setVisibility(View.VISIBLE);
        binding.translateWord11.setVisibility(View.VISIBLE);
        binding.translateWord12.setVisibility(View.VISIBLE);
        binding.translateWord13.setVisibility(View.VISIBLE);
        binding.translateWord14.setVisibility(View.VISIBLE);
        binding.translateWord15.setVisibility(View.VISIBLE);
    }

    private void text1_visible_gone() {

        binding.englishWord1.setVisibility(View.GONE);
        binding.englishWord2.setVisibility(View.GONE);
        binding.englishWord3.setVisibility(View.GONE);
        binding.englishWord4.setVisibility(View.GONE);
        binding.englishWord5.setVisibility(View.GONE);
        binding.englishWord6.setVisibility(View.GONE);
        binding.englishWord7.setVisibility(View.GONE);
        binding.englishWord8.setVisibility(View.GONE);
        binding.englishWord9.setVisibility(View.GONE);
        binding.englishWord10.setVisibility(View.GONE);
        binding.englishWord11.setVisibility(View.GONE);
        binding.englishWord12.setVisibility(View.GONE);
        binding.englishWord13.setVisibility(View.GONE);
        binding.englishWord14.setVisibility(View.GONE);
        binding.englishWord15.setVisibility(View.GONE);
    }

    private void set_all_back_to_lin() {
        int k = 0;
        setBackground_to_lin(binding.linearLay1, k);
        k++;
        setBackground_to_lin(binding.linearLay2, k);
        k++;
        setBackground_to_lin(binding.linearLay3, k);
        k++;
        setBackground_to_lin(binding.linearLay4, k);
        k++;
        setBackground_to_lin(binding.linearLay5, k);
        k++;
        setBackground_to_lin(binding.linearLay6, k);
        k++;
        setBackground_to_lin(binding.linearLay7, k);
        k++;
        setBackground_to_lin(binding.linearLay8, k);
        k++;
        setBackground_to_lin(binding.linearLay9, k);
        k++;
        setBackground_to_lin(binding.linearLay10, k);
        k++;
        setBackground_to_lin(binding.linearLay11, k);
        k++;
        setBackground_to_lin(binding.linearLay12, k);
        k++;
        setBackground_to_lin(binding.linearLay13, k);
        k++;
        setBackground_to_lin(binding.linearLay14, k);
        k++;
        setBackground_to_lin(binding.linearLay15, k);
    }

    @SuppressLint("ResourceAsColor")
    private void setBackground_to_lin(LinearLayout lin, int k) {
        if (isRight[k]) {
            lin.setBackgroundResource(R.color.back_true);
            counter_true++;
        } else
            lin.setBackgroundResource(R.color.back_false);
    }

    private void equals_back_with_true() {
        String word;
        for (int j = 0; j < 15; j++) {
            word = getResources().getString((main_1[num_of_topic][id[j]]));
            back_words[j] = back_words[j].trim();
            isRight[j] = back_words[j].equals(word);
        }
    }

    private void back_inf_from_editText() {
        int t = 0;
        back_words[t++] = binding.editText1.getText().toString();
        back_words[t++] = binding.editText2.getText().toString();
        back_words[t++] = binding.editText3.getText().toString();
        back_words[t++] = binding.editText4.getText().toString();
        back_words[t++] = binding.editText5.getText().toString();
        back_words[t++] = binding.editText6.getText().toString();
        back_words[t++] = binding.editText7.getText().toString();
        back_words[t++] = binding.editText8.getText().toString();
        back_words[t++] = binding.editText9.getText().toString();
        back_words[t++] = binding.editText10.getText().toString();
        back_words[t++] = binding.editText11.getText().toString();
        back_words[t++] = binding.editText12.getText().toString();
        back_words[t++] = binding.editText13.getText().toString();
        back_words[t++] = binding.editText14.getText().toString();
        back_words[t] = binding.editText15.getText().toString();
    }

    private void text1_visible_vis() {
        binding.englishWord1.setVisibility(View.VISIBLE);
        binding.englishWord2.setVisibility(View.VISIBLE);
        binding.englishWord3.setVisibility(View.VISIBLE);
        binding.englishWord4.setVisibility(View.VISIBLE);
        binding.englishWord5.setVisibility(View.VISIBLE);
        binding.englishWord6.setVisibility(View.VISIBLE);
        binding.englishWord7.setVisibility(View.VISIBLE);
        binding.englishWord8.setVisibility(View.VISIBLE);
        binding.englishWord9.setVisibility(View.VISIBLE);
        binding.englishWord10.setVisibility(View.VISIBLE);
        binding.englishWord11.setVisibility(View.VISIBLE);
        binding.englishWord12.setVisibility(View.VISIBLE);
        binding.englishWord13.setVisibility(View.VISIBLE);
        binding.englishWord14.setVisibility(View.VISIBLE);
        binding.englishWord15.setVisibility(View.VISIBLE);
    }

    private void mark_topic_as_done() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        String mod_key = String.valueOf(what_level) + num_of_topic;
        editor.putBoolean(mod_key, true);
        editor.apply();
    }
}