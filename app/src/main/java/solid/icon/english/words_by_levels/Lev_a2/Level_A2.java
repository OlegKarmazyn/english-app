package solid.icon.english.words_by_levels.Lev_a2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

import solid.icon.english.R;
import solid.icon.english.words_by_levels.Definition;
import solid.icon.english.words_by_levels.TestOrLearn;

public class Level_A2 extends AppCompatActivity{

    private TextToSpeech mTTS;
    //private EditText mEdirText;
    //private SeekBar mSeekBarPitch;
    //private SeekBar mSeekBarSpeed;
    //private Button mButtonSpeak;

    public int[] draw0_1 = new int[]{
            R.string.a2_les_0_01, R.string.a2_les_0_02, R.string.a2_les_0_03, R.string.a2_les_0_04, R.string.a2_les_0_05, R.string.a2_les_0_06, R.string.a2_les_0_07, R.string.a2_les_0_08, R.string.a2_les_0_09,
            R.string.a2_les_0_10, R.string.a2_les_0_11, R.string.a2_les_0_12, R.string.a2_les_0_13, R.string.a2_les_0_14, R.string.a2_les_0_15};
    public int[] draw1_1 = new int[]{
            R.string.a2_les_1_01, R.string.a2_les_1_02, R.string.a2_les_1_03, R.string.a2_les_1_04, R.string.a2_les_1_05, R.string.a2_les_1_06, R.string.a2_les_1_07, R.string.a2_les_1_08, R.string.a2_les_1_09,
            R.string.a2_les_1_10, R.string.a2_les_1_11, R.string.a2_les_1_12, R.string.a2_les_1_13, R.string.a2_les_1_14, R.string.a2_les_1_15};
    public int[] draw2_1 = new int[]{
            R.string.a2_les_2_01, R.string.a2_les_2_02, R.string.a2_les_2_03, R.string.a2_les_2_04, R.string.a2_les_2_05, R.string.a2_les_2_06, R.string.a2_les_2_07, R.string.a2_les_2_08, R.string.a2_les_2_09,
            R.string.a2_les_2_10, R.string.a2_les_2_11, R.string.a2_les_2_12, R.string.a2_les_2_13, R.string.a2_les_2_14, R.string.a2_les_2_15};
    public int[] draw3_1 = new int[]{
            R.string.a2_les_3_01, R.string.a2_les_3_02, R.string.a2_les_3_03, R.string.a2_les_3_04, R.string.a2_les_3_05, R.string.a2_les_3_06, R.string.a2_les_3_07, R.string.a2_les_3_08, R.string.a2_les_3_09,
            R.string.a2_les_3_10, R.string.a2_les_3_11, R.string.a2_les_3_12, R.string.a2_les_3_13, R.string.a2_les_3_14, R.string.a2_les_3_15};
    public int[] draw4_1 = new int[]{
            R.string.a2_les_4_01, R.string.a2_les_4_02, R.string.a2_les_4_03, R.string.a2_les_4_04, R.string.a2_les_4_05, R.string.a2_les_4_06, R.string.a2_les_4_07, R.string.a2_les_4_08, R.string.a2_les_4_09,
            R.string.a2_les_4_10, R.string.a2_les_4_11, R.string.a2_les_4_12, R.string.a2_les_4_13, R.string.a2_les_4_14, R.string.a2_les_4_15};
    public int[] draw5_1 = new int[]{
            R.string.a2_les_5_01, R.string.a2_les_5_02, R.string.a2_les_5_03, R.string.a2_les_5_04, R.string.a2_les_5_05, R.string.a2_les_5_06, R.string.a2_les_5_07, R.string.a2_les_5_08, R.string.a2_les_5_09,
            R.string.a2_les_5_10, R.string.a2_les_5_11, R.string.a2_les_5_12, R.string.a2_les_5_13, R.string.a2_les_5_14, R.string.a2_les_5_15};
    public int[] draw6_1 = new int[]{
            R.string.a2_les_6_01, R.string.a2_les_6_02, R.string.a2_les_6_03, R.string.a2_les_6_04, R.string.a2_les_6_05, R.string.a2_les_6_06, R.string.a2_les_6_07, R.string.a2_les_6_08, R.string.a2_les_6_09,
            R.string.a2_les_6_10, R.string.a2_les_6_11, R.string.a2_les_6_12, R.string.a2_les_6_13, R.string.a2_les_6_14, R.string.a2_les_6_15};
    public int[] draw7_1 = new int[]{
            R.string.a2_les_7_01, R.string.a2_les_7_02, R.string.a2_les_7_03, R.string.a2_les_7_04, R.string.a2_les_7_05, R.string.a2_les_7_06, R.string.a2_les_7_07, R.string.a2_les_7_08, R.string.a2_les_7_09,
            R.string.a2_les_7_10, R.string.a2_les_7_11, R.string.a2_les_7_12, R.string.a2_les_7_13, R.string.a2_les_7_14, R.string.a2_les_7_15};
    public int[] draw8_1 = new int[]{
            R.string.a2_les_8_01, R.string.a2_les_8_02, R.string.a2_les_8_03, R.string.a2_les_8_04, R.string.a2_les_8_05, R.string.a2_les_8_06, R.string.a2_les_8_07, R.string.a2_les_8_08, R.string.a2_les_8_09,
            R.string.a2_les_8_10, R.string.a2_les_8_11, R.string.a2_les_8_12, R.string.a2_les_8_13, R.string.a2_les_8_14, R.string.a2_les_8_15};
    public int[] draw9_1 = new int[]{
            R.string.a2_les_9_01, R.string.a2_les_9_02, R.string.a2_les_9_03, R.string.a2_les_9_04, R.string.a2_les_9_05, R.string.a2_les_9_06, R.string.a2_les_9_07, R.string.a2_les_9_08, R.string.a2_les_9_09,
            R.string.a2_les_9_10, R.string.a2_les_9_11, R.string.a2_les_9_12, R.string.a2_les_9_13, R.string.a2_les_9_14, R.string.a2_les_9_15};
    public int[] draw10_1 = new int[]{
            R.string.a2_les_10_01, R.string.a2_les_10_02, R.string.a2_les_10_03, R.string.a2_les_10_04, R.string.a2_les_10_05, R.string.a2_les_10_06, R.string.a2_les_10_07, R.string.a2_les_10_08, R.string.a2_les_10_09,
            R.string.a2_les_10_10, R.string.a2_les_10_11, R.string.a2_les_10_12, R.string.a2_les_10_13, R.string.a2_les_10_14, R.string.a2_les_10_15};
    public int[] draw11_1 = new int[]{
            R.string.a2_les_11_01, R.string.a2_les_11_02, R.string.a2_les_11_03, R.string.a2_les_11_04, R.string.a2_les_11_05, R.string.a2_les_11_06, R.string.a2_les_11_07, R.string.a2_les_11_08, R.string.a2_les_11_09,
            R.string.a2_les_11_10, R.string.a2_les_11_11, R.string.a2_les_11_12, R.string.a2_les_11_13, R.string.a2_les_11_14, R.string.a2_les_11_15};
    public int[] draw12_1 = new int[]{
            R.string.a2_les_12_01, R.string.a2_les_12_02, R.string.a2_les_12_03, R.string.a2_les_12_04, R.string.a2_les_12_05, R.string.a2_les_12_06, R.string.a2_les_12_07, R.string.a2_les_12_08, R.string.a2_les_12_09,
            R.string.a2_les_12_10, R.string.a2_les_12_11, R.string.a2_les_12_12, R.string.a2_les_12_13, R.string.a2_les_12_14, R.string.a2_les_12_15};
    public int[] draw13_1 = new int[]{
            R.string.a2_les_13_01, R.string.a2_les_13_02, R.string.a2_les_13_03, R.string.a2_les_13_04, R.string.a2_les_13_05, R.string.a2_les_13_06, R.string.a2_les_13_07, R.string.a2_les_13_08, R.string.a2_les_13_09,
            R.string.a2_les_13_10, R.string.a2_les_13_11, R.string.a2_les_13_12, R.string.a2_les_13_13, R.string.a2_les_13_14, R.string.a2_les_13_15};
    public int[] draw14_1 = new int[]{
            R.string.a2_les_14_01, R.string.a2_les_14_02, R.string.a2_les_14_03, R.string.a2_les_14_04, R.string.a2_les_14_05, R.string.a2_les_14_06, R.string.a2_les_14_07, R.string.a2_les_14_08, R.string.a2_les_14_09,
            R.string.a2_les_14_10, R.string.a2_les_14_11, R.string.a2_les_14_12, R.string.a2_les_14_13, R.string.a2_les_14_14, R.string.a2_les_14_15};
    public int[] draw15_1 = new int[]{
            R.string.a2_les_15_01, R.string.a2_les_15_02, R.string.a2_les_15_03, R.string.a2_les_15_04, R.string.a2_les_15_05, R.string.a2_les_15_06, R.string.a2_les_15_07, R.string.a2_les_15_08, R.string.a2_les_15_09,
            R.string.a2_les_15_10, R.string.a2_les_15_11, R.string.a2_les_15_12, R.string.a2_les_15_13, R.string.a2_les_15_14, R.string.a2_les_15_15};
    public int[] draw16_1 = new int[]{
            R.string.a2_les_16_01, R.string.a2_les_16_02, R.string.a2_les_16_03, R.string.a2_les_16_04, R.string.a2_les_16_05, R.string.a2_les_16_06, R.string.a2_les_16_07, R.string.a2_les_16_08, R.string.a2_les_16_09,
            R.string.a2_les_16_10, R.string.a2_les_16_11, R.string.a2_les_16_12, R.string.a2_les_16_13, R.string.a2_les_16_14, R.string.a2_les_16_15};
    public int[] draw17_1 = new int[]{
            R.string.a2_les_17_01, R.string.a2_les_17_02, R.string.a2_les_17_03, R.string.a2_les_17_04, R.string.a2_les_17_05, R.string.a2_les_17_06, R.string.a2_les_17_07, R.string.a2_les_17_08, R.string.a2_les_17_09,
            R.string.a2_les_17_10, R.string.a2_les_17_11, R.string.a2_les_17_12, R.string.a2_les_17_13, R.string.a2_les_17_14, R.string.a2_les_17_15};
    public int[] draw18_1 = new int[]{
            R.string.a2_les_18_01, R.string.a2_les_18_02, R.string.a2_les_18_03, R.string.a2_les_18_04, R.string.a2_les_18_05, R.string.a2_les_18_06, R.string.a2_les_18_07, R.string.a2_les_18_08, R.string.a2_les_18_09,
            R.string.a2_les_18_10, R.string.a2_les_18_11, R.string.a2_les_18_12, R.string.a2_les_18_13, R.string.a2_les_18_14, R.string.a2_les_18_15};
    public int[] draw19_1 = new int[]{
            R.string.a2_les_19_01, R.string.a2_les_19_02, R.string.a2_les_19_03, R.string.a2_les_19_04, R.string.a2_les_19_05, R.string.a2_les_19_06, R.string.a2_les_19_07, R.string.a2_les_19_08, R.string.a2_les_19_09,
            R.string.a2_les_19_10, R.string.a2_les_19_11, R.string.a2_les_19_12, R.string.a2_les_19_13, R.string.a2_les_19_14, R.string.a2_les_19_15};
    public int[] draw20_1 = new int[]{
            R.string.a2_les_20_01, R.string.a2_les_20_02, R.string.a2_les_20_03, R.string.a2_les_20_04, R.string.a2_les_20_05, R.string.a2_les_20_06, R.string.a2_les_20_07, R.string.a2_les_20_08, R.string.a2_les_20_09,
            R.string.a2_les_20_10, R.string.a2_les_20_11, R.string.a2_les_20_12, R.string.a2_les_20_13, R.string.a2_les_20_14, R.string.a2_les_20_15};
    public int[] draw21_1 = new int[]{
            R.string.a2_les_21_01, R.string.a2_les_21_02, R.string.a2_les_21_03, R.string.a2_les_21_04, R.string.a2_les_21_05, R.string.a2_les_21_06, R.string.a2_les_21_07, R.string.a2_les_21_08, R.string.a2_les_21_09,
            R.string.a2_les_21_10, R.string.a2_les_21_11, R.string.a2_les_21_12, R.string.a2_les_21_13, R.string.a2_les_21_14, R.string.a2_les_21_15};
    public int[] draw22_1 = new int[]{
            R.string.a2_les_22_01, R.string.a2_les_22_02, R.string.a2_les_22_03, R.string.a2_les_22_04, R.string.a2_les_22_05, R.string.a2_les_22_06, R.string.a2_les_22_07, R.string.a2_les_22_08, R.string.a2_les_22_09,
            R.string.a2_les_22_10, R.string.a2_les_22_11, R.string.a2_les_22_12, R.string.a2_les_22_13, R.string.a2_les_22_14, R.string.a2_les_22_15};
    public int[] draw23_1 = new int[]{
            R.string.a2_les_23_01, R.string.a2_les_23_02, R.string.a2_les_23_03, R.string.a2_les_23_04, R.string.a2_les_23_05, R.string.a2_les_23_06, R.string.a2_les_23_07, R.string.a2_les_23_08, R.string.a2_les_23_09,
            R.string.a2_les_23_10, R.string.a2_les_23_11, R.string.a2_les_23_12, R.string.a2_les_23_13, R.string.a2_les_23_14, R.string.a2_les_23_15};
    public int[] draw24_1 = new int[]{
            R.string.a2_les_24_01, R.string.a2_les_24_02, R.string.a2_les_24_03, R.string.a2_les_24_04, R.string.a2_les_24_05, R.string.a2_les_24_06, R.string.a2_les_24_07, R.string.a2_les_24_08, R.string.a2_les_24_09,
            R.string.a2_les_24_10, R.string.a2_les_24_11, R.string.a2_les_24_12, R.string.a2_les_24_13, R.string.a2_les_24_14, R.string.a2_les_24_15};
    public int[] draw25_1 = new int[]{
            R.string.a2_les_25_01, R.string.a2_les_25_02, R.string.a2_les_25_03, R.string.a2_les_25_04, R.string.a2_les_25_05, R.string.a2_les_25_06, R.string.a2_les_25_07, R.string.a2_les_25_08, R.string.a2_les_25_09,
            R.string.a2_les_25_10, R.string.a2_les_25_11, R.string.a2_les_25_12, R.string.a2_les_25_13, R.string.a2_les_25_14, R.string.a2_les_25_15};
    public int[] draw26_1 = new int[]{
            R.string.a2_les_26_01, R.string.a2_les_26_02, R.string.a2_les_26_03, R.string.a2_les_26_04, R.string.a2_les_26_05, R.string.a2_les_26_06, R.string.a2_les_26_07, R.string.a2_les_26_08, R.string.a2_les_26_09,
            R.string.a2_les_26_10, R.string.a2_les_26_11, R.string.a2_les_26_12, R.string.a2_les_26_13, R.string.a2_les_26_14, R.string.a2_les_26_15};
    public int[] draw27_1= new int[]{
            R.string.a2_les_27_01, R.string.a2_les_27_02, R.string.a2_les_27_03, R.string.a2_les_27_04, R.string.a2_les_27_05, R.string.a2_les_27_06, R.string.a2_les_27_07, R.string.a2_les_27_08, R.string.a2_les_27_09,
            R.string.a2_les_27_10, R.string.a2_les_27_11, R.string.a2_les_27_12, R.string.a2_les_27_13, R.string.a2_les_27_14, R.string.a2_les_27_15};
    public int[] draw28_1 = new int[]{
            R.string.a2_les_28_01, R.string.a2_les_28_02, R.string.a2_les_28_03, R.string.a2_les_28_04, R.string.a2_les_28_05, R.string.a2_les_28_06, R.string.a2_les_28_07, R.string.a2_les_28_08, R.string.a2_les_28_09,
            R.string.a2_les_28_10, R.string.a2_les_28_11, R.string.a2_les_28_12, R.string.a2_les_28_13, R.string.a2_les_28_14, R.string.a2_les_28_15};
    public int[] draw29_1 = new int[]{
            R.string.a2_les_29_01, R.string.a2_les_29_02, R.string.a2_les_29_03, R.string.a2_les_29_04, R.string.a2_les_29_05, R.string.a2_les_29_06, R.string.a2_les_29_07, R.string.a2_les_29_08, R.string.a2_les_29_09,
            R.string.a2_les_29_10, R.string.a2_les_29_11, R.string.a2_les_29_12, R.string.a2_les_29_13, R.string.a2_les_29_14, R.string.a2_les_29_15};
    public int[] draw30_1 = new int[]{
            R.string.a2_les_30_01, R.string.a2_les_30_02, R.string.a2_les_30_03, R.string.a2_les_30_04, R.string.a2_les_30_05, R.string.a2_les_30_06, R.string.a2_les_30_07, R.string.a2_les_30_08, R.string.a2_les_30_09,
            R.string.a2_les_30_10, R.string.a2_les_30_11, R.string.a2_les_30_12, R.string.a2_les_30_13, R.string.a2_les_30_14, R.string.a2_les_30_15};
    public int[] draw31_1 = new int[]{
            R.string.a2_les_31_01, R.string.a2_les_31_02, R.string.a2_les_31_03, R.string.a2_les_31_04, R.string.a2_les_31_05, R.string.a2_les_31_06, R.string.a2_les_31_07, R.string.a2_les_31_08, R.string.a2_les_31_09,
            R.string.a2_les_31_10, R.string.a2_les_31_11, R.string.a2_les_31_12, R.string.a2_les_31_13, R.string.a2_les_31_14, R.string.a2_les_31_15};
    public int[] draw32_1 = new int[]{
            R.string.a2_les_32_01, R.string.a2_les_32_02, R.string.a2_les_32_03, R.string.a2_les_32_04, R.string.a2_les_32_05, R.string.a2_les_32_06, R.string.a2_les_32_07, R.string.a2_les_32_08, R.string.a2_les_32_09,
            R.string.a2_les_32_10, R.string.a2_les_32_11, R.string.a2_les_32_12, R.string.a2_les_32_13, R.string.a2_les_32_14, R.string.a2_les_32_15};

