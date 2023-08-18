package solid.icon.english.architecture.parents;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import es.dmoral.toasty.Toasty;
import solid.icon.english.R;
import solid.icon.english.architecture.Dpi;
import solid.icon.english.architecture.room.App;
import solid.icon.english.architecture.room.TopicModel;
import solid.icon.english.architecture.room.TopicModelDao;
import solid.icon.english.architecture.room.WordModel;
import solid.icon.english.user_line.studying.StudyActivity;
import solid.icon.english.user_line.studying.fragments.FragmentDefinition;

public abstract class UserFragmentActivity extends Fragment {

    protected List<WordModel> wordModelList;
    protected TopicModel topicModel;
    protected String topic, subTopic;
    protected String[] englishTranslArr, rusTranslArr, definitionArr;
    protected StudyActivity studyActivity;
    protected int size = 0;
    protected float metrics = Dpi.metrics,
            pitch = 0, //tone
            speechRate = 0; //speed

    protected int[] id;

    protected TextToSpeech mTTS;

    protected int[] counter_flip;

    protected FragmentActivity context;

    protected String TAG = this.getClass().getSimpleName();

    protected UserFragmentActivity(List<WordModel> wordModelList, String topic, String subTopic, StudyActivity studyActivity) {
        this.wordModelList = wordModelList;
        this.topic = topic;
        this.subTopic = subTopic;
        this.studyActivity = studyActivity;
        outLog(studyActivity.toString());

        deleteEmptyDefinition();
        fillMainArrays();
        topicModel = getTopicsModel();
    }

    protected void setVisibility(View view, boolean isVisible) {
        if (isVisible) {
            if (view.getVisibility() == View.VISIBLE)
                return;
            view.setAlpha(0);
            view.setVisibility(View.VISIBLE);
            view.animate().alpha(1).setListener(null);
        } else {
            if (view.getVisibility() == View.GONE)
                return;
            view.animate().alpha(0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(View.GONE);
                    view.setAlpha(1);
                }
            });
        }
    }

    protected void hideSoftKeyboard(EditText input) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
    }

    protected void showSoftKeyboard(EditText editText) {
        editText.requestFocus(); // set focus to the edit text
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT); // show the keyboard
        }
    }


    /**
     * @return value does internet exists or nor: true - exists; false - not.
     */
    public boolean doesInternetConnectionExist() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        if (!isConnected)
            Toasty.warning(context, R.string.no_internet_connection).show();
        return isConnected;
    }

    private void deleteEmptyDefinition() {
        if (FragmentDefinition.class.getSimpleName().equals(TAG)) {
            List<WordModel> deletingList = new ArrayList<>();
            if (wordModelList != null)
                for (WordModel wordModel : wordModelList) {
                    if (wordModel.definition == null || wordModel.definition.isEmpty()) {
                        deletingList.add(wordModel);
                    }
                }
            for (WordModel wordModel : deletingList) {
                outLog("DELETING - " + wordModel.englishWord + ": " + wordModel.rusWord);
                wordModelList.remove(wordModel);
            }
        }
    }

    private TopicModel getTopicsModel() {
        TopicModelDao topicModelDao = App.getInstance().getDatabase().topicModelDao();
        return topicModelDao.getByTopicsName(topic);
    }

    protected void fillMainArrays() {
        if (wordModelList != null) {
            size = wordModelList.size();
            outLog("size = " + size);
        } else {
            return;
        }
        if (size != 0) {
            englishTranslArr = new String[size];
            rusTranslArr = new String[size];
            definitionArr = new String[size];
            int i = 0;
            for (WordModel w : wordModelList) {
                englishTranslArr[i] = w.englishWord;
                rusTranslArr[i] = w.rusWord;
                definitionArr[i] = w.definition;
                outLog("englishTranslArr = " + englishTranslArr[i]);
                outLog("rusTranslArr = " + rusTranslArr[i]);
                outLog("definitionArr = " + definitionArr[i]);
                i++;
            }
        }
        fillArrays();
        shuffleArray(id);
    }

    //arrays should not have positive values
    protected void fillArrays() {
        id = new int[size];
        counter_flip = new int[size];
        final int NOT_USE_VALUE = -1;
        if (size != 0) {
            Arrays.fill(counter_flip, NOT_USE_VALUE);
        }
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    protected void shuffleArray(int[] ar) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    protected void speak(String text) {
        getSpeedAndPitch(); //todo(redone calling this (at once - and after do test))
        outLog("TTS - is speaking");
        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speechRate);
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void getSpeedAndPitch() {
        if (pitch == 0) {
            pitch = PreferenceManager.getDefaultSharedPreferences(context).getFloat("pitch", 0.7f);
            speechRate = PreferenceManager.getDefaultSharedPreferences(context).getFloat("speechRate", 0.7f);
        }
    }

    protected int getDp(int px) {
        return (int) (px * metrics);
    }

    protected void outLog(String text) {
        Log.d(TAG, text);
    }

    protected void outLog(int text) {
        Log.d(TAG, String.valueOf(text));
    }

    protected void setVisibleAllItems() {
        if (studyActivity.menu == null) return;
        studyActivity.menu.getItem(0).setVisible(true);
        studyActivity.menu.getItem(1).setVisible(true);
    }

    protected void setNotVisibleItem(int i) {
        studyActivity.menu.getItem(i).setVisible(false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}

