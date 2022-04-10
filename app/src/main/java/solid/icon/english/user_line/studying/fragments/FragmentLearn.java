package solid.icon.english.user_line.studying.fragments;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import solid.icon.english.R;
import solid.icon.english.architecture.UserFragmentActivity;
import solid.icon.english.architecture.room.App;
import solid.icon.english.architecture.room.WordModel;
import solid.icon.english.architecture.room.WordModelDao;

public class FragmentLearn extends UserFragmentActivity implements View.OnClickListener {

    public FragmentLearn(List<WordModel> wordModelList, String topic, String subTopic, String[] englishTranslArr, String[] rusTranslArr) {
        super(wordModelList, topic, subTopic, englishTranslArr, rusTranslArr);

        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.user_fragment_learn, container, false);
    }

    TextView words1; TextView words2; TextView words3; TextView words4; TextView words5; TextView words6;
    TextView words7; TextView words8; TextView words9; TextView words10; TextView words11;
    TextView words12; TextView words13; TextView words14; TextView words15;

    TextView words1_1; TextView words2_1; TextView words3_1; TextView words4_1; TextView words5_1; TextView words6_1;
    TextView words7_1; TextView words8_1; TextView words9_1; TextView words10_1; TextView words11_1;
    TextView words12_1; TextView words13_1; TextView words14_1; TextView words15_1;

    ScrollView learn_ScrollView;

    LinearLayout verticalLinearLayout;

    List<Button> buttonListOfEnglish, buttonListOfRus;

    /**
     *  onResume
     */

    @Override
    public void onResume(){
        super.onResume();

        context = getActivity();

        assert context != null;

        learn_ScrollView = context.findViewById(R.id.learn_ScrollView);
        verticalLinearLayout = context.findViewById(R.id.verticalLinearLayout);

        mTTS = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
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
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        /* methods after init */
        fillArrays();
        addTranslationButtonToScreen();
        createAddButton();

        randomizeArray();
        try {
            change_test();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * There is ADDING BUTTON
     */
    private void createAddButton() {
        //counting margin
        int dp_15 = getDp(15);
        Log.e(TAG, String.valueOf(dp_15));

        LinearLayout horizontalLayout = new LinearLayout(context);
        horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
        horizontalLayout.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, dp_15,0,0);

        horizontalLayout.setLayoutParams(layoutParams);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        //set the properties for Translation button
        Button button = new Button(context);
        button.setLayoutParams(params);
        button.setText("Adding button");
        button.setTextSize(15);
        button.setBackgroundResource(R.drawable.person_together);
        button.setPadding(getDp(5), dp_15, getDp(5), dp_15);
        button.setGravity(Gravity.CENTER);

        //listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddingDialog();
            }

            private void showAddingDialog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                final View customLayout = getLayoutInflater().inflate(R.layout.custom_dialog, null);
                builder.setView(customLayout);
                AlertDialog dialog = builder.create();
                dialog.show();

                customLayout.findViewById(R.id.but_yes).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                        EditText englishWord = customLayout.findViewById(R.id.english_word);
                        EditText russianWord = customLayout.findViewById(R.id.russian_word);
                        EditText definition = customLayout.findViewById(R.id.definition);
                        addToDBNewWord(englishWord.getText().toString(), russianWord.getText().toString(), definition.getText().toString());
                    }
                });
                customLayout.findViewById(R.id.but_no).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
            }

            private void addToDBNewWord(String englishWord, String russianWord, String definition){
                WordModelDao wordModelDao = App.instance.getDatabase().wordModelDao();
                WordModel wordModel = new WordModel();
                wordModel.englishWord = englishWord;
                wordModel.rusWord = russianWord;
                wordModel.definition = definition;
                wordModel.topicName = topic;
                wordModel.subTopicName = subTopic;
                wordModelDao.insert(wordModel);
            }
        });

        //add layout to the layout verticalLinearLayout
        horizontalLayout.addView(button);
        verticalLinearLayout.addView(horizontalLayout);
    }

    /**
     * Translation buttons
     */
    private void addTranslationButtonToScreen() {
        int dp_15 = getDp(15);

        for (int i = 0; i < size; i++) {

            LinearLayout horizontalLayout = new LinearLayout(context);
            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);

            //params for horizontalLayout
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, dp_15, 0,0);
            horizontalLayout.setLayoutParams(layoutParams);

            //set params for buttons
            LinearLayout.LayoutParams engButtonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            engButtonParams.weight = 1;
            LinearLayout.LayoutParams rusButtonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            rusButtonParams.weight = 1;
            engButtonParams.setMargins(0, 0, 0,0);
            rusButtonParams.setMargins( getDp(10), 0, 0,0);

            //set the properties for English button
            TextView textViewEng = new TextView(context);
            textViewEng.setText(englishTranslArr[i]);
            textViewEng.setTextSize(15);
            textViewEng.setBackgroundResource(R.drawable.person_together);
            textViewEng.setPadding(getDp(5), dp_15, getDp(5), dp_15);
            textViewEng.setLayoutParams(engButtonParams);
            textViewEng.setGravity(Gravity.CENTER);
            textViewEng.setTextColor(getActivity().getColor(R.color.ios_black));

            //set the properties for Russian button
            TextView textViewRus = new TextView(context);
            textViewRus.setText(rusTranslArr[i]);
            textViewRus.setTextSize(15);
            textViewRus.setBackgroundResource(R.drawable.person_together);
            textViewRus.setPadding(getDp(5), dp_15, getDp(5), dp_15);
            textViewRus.setLayoutParams(rusButtonParams);
            textViewRus.setGravity(Gravity.CENTER);
            textViewRus.setTextColor(getActivity().getColor(R.color.ios_black));

            //add button to the horizontalLayout
            horizontalLayout.addView(textViewEng);
            horizontalLayout.addView(textViewRus);

            //add layout to the layout verticalLinearLayout
            verticalLinearLayout.addView(horizontalLayout);
        }
    }

    private void fillArrays() {
        id = new int[size];
        counter_flip = new int[size];
        if(size != 0) {
            Arrays.fill(id, 99999);
            Arrays.fill(counter_flip, 0);
        }
    }


    private void randomizeArray(){
        int rand;
        boolean isTrue = false;
        int k;


        for (int iter = 0; iter < size; iter++) {
            do {
                k = 0;
                rand = (int) (Math.random() * size);
                for (int j = 0; j < size; j++) {
                    if (rand == id[j]) {
                        isTrue = true;
                    }else{
                        k++;
                    }
                }
                if(k == size){
                    isTrue = false;
                }

            } while (isTrue);
            id[iter] = rand;
        }
    }

    private void text1_1_visibel_gone() {

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

    private void change_test(){

//        words1.setText(main_1[num_of_topic][id[i++]]);
//        words2.setText(main_1[num_of_topic][id[i++]]);
//        words3.setText(main_1[num_of_topic][id[i++]]);
//        words4.setText(main_1[num_of_topic][id[i++]]);
//        words5.setText(main_1[num_of_topic][id[i++]]);
//        words6.setText(main_1[num_of_topic][id[i++]]);
//        words7.setText(main_1[num_of_topic][id[i++]]);
//        words8.setText(main_1[num_of_topic][id[i++]]);
//        words9.setText(main_1[num_of_topic][id[i++]]);
//        words10.setText(main_1[num_of_topic][id[i++]]);
//        words11.setText(main_1[num_of_topic][id[i++]]);
//        words12.setText(main_1[num_of_topic][id[i++]]);
//        words13.setText(main_1[num_of_topic][id[i++]]);
//        words14.setText(main_1[num_of_topic][id[i++]]);
//        words15.setText(main_1[num_of_topic][id[i]]);
//        i = 0;
//
//        words1_1.setText(main_2[num_of_topic][id[i++]]);
//        words2_1.setText(main_2[num_of_topic][id[i++]]);
//        words3_1.setText(main_2[num_of_topic][id[i++]]);
//        words4_1.setText(main_2[num_of_topic][id[i++]]);
//        words5_1.setText(main_2[num_of_topic][id[i++]]);
//        words6_1.setText(main_2[num_of_topic][id[i++]]);
//        words7_1.setText(main_2[num_of_topic][id[i++]]);
//        words8_1.setText(main_2[num_of_topic][id[i++]]);
//        words9_1.setText(main_2[num_of_topic][id[i++]]);
//        words10_1.setText(main_2[num_of_topic][id[i++]]);
//        words11_1.setText(main_2[num_of_topic][id[i++]]);
//        words12_1.setText(main_2[num_of_topic][id[i++]]);
//        words13_1.setText(main_2[num_of_topic][id[i++]]);
//        words14_1.setText(main_2[num_of_topic][id[i++]]);
//        words15_1.setText(main_2[num_of_topic][id[i]]);
    }

    private void speak(String text){
//        float pitch = 0.5f;
//        float speed = 0.5f;
//        mTTS.setPitch(pitch);
//        mTTS.setSpeechRate(speed);

        Log.e("TTS", "speaking");

        mTTS.speak(text,TextToSpeech.QUEUE_FLUSH, null);
    }

    public void calculate_flip(){
        int co = 0;
        for (int g: counter_flip) {
            if (g == 1){
                co++;
            }
        }
        if (co == size){
            //el_next.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.words1:
                if (words1_1.getVisibility() == View.GONE) {
                    words1_1.setVisibility(View.VISIBLE);
                    counter_flip[0] = 1;
                    calculate_flip();
                }
                speak((String) words1.getText());
                break;

            case R.id.words2:
                if (words2_1.getVisibility() == View.GONE) {
                    words2_1.setVisibility(View.VISIBLE);
                    counter_flip[1] = 1;
                    calculate_flip();
                }
                speak((String) words2.getText());
                break;

            case R.id.words3:
                if (words3_1.getVisibility() == View.GONE) {
                    words3_1.setVisibility(View.VISIBLE);
                    counter_flip[2] = 1;
                    calculate_flip();
                }
                speak((String) words3.getText());
                break;

            case R.id.words4:
                if (words4_1.getVisibility() == View.GONE) {
                    words4_1.setVisibility(View.VISIBLE);
                    counter_flip[3] = 1;
                    calculate_flip();
                }
                speak((String) words4.getText());
                break;

            case R.id.words5:
                if (words5_1.getVisibility() == View.GONE) {
                    words5_1.setVisibility(View.VISIBLE);
                    counter_flip[4] = 1;
                    calculate_flip();
                }
                speak((String) words5.getText());
                break;

            case R.id.words6:
                if (words6_1.getVisibility() == View.GONE) {
                    words6_1.setVisibility(View.VISIBLE);
                    counter_flip[5] = 1;
                    calculate_flip();
                }
                speak((String) words6.getText());
                break;

            case R.id.words7:
                if (words7_1.getVisibility() == View.GONE) {
                    words7_1.setVisibility(View.VISIBLE);
                    counter_flip[6] = 1;
                    calculate_flip();
                }
                speak((String) words7.getText());
                break;

            case R.id.words8:
                if (words8_1.getVisibility() == View.GONE) {
                    words8_1.setVisibility(View.VISIBLE);
                    counter_flip[7] = 1;
                    calculate_flip();
                }
                speak((String) words8.getText());
                break;

            case R.id.words9:
                if (words9_1.getVisibility() == View.GONE) {
                    words9_1.setVisibility(View.VISIBLE);
                    counter_flip[8] = 1;
                    calculate_flip();
                }
                speak((String) words9.getText());
                break;

            case R.id.words10:
                if (words10_1.getVisibility() == View.GONE) {
                    words10_1.setVisibility(View.VISIBLE);
                    counter_flip[9] = 1;
                    calculate_flip();
                }
                speak((String) words10.getText());
                break;

            case R.id.words11:
                if (words11_1.getVisibility() == View.GONE) {
                    words11_1.setVisibility(View.VISIBLE);
                    counter_flip[10] = 1;
                    calculate_flip();
                }
                speak((String) words11.getText());
                break;

            case R.id.words12:
                if (words12_1.getVisibility() == View.GONE) {
                    words12_1.setVisibility(View.VISIBLE);
                    counter_flip[11] = 1;
                    calculate_flip();
                }
                speak((String) words12.getText());
                break;

            case R.id.words13:
                if (words13_1.getVisibility() == View.GONE) {
                    words13_1.setVisibility(View.VISIBLE);
                    counter_flip[12] = 1;
                    calculate_flip();
                }
                speak((String) words13.getText());
                break;

            case R.id.words14:
                if (words14_1.getVisibility() == View.GONE) {
                    words14_1.setVisibility(View.VISIBLE);
                    counter_flip[13] = 1;
                    calculate_flip();
                }
                speak((String) words14.getText());
                break;

            case R.id.words15:
                if (words15_1.getVisibility() == View.GONE) {
                    words15_1.setVisibility(View.VISIBLE);
                    counter_flip[14] = 1;
                    calculate_flip();
                }
                speak((String) words15.getText());
                break;
        }
    }

    private void reply__text(){
        int temp = 1000;
        for (int j = 0; j < size; j++) {
            id[j] = temp;
            temp++;
        }
        randomizeArray();

        text1_1_visibel_gone();
        change_test();
    }

    @Override
    public void onPause() {
        super.onPause();
        //text1_1_visibel_gone();
        //todo
    }
}