    public int[] draw0_2 = new int[]{
            R.string.a2_les_0_01_1, R.string.a2_les_0_02_1, R.string.a2_les_0_03_1, R.string.a2_les_0_04_1, R.string.a2_les_0_05_1, R.string.a2_les_0_06_1, R.string.a2_les_0_07_1, R.string.a2_les_0_08_1, R.string.a2_les_0_09_1,
            R.string.a2_les_0_10_1, R.string.a2_les_0_11_1, R.string.a2_les_0_12_1, R.string.a2_les_0_13_1, R.string.a2_les_0_14_1, R.string.a2_les_0_15_1};
    public int[] draw1_2 = new int[]{
            R.string.a2_les_1_01_1, R.string.a2_les_1_02_1, R.string.a2_les_1_03_1, R.string.a2_les_1_04_1, R.string.a2_les_1_05_1, R.string.a2_les_1_06_1, R.string.a2_les_1_07_1, R.string.a2_les_1_08_1, R.string.a2_les_1_09_1,
            R.string.a2_les_1_10_1, R.string.a2_les_1_11_1, R.string.a2_les_1_12_1, R.string.a2_les_1_13_1, R.string.a2_les_1_14_1, R.string.a2_les_1_15_1};
    public int[] draw2_2 = new int[]{
            R.string.a2_les_2_01_1, R.string.a2_les_2_02_1, R.string.a2_les_2_03_1, R.string.a2_les_2_04_1, R.string.a2_les_2_05_1, R.string.a2_les_2_06_1, R.string.a2_les_2_07_1, R.string.a2_les_2_08_1, R.string.a2_les_2_09_1,
            R.string.a2_les_2_10_1, R.string.a2_les_2_11_1, R.string.a2_les_2_12_1, R.string.a2_les_2_13_1, R.string.a2_les_2_14_1, R.string.a2_les_2_15_1};
    public int[] draw3_2 = new int[]{
            R.string.a2_les_3_01_1, R.string.a2_les_3_02_1, R.string.a2_les_3_03_1, R.string.a2_les_3_04_1, R.string.a2_les_3_05_1, R.string.a2_les_3_06_1, R.string.a2_les_3_07_1, R.string.a2_les_3_08_1, R.string.a2_les_3_09_1,
            R.string.a2_les_3_10_1, R.string.a2_les_3_11_1, R.string.a2_les_3_12_1, R.string.a2_les_3_13_1, R.string.a2_les_3_14_1, R.string.a2_les_3_15_1};
    public int[] draw4_2 = new int[]{
            R.string.a2_les_4_01_1, R.string.a2_les_4_02_1, R.string.a2_les_4_03_1, R.string.a2_les_4_04_1, R.string.a2_les_4_05_1, R.string.a2_les_4_06_1, R.string.a2_les_4_07_1, R.string.a2_les_4_08_1, R.string.a2_les_4_09_1,
            R.string.a2_les_4_10_1, R.string.a2_les_4_11_1, R.string.a2_les_4_12_1, R.string.a2_les_4_13_1, R.string.a2_les_4_14_1, R.string.a2_les_4_15_1};
    public int[] draw5_2 = new int[]{
            R.string.a2_les_5_01_1, R.string.a2_les_5_02_1, R.string.a2_les_5_03_1, R.string.a2_les_5_04_1, R.string.a2_les_5_05_1, R.string.a2_les_5_06_1, R.string.a2_les_5_07_1, R.string.a2_les_5_08_1, R.string.a2_les_5_09_1,
            R.string.a2_les_5_10_1, R.string.a2_les_5_11_1, R.string.a2_les_5_12_1, R.string.a2_les_5_13_1, R.string.a2_les_5_14_1, R.string.a2_les_5_15_1};
    public int[] draw6_2 = new int[]{
            R.string.a2_les_6_01_1, R.string.a2_les_6_02_1, R.string.a2_les_6_03_1, R.string.a2_les_6_04_1, R.string.a2_les_6_05_1, R.string.a2_les_6_06_1, R.string.a2_les_6_07_1, R.string.a2_les_6_08_1, R.string.a2_les_6_09_1,
            R.string.a2_les_6_10_1, R.string.a2_les_6_11_1, R.string.a2_les_6_12_1, R.string.a2_les_6_13_1, R.string.a2_les_6_14_1, R.string.a2_les_6_15_1};
    public int[] draw7_2 = new int[]{
            R.string.a2_les_7_01_1, R.string.a2_les_7_02_1, R.string.a2_les_7_03_1, R.string.a2_les_7_04_1, R.string.a2_les_7_05_1, R.string.a2_les_7_06_1, R.string.a2_les_7_07_1, R.string.a2_les_7_08_1, R.string.a2_les_7_09_1,
            R.string.a2_les_7_10_1, R.string.a2_les_7_11_1, R.string.a2_les_7_12_1, R.string.a2_les_7_13_1, R.string.a2_les_7_14_1, R.string.a2_les_7_15_1};
    public int[] draw8_2 = new int[]{
            R.string.a2_les_8_01_1, R.string.a2_les_8_02_1, R.string.a2_les_8_03_1, R.string.a2_les_8_04_1, R.string.a2_les_8_05_1, R.string.a2_les_8_06_1, R.string.a2_les_8_07_1, R.string.a2_les_8_08_1, R.string.a2_les_8_09_1,
            R.string.a2_les_8_10_1, R.string.a2_les_8_11_1, R.string.a2_les_8_12_1, R.string.a2_les_8_13_1, R.string.a2_les_8_14_1, R.string.a2_les_8_15_1};
    public int[] draw9_2 = new int[]{
            R.string.a2_les_9_01_1, R.string.a2_les_9_02_1, R.string.a2_les_9_03_1, R.string.a2_les_9_04_1, R.string.a2_les_9_05_1, R.string.a2_les_9_06_1, R.string.a2_les_9_07_1, R.string.a2_les_9_08_1, R.string.a2_les_9_09_1,
            R.string.a2_les_9_10_1, R.string.a2_les_9_11_1, R.string.a2_les_9_12_1, R.string.a2_les_9_13_1, R.string.a2_les_9_14_1, R.string.a2_les_9_15_1};
    public int[] draw10_2 = new int[]{
            R.string.a2_les_10_01_1, R.string.a2_les_10_02_1, R.string.a2_les_10_03_1, R.string.a2_les_10_04_1, R.string.a2_les_10_05_1, R.string.a2_les_10_06_1, R.string.a2_les_10_07_1, R.string.a2_les_10_08_1, R.string.a2_les_10_09_1,
            R.string.a2_les_10_10_1, R.string.a2_les_10_11_1, R.string.a2_les_10_12_1, R.string.a2_les_10_13_1, R.string.a2_les_10_14_1, R.string.a2_les_10_15_1};
    public int[] draw11_2 = new int[]{
            R.string.a2_les_11_01_1, R.string.a2_les_11_02_1, R.string.a2_les_11_03_1, R.string.a2_les_11_04_1, R.string.a2_les_11_05_1, R.string.a2_les_11_06_1, R.string.a2_les_11_07_1, R.string.a2_les_11_08_1, R.string.a2_les_11_09_1,
            R.string.a2_les_11_10_1, R.string.a2_les_11_11_1, R.string.a2_les_11_12_1, R.string.a2_les_11_13_1, R.string.a2_les_11_14_1, R.string.a2_les_11_15_1};
    public int[] draw12_2 = new int[]{
            R.string.a2_les_12_01_1, R.string.a2_les_12_02_1, R.string.a2_les_12_03_1, R.string.a2_les_12_04_1, R.string.a2_les_12_05_1, R.string.a2_les_12_06_1, R.string.a2_les_12_07_1, R.string.a2_les_12_08_1, R.string.a2_les_12_09_1,
            R.string.a2_les_12_10_1, R.string.a2_les_12_11_1, R.string.a2_les_12_12_1, R.string.a2_les_12_13_1, R.string.a2_les_12_14_1, R.string.a2_les_12_15_1};
    public int[] draw13_2 = new int[]{
            R.string.a2_les_13_01_1, R.string.a2_les_13_02_1, R.string.a2_les_13_03_1, R.string.a2_les_13_04_1, R.string.a2_les_13_05_1, R.string.a2_les_13_06_1, R.string.a2_les_13_07_1, R.string.a2_les_13_08_1, R.string.a2_les_13_09_1,
            R.string.a2_les_13_10_1, R.string.a2_les_13_11_1, R.string.a2_les_13_12_1, R.string.a2_les_13_13_1, R.string.a2_les_13_14_1, R.string.a2_les_13_15_1};
    public int[] draw14_2 = new int[]{
            R.string.a2_les_14_01_1, R.string.a2_les_14_02_1, R.string.a2_les_14_03_1, R.string.a2_les_14_04_1, R.string.a2_les_14_05_1, R.string.a2_les_14_06_1, R.string.a2_les_14_07_1, R.string.a2_les_14_08_1, R.string.a2_les_14_09_1,
            R.string.a2_les_14_10_1, R.string.a2_les_14_11_1, R.string.a2_les_14_12_1, R.string.a2_les_14_13_1, R.string.a2_les_14_14_1, R.string.a2_les_14_15_1};
    public int[] draw15_2 = new int[]{
            R.string.a2_les_15_01_1, R.string.a2_les_15_02_1, R.string.a2_les_15_03_1, R.string.a2_les_15_04_1, R.string.a2_les_15_05_1, R.string.a2_les_15_06_1, R.string.a2_les_15_07_1, R.string.a2_les_15_08_1, R.string.a2_les_15_09_1,
            R.string.a2_les_15_10_1, R.string.a2_les_15_11_1, R.string.a2_les_15_12_1, R.string.a2_les_15_13_1, R.string.a2_les_15_14_1, R.string.a2_les_15_15_1};
    public int[] draw16_2 = new int[]{
            R.string.a2_les_16_01_1, R.string.a2_les_16_02_1, R.string.a2_les_16_03_1, R.string.a2_les_16_04_1, R.string.a2_les_16_05_1, R.string.a2_les_16_06_1, R.string.a2_les_16_07_1, R.string.a2_les_16_08_1, R.string.a2_les_16_09_1,
            R.string.a2_les_16_10_1, R.string.a2_les_16_11_1, R.string.a2_les_16_12_1, R.string.a2_les_16_13_1, R.string.a2_les_16_14_1, R.string.a2_les_16_15_1};
    public int[] draw17_2 = new int[]{
            R.string.a2_les_17_01_1, R.string.a2_les_17_02_1, R.string.a2_les_17_03_1, R.string.a2_les_17_04_1, R.string.a2_les_17_05_1, R.string.a2_les_17_06_1, R.string.a2_les_17_07_1, R.string.a2_les_17_08_1, R.string.a2_les_17_09_1,
            R.string.a2_les_17_10_1, R.string.a2_les_17_11_1, R.string.a2_les_17_12_1, R.string.a2_les_17_13_1, R.string.a2_les_17_14_1, R.string.a2_les_17_15_1};
    public int[] draw18_2 = new int[]{
            R.string.a2_les_18_01_1, R.string.a2_les_18_02_1, R.string.a2_les_18_03_1, R.string.a2_les_18_04_1, R.string.a2_les_18_05_1, R.string.a2_les_18_06_1, R.string.a2_les_18_07_1, R.string.a2_les_18_08_1, R.string.a2_les_18_09_1,
            R.string.a2_les_18_10_1, R.string.a2_les_18_11_1, R.string.a2_les_18_12_1, R.string.a2_les_18_13_1, R.string.a2_les_18_14_1, R.string.a2_les_18_15_1};
    public int[] draw19_2 = new int[]{
            R.string.a2_les_19_01_1, R.string.a2_les_19_02_1, R.string.a2_les_19_03_1, R.string.a2_les_19_04_1, R.string.a2_les_19_05_1, R.string.a2_les_19_06_1, R.string.a2_les_19_07_1, R.string.a2_les_19_08_1, R.string.a2_les_19_09_1,
            R.string.a2_les_19_10_1, R.string.a2_les_19_11_1, R.string.a2_les_19_12_1, R.string.a2_les_19_13_1, R.string.a2_les_19_14_1, R.string.a2_les_19_15_1};
    public int[] draw20_2 = new int[]{
            R.string.a2_les_20_01_1, R.string.a2_les_20_02_1, R.string.a2_les_20_03_1, R.string.a2_les_20_04_1, R.string.a2_les_20_05_1, R.string.a2_les_20_06_1, R.string.a2_les_20_07_1, R.string.a2_les_20_08_1, R.string.a2_les_20_09_1,
            R.string.a2_les_20_10_1, R.string.a2_les_20_11_1, R.string.a2_les_20_12_1, R.string.a2_les_20_13_1, R.string.a2_les_20_14_1, R.string.a2_les_20_15_1};
    public int[] draw21_2 = new int[]{
            R.string.a2_les_21_01_1, R.string.a2_les_21_02_1, R.string.a2_les_21_03_1, R.string.a2_les_21_04_1, R.string.a2_les_21_05_1, R.string.a2_les_21_06_1, R.string.a2_les_21_07_1, R.string.a2_les_21_08_1, R.string.a2_les_21_09_1,
            R.string.a2_les_21_10_1, R.string.a2_les_21_11_1, R.string.a2_les_21_12_1, R.string.a2_les_21_13_1, R.string.a2_les_21_14_1, R.string.a2_les_21_15_1};
    public int[] draw22_2 = new int[]{
            R.string.a2_les_22_01_1, R.string.a2_les_22_02_1, R.string.a2_les_22_03_1, R.string.a2_les_22_04_1, R.string.a2_les_22_05_1, R.string.a2_les_22_06_1, R.string.a2_les_22_07_1, R.string.a2_les_22_08_1, R.string.a2_les_22_09_1,
            R.string.a2_les_22_10_1, R.string.a2_les_22_11_1, R.string.a2_les_22_12_1, R.string.a2_les_22_13_1, R.string.a2_les_22_14_1, R.string.a2_les_22_15_1};
    public int[] draw23_2 = new int[]{
            R.string.a2_les_23_01_1, R.string.a2_les_23_02_1, R.string.a2_les_23_03_1, R.string.a2_les_23_04_1, R.string.a2_les_23_05_1, R.string.a2_les_23_06_1, R.string.a2_les_23_07_1, R.string.a2_les_23_08_1, R.string.a2_les_23_09_1,
            R.string.a2_les_23_10_1, R.string.a2_les_23_11_1, R.string.a2_les_23_12_1, R.string.a2_les_23_13_1, R.string.a2_les_23_14_1, R.string.a2_les_23_15_1};
    public int[] draw24_2 = new int[]{
            R.string.a2_les_24_01_1, R.string.a2_les_24_02_1, R.string.a2_les_24_03_1, R.string.a2_les_24_04_1, R.string.a2_les_24_05_1, R.string.a2_les_24_06_1, R.string.a2_les_24_07_1, R.string.a2_les_24_08_1, R.string.a2_les_24_09_1,
            R.string.a2_les_24_10_1, R.string.a2_les_24_11_1, R.string.a2_les_24_12_1, R.string.a2_les_24_13_1, R.string.a2_les_24_14_1, R.string.a2_les_24_15_1};
    public int[] draw25_2 = new int[]{
            R.string.a2_les_25_01_1, R.string.a2_les_25_02_1, R.string.a2_les_25_03_1, R.string.a2_les_25_04_1, R.string.a2_les_25_05_1, R.string.a2_les_25_06_1, R.string.a2_les_25_07_1, R.string.a2_les_25_08_1, R.string.a2_les_25_09_1,
            R.string.a2_les_25_10_1, R.string.a2_les_25_11_1, R.string.a2_les_25_12_1, R.string.a2_les_25_13_1, R.string.a2_les_25_14_1, R.string.a2_les_25_15_1};
    public int[] draw26_2 = new int[]{
            R.string.a2_les_26_01_1, R.string.a2_les_26_02_1, R.string.a2_les_26_03_1, R.string.a2_les_26_04_1, R.string.a2_les_26_05_1, R.string.a2_les_26_06_1, R.string.a2_les_26_07_1, R.string.a2_les_26_08_1, R.string.a2_les_26_09_1,
            R.string.a2_les_26_10_1, R.string.a2_les_26_11_1, R.string.a2_les_26_12_1, R.string.a2_les_26_13_1, R.string.a2_les_26_14_1, R.string.a2_les_26_15_1};
    public int[] draw27_2= new int[]{
            R.string.a2_les_27_01_1, R.string.a2_les_27_02_1, R.string.a2_les_27_03_1, R.string.a2_les_27_04_1, R.string.a2_les_27_05_1, R.string.a2_les_27_06_1, R.string.a2_les_27_07_1, R.string.a2_les_27_08_1, R.string.a2_les_27_09_1,
            R.string.a2_les_27_10_1, R.string.a2_les_27_11_1, R.string.a2_les_27_12_1, R.string.a2_les_27_13_1, R.string.a2_les_27_14_1, R.string.a2_les_27_15_1};
    public int[] draw28_2 = new int[]{
            R.string.a2_les_28_01_1, R.string.a2_les_28_02_1, R.string.a2_les_28_03_1, R.string.a2_les_28_04_1, R.string.a2_les_28_05_1, R.string.a2_les_28_06_1, R.string.a2_les_28_07_1, R.string.a2_les_28_08_1, R.string.a2_les_28_09_1,
            R.string.a2_les_28_10_1, R.string.a2_les_28_11_1, R.string.a2_les_28_12_1, R.string.a2_les_28_13_1, R.string.a2_les_28_14_1, R.string.a2_les_28_15_1};
    public int[] draw29_2 = new int[]{
            R.string.a2_les_29_01_1, R.string.a2_les_29_02_1, R.string.a2_les_29_03_1, R.string.a2_les_29_04_1, R.string.a2_les_29_05_1, R.string.a2_les_29_06_1, R.string.a2_les_29_07_1, R.string.a2_les_29_08_1, R.string.a2_les_29_09_1,
            R.string.a2_les_29_10_1, R.string.a2_les_29_11_1, R.string.a2_les_29_12_1, R.string.a2_les_29_13_1, R.string.a2_les_29_14_1, R.string.a2_les_29_15_1};
    public int[] draw30_2 = new int[]{
            R.string.a2_les_30_01_1, R.string.a2_les_30_02_1, R.string.a2_les_30_03_1, R.string.a2_les_30_04_1, R.string.a2_les_30_05_1, R.string.a2_les_30_06_1, R.string.a2_les_30_07_1, R.string.a2_les_30_08_1, R.string.a2_les_30_09_1,
            R.string.a2_les_30_10_1, R.string.a2_les_30_11_1, R.string.a2_les_30_12_1, R.string.a2_les_30_13_1, R.string.a2_les_30_14_1, R.string.a2_les_30_15_1};
    public int[] draw31_2 = new int[]{
            R.string.a2_les_31_01_1, R.string.a2_les_31_02_1, R.string.a2_les_31_03_1, R.string.a2_les_31_04_1, R.string.a2_les_31_05_1, R.string.a2_les_31_06_1, R.string.a2_les_31_07_1, R.string.a2_les_31_08_1, R.string.a2_les_31_09_1,
            R.string.a2_les_31_10_1, R.string.a2_les_31_11_1, R.string.a2_les_31_12_1, R.string.a2_les_31_13_1, R.string.a2_les_31_14_1, R.string.a2_les_31_15_1};
    public int[] draw32_2 = new int[]{
            R.string.a2_les_32_01_1, R.string.a2_les_32_02_1, R.string.a2_les_32_03_1, R.string.a2_les_32_04_1, R.string.a2_les_32_05_1, R.string.a2_les_32_06_1, R.string.a2_les_32_07_1, R.string.a2_les_32_08_1, R.string.a2_les_32_09_1,
            R.string.a2_les_32_10_1, R.string.a2_les_32_11_1, R.string.a2_les_32_12_1, R.string.a2_les_32_13_1, R.string.a2_les_32_14_1, R.string.a2_les_32_15_1};

