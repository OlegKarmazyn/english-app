package solid.icon.english.user_line.studying.fragments;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import solid.icon.english.R;
import solid.icon.english.architecture.firebase.database.WordFB;
import solid.icon.english.architecture.firebase.database.operations.FirebaseOperation;
import solid.icon.english.architecture.gpt.GPT;
import solid.icon.english.architecture.local_data.PreferencesOperations;
import solid.icon.english.architecture.parents.UserFragmentActivity;
import solid.icon.english.architecture.room.App;
import solid.icon.english.architecture.room.WordModel;
import solid.icon.english.architecture.room.WordModelDao;
import solid.icon.english.dialogs.TitleDialog;
import solid.icon.english.user_line.studying.StudyActivity;

public class FragmentLearn extends UserFragmentActivity {

    FirebaseOperation firebaseOperation = new FirebaseOperation();
    boolean isSubTest;

    public FragmentLearn(List<WordModel> wordModelList, String topic, String subTopic, boolean isSubTest, StudyActivity studyActivity) {
        super(wordModelList, topic, subTopic, studyActivity);
        this.isSubTest = isSubTest;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
    private ImageView callGptDefinition;


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
        callGptDefinition = context.findViewById(R.id.callGptDefinition);

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

    //NOTE: There is ADDING BUTTON
    private void createAddButton() {
        if (!isSubTest) {
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
            button.setText(R.string.adding_button);
            button.setTextSize(15);
            button.setBackgroundResource(R.drawable.person_together);
            button.setPadding(getDp(5), dp_15, getDp(5), dp_15);
            button.setGravity(Gravity.CENTER);

            //listener
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    setUpAddingMenu();
                    appearMenu();
                }

                private void setUpAddingMenu() {
                    menu_title.setText(R.string.enter_info_to_create_new_word);
                    but_no.setText(R.string.cancel);
                    but_yes.setText(R.string.create);
                    but_no.setOnClickListener(v1 -> closeMenu());
                    callGptDefinition.setOnClickListener(v1 -> proposeDefinition());
                    but_yes.setOnClickListener(v1 -> combineData());
                }

                private void combineData() {
                    addToDBNewWord(
                            englishWord.getText().toString().trim(),
                            russianWord.getText().toString().trim(),
                            definition.getText().toString().trim());
                }

                private void addToDBNewWord(String englishWord, String russianWord, String definition) {
                    if (englishWord.isEmpty()) {
                        Toasty.error(context, R.string.engl_field_is_empty).show();
                        return;
                    }
                    if (russianWord.isEmpty()) {
                        Toasty.error(context, R.string.translation_field_is_empty).show();
                        return;
                    }

                    WordModel word = wordModelDao.getWordModelByName(englishWord, subTopic, topic);
                    if (word == null) { //if doesn't exist
                        WordModel wordModel = new WordModel();
                        wordModel.englishWord = englishWord;
                        wordModel.rusWord = russianWord;
                        wordModel.definition = definition;
                        wordModel.topicName = topic;
                        wordModel.subTopicName = subTopic;
                        wordModelDao.upsert(wordModel);
                        wordModelList.add(wordModel);
                        moveDataFB(englishWord, russianWord, definition);
                    } else {
                        Toasty.error(context, "\"" + englishWord + "\"" + " already exists").show();
                    }
                    closeMenu();
                    studyActivity.setDateToActivity();
                }
            });

