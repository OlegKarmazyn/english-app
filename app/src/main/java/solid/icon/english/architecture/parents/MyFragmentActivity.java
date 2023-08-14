package solid.icon.english.architecture.parents;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.PreferenceManager;

import java.io.Serializable;

import solid.icon.english.architecture.res.Res_array;

public abstract class MyFragmentActivity extends Fragment {

    protected Serializable what_level;
    protected int num_of_topic;
    protected int[][] main_1 = new int[][]{};
    protected int[][] main_2 = new int[][]{};
    protected float pitch = 0, //tone
            speechRate = 0; //speed
    protected TextToSpeech mTTS;

    protected FragmentActivity context;

    public MyFragmentActivity() {
        //while empty
    }

    private void getSpeedAndPitch() {
        if (pitch == 0) {
            pitch = PreferenceManager.getDefaultSharedPreferences(context).getFloat("pitch", 0.7f);
            speechRate = PreferenceManager.getDefaultSharedPreferences(context).getFloat("speechRate", 0.7f);
        }
    }

    protected void speak(String text) {
        getSpeedAndPitch();
        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speechRate);
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
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

    protected void defineArrays() {
        if (what_level == ActivityGlobal.LessonsName.A2) {
            main_1 = new Res_array().main_1_learn_a2.clone();
            main_2 = new Res_array().main_2_learn_a2.clone();
        } else if (what_level == ActivityGlobal.LessonsName.B1) {
            main_1 = new Res_array().main_1_learn_b1.clone();
            main_2 = new Res_array().main_2_learn_b1.clone();
        } else if (what_level == ActivityGlobal.LessonsName.B2) {
            main_1 = new Res_array().main_1_learn_b2.clone();
            main_2 = new Res_array().main_2_learn_b2.clone();
        }
    }
}
