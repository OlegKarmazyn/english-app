package solid.icon.english.user_line.studying.fragments;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import solid.icon.english.R;
import solid.icon.english.architecture.UserFragmentActivity;
import solid.icon.english.architecture.room.WordModel;

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

        /* method after init */
        fillArrays();
        addButtonToScreen();
        createAddButton();

        randomizeArray();
        try {
            change_test();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void createAddButton() {

        Button btnTag = new Button(context);
        btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        btnTag.setText("Adding button");
        btnTag.setTextSize(18);
        verticalLinearLayout.addView(btnTag);
    }

    private void addButtonToScreen() {

        for (int i = 0; i < 1; i++) {

            LinearLayout horizontalLayout = new LinearLayout(context);
            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);

            //set the properties for button
            Button btnTag1 = new Button(context);
            btnTag1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btnTag1.setText("English");
            btnTag1.setTextSize(18);

            //set the properties for button
            Button btnTag2 = new Button(context);
            btnTag2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btnTag2.setText("Russion");
            btnTag2.setTextSize(18);

            //add button to the horizontalLayout
            horizontalLayout.addView(btnTag1);
            horizontalLayout.addView(btnTag2);

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