    public int[] draw33_1 = new int[]{
            R.string.a2_les_33_01, R.string.a2_les_33_02, R.string.a2_les_33_03, R.string.a2_les_33_04, R.string.a2_les_33_05, R.string.a2_les_33_06, R.string.a2_les_33_07, R.string.a2_les_33_08, R.string.a2_les_33_09,
            R.string.a2_les_33_10, R.string.a2_les_33_11, R.string.a2_les_33_12, R.string.a2_les_33_13, R.string.a2_les_33_14, R.string.a2_les_33_15};
    public int[] draw33_2 = new int[]{
            R.string.a2_les_33_01_1, R.string.a2_les_33_02_1, R.string.a2_les_33_03_1, R.string.a2_les_33_04_1, R.string.a2_les_33_05_1, R.string.a2_les_33_06_1, R.string.a2_les_33_07_1, R.string.a2_les_33_08_1, R.string.a2_les_33_09_1,
            R.string.a2_les_33_10_1, R.string.a2_les_33_11_1, R.string.a2_les_33_12_1, R.string.a2_les_33_13_1, R.string.a2_les_33_14_1, R.string.a2_les_33_15_1};
    public int[] draw34_1 = new int[]{
            R.string.a2_les_34_01, R.string.a2_les_34_02, R.string.a2_les_34_03, R.string.a2_les_34_04, R.string.a2_les_34_05, R.string.a2_les_34_06, R.string.a2_les_34_07, R.string.a2_les_34_08, R.string.a2_les_34_09,
            R.string.a2_les_34_10, R.string.a2_les_34_11, R.string.a2_les_34_12, R.string.a2_les_34_13, R.string.a2_les_34_14, R.string.a2_les_34_15};
    public int[] draw34_2 = new int[]{
            R.string.a2_les_34_01_1, R.string.a2_les_34_02_1, R.string.a2_les_34_03_1, R.string.a2_les_34_04_1, R.string.a2_les_34_05_1, R.string.a2_les_34_06_1, R.string.a2_les_34_07_1, R.string.a2_les_34_08_1, R.string.a2_les_34_09_1,
            R.string.a2_les_34_10_1, R.string.a2_les_34_11_1, R.string.a2_les_34_12_1, R.string.a2_les_34_13_1, R.string.a2_les_34_14_1, R.string.a2_les_34_15_1};
    public int[] draw35_1 = new int[]{
            R.string.a2_les_35_01, R.string.a2_les_35_02, R.string.a2_les_35_03, R.string.a2_les_35_04, R.string.a2_les_35_05, R.string.a2_les_35_06, R.string.a2_les_35_07, R.string.a2_les_35_08, R.string.a2_les_35_09,
            R.string.a2_les_35_10, R.string.a2_les_35_11, R.string.a2_les_35_12, R.string.a2_les_35_13, R.string.a2_les_35_14, R.string.a2_les_35_15};
    public int[] draw35_2 = new int[]{
            R.string.a2_les_35_01_1, R.string.a2_les_35_02_1, R.string.a2_les_35_03_1, R.string.a2_les_35_04_1, R.string.a2_les_35_05_1, R.string.a2_les_35_06_1, R.string.a2_les_35_07_1, R.string.a2_les_35_08_1, R.string.a2_les_35_09_1,
            R.string.a2_les_35_10_1, R.string.a2_les_35_11_1, R.string.a2_les_35_12_1, R.string.a2_les_35_13_1, R.string.a2_les_35_14_1, R.string.a2_les_35_15_1};
    public int[] draw36_1 = new int[]{
            R.string.a2_les_36_01, R.string.a2_les_36_02, R.string.a2_les_36_03, R.string.a2_les_36_04, R.string.a2_les_36_05, R.string.a2_les_36_06, R.string.a2_les_36_07, R.string.a2_les_36_08, R.string.a2_les_36_09,
            R.string.a2_les_36_10, R.string.a2_les_36_11, R.string.a2_les_36_12, R.string.a2_les_36_13, R.string.a2_les_36_14, R.string.a2_les_36_15};
    public int[] draw36_2 = new int[]{
            R.string.a2_les_36_01_1, R.string.a2_les_36_02_1, R.string.a2_les_36_03_1, R.string.a2_les_36_04_1, R.string.a2_les_36_05_1, R.string.a2_les_36_06_1, R.string.a2_les_36_07_1, R.string.a2_les_36_08_1, R.string.a2_les_36_09_1,
            R.string.a2_les_36_10_1, R.string.a2_les_36_11_1, R.string.a2_les_36_12_1, R.string.a2_les_36_13_1, R.string.a2_les_36_14_1, R.string.a2_les_36_15_1};
    public int[] draw37_1 = new int[]{
            R.string.a2_les_37_01, R.string.a2_les_37_02, R.string.a2_les_37_03, R.string.a2_les_37_04, R.string.a2_les_37_05, R.string.a2_les_37_06, R.string.a2_les_37_07, R.string.a2_les_37_08, R.string.a2_les_37_09,
            R.string.a2_les_37_10, R.string.a2_les_37_11, R.string.a2_les_37_12, R.string.a2_les_37_13, R.string.a2_les_37_14, R.string.a2_les_37_15};
    public int[] draw37_2 = new int[]{
            R.string.a2_les_37_01_1, R.string.a2_les_37_02_1, R.string.a2_les_37_03_1, R.string.a2_les_37_04_1, R.string.a2_les_37_05_1, R.string.a2_les_37_06_1, R.string.a2_les_37_07_1, R.string.a2_les_37_08_1, R.string.a2_les_37_09_1,
            R.string.a2_les_37_10_1, R.string.a2_les_37_11_1, R.string.a2_les_37_12_1, R.string.a2_les_37_13_1, R.string.a2_les_37_14_1, R.string.a2_les_37_15_1};
    public int[] draw38_1 = new int[]{
            R.string.a2_les_38_01, R.string.a2_les_38_02, R.string.a2_les_38_03, R.string.a2_les_38_04, R.string.a2_les_38_05, R.string.a2_les_38_06, R.string.a2_les_38_07, R.string.a2_les_38_08, R.string.a2_les_38_09,
            R.string.a2_les_38_10, R.string.a2_les_38_11, R.string.a2_les_38_12, R.string.a2_les_38_13, R.string.a2_les_38_14, R.string.a2_les_38_15};
    public int[] draw38_2 = new int[]{
            R.string.a2_les_38_01_1, R.string.a2_les_38_02_1, R.string.a2_les_38_03_1, R.string.a2_les_38_04_1, R.string.a2_les_38_05_1, R.string.a2_les_38_06_1, R.string.a2_les_38_07_1, R.string.a2_les_38_08_1, R.string.a2_les_38_09_1,
            R.string.a2_les_38_10_1, R.string.a2_les_38_11_1, R.string.a2_les_38_12_1, R.string.a2_les_38_13_1, R.string.a2_les_38_14_1, R.string.a2_les_38_15_1};
    public int[] draw39_1 = new int[]{
            R.string.a2_les_39_01, R.string.a2_les_39_02, R.string.a2_les_39_03, R.string.a2_les_39_04, R.string.a2_les_39_05, R.string.a2_les_39_06, R.string.a2_les_39_07, R.string.a2_les_39_08, R.string.a2_les_39_09,
            R.string.a2_les_39_10, R.string.a2_les_39_11, R.string.a2_les_39_12, R.string.a2_les_39_13, R.string.a2_les_39_14, R.string.a2_les_39_15};
    public int[] draw39_2 = new int[]{
            R.string.a2_les_39_01_1, R.string.a2_les_39_02_1, R.string.a2_les_39_03_1, R.string.a2_les_39_04_1, R.string.a2_les_39_05_1, R.string.a2_les_39_06_1, R.string.a2_les_39_07_1, R.string.a2_les_39_08_1, R.string.a2_les_39_09_1,
            R.string.a2_les_39_10_1, R.string.a2_les_39_11_1, R.string.a2_les_39_12_1, R.string.a2_les_39_13_1, R.string.a2_les_39_14_1, R.string.a2_les_39_15_1};
    public int[] draw40_1 = new int[]{
            R.string.a2_les_40_01, R.string.a2_les_40_02, R.string.a2_les_40_03, R.string.a2_les_40_04, R.string.a2_les_40_05, R.string.a2_les_40_06, R.string.a2_les_40_07, R.string.a2_les_40_08, R.string.a2_les_40_09,
            R.string.a2_les_40_10, R.string.a2_les_40_11, R.string.a2_les_40_12, R.string.a2_les_40_13, R.string.a2_les_40_14, R.string.a2_les_40_15};
    public int[] draw40_2 = new int[]{
            R.string.a2_les_40_01_1, R.string.a2_les_40_02_1, R.string.a2_les_40_03_1, R.string.a2_les_40_04_1, R.string.a2_les_40_05_1, R.string.a2_les_40_06_1, R.string.a2_les_40_07_1, R.string.a2_les_40_08_1, R.string.a2_les_40_09_1,
            R.string.a2_les_40_10_1, R.string.a2_les_40_11_1, R.string.a2_les_40_12_1, R.string.a2_les_40_13_1, R.string.a2_les_40_14_1, R.string.a2_les_40_15_1};
    public int[] draw41_1 = new int[]{
            R.string.a2_les_41_01, R.string.a2_les_41_02, R.string.a2_les_41_03, R.string.a2_les_41_04, R.string.a2_les_41_05, R.string.a2_les_41_06, R.string.a2_les_41_07, R.string.a2_les_41_08, R.string.a2_les_41_09,
            R.string.a2_les_41_10, R.string.a2_les_41_11, R.string.a2_les_41_12, R.string.a2_les_41_13, R.string.a2_les_41_14, R.string.a2_les_41_15};
    public int[] draw41_2 = new int[]{
            R.string.a2_les_41_01_1, R.string.a2_les_41_02_1, R.string.a2_les_41_03_1, R.string.a2_les_41_04_1, R.string.a2_les_41_05_1, R.string.a2_les_41_06_1, R.string.a2_les_41_07_1, R.string.a2_les_41_08_1, R.string.a2_les_41_09_1,
            R.string.a2_les_41_10_1, R.string.a2_les_41_11_1, R.string.a2_les_41_12_1, R.string.a2_les_41_13_1, R.string.a2_les_41_14_1, R.string.a2_les_41_15_1};
    public int[] draw42_1 = new int[]{
            R.string.a2_les_42_01, R.string.a2_les_42_02, R.string.a2_les_42_03, R.string.a2_les_42_04, R.string.a2_les_42_05, R.string.a2_les_42_06, R.string.a2_les_42_07, R.string.a2_les_42_08, R.string.a2_les_42_09,
            R.string.a2_les_42_10, R.string.a2_les_42_11, R.string.a2_les_42_12, R.string.a2_les_42_13, R.string.a2_les_42_14, R.string.a2_les_42_15};
    public int[] draw42_2 = new int[]{
            R.string.a2_les_42_01_1, R.string.a2_les_42_02_1, R.string.a2_les_42_03_1, R.string.a2_les_42_04_1, R.string.a2_les_42_05_1, R.string.a2_les_42_06_1, R.string.a2_les_42_07_1, R.string.a2_les_42_08_1, R.string.a2_les_42_09_1,
            R.string.a2_les_42_10_1, R.string.a2_les_42_11_1, R.string.a2_les_42_12_1, R.string.a2_les_42_13_1, R.string.a2_les_42_14_1, R.string.a2_les_42_15_1};
    public int[] draw43_1 = new int[]{
            R.string.a2_les_43_01, R.string.a2_les_43_02, R.string.a2_les_43_03, R.string.a2_les_43_04, R.string.a2_les_43_05, R.string.a2_les_43_06, R.string.a2_les_43_07, R.string.a2_les_43_08, R.string.a2_les_43_09,
            R.string.a2_les_43_10, R.string.a2_les_43_11, R.string.a2_les_43_12, R.string.a2_les_43_13, R.string.a2_les_43_14, R.string.a2_les_43_15};
    public int[] draw43_2 = new int[]{
            R.string.a2_les_43_01_1, R.string.a2_les_43_02_1, R.string.a2_les_43_03_1, R.string.a2_les_43_04_1, R.string.a2_les_43_05_1, R.string.a2_les_43_06_1, R.string.a2_les_43_07_1, R.string.a2_les_43_08_1, R.string.a2_les_43_09_1,
            R.string.a2_les_43_10_1, R.string.a2_les_43_11_1, R.string.a2_les_43_12_1, R.string.a2_les_43_13_1, R.string.a2_les_43_14_1, R.string.a2_les_43_15_1};
    public int[] draw44_1 = new int[]{
            R.string.a2_les_44_01, R.string.a2_les_44_02, R.string.a2_les_44_03, R.string.a2_les_44_04, R.string.a2_les_44_05, R.string.a2_les_44_06, R.string.a2_les_44_07, R.string.a2_les_44_08, R.string.a2_les_44_09,
            R.string.a2_les_44_10, R.string.a2_les_44_11, R.string.a2_les_44_12, R.string.a2_les_44_13, R.string.a2_les_44_14, R.string.a2_les_44_15};
    public int[] draw44_2 = new int[]{
            R.string.a2_les_44_01_1, R.string.a2_les_44_02_1, R.string.a2_les_44_03_1, R.string.a2_les_44_04_1, R.string.a2_les_44_05_1, R.string.a2_les_44_06_1, R.string.a2_les_44_07_1, R.string.a2_les_44_08_1, R.string.a2_les_44_09_1,
            R.string.a2_les_44_10_1, R.string.a2_les_44_11_1, R.string.a2_les_44_12_1, R.string.a2_les_44_13_1, R.string.a2_les_44_14_1, R.string.a2_les_44_15_1};
    public int[] draw45_1 = new int[]{
            R.string.a2_les_45_01, R.string.a2_les_45_02, R.string.a2_les_45_03, R.string.a2_les_45_04, R.string.a2_les_45_05, R.string.a2_les_45_06, R.string.a2_les_45_07, R.string.a2_les_45_08, R.string.a2_les_45_09,
            R.string.a2_les_45_10, R.string.a2_les_45_11, R.string.a2_les_45_12, R.string.a2_les_45_13, R.string.a2_les_45_14, R.string.a2_les_45_15};
    public int[] draw45_2 = new int[]{
            R.string.a2_les_45_01_1, R.string.a2_les_45_02_1, R.string.a2_les_45_03_1, R.string.a2_les_45_04_1, R.string.a2_les_45_05_1, R.string.a2_les_45_06_1, R.string.a2_les_45_07_1, R.string.a2_les_45_08_1, R.string.a2_les_45_09_1,
            R.string.a2_les_45_10_1, R.string.a2_les_45_11_1, R.string.a2_les_45_12_1, R.string.a2_les_45_13_1, R.string.a2_les_45_14_1, R.string.a2_les_45_15_1};
    public int[] draw46_1 = new int[]{
            R.string.a2_les_46_01, R.string.a2_les_46_02, R.string.a2_les_46_03, R.string.a2_les_46_04, R.string.a2_les_46_05, R.string.a2_les_46_06, R.string.a2_les_46_07, R.string.a2_les_46_08, R.string.a2_les_46_09,
            R.string.a2_les_46_10, R.string.a2_les_46_11, R.string.a2_les_46_12, R.string.a2_les_46_13, R.string.a2_les_46_14, R.string.a2_les_46_15};
    public int[] draw46_2 = new int[]{
            R.string.a2_les_46_01_1, R.string.a2_les_46_02_1, R.string.a2_les_46_03_1, R.string.a2_les_46_04_1, R.string.a2_les_46_05_1, R.string.a2_les_46_06_1, R.string.a2_les_46_07_1, R.string.a2_les_46_08_1, R.string.a2_les_46_09_1,
            R.string.a2_les_46_10_1, R.string.a2_les_46_11_1, R.string.a2_les_46_12_1, R.string.a2_les_46_13_1, R.string.a2_les_46_14_1, R.string.a2_les_46_15_1};
    public int[] draw47_1 = new int[]{
            R.string.a2_les_47_01, R.string.a2_les_47_02, R.string.a2_les_47_03, R.string.a2_les_47_04, R.string.a2_les_47_05, R.string.a2_les_47_06, R.string.a2_les_47_07, R.string.a2_les_47_08, R.string.a2_les_47_09,
            R.string.a2_les_47_10, R.string.a2_les_47_11, R.string.a2_les_47_12, R.string.a2_les_47_13, R.string.a2_les_47_14, R.string.a2_les_47_15};
    public int[] draw47_2 = new int[]{
            R.string.a2_les_47_01_1, R.string.a2_les_47_02_1, R.string.a2_les_47_03_1, R.string.a2_les_47_04_1, R.string.a2_les_47_05_1, R.string.a2_les_47_06_1, R.string.a2_les_47_07_1, R.string.a2_les_47_08_1, R.string.a2_les_47_09_1,
            R.string.a2_les_47_10_1, R.string.a2_les_47_11_1, R.string.a2_les_47_12_1, R.string.a2_les_47_13_1, R.string.a2_les_47_14_1, R.string.a2_les_47_15_1};
    public int[] draw48_1 = new int[]{
            R.string.a2_les_48_01, R.string.a2_les_48_02, R.string.a2_les_48_03, R.string.a2_les_48_04, R.string.a2_les_48_05, R.string.a2_les_48_06, R.string.a2_les_48_07, R.string.a2_les_48_08, R.string.a2_les_48_09,
            R.string.a2_les_48_10, R.string.a2_les_48_11, R.string.a2_les_48_12, R.string.a2_les_48_13, R.string.a2_les_48_14, R.string.a2_les_48_15};
    public int[] draw48_2 = new int[]{
            R.string.a2_les_48_01_1, R.string.a2_les_48_02_1, R.string.a2_les_48_03_1, R.string.a2_les_48_04_1, R.string.a2_les_48_05_1, R.string.a2_les_48_06_1, R.string.a2_les_48_07_1, R.string.a2_les_48_08_1, R.string.a2_les_48_09_1,
            R.string.a2_les_48_10_1, R.string.a2_les_48_11_1, R.string.a2_les_48_12_1, R.string.a2_les_48_13_1, R.string.a2_les_48_14_1, R.string.a2_les_48_15_1};
    public int[] draw49_1 = new int[]{
            R.string.a2_les_49_01, R.string.a2_les_49_02, R.string.a2_les_49_03, R.string.a2_les_49_04, R.string.a2_les_49_05, R.string.a2_les_49_06, R.string.a2_les_49_07, R.string.a2_les_49_08, R.string.a2_les_49_09,
            R.string.a2_les_49_10, R.string.a2_les_49_11, R.string.a2_les_49_12, R.string.a2_les_49_13, R.string.a2_les_49_14, R.string.a2_les_49_15};
    public int[] draw49_2 = new int[]{
            R.string.a2_les_49_01_1, R.string.a2_les_49_02_1, R.string.a2_les_49_03_1, R.string.a2_les_49_04_1, R.string.a2_les_49_05_1, R.string.a2_les_49_06_1, R.string.a2_les_49_07_1, R.string.a2_les_49_08_1, R.string.a2_les_49_09_1,
            R.string.a2_les_49_10_1, R.string.a2_les_49_11_1, R.string.a2_les_49_12_1, R.string.a2_les_49_13_1, R.string.a2_les_49_14_1, R.string.a2_les_49_15_1};
    public int[] draw50_1 = new int[]{
            R.string.a2_les_50_01, R.string.a2_les_50_02, R.string.a2_les_50_03, R.string.a2_les_50_04, R.string.a2_les_50_05, R.string.a2_les_50_06, R.string.a2_les_50_07, R.string.a2_les_50_08, R.string.a2_les_50_09,
            R.string.a2_les_50_10, R.string.a2_les_50_11, R.string.a2_les_50_12, R.string.a2_les_50_13, R.string.a2_les_50_14, R.string.a2_les_50_15};
    public int[] draw50_2 = new int[]{
            R.string.a2_les_50_01_1, R.string.a2_les_50_02_1, R.string.a2_les_50_03_1, R.string.a2_les_50_04_1, R.string.a2_les_50_05_1, R.string.a2_les_50_06_1, R.string.a2_les_50_07_1, R.string.a2_les_50_08_1, R.string.a2_les_50_09_1,
            R.string.a2_les_50_10_1, R.string.a2_les_50_11_1, R.string.a2_les_50_12_1, R.string.a2_les_50_13_1, R.string.a2_les_50_14_1, R.string.a2_les_50_15_1};

