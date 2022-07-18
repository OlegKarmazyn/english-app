package solid.icon.english.user_line.studying.fragments;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.user_fragment_learn, container, false);
    }

    ScrollView learn_ScrollView;

    LinearLayout verticalLinearLayout;

    List<TextView> buttonListOfEnglish = new ArrayList<>(),
            buttonListOfRus = new ArrayList<>();

    WordModelDao wordModelDao;

    /**
     *  onResume
     */

    private boolean isCreate = false;
    @Override
    public void onResume(){
        super.onResume();

        context = getActivity();

        assert context != null;

        learn_ScrollView = context.findViewById(R.id.learn_ScrollView);
        verticalLinearLayout = context.findViewById(R.id.verticalLinearLayout);

        wordModelDao = App.instance.getDatabase().wordModelDao();

        mTTS = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result =  mTTS.setLanguage(Locale.ENGLISH);

                    if(result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        outLog("TTS - Language not supported");
                    }

                }else{
                    outLog("TTS - Initialization failed");
                }
            }
        });

        /* methods after init */
        if(!isCreate) {
            isCreate = true;

            fillArrays();

            addTranslationButtonToScreen();
            createAddButton();

            randomizeArray();
        }

    }

    /**
     * There is ADDING BUTTON
     */
    private void createAddButton() {
        //counting margin
        int dp_15 = getDp(15);
        outLog("dp_15 = " + dp_15);

        LinearLayout horizontalLayout = new LinearLayout(context);
        horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
        horizontalLayout.setGravity(Gravity.CENTER);
        horizontalLayout.setAlpha(0f);

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

                customLayout.findViewById(R.id.but_yes).setOnClickListener(view -> {
                    dialog.cancel();
                    EditText englishWord = customLayout.findViewById(R.id.english_word);
                    EditText russianWord = customLayout.findViewById(R.id.russian_word);
                    EditText definition = customLayout.findViewById(R.id.definition);
                    addToDBNewWord(englishWord.getText().toString(), russianWord.getText().toString(), definition.getText().toString());
                });

                customLayout.findViewById(R.id.but_no).setOnClickListener(view -> dialog.cancel());
            }

            private void addToDBNewWord(String englishWord, String russianWord, String definition){
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

        horizontalLayout.animate().alpha(1f).setDuration(1300);
    }

    /**
     * Translation buttons
     */
    private void addTranslationButtonToScreen() {
        int dp_15 = getDp(15);

        for (int i = 0; i < size; i++) {
            final int I = i;

            LinearLayout horizontalLayout = new LinearLayout(context);
            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
            horizontalLayout.setAlpha(0f);

            //params for horizontalLayout
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, dp_15, 0,0);
            horizontalLayout.setLayoutParams(layoutParams);

            //set params for buttons
            LinearLayout.LayoutParams engButtonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            engButtonParams.weight = 1;
            LinearLayout.LayoutParams rusButtonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            rusButtonParams.weight = 1;
            engButtonParams.setMargins(getDp(10), 0, 0,0);
            rusButtonParams.setMargins( 0, 0, 0,0);

            TextView textViewEng = new TextView(context),
                    textViewRus = new TextView(context);

            //set the properties for English button
            textViewEng.setText(englishTranslArr[i]);
            textViewEng.setTextSize(15);
            textViewEng.setBackgroundResource(R.drawable.person_together);
            textViewEng.setPadding(getDp(5), dp_15, getDp(5), dp_15);
            textViewEng.setLayoutParams(engButtonParams);
            textViewEng.setGravity(Gravity.CENTER);
            textViewEng.setTextColor(getActivity().getColor(R.color.ios_black));

            //set the properties for Russian button
            textViewRus.setText(rusTranslArr[i]);
            textViewRus.setTextSize(15);
            textViewRus.setBackgroundResource(R.drawable.person_together);
            textViewRus.setPadding(getDp(5), dp_15, getDp(5), dp_15);
            textViewRus.setLayoutParams(rusButtonParams);
            textViewRus.setGravity(Gravity.CENTER);
            textViewRus.setTextColor(getActivity().getColor(R.color.ios_black));

            // create listener ( + long Listener)
            View.OnClickListener listener = v -> {
                textViewEng.setVisibility(View.VISIBLE);
                speak(textViewEng.getText().toString());
                outLog("OnClickListener - first (speak) step");

                v.setOnLongClickListener(v1 -> {
                    deleteWord(I);
                    outLog("OnClickListener - second (delete) step");
                    return false;
                });
            };

            // setOnClickListener
            textViewRus.setOnClickListener(listener);
            textViewEng.setOnClickListener(listener);

            //add button to the lists
            buttonListOfRus.add(textViewRus);
            buttonListOfEnglish.add(textViewEng);

            //add button to the horizontalLayout
            horizontalLayout.addView(textViewRus);
            horizontalLayout.addView(textViewEng);


            //add layout to the layout verticalLinearLayout
            verticalLinearLayout.addView(horizontalLayout);

            horizontalLayout.animate().alpha(1f).setDuration(1300);
        }
    }

    private void deleteWord(int i){
        WordModel wordModel = wordModelDao.getWordModelByName(englishTranslArr[i], rusTranslArr[i], subTopic, topic);
        wordModelDao.delete(wordModel);
        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
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

    private void text1_1_visible_gone() {
        for (TextView t: buttonListOfRus) {
            t.setVisibility(View.GONE);
        }
        //todo Visible done second buttons
    }

    private void speak(String text){
        outLog("TTS - is speaking");

        mTTS.speak(text,TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPause() {
        super.onPause();
        text1_1_visible_gone();
        //todo
    }
}