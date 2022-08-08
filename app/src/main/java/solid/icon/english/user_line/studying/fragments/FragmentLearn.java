package solid.icon.english.user_line.studying.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import solid.icon.english.R;
import solid.icon.english.architecture.UserFragmentActivity;
import solid.icon.english.architecture.room.App;
import solid.icon.english.architecture.room.WordModel;
import solid.icon.english.architecture.room.WordModelDao;
import solid.icon.english.user_line.studying.StudyActivity;

public class FragmentLearn extends UserFragmentActivity implements View.OnClickListener {

    public FragmentLearn(List<WordModel> wordModelList, String topic, String subTopic, StudyActivity studyActivity) {
        super(wordModelList, topic, subTopic, studyActivity);

        // Required empty public constructor
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

    private boolean isCreate = false;

    private RelativeLayout relativeLayout_proz;
    private LinearLayout bottom_lay;


    /**
     *  onResume
     */
    @Override
    public void onResume(){
        super.onResume();

        context = getActivity();

        assert context != null;

        learn_ScrollView = context.findViewById(R.id.scrollView);
        verticalLinearLayout = context.findViewById(R.id.verticalLinearLayout);
        relativeLayout_proz = context.findViewById(R.id.relativeLayout_proz);
        bottom_lay = context.findViewById(R.id.bottom_lay);

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

            addTranslationButtonToScreen();
            createAddButton();
        }

        setVisibleAllItems();
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

            final EditText englishWord = context.findViewById(R.id.english_word),
                    russianWord = context.findViewById(R.id.russian_word),
                    definition = context.findViewById(R.id.definition);

            @Override
            public void onClick(View v) {
                showMenu();
                context.findViewById(R.id.but_yes).setOnClickListener(v1 -> combineData());
            }

            private void combineData() {
                addToDBNewWord(
                        englishWord.getText().toString().trim(),
                        russianWord.getText().toString().trim(),
                        definition.getText().toString().trim());
            }

            private void addToDBNewWord(String englishWord, String russianWord, String definition){
                WordModel wordModel = new WordModel();
                wordModel.englishWord = englishWord;
                wordModel.rusWord = russianWord;
                wordModel.definition = definition;
                wordModel.topicName = topic;
                wordModel.subTopicName = subTopic;
                wordModelDao.insert(wordModel);
                wordModelList.add(wordModel);
                studyActivity.setDateToActivity();
            }
        });

        //adding layout to the layout verticalLinearLayout
        horizontalLayout.addView(button);
        verticalLinearLayout.addView(horizontalLayout);

    }

    /**
     * Translation buttons
     */
    private void addTranslationButtonToScreen() {
        int dp_15 = getDp(15);

        for (int i = 0; i < size; i++) {
            final int randomInt = id[i];

            LinearLayout horizontalLayout = new LinearLayout(context);
            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);

            //params for horizontalLayout
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(0, dp_15, 0,0);
            horizontalLayout.setLayoutParams(layoutParams);

            //set params for buttons
            LinearLayout.LayoutParams engButtonParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            LinearLayout.LayoutParams rusButtonParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);


            TextView textViewEng = new TextView(context),
                    textViewRus = new TextView(context);

            //set the properties for English button
            textViewEng.setText(englishTranslArr[randomInt]);
            textViewEng.setTextSize(15);
            textViewEng.setBackgroundResource(R.drawable.person_together);
            textViewEng.setPadding(getDp(5), dp_15, getDp(5), dp_15);
            textViewEng.setLayoutParams(engButtonParams);
            textViewEng.setGravity(Gravity.CENTER);
            textViewEng.setTextColor(getActivity().getColor(R.color.ios_black));


            //set the properties for Russian button
            textViewRus.setText(rusTranslArr[randomInt]);
            textViewRus.setTextSize(15);
            textViewRus.setBackgroundResource(R.drawable.person_together);
            textViewRus.setPadding(getDp(5), dp_15, getDp(5), dp_15);
            textViewRus.setLayoutParams(rusButtonParams);
            textViewRus.setGravity(Gravity.CENTER);
            textViewRus.setTextColor(getActivity().getColor(R.color.ios_black));


            // create listener ( + long Listener)
            View.OnClickListener listener = v -> {
                if(!studyActivity.isReplaced) {
                    textViewRus.setVisibility(View.VISIBLE);
                }else {
                    textViewEng.setVisibility(View.VISIBLE);
                }
                speak(textViewEng.getText().toString());
                outLog("OnClickListener - first (speak) step");

                v.setOnLongClickListener(v1 -> {
                    showDeleteDialog(randomInt);
                    outLog("OnClickListener - second (delete) step");
                    //studyActivity.showMenu(); //todo edit + delete
                    return false;
                });
            };

            // setOnClickListener
            textViewEng.setOnClickListener(listener);
            textViewRus.setOnClickListener(listener);

            //add button to the lists
            buttonListOfEnglish.add(textViewEng);
            buttonListOfRus.add(textViewRus);

            if(!studyActivity.isReplaced) {
                engButtonParams.setMargins(0, 0, 0, 0);
                rusButtonParams.setMargins(getDp(10), 0, 0, 0);
                textViewEng.setVisibility(View.VISIBLE);
                textViewRus.setVisibility(View.GONE);

                horizontalLayout.addView(textViewEng);
                horizontalLayout.addView(textViewRus);
            } else{
                engButtonParams.setMargins(getDp(10), 0, 0, 0);
                rusButtonParams.setMargins(0, 0, 0, 0);
                textViewEng.setVisibility(View.GONE);
                textViewRus.setVisibility(View.VISIBLE);

                horizontalLayout.addView(textViewRus);
                horizontalLayout.addView(textViewEng);
            }

            //add layout to the layout verticalLinearLayout
            verticalLinearLayout.addView(horizontalLayout);

        }
    }

    private void showMenu(){
        relativeLayout_proz.setAlpha(0f);

        TranslateAnimation animation = new TranslateAnimation(0, 0, 2000, 0);
        animation.setDuration(1000);
        animation.setFillAfter(true);

        bottom_lay.setVisibility(View.VISIBLE);
        relativeLayout_proz.setVisibility(View.VISIBLE);
        relativeLayout_proz.animate().alpha(1f).setDuration(1000);
        bottom_lay.startAnimation(animation);

        relativeLayout_proz.setOnClickListener(this::closeMenu);

        context.findViewById(R.id.but_no).setOnClickListener(this::closeMenu);
    }

    public void closeMenu(View v){
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, 2000);
        animation.setDuration(1000);
        animation.setFillAfter(true);

        new Handler().postDelayed(() -> {
            bottom_lay.setVisibility(View.GONE);
            relativeLayout_proz.setVisibility(View.GONE);
        }, 1000);

        relativeLayout_proz.animate().alpha(0f).setDuration(1000);
        bottom_lay.startAnimation(animation);
    }

    public void showDeleteDialog(int i){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Do you want to delete topic?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                WordModel wordModel = wordModelDao.getWordModelByName(englishTranslArr[i], rusTranslArr[i], subTopic, topic);
                outLog("deleted - " + wordModel.englishWord);
                wordModelDao.delete(wordModel);
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();
    }

    private void text_visible_gone() {
        if(!studyActivity.isReplaced) {
            for (TextView t : buttonListOfRus) {
                t.setVisibility(View.GONE);
            }
        }else {
            for (TextView t : buttonListOfEnglish) {
                t.setVisibility(View.GONE);
            }
        }
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
        text_visible_gone();

    }
}