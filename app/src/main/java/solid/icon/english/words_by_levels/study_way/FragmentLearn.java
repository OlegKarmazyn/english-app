package solid.icon.english.words_by_levels.study_way;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.Serializable;

import solid.icon.english.architecture.parents.MyFragmentActivity;
import solid.icon.english.databinding.FragmentLearnBinding;

public class FragmentLearn extends MyFragmentActivity {

    public FragmentLearn(Serializable what_level, int num_of_topic) {
        super();
        this.what_level = what_level;
        this.num_of_topic = num_of_topic;
        defineArrays();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLearnBinding.inflate(inflater);
        return binding.getRoot();
    }

    FragmentLearnBinding binding;

    private int[] id = new int[]{55, 66, 77, 88, 99, 100, 110, 112, 114, 124, 1234, 124, 768, 345, 98};

    private int iterator = 0;

    private View.OnClickListener listener(TextView view) {
        return v -> {
            view.setVisibility(View.VISIBLE);
            speak(((TextView) view).getText().toString());
        };
    }

    @Override
    public void onResume() {
        super.onResume();

        context = getActivity();

        for (int i = 0; i < 15; i++) {
            id[i] = i * -1;
        }

        assert context != null;
        binding.englishWord1.setOnClickListener(listener(binding.translateWord1));
        binding.englishWord2.setOnClickListener(listener(binding.translateWord2));
        binding.englishWord3.setOnClickListener(listener(binding.translateWord3));
        binding.englishWord4.setOnClickListener(listener(binding.translateWord4));
        binding.englishWord5.setOnClickListener(listener(binding.translateWord5));
        binding.englishWord6.setOnClickListener(listener(binding.translateWord6));
        binding.englishWord7.setOnClickListener(listener(binding.translateWord7));
        binding.englishWord8.setOnClickListener(listener(binding.translateWord8));
        binding.englishWord9.setOnClickListener(listener(binding.translateWord9));
        binding.englishWord10.setOnClickListener(listener(binding.translateWord10));
        binding.englishWord11.setOnClickListener(listener(binding.translateWord11));
        binding.englishWord12.setOnClickListener(listener(binding.translateWord12));
        binding.englishWord13.setOnClickListener(listener(binding.translateWord13));
        binding.englishWord14.setOnClickListener(listener(binding.translateWord14));
        binding.englishWord15.setOnClickListener(listener(binding.translateWord15));

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
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        full_array();
        try {
            change_test();
        } catch (Exception e) {/*empty*/}
    }

    @Override
    public void onPause() {
        super.onPause();
        text1_1_visibel_gone();
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

    private void text1_1_visibel_gone() {

        binding.translateWord1.setVisibility(View.GONE);
        binding.translateWord2.setVisibility(View.GONE);
        binding.translateWord3.setVisibility(View.GONE);
        binding.translateWord4.setVisibility(View.GONE);
        binding.translateWord5.setVisibility(View.GONE);
        binding.translateWord6.setVisibility(View.GONE);
        binding.translateWord7.setVisibility(View.GONE);
        binding.translateWord8.setVisibility(View.GONE);
        binding.translateWord9.setVisibility(View.GONE);
        binding.translateWord10.setVisibility(View.GONE);
        binding.translateWord11.setVisibility(View.GONE);
        binding.translateWord12.setVisibility(View.GONE);
        binding.translateWord13.setVisibility(View.GONE);
        binding.translateWord14.setVisibility(View.GONE);
        binding.translateWord15.setVisibility(View.GONE);
    }

    private void change_test() {

        binding.englishWord1.setText(main_1[num_of_topic][id[iterator++]]);
        binding.englishWord2.setText(main_1[num_of_topic][id[iterator++]]);
        binding.englishWord3.setText(main_1[num_of_topic][id[iterator++]]);
        binding.englishWord4.setText(main_1[num_of_topic][id[iterator++]]);
        binding.englishWord5.setText(main_1[num_of_topic][id[iterator++]]);
        binding.englishWord6.setText(main_1[num_of_topic][id[iterator++]]);
        binding.englishWord7.setText(main_1[num_of_topic][id[iterator++]]);
        binding.englishWord8.setText(main_1[num_of_topic][id[iterator++]]);
        binding.englishWord9.setText(main_1[num_of_topic][id[iterator++]]);
        binding.englishWord10.setText(main_1[num_of_topic][id[iterator++]]);
        binding.englishWord11.setText(main_1[num_of_topic][id[iterator++]]);
        binding.englishWord12.setText(main_1[num_of_topic][id[iterator++]]);
        binding.englishWord13.setText(main_1[num_of_topic][id[iterator++]]);
        binding.englishWord14.setText(main_1[num_of_topic][id[iterator++]]);
        binding.englishWord15.setText(main_1[num_of_topic][id[iterator]]);
        iterator = 0;

        binding.translateWord1.setText(main_2[num_of_topic][id[iterator++]]);
        binding.translateWord2.setText(main_2[num_of_topic][id[iterator++]]);
        binding.translateWord3.setText(main_2[num_of_topic][id[iterator++]]);
        binding.translateWord4.setText(main_2[num_of_topic][id[iterator++]]);
        binding.translateWord5.setText(main_2[num_of_topic][id[iterator++]]);
        binding.translateWord6.setText(main_2[num_of_topic][id[iterator++]]);
        binding.translateWord7.setText(main_2[num_of_topic][id[iterator++]]);
        binding.translateWord8.setText(main_2[num_of_topic][id[iterator++]]);
        binding.translateWord9.setText(main_2[num_of_topic][id[iterator++]]);
        binding.translateWord10.setText(main_2[num_of_topic][id[iterator++]]);
        binding.translateWord11.setText(main_2[num_of_topic][id[iterator++]]);
        binding.translateWord12.setText(main_2[num_of_topic][id[iterator++]]);
        binding.translateWord13.setText(main_2[num_of_topic][id[iterator++]]);
        binding.translateWord14.setText(main_2[num_of_topic][id[iterator++]]);
        binding.translateWord15.setText(main_2[num_of_topic][id[iterator]]);
        iterator = 0;
    }
}