    public int[][] main_1 = new int[][]{draw0_1, draw1_1, draw2_1, draw3_1, draw4_1, draw5_1, draw6_1, draw7_1, draw8_1, draw9_1, draw10_1, draw11_1, draw12_1, draw13_1, draw14_1, draw15_1, draw16_1, draw17_1, draw18_1, draw19_1, draw20_1, draw21_1, draw22_1, draw23_1, draw24_1, draw25_1, draw26_1, draw27_1, draw28_1, draw29_1, draw30_1, draw31_1, draw32_1, draw33_1, draw34_1, draw35_1, draw36_1, draw37_1, draw38_1, draw39_1, draw40_1, draw41_1, draw42_1, draw43_1, draw44_1, draw45_1, draw46_1, draw47_1, draw48_1, draw49_1, draw50_1};
    public int[][] main_2 = new int[][]{draw0_2, draw1_2, draw2_2, draw3_2, draw4_2, draw5_2, draw6_2, draw7_2, draw8_2, draw9_2, draw10_2, draw11_2, draw12_2, draw13_2, draw14_2, draw15_2, draw16_2, draw17_2, draw18_2, draw19_2, draw20_2, draw21_2, draw22_2, draw23_2, draw24_2, draw25_2, draw26_2, draw27_2, draw28_2, draw29_2, draw30_2, draw31_2, draw32_2, draw33_2, draw34_2, draw35_2, draw36_2, draw37_2, draw38_2, draw39_2, draw40_2, draw41_2, draw42_2, draw43_2, draw44_2, draw45_2, draw46_2, draw47_2, draw48_2, draw49_2, draw50_2};


