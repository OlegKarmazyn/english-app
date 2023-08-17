package solid.icon.english.words_by_levels.study_way;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;

import solid.icon.english.R;
import solid.icon.english.architecture.parents.ActivityGlobal;
import solid.icon.english.architecture.parents.MyFragmentActivity;
import solid.icon.english.architecture.res.Res_array;
import solid.icon.english.databinding.FragmentDefinitionBinding;


public class FragmentDefinition extends MyFragmentActivity implements View.OnClickListener {

    public FragmentDefinition(Serializable what_level, int num_of_topic) {
        this.what_level = what_level;
        this.num_of_topic = num_of_topic;
        defineArrays();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDefinitionBinding.inflate(inflater);
        return binding.getRoot();
    }

    FragmentDefinitionBinding binding;

    private int i = 0;

    public int[][] main_meaning = new int[][]{};

    private int[] id = new int[]{55, 66, 77, 88, 99, 100, 110, 112, 114, 124, 1234, 124, 768, 345, 98};

    private Drawable f;


    private int[] counter_true = new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
    private int count = 0;

    private static boolean isNotTranslating = true;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isNotTranslating = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isNotTranslating = false;
    }

    @Override
    public void onResume() {
        super.onResume();

        context = getActivity();

        if (isNotTranslating) {

            for (int i = 0; i < 15; i++) {
                id[i] = i * -1;
            }

            assert context != null;
            binding.meaning.setClickable(false);

            full_array();

            f = binding.writeEdit.getBackground();

            binding.fab.setOnClickListener(this);
            binding.checkButton.setOnClickListener(this);
            binding.englishWord.setOnClickListener(this);

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

            binding.translateWord.setClickable(false);
            words_get_text();
        }
    }

    @Override
    protected void defineArrays() {
        super.defineArrays();
        if (what_level == ActivityGlobal.LessonsName.A2) {
            main_meaning = new Res_array().main_meaning_a2.clone();
        } else if (what_level == ActivityGlobal.LessonsName.B1) {
            main_meaning = new Res_array().main_meaning_b1.clone();
        } else if (what_level == ActivityGlobal.LessonsName.B2) {
            main_meaning = new Res_array().main_meaning_b2.clone();
        }
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

    private void listen() {
        speak(getResources().getString((main_1[num_of_topic][id[i]])));
    }

    private void words_get_text() {
        binding.meaning.setText(main_meaning[num_of_topic][id[i]]);
        binding.englishWord.setText(main_1[num_of_topic][id[i]]);
        binding.translateWord.setText(main_2[num_of_topic][id[i]]);
    }

    private boolean isTrueWords() {
        String eT = binding.writeEdit.getText().toString();
        eT = eT.trim();
        String res = (getResources().getString((main_1[num_of_topic][id[i]])));
        return eT.equals(res);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkButton:
                binding.definitionLayout.setVisibility(View.VISIBLE);

                if (isTrueWords()) {
                    if (counter_true[i] != 0) {
                        counter_true[i] = 1;
                    }

                    binding.writeEdit.setBackgroundResource(R.color.back_true);
                    binding.fab.setVisibility(View.VISIBLE);
                } else {
                    counter_true[i] = 0;
                    binding.writeEdit.setBackgroundResource(R.color.back_false);
                }
                break;

            case R.id.fab:
                if (i < main_1[num_of_topic].length - 1) {
                    i++;
                    binding.definitionLayout.setVisibility(View.GONE);
                    binding.fab.setVisibility(View.GONE);
                    binding.writeEdit.setText("");
                    words_get_text();
                    binding.writeEdit.setBackground(f);
                } else {
                    count = 0;
                    for (int c : counter_true)
                        if (c == 1)
                            count++;

                    Toast mess = Toast.makeText(context, "Correct answers " + count + " of " + main_1[num_of_topic].length, Toast.LENGTH_LONG);
                    mess.show();
                    binding.fab.setVisibility(View.GONE);
                }
                break;
            case R.id.englishWord:
                listen();
                break;
        }
    }
}