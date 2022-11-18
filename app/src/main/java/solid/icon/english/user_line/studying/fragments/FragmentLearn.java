package solid.icon.english.user_line.studying.fragments;

import android.os.Build;
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

public class FragmentLearn extends UserFragmentActivity {

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

    private TextView but_yes, but_no, menu_title;
    private EditText englishWord, russianWord, definition;


    //____________________onResume____________________//
    @Override
    public void onResume() {
        super.onResume();

        context = getActivity();
        assert context != null;

        init();

        /* methods after init */
        if (!isCreate) {
            isCreate = true;

            addTranslationButtonToScreen();
            createAddButton();
        }

        setVisibleAllItems();
    }

    private void init() {
        learn_ScrollView = context.findViewById(R.id.scrollView);
        verticalLinearLayout = context.findViewById(R.id.verticalLinearLayout);
        relativeLayout_proz = context.findViewById(R.id.relativeLayout_proz);
        relativeLayout_proz.setOnClickListener(this::closeMenu);
        bottom_lay = context.findViewById(R.id.bottom_lay);
        but_no = context.findViewById(R.id.but_no);
        but_yes = context.findViewById(R.id.but_yes);
        menu_title = context.findViewById(R.id.menu_title);
        englishWord = context.findViewById(R.id.english_word);
        russianWord = context.findViewById(R.id.russian_word);
        definition = context.findViewById(R.id.definition);

        wordModelDao = App.instance.getDatabase().wordModelDao();

        mTTS = new TextToSpeech(context, status -> {
            if (status == TextToSpeech.SUCCESS) {
                Locale locale = new Locale(topicModel.country);
                int result = mTTS.setLanguage(locale);

                if (result == TextToSpeech.LANG_MISSING_DATA
                        || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    outLog("TTS - Language not supported");
                }

            } else {
                outLog("TTS - Initialization failed");
            }
        });
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
        layoutParams.setMargins(0, dp_15, 0, 0);

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
                appearMenu();
                setUpEditingMenu();
            }

            private void setUpEditingMenu() {
                menu_title.setText("Enter info for create new word");
                but_no.setText("Close");
                but_yes.setText("Create");
                but_no.setOnClickListener(v1 -> closeMenu(v1));
                but_yes.setOnClickListener(v1 -> combineData());
            }

            private void combineData() {
                addToDBNewWord(
                        englishWord.getText().toString().trim(),
                        russianWord.getText().toString().trim(),
                        definition.getText().toString().trim());
            }