    private int [] id = new int[]{1001,1002,1003,1004,1005,1006,1007,1008,1009,1010,1011,1012,1013,1014,1015};

    private String [] back_words = new String[]{"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"};

    private boolean [] isRight = new boolean[]{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};

    private int counter_true = 0; private Toast mess;

    TextView words1; TextView words2; TextView words3; TextView words4; TextView words5; TextView words6; TextView words7; TextView words8; TextView words9; TextView words10; TextView words11;
    TextView words12; TextView words13; TextView words14; TextView words15;

    TextView words1_1; TextView words2_1; TextView words3_1; TextView words4_1; TextView words5_1; TextView words6_1; TextView words7_1; TextView words8_1; TextView words9_1; TextView words10_1; TextView words11_1;
    TextView words12_1; TextView words13_1; TextView words14_1; TextView words15_1;

    EditText editText1, editText2, editText3, editText4, editText5, editText6, editText7, editText8, editText9, editText10, editText11, editText12, editText13, editText14, editText15;

    LinearLayout lin_1; LinearLayout lin_2; LinearLayout lin_3; LinearLayout lin_4; LinearLayout lin_5; LinearLayout lin_6; LinearLayout lin_7; LinearLayout lin_8; LinearLayout lin_9; LinearLayout lin_10; LinearLayout lin_11;
    LinearLayout lin_12; LinearLayout lin_13; LinearLayout lin_14; LinearLayout lin_15;