            //adding layout to the layout verticalLinearLayout
            horizontalLayout.addView(button);
            verticalLinearLayout.addView(horizontalLayout);
        }
    }

    private void moveDataFB(String englishWord, String russianWord, String definition) {
        if (!doesInternetConnectionExist())
            return;
        WordFB wordFB = new WordFB(englishWord, russianWord, definition);
        firebaseOperation.moveWord(topic, subTopic, wordFB);
    }

    private void updateDataFB(String previousName, String englishWord, String russianWord, String definition) {
        if (!doesInternetConnectionExist())
            return;
        WordFB wordFB = new WordFB(englishWord, russianWord, definition);
        firebaseOperation.updateWord(previousName, topic, subTopic, wordFB);
    }

    private void deleteDataFB(String englishWord) {
        if (!doesInternetConnectionExist())
            return;
        firebaseOperation.deleteWord(topic, subTopic, englishWord);
    }

    //NOTE: Translation buttons
    private void addTranslationButtonToScreen() {
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

            textViewRus.setTextColor(context.getColor(R.color.ios_black));
            textViewEng.setTextColor(context.getColor(R.color.ios_black));


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
        WordModel wordModel = wordModelDao.getWordModelByName(englishTranslArr[topicId], subTopic, topic);
        if (wordModel == null)
            return;
        String editingText = context.getString(R.string.Enter_info_to_edit);
        menu_title.setText(editingText);
        englishWord.setText(wordModel.englishWord);
        russianWord.setText(wordModel.rusWord);
        definition.setText(wordModel.definition);
        String previousName = wordModel.englishWord;

        but_yes.setText(R.string.edit);
        but_yes.setOnClickListener(v -> {
            wordModel.englishWord = englishWord.getText().toString().trim();
            wordModel.rusWord = russianWord.getText().toString().trim();
            wordModel.definition = definition.getText().toString().trim();
            wordModelDao.upsert(wordModel);
            closeMenu();
            updateDataFB(previousName, wordModel.englishWord, wordModel.rusWord, wordModel.definition);
            studyActivity.setDateToActivity();
        });

        callGptDefinition.setOnClickListener(v1 -> proposeDefinition());

        but_no.setText(R.string.delete);
        but_no.setOnClickListener(v -> showDeleteDialog(topicId));
        appearMenu();
    }

    private void appearMenu() {
        int duration = 700;
        relativeLayout_proz.setAlpha(0f);

        TranslateAnimation animation = new TranslateAnimation(0, 0, 2000, 0);
        animation.setDuration(duration);
        animation.setFillAfter(true);

        bottom_lay.setVisibility(View.VISIBLE);
        relativeLayout_proz.setVisibility(View.VISIBLE);
        relativeLayout_proz.animate().alpha(1f).setDuration(duration);
        bottom_lay.startAnimation(animation);
    }

    public void proposeDefinition() {
        String eng = englishWord.getText().toString().trim();
        String trl = russianWord.getText().toString().trim();
        if (new PreferencesOperations().getEmail() == null) {
            Toasty.warning(context, getString(R.string.use_after_registration)).show();
            return;
        }
        if (new PreferencesOperations().getGptCalls() == 0) {
            Toasty.warning(context, getString(R.string.your_free_daily_limit)).show();
            return;
        }

        if (!doesInternetConnectionExist())
            return;

        if (!eng.isEmpty() && !trl.isEmpty()) {
            Toasty.info(context, getString(R.string.loading)).show();
            new GPT().giveDefinition(eng, trl, response -> {
                new Thread(() -> context.runOnUiThread(() -> {
                    definition.setText(response);
                })).start();
            });
        } else {
            Toasty.error(context, getString(R.string.empty_field)).show();
        }
    }

    private void closeMenu(View v) {
        closeMenu();
    }

    private void closeMenu() {
        hideSoftKeyboard(englishWord);
        int duration = 600;
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, 2000);
        animation.setDuration(duration);
        animation.setFillAfter(true);

        new Handler().postDelayed(() -> {
            bottom_lay.setVisibility(View.GONE);
            relativeLayout_proz.setVisibility(View.GONE);

            //empty editTexts
            englishWord.setText("");
            russianWord.setText("");
            definition.setText("");
        }, duration);

        relativeLayout_proz.animate().alpha(0f).setDuration(duration);
        bottom_lay.startAnimation(animation);
    }

    public void showDeleteDialog(int i) {
        TitleDialog dialog = new TitleDialog(context);
        dialog.setTitle(R.string.delete_word);
        dialog.setPositiveButton("Yes", v -> {
            WordModel wordModel = wordModelDao.getWordModelByName(englishTranslArr[i], subTopic, topic);
            outLog("deleted - " + wordModel.englishWord);
            String deletedWord = wordModel.englishWord;
            deleteDataFB(deletedWord);
            wordModelDao.delete(wordModel);
            Toast.makeText(context, "Deleted: " + deletedWord, Toast.LENGTH_SHORT).show();
            closeMenu();
            studyActivity.setDateToActivity();
        });
        dialog.setNegativeButton("No", (v -> closeMenu()));
        dialog.show();
    }

    private void text_visible_gone() {
        if (!studyActivity.isReplaced)
            for (TextView t : buttonListOfRus)
                t.setVisibility(View.GONE);
        else
            for (TextView t : buttonListOfEnglish)
                t.setVisibility(View.GONE);
    }

    @Override
    public void onPause() {
        super.onPause();
        text_visible_gone();
        closeMenu();
    }
}