            private void addToDBNewWord(String englishWord, String russianWord, String definition) {
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
    private void addTranslationButtonToScreen() { //todo learn definition
        int dp_15 = getDp(15);

        for (int i = 0; i < size; i++) {
            final int randomInt = id[i];

            LinearLayout horizontalLayout = new LinearLayout(context);
            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);

            //params for horizontalLayout
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(0, dp_15, 0, 0);
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

            //set the properties for Russian button
            textViewRus.setText(rusTranslArr[randomInt]);
            textViewRus.setTextSize(15);
            textViewRus.setBackgroundResource(R.drawable.person_together);
            textViewRus.setPadding(getDp(5), dp_15, getDp(5), dp_15);
            textViewRus.setLayoutParams(rusButtonParams);
            textViewRus.setGravity(Gravity.CENTER);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textViewRus.setTextColor(context.getColor(R.color.ios_black));
                textViewEng.setTextColor(context.getColor(R.color.ios_black));
            }


            // create listener ( + long Listener)
            View.OnClickListener onClickListener = v -> {
                if (!studyActivity.isReplaced) {
                    textViewRus.setVisibility(View.VISIBLE);
                } else {
                    textViewEng.setVisibility(View.VISIBLE);
                }
                speak(textViewEng.getText().toString());
                outLog("OnClickListener - first (speak) step");
            };

            View.OnLongClickListener onLongClickListener = v -> {
                outLog("OnClickListener - second (delete) step");
                setUpEditingMenu(randomInt);
                return false;
            };

            // setOnClickListener
            textViewEng.setOnClickListener(onClickListener);
            textViewRus.setOnClickListener(onClickListener);
            textViewEng.setOnLongClickListener(onLongClickListener);
            textViewRus.setOnLongClickListener(onLongClickListener);

            //add button to the lists
            buttonListOfEnglish.add(textViewEng);
            buttonListOfRus.add(textViewRus);

            if (!studyActivity.isReplaced) {
                engButtonParams.setMargins(0, 0, 0, 0);
                rusButtonParams.setMargins(getDp(10), 0, 0, 0);
                textViewEng.setVisibility(View.VISIBLE);
                textViewRus.setVisibility(View.GONE);

                horizontalLayout.addView(textViewEng);
                horizontalLayout.addView(textViewRus);
            } else {
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

    private void setUpEditingMenu(int topicId) {
        appearMenu();
        WordModel wordModel = wordModelDao.getWordModelByName(englishTranslArr[topicId],
                rusTranslArr[topicId], subTopic, topic);
        String editingText = "Enter info for editing word";
        menu_title.setText(editingText);
        englishWord.setText(wordModel.englishWord);
        russianWord.setText(wordModel.rusWord);
        definition.setText(wordModel.definition);

        but_yes.setText("Edit");
        but_yes.setOnClickListener(v -> {

            wordModel.englishWord = englishWord.getText().toString().trim();
            wordModel.rusWord = russianWord.getText().toString().trim();
            wordModel.definition = definition.getText().toString().trim();
            wordModelDao.update(wordModel);
            closeMenu();
            studyActivity.setDateToActivity();
        });

        but_no.setText("Delete");
        but_no.setOnClickListener(v -> showDeleteDialog(topicId));
    }

    private void appearMenu() {
        relativeLayout_proz.setAlpha(0f);

        TranslateAnimation animation = new TranslateAnimation(0, 0, 2000, 0);
        animation.setDuration(1000);
        animation.setFillAfter(true);

        bottom_lay.setVisibility(View.VISIBLE);
        relativeLayout_proz.setVisibility(View.VISIBLE);
        relativeLayout_proz.animate().alpha(1f).setDuration(1000);
        bottom_lay.startAnimation(animation);
    }

    public void closeMenu(View v) {
        closeMenu();
    }

    private void closeMenu() {
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, 2000);
        animation.setDuration(1000);
        animation.setFillAfter(true);

        new Handler().postDelayed(() -> {
            bottom_lay.setVisibility(View.GONE);
            relativeLayout_proz.setVisibility(View.GONE);

            //empty editTexts
            englishWord.setText("");
            russianWord.setText("");
            definition.setText("");
        }, 1000);

        relativeLayout_proz.animate().alpha(0f).setDuration(1000);
        bottom_lay.startAnimation(animation);
    }

    public void showDeleteDialog(int i) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Do you want to delete topic?");
        alert.setPositiveButton("Yes", (dialog, which) -> {
            WordModel wordModel = wordModelDao.getWordModelByName(englishTranslArr[i], rusTranslArr[i], subTopic, topic);
            outLog("deleted - " + wordModel.englishWord);
            String deletedWord = wordModel.englishWord;
            wordModelDao.delete(wordModel);
            Toast.makeText(context, "Deleted: " + deletedWord, Toast.LENGTH_SHORT).show();
            closeMenu();
            studyActivity.setDateToActivity();
        });
        alert.setNegativeButton("No", ((dialog, which) -> closeMenu()));
        alert.show();
    }

    private void text_visible_gone() {
        if (!studyActivity.isReplaced) {
            for (TextView t : buttonListOfRus) {
                t.setVisibility(View.GONE);
            }
        } else {
            for (TextView t : buttonListOfEnglish) {
                t.setVisibility(View.GONE);
            }
        }
    }

    private void speak(String text) {
        outLog("TTS - is speaking");
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    public void onPause() {
        super.onPause();
        text_visible_gone();
    }
}