    private TextView check;

    private  int index = 0;
    private int i = 0;

    private String doing;

    private int [] counter_flip = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    private FloatingActionButton el_next;

    private Drawable LinDraw;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        words1 = findViewById(R.id.words1);   words2 = findViewById(R.id.words2); words3 = findViewById(R.id.words3); words4 = findViewById(R.id.words4); words5 = findViewById(R.id.words5);
        words6 = findViewById(R.id.words6);   words7 = findViewById(R.id.words7); words8 = findViewById(R.id.words8); words9 = findViewById(R.id.words9); words10 = findViewById(R.id.words10);
        words11 = findViewById(R.id.words11); words12 = findViewById(R.id.words12); words13 = findViewById(R.id.words13); words14 = findViewById(R.id.words14); words15 = findViewById(R.id.words15);

        words1_1 = findViewById(R.id.words1_1); words2_1 = findViewById(R.id.words2_1); words3_1 = findViewById(R.id.words3_1); words4_1 = findViewById(R.id.words4_1); words5_1 = findViewById(R.id.words5_1);
        words6_1 = findViewById(R.id.words6_1); words7_1 = findViewById(R.id.words7_1); words8_1 = findViewById(R.id.words8_1); words9_1 = findViewById(R.id.words9_1); words10_1 = findViewById(R.id.words10_1);
        words11_1 = findViewById(R.id.words11_1); words12_1 = findViewById(R.id.words12_1); words13_1 = findViewById(R.id.words13_1); words14_1 = findViewById(R.id.words14_1); words15_1 = findViewById(R.id.words15_1);

        editText1 = findViewById(R.id.editText1); editText2 = findViewById(R.id.editText2); editText3 = findViewById(R.id.editText3); editText4 = findViewById(R.id.editText4); editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6); editText7 = findViewById(R.id.editText7); editText8 = findViewById(R.id.editText8); editText9 = findViewById(R.id.editText9); editText10 = findViewById(R.id.editText10);
        editText11 = findViewById(R.id.editText11); editText12 = findViewById(R.id.editText12); editText13 = findViewById(R.id.editText13); editText14 = findViewById(R.id.editText14); editText15 = findViewById(R.id.editText15);

        lin_1 = findViewById(R.id.lin_1); lin_2 = findViewById(R.id.lin_2); lin_3 = findViewById(R.id.lin_3); lin_4 = findViewById(R.id.lin_4); lin_5 = findViewById(R.id.lin_5);
        lin_6 = findViewById(R.id.lin_6); lin_7 = findViewById(R.id.lin_7); lin_8 = findViewById(R.id.lin_8); lin_9 = findViewById(R.id.lin_9); lin_10 = findViewById(R.id.lin_10);
        lin_11 = findViewById(R.id.lin_11); lin_12 = findViewById(R.id.lin_12); lin_13 = findViewById(R.id.lin_13); lin_14 = findViewById(R.id.lin_14); lin_15 = findViewById(R.id.lin_15);

        check = findViewById(R.id.check);
        el_next = findViewById(R.id.el_next);


        doing = TestOrLearn.doing;

        LinDraw = lin_1.getBackground();

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
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
                    Log.e("TTS", "Initializator failef");
                }
            }
        });

        if(doing.equals("learn")){

            full_array();
            index = PreIntermediate.abs;
            change_test();
            editText_visible_gone();
            check_visible_gone();

        } else if (doing.equals("test")){

            text1_1_visibel_vis();
            text1_visibel_gone();
            full_array();
            index = PreIntermediate.abs;
            change_test();

        }

    }

    private void full_array(){
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
                    }else{
                        k++;
                    }
                }
                if(k == 15){
                    isTrue = false;
                }

            } while (isTrue);
            id[iter] = rand;
        }
    }


    private void change_test(){

        words1.setText(main_1[index][id[i++]]);
        words2.setText(main_1[index][id[i++]]);
        words3.setText(main_1[index][id[i++]]);
        words4.setText(main_1[index][id[i++]]);
        words5.setText(main_1[index][id[i++]]);
        words6.setText(main_1[index][id[i++]]);
        words7.setText(main_1[index][id[i++]]);
        words8.setText(main_1[index][id[i++]]);
        words9.setText(main_1[index][id[i++]]);
        words10.setText(main_1[index][id[i++]]);
        words11.setText(main_1[index][id[i++]]);
        words12.setText(main_1[index][id[i++]]);
        words13.setText(main_1[index][id[i++]]);
        words14.setText(main_1[index][id[i++]]);
        words15.setText(main_1[index][id[i]]);
        i = 0;

        words1_1.setText(main_2[index][id[i++]]);
        words2_1.setText(main_2[index][id[i++]]);
        words3_1.setText(main_2[index][id[i++]]);
        words4_1.setText(main_2[index][id[i++]]);
        words5_1.setText(main_2[index][id[i++]]);
        words6_1.setText(main_2[index][id[i++]]);
        words7_1.setText(main_2[index][id[i++]]);
        words8_1.setText(main_2[index][id[i++]]);
        words9_1.setText(main_2[index][id[i++]]);
        words10_1.setText(main_2[index][id[i++]]);
        words11_1.setText(main_2[index][id[i++]]);
        words12_1.setText(main_2[index][id[i++]]);
        words13_1.setText(main_2[index][id[i++]]);
        words14_1.setText(main_2[index][id[i++]]);
        words15_1.setText(main_2[index][id[i]]);
        i = 0;
    }

    private void editText_visible_gone(){

        editText1.setVisibility(View.GONE);
        editText2.setVisibility(View.GONE);
        editText3.setVisibility(View.GONE);
        editText4.setVisibility(View.GONE);
        editText5.setVisibility(View.GONE);
        editText6.setVisibility(View.GONE);
        editText7.setVisibility(View.GONE);
        editText8.setVisibility(View.GONE);
        editText9.setVisibility(View.GONE);
        editText10.setVisibility(View.GONE);
        editText11.setVisibility(View.GONE);
        editText12.setVisibility(View.GONE);
        editText13.setVisibility(View.GONE);
        editText14.setVisibility(View.GONE);
        editText15.setVisibility(View.GONE);

    }

    private void text1_1_visibel_vis(){

        words1_1.setVisibility(View.VISIBLE);
        words2_1.setVisibility(View.VISIBLE);
        words3_1.setVisibility(View.VISIBLE);
        words4_1.setVisibility(View.VISIBLE);
        words5_1.setVisibility(View.VISIBLE);
        words6_1.setVisibility(View.VISIBLE);
        words7_1.setVisibility(View.VISIBLE);
        words8_1.setVisibility(View.VISIBLE);
        words9_1.setVisibility(View.VISIBLE);
        words10_1.setVisibility(View.VISIBLE);
        words11_1.setVisibility(View.VISIBLE);
        words12_1.setVisibility(View.VISIBLE);
        words13_1.setVisibility(View.VISIBLE);
        words14_1.setVisibility(View.VISIBLE);
        words15_1.setVisibility(View.VISIBLE);
    }

    private void text1_1_visibel_gone(){

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

    private void text1_visibel_gone(){

        words1.setVisibility(View.GONE);
        words2.setVisibility(View.GONE);
        words3.setVisibility(View.GONE);
        words4.setVisibility(View.GONE);
        words5.setVisibility(View.GONE);
        words6.setVisibility(View.GONE);
        words7.setVisibility(View.GONE);
        words8.setVisibility(View.GONE);
        words9.setVisibility(View.GONE);
        words10.setVisibility(View.GONE);
        words11.setVisibility(View.GONE);
        words12.setVisibility(View.GONE);
        words13.setVisibility(View.GONE);
        words14.setVisibility(View.GONE);
        words15.setVisibility(View.GONE);

    }

    private void text1_visibel_vis(){

        words1.setVisibility(View.VISIBLE);
        words2.setVisibility(View.VISIBLE);
        words3.setVisibility(View.VISIBLE);
        words4.setVisibility(View.VISIBLE);
        words5.setVisibility(View.VISIBLE);
        words6.setVisibility(View.VISIBLE);
        words7.setVisibility(View.VISIBLE);
        words8.setVisibility(View.VISIBLE);
        words9.setVisibility(View.VISIBLE);
        words10.setVisibility(View.VISIBLE);
        words11.setVisibility(View.VISIBLE);
        words12.setVisibility(View.VISIBLE);
        words13.setVisibility(View.VISIBLE);
        words14.setVisibility(View.VISIBLE);
        words15.setVisibility(View.VISIBLE);

    }

    private void check_visible_gone(){
        check.setVisibility(View.GONE);
    }

    private void back_inf_from_editText(){
        int t = 0;
        back_words[t++] = editText1.getText().toString(); // приводим к типу String
        back_words[t++] = editText2.getText().toString();
        back_words[t++] = editText3.getText().toString();
        back_words[t++] = editText4.getText().toString();
        back_words[t++] = editText5.getText().toString();
        back_words[t++] = editText6.getText().toString();
        back_words[t++] = editText7.getText().toString();
        back_words[t++] = editText8.getText().toString();
        back_words[t++] = editText9.getText().toString();
        back_words[t++] = editText10.getText().toString();
        back_words[t++] = editText11.getText().toString();
        back_words[t++] = editText12.getText().toString();
        back_words[t++] = editText13.getText().toString();
        back_words[t++] = editText14.getText().toString();
        back_words[t] = editText15.getText().toString();
        t = 0;
    }

    private void set_null_editText(){
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");
        editText5.setText("");
        editText6.setText("");
        editText7.setText("");
        editText8.setText("");
        editText9.setText("");
        editText10.setText("");
        editText11.setText("");
        editText12.setText("");
        editText13.setText("");
        editText14.setText("");
        editText15.setText("");
    }

    private void equals_back_with_true(){
        String word = "";

        for (int j = 0; j < 15; j++){
            word = getResources().getString((main_1[index][id[j]]));
            back_words[j] = back_words[j].trim();
            isRight[j] = back_words[j].equals(word);
            //System.out.println("что " + back_words[j] + " с чем " + word + " " + isRight[j]);
        }
    }

    @SuppressLint("ResourceAsColor")
    private void setBackgraund_to_lin(LinearLayout lin, int k){
        //System.out.println(k + " " + " " +isRight[k]);
        if (isRight[k]){
            lin.setBackgroundResource(R.color.back_true);
            counter_true++;
            //System.out.println("true");
        }else {
            lin.setBackgroundResource(R.color.back_false);
            //System.out.println("false");
        }

    }

    @SuppressLint("ResourceAsColor")
    private void setBackgraund_to_lin_reply(){
            lin_1.setBackground(LinDraw);
            lin_2.setBackground(LinDraw);
            lin_3.setBackground(LinDraw);
            lin_4.setBackground(LinDraw);
            lin_5.setBackground(LinDraw);
            lin_6.setBackground(LinDraw);
            lin_7.setBackground(LinDraw);
            lin_8.setBackground(LinDraw);
            lin_9.setBackground(LinDraw);
            lin_10.setBackground(LinDraw);
            lin_11.setBackground(LinDraw);
            lin_12.setBackground(LinDraw);
            lin_13.setBackground(LinDraw);
            lin_14.setBackground(LinDraw);
            lin_15.setBackground(LinDraw);
    }



    private void set_all_back_to_lin(){
        int k = 0;
        setBackgraund_to_lin(lin_1, k); k++;
        setBackgraund_to_lin(lin_2, k); k++;
        setBackgraund_to_lin(lin_3, k); k++;
        setBackgraund_to_lin(lin_4, k); k++;
        setBackgraund_to_lin(lin_5, k); k++;
        setBackgraund_to_lin(lin_6, k); k++;
        setBackgraund_to_lin(lin_7, k); k++;
        setBackgraund_to_lin(lin_8, k); k++;
        setBackgraund_to_lin(lin_9, k); k++;
        setBackgraund_to_lin(lin_10, k); k++;
        setBackgraund_to_lin(lin_11, k); k++;
        setBackgraund_to_lin(lin_12, k); k++;
        setBackgraund_to_lin(lin_13, k); k++;
        setBackgraund_to_lin(lin_14, k); k++;
        setBackgraund_to_lin(lin_15, k); k++;
    }

    private void speak(TextView textView){
        String text = textView.getText().toString();
        float pitch = 0.5f;
        float speed = 0.5f;
        Log.e("TTS", "123");
        //mTTS.setPitch(pitch);
        //mTTS.setSpeechRate(speed);

        mTTS.speak(text,TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {

        if(mTTS != null){
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }

    public void next_learn(View v) {
        switch (v.getId()) {
            case R.id.el_next:
                if(doing.equals("learn")){
                    Intent intent = new Intent(this, Definition.class);
                    startActivity(intent);
                    this.finish();
                }
                break;
        }
    }

    public void click(View v) {
        switch (v.getId()) {
            case R.id.words1:
                if(words1_1.getVisibility() == View.GONE){
                    words1_1.setVisibility(View.VISIBLE);
                    counter_flip[0] = 1;
                    calculate_flip();
                }
                speak(words1);
                break;

            case R.id.words2:
                if(words2_1.getVisibility() == View.GONE){
                    words2_1.setVisibility(View.VISIBLE);
                    counter_flip[1] = 1;
                    calculate_flip();
                }
                speak(words2);
                break;

            case R.id.words3:
                if(words3_1.getVisibility() == View.GONE){
                    words3_1.setVisibility(View.VISIBLE);
                    counter_flip[2] = 1;
                    calculate_flip();
                }
                speak(words3);
                break;

            case R.id.words4:
                if(words4_1.getVisibility() == View.GONE){
                    words4_1.setVisibility(View.VISIBLE);
                    counter_flip[3] = 1;
                    calculate_flip();
                }
                speak(words4);
                break;

            case R.id.words5:
                if(words5_1.getVisibility() == View.GONE){
                    words5_1.setVisibility(View.VISIBLE);
                    counter_flip[4] = 1;
                    calculate_flip();
                }
                speak(words5);
                break;

            case R.id.words6:
                if(words6_1.getVisibility() == View.GONE){
                    words6_1.setVisibility(View.VISIBLE);
                    counter_flip[5] = 1;
                    calculate_flip();
                }
                speak(words6);
                break;

            case R.id.words7:
                if(words7_1.getVisibility() == View.GONE){
                    words7_1.setVisibility(View.VISIBLE);
                    counter_flip[6] = 1;
                    calculate_flip();
                }
                speak(words7);
                break;

            case R.id.words8:
                if(words8_1.getVisibility() == View.GONE){
                    words8_1.setVisibility(View.VISIBLE);
                    counter_flip[7] = 1;
                    calculate_flip();
                }
                speak(words8);
                break;

            case R.id.words9:
                if(words9_1.getVisibility() == View.GONE){
                    words9_1.setVisibility(View.VISIBLE);
                    counter_flip[8] = 1;
                    calculate_flip();
                }
                speak(words9);
                break;

            case R.id.words10:
                if(words10_1.getVisibility() == View.GONE){
                    words10_1.setVisibility(View.VISIBLE);
                    counter_flip[9] = 1;
                    calculate_flip();
                }
                speak(words10);
                break;

            case R.id.words11:
                if(words11_1.getVisibility() == View.GONE){
                    words11_1.setVisibility(View.VISIBLE);
                    counter_flip[10] = 1;
                    calculate_flip();
                }
                speak(words11);
                break;

            case R.id.words12:
                if(words12_1.getVisibility() == View.GONE){
                    words12_1.setVisibility(View.VISIBLE);
                    counter_flip[11] = 1;
                    calculate_flip();
                }
                speak(words12);
                break;

            case R.id.words13:
                if(words13_1.getVisibility() == View.GONE){
                    words13_1.setVisibility(View.VISIBLE);
                    counter_flip[12] = 1;
                    calculate_flip();
                }
                speak(words13);
                break;

            case R.id.words14:
                if(words14_1.getVisibility() == View.GONE){
                    words14_1.setVisibility(View.VISIBLE);
                    counter_flip[13] = 1;
                    calculate_flip();
                }
                speak(words14);
                break;

            case R.id.words15:
                if(words15_1.getVisibility() == View.GONE){
                    words15_1.setVisibility(View.VISIBLE);
                    counter_flip[14] = 1;
                    calculate_flip();
                }
                speak(words15);
                break;

            case R.id.check:
                text1_visibel_vis();
                back_inf_from_editText();
                equals_back_with_true();
                set_all_back_to_lin();
                check_visible_gone();
                mess = Toast.makeText(this, "Correct answers " + counter_true + " of " + 15, Toast.LENGTH_LONG);
                mess.show();

                break;
        }
    }

    public void calculate_flip(){
        int ma = 0;
        for (int g:
             counter_flip) {
            if (g == 1){
                ma++;
            }
        }
        if (ma == 15){
            el_next.setVisibility(View.VISIBLE);
        }
    }

    private void reply__text(){
        int temp = 1000;
        for (int j = 0; j < 15; j++) {
            id[j] = temp;
            temp++;
        }
        full_array();

        if(doing.equals("learn")){

            text1_1_visibel_gone();
            change_test();

        } else if (doing.equals("test")) {
            counter_true = 0;
            setBackgraund_to_lin_reply();
            text1_visibel_gone();
            check.setVisibility(View.VISIBLE);
            change_test();
            set_null_editText();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case android.R.id.home:
                Intent intent = new Intent(this, PreIntermediate.class);
                startActivity(intent);
                this.finish();
                return true;

            case R.id.replay_mipmap:
                reply__text();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PreIntermediate.class);
        startActivity(intent);
        this.finish();
    }
}
