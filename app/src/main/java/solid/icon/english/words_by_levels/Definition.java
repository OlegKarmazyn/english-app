package solid.icon.english.words_by_levels;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

import solid.icon.english.R;
import solid.icon.english.words_by_levels.Lev_a2.Level_A2;
import solid.icon.english.words_by_levels.Lev_a2.PreIntermediate;
import solid.icon.english.words_by_levels.lev_b1.Intermediate;
import solid.icon.english.words_by_levels.lev_b1.Level_B1;

public class Definition extends AppCompatActivity {

    private int[] draw0_1 = new int[]{
            R.string.b1_mea0_1, R.string.b1_mea0_2, R.string.b1_mea0_3, R.string.b1_mea0_4, R.string.b1_mea0_5, R.string.b1_mea0_6, R.string.b1_mea0_7, R.string.b1_mea0_8, R.string.b1_mea0_9,
            R.string.b1_mea0_10, R.string.b1_mea0_11, R.string.b1_mea0_12, R.string.b1_mea0_13, R.string.b1_mea0_14, R.string.b1_mea0_15};
    private int[] draw1_1 = new int[]{
            R.string.b1_mea1_1, R.string.b1_mea1_2, R.string.b1_mea1_3, R.string.b1_mea1_4, R.string.b1_mea1_5, R.string.b1_mea1_6, R.string.b1_mea1_7, R.string.b1_mea1_8, R.string.b1_mea1_9,
            R.string.b1_mea1_10, R.string.b1_mea1_11, R.string.b1_mea1_12, R.string.b1_mea1_13, R.string.b1_mea1_14, R.string.b1_mea1_15};
    private int[] draw2_1 = new int[]{
            R.string.b1_mea2_1, R.string.b1_mea2_2, R.string.b1_mea2_3, R.string.b1_mea2_4, R.string.b1_mea2_5, R.string.b1_mea2_6, R.string.b1_mea2_7, R.string.b1_mea2_8, R.string.b1_mea2_9,
            R.string.b1_mea2_10, R.string.b1_mea2_11, R.string.b1_mea2_12, R.string.b1_mea2_13, R.string.b1_mea2_14, R.string.b1_mea2_15};
    private int[] draw3_1 = new int[]{
            R.string.b1_mea3_1, R.string.b1_mea3_2, R.string.b1_mea3_3, R.string.b1_mea3_4, R.string.b1_mea3_5, R.string.b1_mea3_6, R.string.b1_mea3_7, R.string.b1_mea3_8, R.string.b1_mea3_9,
            R.string.b1_mea3_10, R.string.b1_mea3_11, R.string.b1_mea3_12, R.string.b1_mea3_13, R.string.b1_mea3_14, R.string.b1_mea3_15};
    private int[] draw4_1 = new int[]{
            R.string.b1_mea4_1, R.string.b1_mea4_2, R.string.b1_mea4_3, R.string.b1_mea4_4, R.string.b1_mea4_5, R.string.b1_mea4_6, R.string.b1_mea4_7, R.string.b1_mea4_8, R.string.b1_mea4_9,
            R.string.b1_mea4_10, R.string.b1_mea4_11, R.string.b1_mea4_12, R.string.b1_mea4_13, R.string.b1_mea4_14, R.string.b1_mea4_15};
    private int[] draw5_1 = new int[]{
            R.string.b1_mea5_1, R.string.b1_mea5_2, R.string.b1_mea5_3, R.string.b1_mea5_4, R.string.b1_mea5_5, R.string.b1_mea5_6, R.string.b1_mea5_7, R.string.b1_mea5_8, R.string.b1_mea5_9,
            R.string.b1_mea5_10, R.string.b1_mea5_11, R.string.b1_mea5_12, R.string.b1_mea5_13, R.string.b1_mea5_14, R.string.b1_mea5_15};
    private int[] draw6_1 = new int[]{
            R.string.b1_mea6_1, R.string.b1_mea6_2, R.string.b1_mea6_3, R.string.b1_mea6_4, R.string.b1_mea6_5, R.string.b1_mea6_6, R.string.b1_mea6_7, R.string.b1_mea6_8, R.string.b1_mea6_9,
            R.string.b1_mea6_10, R.string.b1_mea6_11, R.string.b1_mea6_12, R.string.b1_mea6_13, R.string.b1_mea6_14, R.string.b1_mea6_15};
    private int[] draw7_1 = new int[]{
            R.string.b1_mea7_1, R.string.b1_mea7_2, R.string.b1_mea7_3, R.string.b1_mea7_4, R.string.b1_mea7_5, R.string.b1_mea7_6, R.string.b1_mea7_7, R.string.b1_mea7_8, R.string.b1_mea7_9,
            R.string.b1_mea7_10, R.string.b1_mea7_11, R.string.b1_mea7_12, R.string.b1_mea7_13, R.string.b1_mea7_14, R.string.b1_mea7_15};
    private int[] draw8_1 = new int[]{
            R.string.b1_mea8_1, R.string.b1_mea8_2, R.string.b1_mea8_3, R.string.b1_mea8_4, R.string.b1_mea8_5, R.string.b1_mea8_6, R.string.b1_mea8_7, R.string.b1_mea8_8, R.string.b1_mea8_9,
            R.string.b1_mea8_10, R.string.b1_mea8_11, R.string.b1_mea8_12, R.string.b1_mea8_13, R.string.b1_mea8_14, R.string.b1_mea8_15};
    private int[] draw9_1 = new int[]{
            R.string.b1_mea9_1, R.string.b1_mea9_2, R.string.b1_mea9_3, R.string.b1_mea9_4, R.string.b1_mea9_5, R.string.b1_mea9_6, R.string.b1_mea9_7, R.string.b1_mea9_8, R.string.b1_mea9_9,
            R.string.b1_mea9_10, R.string.b1_mea9_11, R.string.b1_mea9_12, R.string.b1_mea9_13, R.string.b1_mea9_14, R.string.b1_mea9_15};
    private int[] draw10_1 = new int[]{
            R.string.b1_mea10_1, R.string.b1_mea10_2, R.string.b1_mea10_3, R.string.b1_mea10_4, R.string.b1_mea10_5, R.string.b1_mea10_6, R.string.b1_mea10_7, R.string.b1_mea10_8, R.string.b1_mea10_9,
            R.string.b1_mea10_10, R.string.b1_mea10_11, R.string.b1_mea10_12, R.string.b1_mea10_13, R.string.b1_mea10_14, R.string.b1_mea10_15};
    private int[] draw11_1 = new int[]{
            R.string.b1_mea11_1, R.string.b1_mea11_2, R.string.b1_mea11_3, R.string.b1_mea11_4, R.string.b1_mea11_5, R.string.b1_mea11_6, R.string.b1_mea11_7, R.string.b1_mea11_8, R.string.b1_mea11_9,
            R.string.b1_mea11_10, R.string.b1_mea11_11, R.string.b1_mea11_12, R.string.b1_mea11_13, R.string.b1_mea11_14, R.string.b1_mea11_15};
    private int[] draw12_1 = new int[]{
            R.string.b1_mea12_1, R.string.b1_mea12_2, R.string.b1_mea12_3, R.string.b1_mea12_4, R.string.b1_mea12_5, R.string.b1_mea12_6, R.string.b1_mea12_7, R.string.b1_mea12_8, R.string.b1_mea12_9,
            R.string.b1_mea12_10, R.string.b1_mea12_11, R.string.b1_mea12_12, R.string.b1_mea12_13, R.string.b1_mea12_14, R.string.b1_mea12_15};
    private int[] draw13_1 = new int[]{
            R.string.b1_mea13_1, R.string.b1_mea13_2, R.string.b1_mea13_3, R.string.b1_mea13_4, R.string.b1_mea13_5, R.string.b1_mea13_6, R.string.b1_mea13_7, R.string.b1_mea13_8, R.string.b1_mea13_9,
            R.string.b1_mea13_10, R.string.b1_mea13_11, R.string.b1_mea13_12, R.string.b1_mea13_13, R.string.b1_mea13_14, R.string.b1_mea13_15};
    private int[] draw14_1 = new int[]{
            R.string.b1_mea14_1, R.string.b1_mea14_2, R.string.b1_mea14_3, R.string.b1_mea14_4, R.string.b1_mea14_5, R.string.b1_mea14_6, R.string.b1_mea14_7, R.string.b1_mea14_8, R.string.b1_mea14_9,
            R.string.b1_mea14_10, R.string.b1_mea14_11, R.string.b1_mea14_12, R.string.b1_mea14_13, R.string.b1_mea14_14, R.string.b1_mea14_15};
    private int[] draw15_1 = new int[]{
            R.string.b1_mea15_1, R.string.b1_mea15_2, R.string.b1_mea15_3, R.string.b1_mea15_4, R.string.b1_mea15_5, R.string.b1_mea15_6, R.string.b1_mea15_7, R.string.b1_mea15_8, R.string.b1_mea15_9,
            R.string.b1_mea15_10, R.string.b1_mea15_11, R.string.b1_mea15_12, R.string.b1_mea15_13, R.string.b1_mea15_14, R.string.b1_mea15_15};
    private int[] draw16_1 = new int[]{
            R.string.b1_mea16_1, R.string.b1_mea16_2, R.string.b1_mea16_3, R.string.b1_mea16_4, R.string.b1_mea16_5, R.string.b1_mea16_6, R.string.b1_mea16_7, R.string.b1_mea16_8, R.string.b1_mea16_9,
            R.string.b1_mea16_10, R.string.b1_mea16_11, R.string.b1_mea16_12, R.string.b1_mea16_13, R.string.b1_mea16_14, R.string.b1_mea16_15};
    private int[] draw17_1 = new int[]{
            R.string.b1_mea17_1, R.string.b1_mea17_2, R.string.b1_mea17_3, R.string.b1_mea17_4, R.string.b1_mea17_5, R.string.b1_mea17_6, R.string.b1_mea17_7, R.string.b1_mea17_8, R.string.b1_mea17_9,
            R.string.b1_mea17_10, R.string.b1_mea17_11, R.string.b1_mea17_12, R.string.b1_mea17_13, R.string.b1_mea17_14, R.string.b1_mea17_15};
    private int[] draw18_1 = new int[]{
            R.string.b1_mea18_1, R.string.b1_mea18_2, R.string.b1_mea18_3, R.string.b1_mea18_4, R.string.b1_mea18_5, R.string.b1_mea18_6, R.string.b1_mea18_7, R.string.b1_mea18_8, R.string.b1_mea18_9,
            R.string.b1_mea18_10, R.string.b1_mea18_11, R.string.b1_mea18_12, R.string.b1_mea18_13, R.string.b1_mea18_14, R.string.b1_mea18_15};
    private int[] draw19_1 = new int[]{
            R.string.b1_mea19_1, R.string.b1_mea19_2, R.string.b1_mea19_3, R.string.b1_mea19_4, R.string.b1_mea19_5, R.string.b1_mea19_6, R.string.b1_mea19_7, R.string.b1_mea19_8, R.string.b1_mea19_9,
            R.string.b1_mea19_10, R.string.b1_mea19_11, R.string.b1_mea19_12, R.string.b1_mea19_13, R.string.b1_mea19_14, R.string.b1_mea19_15};
    private int[] draw20_1 = new int[]{
            R.string.b1_mea20_1, R.string.b1_mea20_2, R.string.b1_mea20_3, R.string.b1_mea20_4, R.string.b1_mea20_5, R.string.b1_mea20_6, R.string.b1_mea20_7, R.string.b1_mea20_8, R.string.b1_mea20_9,
            R.string.b1_mea20_10, R.string.b1_mea20_11, R.string.b1_mea20_12, R.string.b1_mea20_13, R.string.b1_mea20_14, R.string.b1_mea20_15};
    private int[] draw21_1 = new int[]{
            R.string.b1_mea21_1, R.string.b1_mea21_2, R.string.b1_mea21_3, R.string.b1_mea21_4, R.string.b1_mea21_5, R.string.b1_mea21_6, R.string.b1_mea21_7, R.string.b1_mea21_8, R.string.b1_mea21_9,
            R.string.b1_mea21_10, R.string.b1_mea21_11, R.string.b1_mea21_12, R.string.b1_mea21_13, R.string.b1_mea21_14, R.string.b1_mea21_15};
    private int[] draw22_1 = new int[]{
            R.string.b1_mea22_1, R.string.b1_mea22_2, R.string.b1_mea22_3, R.string.b1_mea22_4, R.string.b1_mea22_5, R.string.b1_mea22_6, R.string.b1_mea22_7, R.string.b1_mea22_8, R.string.b1_mea22_9,
            R.string.b1_mea22_10, R.string.b1_mea22_11, R.string.b1_mea22_12, R.string.b1_mea22_13, R.string.b1_mea22_14, R.string.b1_mea22_15};
    private int[] draw23_1 = new int[]{
            R.string.b1_mea23_1, R.string.b1_mea23_2, R.string.b1_mea23_3, R.string.b1_mea23_4, R.string.b1_mea23_5, R.string.b1_mea23_6, R.string.b1_mea23_7, R.string.b1_mea23_8, R.string.b1_mea23_9,
            R.string.b1_mea23_10, R.string.b1_mea23_11, R.string.b1_mea23_12, R.string.b1_mea23_13, R.string.b1_mea23_14, R.string.b1_mea23_15};
    private int[] draw24_1 = new int[]{
            R.string.b1_mea24_1, R.string.b1_mea24_2, R.string.b1_mea24_3, R.string.b1_mea24_4, R.string.b1_mea24_5, R.string.b1_mea24_6, R.string.b1_mea24_7, R.string.b1_mea24_8, R.string.b1_mea24_9,
            R.string.b1_mea24_10, R.string.b1_mea24_11, R.string.b1_mea24_12, R.string.b1_mea24_13, R.string.b1_mea24_14, R.string.b1_mea24_15};
    private int[] draw25_1 = new int[]{
            R.string.b1_mea25_1, R.string.b1_mea25_2, R.string.b1_mea25_3, R.string.b1_mea25_4, R.string.b1_mea25_5, R.string.b1_mea25_6, R.string.b1_mea25_7, R.string.b1_mea25_8, R.string.b1_mea25_9,
            R.string.b1_mea25_10, R.string.b1_mea25_11, R.string.b1_mea25_12, R.string.b1_mea25_13, R.string.b1_mea25_14, R.string.b1_mea25_15};
    private int[] draw26_1 = new int[]{
            R.string.b1_mea26_1, R.string.b1_mea26_2, R.string.b1_mea26_3, R.string.b1_mea26_4, R.string.b1_mea26_5, R.string.b1_mea26_6, R.string.b1_mea26_7, R.string.b1_mea26_8, R.string.b1_mea26_9,
            R.string.b1_mea26_10, R.string.b1_mea26_11, R.string.b1_mea26_12, R.string.b1_mea26_13, R.string.b1_mea26_14, R.string.b1_mea26_15};
    private int[] draw27_1 = new int[]{
            R.string.b1_mea27_1, R.string.b1_mea27_2, R.string.b1_mea27_3, R.string.b1_mea27_4, R.string.b1_mea27_5, R.string.b1_mea27_6, R.string.b1_mea27_7, R.string.b1_mea27_8, R.string.b1_mea27_9,
            R.string.b1_mea27_10, R.string.b1_mea27_11, R.string.b1_mea27_12, R.string.b1_mea27_13, R.string.b1_mea27_14, R.string.b1_mea27_15};
    private int[] draw28_1 = new int[]{
            R.string.b1_mea28_1, R.string.b1_mea28_2, R.string.b1_mea28_3, R.string.b1_mea28_4, R.string.b1_mea28_5, R.string.b1_mea28_6, R.string.b1_mea28_7, R.string.b1_mea28_8, R.string.b1_mea28_9,
            R.string.b1_mea28_10, R.string.b1_mea28_11, R.string.b1_mea28_12, R.string.b1_mea28_13, R.string.b1_mea28_14, R.string.b1_mea28_15};
    private int[] draw29_1 = new int[]{
            R.string.b1_mea29_1, R.string.b1_mea29_2, R.string.b1_mea29_3, R.string.b1_mea29_4, R.string.b1_mea29_5, R.string.b1_mea29_6, R.string.b1_mea29_7, R.string.b1_mea29_8, R.string.b1_mea29_9,
            R.string.b1_mea29_10, R.string.b1_mea29_11, R.string.b1_mea29_12, R.string.b1_mea29_13, R.string.b1_mea29_14, R.string.b1_mea29_15};
    private int[] draw30_1 = new int[]{
            R.string.b1_mea30_1, R.string.b1_mea30_2, R.string.b1_mea30_3, R.string.b1_mea30_4, R.string.b1_mea30_5, R.string.b1_mea30_6, R.string.b1_mea30_7, R.string.b1_mea30_8, R.string.b1_mea30_9,
            R.string.b1_mea30_10, R.string.b1_mea30_11, R.string.b1_mea30_12, R.string.b1_mea30_13, R.string.b1_mea30_14, R.string.b1_mea30_15};
    private int[] draw31_1 = new int[]{
            R.string.b1_mea31_1, R.string.b1_mea31_2, R.string.b1_mea31_3, R.string.b1_mea31_4, R.string.b1_mea31_5, R.string.b1_mea31_6, R.string.b1_mea31_7, R.string.b1_mea31_8, R.string.b1_mea31_9,
            R.string.b1_mea31_10, R.string.b1_mea31_11, R.string.b1_mea31_12, R.string.b1_mea31_13, R.string.b1_mea31_14, R.string.b1_mea31_15};
    private int[] draw32_1 = new int[]{
            R.string.b1_mea32_1, R.string.b1_mea32_2, R.string.b1_mea32_3, R.string.b1_mea32_4, R.string.b1_mea32_5, R.string.b1_mea32_6, R.string.b1_mea32_7, R.string.b1_mea32_8, R.string.b1_mea32_9,
            R.string.b1_mea32_10, R.string.b1_mea32_11, R.string.b1_mea32_12, R.string.b1_mea32_13, R.string.b1_mea32_14, R.string.b1_mea32_15};
    private int[] draw33_1 = new int[]{
            R.string.b1_mea33_1, R.string.b1_mea33_2, R.string.b1_mea33_3, R.string.b1_mea33_4, R.string.b1_mea33_5, R.string.b1_mea33_6, R.string.b1_mea33_7, R.string.b1_mea33_8, R.string.b1_mea33_9,
            R.string.b1_mea33_10, R.string.b1_mea33_11, R.string.b1_mea33_12, R.string.b1_mea33_13, R.string.b1_mea33_14, R.string.b1_mea33_15};
    private int[] draw34_1 = new int[]{
            R.string.b1_mea34_1, R.string.b1_mea34_2, R.string.b1_mea34_3, R.string.b1_mea34_4, R.string.b1_mea34_5, R.string.b1_mea34_6, R.string.b1_mea34_7, R.string.b1_mea34_8, R.string.b1_mea34_9,
            R.string.b1_mea34_10, R.string.b1_mea34_11, R.string.b1_mea34_12, R.string.b1_mea34_13, R.string.b1_mea34_14, R.string.b1_mea34_15};
    private int[] draw35_1 = new int[]{
            R.string.b1_mea35_1, R.string.b1_mea35_2, R.string.b1_mea35_3, R.string.b1_mea35_4, R.string.b1_mea35_5, R.string.b1_mea35_6, R.string.b1_mea35_7, R.string.b1_mea35_8, R.string.b1_mea35_9,
            R.string.b1_mea35_10, R.string.b1_mea35_11, R.string.b1_mea35_12, R.string.b1_mea35_13, R.string.b1_mea35_14, R.string.b1_mea35_15};
    private int[] draw36_1 = new int[]{
            R.string.b1_mea36_1, R.string.b1_mea36_2, R.string.b1_mea36_3, R.string.b1_mea36_4, R.string.b1_mea36_5, R.string.b1_mea36_6, R.string.b1_mea36_7, R.string.b1_mea36_8, R.string.b1_mea36_9,
            R.string.b1_mea36_10, R.string.b1_mea36_11, R.string.b1_mea36_12, R.string.b1_mea36_13, R.string.b1_mea36_14, R.string.b1_mea36_15};
    private int[] draw37_1 = new int[]{
            R.string.b1_mea37_1, R.string.b1_mea37_2, R.string.b1_mea37_3, R.string.b1_mea37_4, R.string.b1_mea37_5, R.string.b1_mea37_6, R.string.b1_mea37_7, R.string.b1_mea37_8, R.string.b1_mea37_9,
            R.string.b1_mea37_10, R.string.b1_mea37_11, R.string.b1_mea37_12, R.string.b1_mea37_13, R.string.b1_mea37_14, R.string.b1_mea37_15};
    private int[] draw38_1 = new int[]{
            R.string.b1_mea38_1, R.string.b1_mea38_2, R.string.b1_mea38_3, R.string.b1_mea38_4, R.string.b1_mea38_5, R.string.b1_mea38_6, R.string.b1_mea38_7, R.string.b1_mea38_8, R.string.b1_mea38_9,
            R.string.b1_mea38_10, R.string.b1_mea38_11, R.string.b1_mea38_12, R.string.b1_mea38_13, R.string.b1_mea38_14, R.string.b1_mea38_15};
    private int[] draw39_1 = new int[]{
            R.string.b1_mea39_1, R.string.b1_mea39_2, R.string.b1_mea39_3, R.string.b1_mea39_4, R.string.b1_mea39_5, R.string.b1_mea39_6, R.string.b1_mea39_7, R.string.b1_mea39_8, R.string.b1_mea39_9,
            R.string.b1_mea39_10, R.string.b1_mea39_11, R.string.b1_mea39_12, R.string.b1_mea39_13, R.string.b1_mea39_14, R.string.b1_mea39_15};
    private int[] draw40_1 = new int[]{
            R.string.b1_mea40_1, R.string.b1_mea40_2, R.string.b1_mea40_3, R.string.b1_mea40_4, R.string.b1_mea40_5, R.string.b1_mea40_6, R.string.b1_mea40_7, R.string.b1_mea40_8, R.string.b1_mea40_9,
            R.string.b1_mea40_10, R.string.b1_mea40_11, R.string.b1_mea40_12, R.string.b1_mea40_13, R.string.b1_mea40_14, R.string.b1_mea40_15};
    private int[] draw41_1 = new int[]{
            R.string.b1_mea41_1, R.string.b1_mea41_2, R.string.b1_mea41_3, R.string.b1_mea41_4, R.string.b1_mea41_5, R.string.b1_mea41_6, R.string.b1_mea41_7, R.string.b1_mea41_8, R.string.b1_mea41_9,
            R.string.b1_mea41_10, R.string.b1_mea41_11, R.string.b1_mea41_12, R.string.b1_mea41_13, R.string.b1_mea41_14, R.string.b1_mea41_15};
    private int[] draw42_1 = new int[]{
            R.string.b1_mea42_1, R.string.b1_mea42_2, R.string.b1_mea42_3, R.string.b1_mea42_4, R.string.b1_mea42_5, R.string.b1_mea42_6, R.string.b1_mea42_7, R.string.b1_mea42_8, R.string.b1_mea42_9,
            R.string.b1_mea42_10, R.string.b1_mea42_11, R.string.b1_mea42_12, R.string.b1_mea42_13, R.string.b1_mea42_14, R.string.b1_mea42_15};
    private int[] draw43_1 = new int[]{
            R.string.b1_mea43_1, R.string.b1_mea43_2, R.string.b1_mea43_3, R.string.b1_mea43_4, R.string.b1_mea43_5, R.string.b1_mea43_6, R.string.b1_mea43_7, R.string.b1_mea43_8, R.string.b1_mea43_9,
            R.string.b1_mea43_10, R.string.b1_mea43_11, R.string.b1_mea43_12, R.string.b1_mea43_13, R.string.b1_mea43_14, R.string.b1_mea43_15};
    private int[] draw44_1 = new int[]{
            R.string.b1_mea44_1, R.string.b1_mea44_2, R.string.b1_mea44_3, R.string.b1_mea44_4, R.string.b1_mea44_5, R.string.b1_mea44_6, R.string.b1_mea44_7, R.string.b1_mea44_8, R.string.b1_mea44_9,
            R.string.b1_mea44_10, R.string.b1_mea44_11, R.string.b1_mea44_12, R.string.b1_mea44_13, R.string.b1_mea44_14, R.string.b1_mea44_15};
    private int[] draw45_1 = new int[]{
            R.string.b1_mea45_1, R.string.b1_mea45_2, R.string.b1_mea45_3, R.string.b1_mea45_4, R.string.b1_mea45_5, R.string.b1_mea45_6, R.string.b1_mea45_7, R.string.b1_mea45_8, R.string.b1_mea45_9,
            R.string.b1_mea45_10, R.string.b1_mea45_11, R.string.b1_mea45_12, R.string.b1_mea45_13, R.string.b1_mea45_14, R.string.b1_mea45_15};
    private int[] draw46_1 = new int[]{
            R.string.b1_mea46_1, R.string.b1_mea46_2, R.string.b1_mea46_3, R.string.b1_mea46_4, R.string.b1_mea46_5, R.string.b1_mea46_6, R.string.b1_mea46_7, R.string.b1_mea46_8, R.string.b1_mea46_9,
            R.string.b1_mea46_10, R.string.b1_mea46_11, R.string.b1_mea46_12, R.string.b1_mea46_13, R.string.b1_mea46_14, R.string.b1_mea46_15};
    private int[] draw47_1 = new int[]{
            R.string.b1_mea47_1, R.string.b1_mea47_2, R.string.b1_mea47_3, R.string.b1_mea47_4, R.string.b1_mea47_5, R.string.b1_mea47_6, R.string.b1_mea47_7, R.string.b1_mea47_8, R.string.b1_mea47_9,
            R.string.b1_mea47_10, R.string.b1_mea47_11, R.string.b1_mea47_12, R.string.b1_mea47_13, R.string.b1_mea47_14, R.string.b1_mea47_15};
    private int[] draw48_1 = new int[]{
            R.string.b1_mea48_1, R.string.b1_mea48_2, R.string.b1_mea48_3, R.string.b1_mea48_4, R.string.b1_mea48_5, R.string.b1_mea48_6, R.string.b1_mea48_7, R.string.b1_mea48_8, R.string.b1_mea48_9,
            R.string.b1_mea48_10, R.string.b1_mea48_11, R.string.b1_mea48_12, R.string.b1_mea48_13, R.string.b1_mea48_14, R.string.b1_mea48_15};
    private int[] draw49_1 = new int[]{
            R.string.b1_mea49_1, R.string.b1_mea49_2, R.string.b1_mea49_3, R.string.b1_mea49_4, R.string.b1_mea49_5, R.string.b1_mea49_6, R.string.b1_mea49_7, R.string.b1_mea49_8, R.string.b1_mea49_9,
            R.string.b1_mea49_10, R.string.b1_mea49_11, R.string.b1_mea49_12, R.string.b1_mea49_13, R.string.b1_mea49_14, R.string.b1_mea49_15};
    private int[] draw50_1 = new int[]{
            R.string.b1_mea50_1, R.string.b1_mea50_2, R.string.b1_mea50_3, R.string.b1_mea50_4, R.string.b1_mea50_5, R.string.b1_mea50_6, R.string.b1_mea50_7, R.string.b1_mea50_8, R.string.b1_mea50_9,
            R.string.b1_mea50_10, R.string.b1_mea50_11, R.string.b1_mea50_12, R.string.b1_mea50_13, R.string.b1_mea50_14, R.string.b1_mea50_15};


    private int[] draw0_2 = new int[]{
            R.string.a2_mea0_1, R.string.a2_mea0_2, R.string.a2_mea0_3, R.string.a2_mea0_4, R.string.a2_mea0_5, R.string.a2_mea0_6, R.string.a2_mea0_7, R.string.a2_mea0_8, R.string.a2_mea0_9,
            R.string.a2_mea0_10, R.string.a2_mea0_11, R.string.a2_mea0_12, R.string.a2_mea0_13, R.string.a2_mea0_14, R.string.a2_mea0_15};
    private int[] draw1_2 = new int[]{
            R.string.a2_mea1_1, R.string.a2_mea1_2, R.string.a2_mea1_3, R.string.a2_mea1_4, R.string.a2_mea1_5, R.string.a2_mea1_6, R.string.a2_mea1_7, R.string.a2_mea1_8, R.string.a2_mea1_9,
            R.string.a2_mea1_10, R.string.a2_mea1_11, R.string.a2_mea1_12, R.string.a2_mea1_13, R.string.a2_mea1_14, R.string.a2_mea1_15};
    private int[] draw2_2 = new int[]{
            R.string.a2_mea2_1, R.string.a2_mea2_2, R.string.a2_mea2_3, R.string.a2_mea2_4, R.string.a2_mea2_5, R.string.a2_mea2_6, R.string.a2_mea2_7, R.string.a2_mea2_8, R.string.a2_mea2_9,
            R.string.a2_mea2_10, R.string.a2_mea2_11, R.string.a2_mea2_12, R.string.a2_mea2_13, R.string.a2_mea2_14, R.string.a2_mea2_15};
    private int[] draw3_2 = new int[]{
            R.string.a2_mea3_1, R.string.a2_mea3_2, R.string.a2_mea3_3, R.string.a2_mea3_4, R.string.a2_mea3_5, R.string.a2_mea3_6, R.string.a2_mea3_7, R.string.a2_mea3_8, R.string.a2_mea3_9,
            R.string.a2_mea3_10, R.string.a2_mea3_11, R.string.a2_mea3_12, R.string.a2_mea3_13, R.string.a2_mea3_14, R.string.a2_mea3_15};
    private int[] draw4_2 = new int[]{
            R.string.a2_mea4_1, R.string.a2_mea4_2, R.string.a2_mea4_3, R.string.a2_mea4_4, R.string.a2_mea4_5, R.string.a2_mea4_6, R.string.a2_mea4_7, R.string.a2_mea4_8, R.string.a2_mea4_9,
            R.string.a2_mea4_10, R.string.a2_mea4_11, R.string.a2_mea4_12, R.string.a2_mea4_13, R.string.a2_mea4_14, R.string.a2_mea4_15};
    private int[] draw5_2 = new int[]{
            R.string.a2_mea5_1, R.string.a2_mea5_2, R.string.a2_mea5_3, R.string.a2_mea5_4, R.string.a2_mea5_5, R.string.a2_mea5_6, R.string.a2_mea5_7, R.string.a2_mea5_8, R.string.a2_mea5_9,
            R.string.a2_mea5_10, R.string.a2_mea5_11, R.string.a2_mea5_12, R.string.a2_mea5_13, R.string.a2_mea5_14, R.string.a2_mea5_15};
    private int[] draw6_2 = new int[]{
            R.string.a2_mea6_1, R.string.a2_mea6_2, R.string.a2_mea6_3, R.string.a2_mea6_4, R.string.a2_mea6_5, R.string.a2_mea6_6, R.string.a2_mea6_7, R.string.a2_mea6_8, R.string.a2_mea6_9,
            R.string.a2_mea6_10, R.string.a2_mea6_11, R.string.a2_mea6_12, R.string.a2_mea6_13, R.string.a2_mea6_14, R.string.a2_mea6_15};
    private int[] draw7_2 = new int[]{
            R.string.a2_mea7_1, R.string.a2_mea7_2, R.string.a2_mea7_3, R.string.a2_mea7_4, R.string.a2_mea7_5, R.string.a2_mea7_6, R.string.a2_mea7_7, R.string.a2_mea7_8, R.string.a2_mea7_9,
            R.string.a2_mea7_10, R.string.a2_mea7_11, R.string.a2_mea7_12, R.string.a2_mea7_13, R.string.a2_mea7_14, R.string.a2_mea7_15};
    private int[] draw8_2 = new int[]{
            R.string.a2_mea8_1, R.string.a2_mea8_2, R.string.a2_mea8_3, R.string.a2_mea8_4, R.string.a2_mea8_5, R.string.a2_mea8_6, R.string.a2_mea8_7, R.string.a2_mea8_8, R.string.a2_mea8_9,
            R.string.a2_mea8_10, R.string.a2_mea8_11, R.string.a2_mea8_12, R.string.a2_mea8_13, R.string.a2_mea8_14, R.string.a2_mea8_15};
    private int[] draw9_2 = new int[]{
            R.string.a2_mea9_1, R.string.a2_mea9_2, R.string.a2_mea9_3, R.string.a2_mea9_4, R.string.a2_mea9_5, R.string.a2_mea9_6, R.string.a2_mea9_7, R.string.a2_mea9_8, R.string.a2_mea9_9,
            R.string.a2_mea9_10, R.string.a2_mea9_11, R.string.a2_mea9_12, R.string.a2_mea9_13, R.string.a2_mea9_14, R.string.a2_mea9_15};
    private int[] draw10_2 = new int[]{
            R.string.a2_mea10_1, R.string.a2_mea10_2, R.string.a2_mea10_3, R.string.a2_mea10_4, R.string.a2_mea10_5, R.string.a2_mea10_6, R.string.a2_mea10_7, R.string.a2_mea10_8, R.string.a2_mea10_9,
            R.string.a2_mea10_10, R.string.a2_mea10_11, R.string.a2_mea10_12, R.string.a2_mea10_13, R.string.a2_mea10_14, R.string.a2_mea10_15};
    private int[] draw11_2 = new int[]{
            R.string.a2_mea11_1, R.string.a2_mea11_2, R.string.a2_mea11_3, R.string.a2_mea11_4, R.string.a2_mea11_5, R.string.a2_mea11_6, R.string.a2_mea11_7, R.string.a2_mea11_8, R.string.a2_mea11_9,
            R.string.a2_mea11_10, R.string.a2_mea11_11, R.string.a2_mea11_12, R.string.a2_mea11_13, R.string.a2_mea11_14, R.string.a2_mea11_15};
    private int[] draw12_2 = new int[]{
            R.string.a2_mea12_1, R.string.a2_mea12_2, R.string.a2_mea12_3, R.string.a2_mea12_4, R.string.a2_mea12_5, R.string.a2_mea12_6, R.string.a2_mea12_7, R.string.a2_mea12_8, R.string.a2_mea12_9,
            R.string.a2_mea12_10, R.string.a2_mea12_11, R.string.a2_mea12_12, R.string.a2_mea12_13, R.string.a2_mea12_14, R.string.a2_mea12_15};
    private int[] draw13_2 = new int[]{
            R.string.a2_mea13_1, R.string.a2_mea13_2, R.string.a2_mea13_3, R.string.a2_mea13_4, R.string.a2_mea13_5, R.string.a2_mea13_6, R.string.a2_mea13_7, R.string.a2_mea13_8, R.string.a2_mea13_9,
            R.string.a2_mea13_10, R.string.a2_mea13_11, R.string.a2_mea13_12, R.string.a2_mea13_13, R.string.a2_mea13_14, R.string.a2_mea13_15};
    private int[] draw14_2 = new int[]{
            R.string.a2_mea14_1, R.string.a2_mea14_2, R.string.a2_mea14_3, R.string.a2_mea14_4, R.string.a2_mea14_5, R.string.a2_mea14_6, R.string.a2_mea14_7, R.string.a2_mea14_8, R.string.a2_mea14_9,
            R.string.a2_mea14_10, R.string.a2_mea14_11, R.string.a2_mea14_12, R.string.a2_mea14_13, R.string.a2_mea14_14, R.string.a2_mea14_15};
    private int[] draw15_2 = new int[]{
            R.string.a2_mea15_1, R.string.a2_mea15_2, R.string.a2_mea15_3, R.string.a2_mea15_4, R.string.a2_mea15_5, R.string.a2_mea15_6, R.string.a2_mea15_7, R.string.a2_mea15_8, R.string.a2_mea15_9,
            R.string.a2_mea15_10, R.string.a2_mea15_11, R.string.a2_mea15_12, R.string.a2_mea15_13, R.string.a2_mea15_14, R.string.a2_mea15_15};
    private int[] draw16_2 = new int[]{
            R.string.a2_mea16_1, R.string.a2_mea16_2, R.string.a2_mea16_3, R.string.a2_mea16_4, R.string.a2_mea16_5, R.string.a2_mea16_6, R.string.a2_mea16_7, R.string.a2_mea16_8, R.string.a2_mea16_9,
            R.string.a2_mea16_10, R.string.a2_mea16_11, R.string.a2_mea16_12, R.string.a2_mea16_13, R.string.a2_mea16_14, R.string.a2_mea16_15};
    private int[] draw17_2 = new int[]{
            R.string.a2_mea17_1, R.string.a2_mea17_2, R.string.a2_mea17_3, R.string.a2_mea17_4, R.string.a2_mea17_5, R.string.a2_mea17_6, R.string.a2_mea17_7, R.string.a2_mea17_8, R.string.a2_mea17_9,
            R.string.a2_mea17_10, R.string.a2_mea17_11, R.string.a2_mea17_12, R.string.a2_mea17_13, R.string.a2_mea17_14, R.string.a2_mea17_15};
    private int[] draw18_2 = new int[]{
            R.string.a2_mea18_1, R.string.a2_mea18_2, R.string.a2_mea18_3, R.string.a2_mea18_4, R.string.a2_mea18_5, R.string.a2_mea18_6, R.string.a2_mea18_7, R.string.a2_mea18_8, R.string.a2_mea18_9,
            R.string.a2_mea18_10, R.string.a2_mea18_11, R.string.a2_mea18_12, R.string.a2_mea18_13, R.string.a2_mea18_14, R.string.a2_mea18_15};
    private int[] draw19_2 = new int[]{
            R.string.a2_mea19_1, R.string.a2_mea19_2, R.string.a2_mea19_3, R.string.a2_mea19_4, R.string.a2_mea19_5, R.string.a2_mea19_6, R.string.a2_mea19_7, R.string.a2_mea19_8, R.string.a2_mea19_9,
            R.string.a2_mea19_10, R.string.a2_mea19_11, R.string.a2_mea19_12, R.string.a2_mea19_13, R.string.a2_mea19_14, R.string.a2_mea19_15};
    private int[] draw20_2 = new int[]{
            R.string.a2_mea20_1, R.string.a2_mea20_2, R.string.a2_mea20_3, R.string.a2_mea20_4, R.string.a2_mea20_5, R.string.a2_mea20_6, R.string.a2_mea20_7, R.string.a2_mea20_8, R.string.a2_mea20_9,
            R.string.a2_mea20_10, R.string.a2_mea20_11, R.string.a2_mea20_12, R.string.a2_mea20_13, R.string.a2_mea20_14, R.string.a2_mea20_15};
    private int[] draw21_2 = new int[]{
            R.string.a2_mea21_1, R.string.a2_mea21_2, R.string.a2_mea21_3, R.string.a2_mea21_4, R.string.a2_mea21_5, R.string.a2_mea21_6, R.string.a2_mea21_7, R.string.a2_mea21_8, R.string.a2_mea21_9,
            R.string.a2_mea21_10, R.string.a2_mea21_11, R.string.a2_mea21_12, R.string.a2_mea21_13, R.string.a2_mea21_14, R.string.a2_mea21_15};
    private int[] draw22_2 = new int[]{
            R.string.a2_mea22_1, R.string.a2_mea22_2, R.string.a2_mea22_3, R.string.a2_mea22_4, R.string.a2_mea22_5, R.string.a2_mea22_6, R.string.a2_mea22_7, R.string.a2_mea22_8, R.string.a2_mea22_9,
            R.string.a2_mea22_10, R.string.a2_mea22_11, R.string.a2_mea22_12, R.string.a2_mea22_13, R.string.a2_mea22_14, R.string.a2_mea22_15};
    private int[] draw23_2 = new int[]{
            R.string.a2_mea23_1, R.string.a2_mea23_2, R.string.a2_mea23_3, R.string.a2_mea23_4, R.string.a2_mea23_5, R.string.a2_mea23_6, R.string.a2_mea23_7, R.string.a2_mea23_8, R.string.a2_mea23_9,
            R.string.a2_mea23_10, R.string.a2_mea23_11, R.string.a2_mea23_12, R.string.a2_mea23_13, R.string.a2_mea23_14, R.string.a2_mea23_15};
    private int[] draw24_2 = new int[]{
            R.string.a2_mea24_1, R.string.a2_mea24_2, R.string.a2_mea24_3, R.string.a2_mea24_4, R.string.a2_mea24_5, R.string.a2_mea24_6, R.string.a2_mea24_7, R.string.a2_mea24_8, R.string.a2_mea24_9,
            R.string.a2_mea24_10, R.string.a2_mea24_11, R.string.a2_mea24_12, R.string.a2_mea24_13, R.string.a2_mea24_14, R.string.a2_mea24_15};
    private int[] draw25_2 = new int[]{
            R.string.a2_mea25_1, R.string.a2_mea25_2, R.string.a2_mea25_3, R.string.a2_mea25_4, R.string.a2_mea25_5, R.string.a2_mea25_6, R.string.a2_mea25_7, R.string.a2_mea25_8, R.string.a2_mea25_9,
            R.string.a2_mea25_10, R.string.a2_mea25_11, R.string.a2_mea25_12, R.string.a2_mea25_13, R.string.a2_mea25_14, R.string.a2_mea25_15};
    private int[] draw26_2 = new int[]{
            R.string.a2_mea26_1, R.string.a2_mea26_2, R.string.a2_mea26_3, R.string.a2_mea26_4, R.string.a2_mea26_5, R.string.a2_mea26_6, R.string.a2_mea26_7, R.string.a2_mea26_8, R.string.a2_mea26_9,
            R.string.a2_mea26_10, R.string.a2_mea26_11, R.string.a2_mea26_12, R.string.a2_mea26_13, R.string.a2_mea26_14, R.string.a2_mea26_15};
    private int[] draw27_2 = new int[]{
            R.string.a2_mea27_1, R.string.a2_mea27_2, R.string.a2_mea27_3, R.string.a2_mea27_4, R.string.a2_mea27_5, R.string.a2_mea27_6, R.string.a2_mea27_7, R.string.a2_mea27_8, R.string.a2_mea27_9,
            R.string.a2_mea27_10, R.string.a2_mea27_11, R.string.a2_mea27_12, R.string.a2_mea27_13, R.string.a2_mea27_14, R.string.a2_mea27_15};
    private int[] draw28_2 = new int[]{
            R.string.a2_mea28_1, R.string.a2_mea28_2, R.string.a2_mea28_3, R.string.a2_mea28_4, R.string.a2_mea28_5, R.string.a2_mea28_6, R.string.a2_mea28_7, R.string.a2_mea28_8, R.string.a2_mea28_9,
            R.string.a2_mea28_10, R.string.a2_mea28_11, R.string.a2_mea28_12, R.string.a2_mea28_13, R.string.a2_mea28_14, R.string.a2_mea28_15};
    private int[] draw29_2 = new int[]{
            R.string.a2_mea29_1, R.string.a2_mea29_2, R.string.a2_mea29_3, R.string.a2_mea29_4, R.string.a2_mea29_5, R.string.a2_mea29_6, R.string.a2_mea29_7, R.string.a2_mea29_8, R.string.a2_mea29_9,
            R.string.a2_mea29_10, R.string.a2_mea29_11, R.string.a2_mea29_12, R.string.a2_mea29_13, R.string.a2_mea29_14, R.string.a2_mea29_15};
    private int[] draw30_2 = new int[]{
            R.string.a2_mea30_1, R.string.a2_mea30_2, R.string.a2_mea30_3, R.string.a2_mea30_4, R.string.a2_mea30_5, R.string.a2_mea30_6, R.string.a2_mea30_7, R.string.a2_mea30_8, R.string.a2_mea30_9,
            R.string.a2_mea30_10, R.string.a2_mea30_11, R.string.a2_mea30_12, R.string.a2_mea30_13, R.string.a2_mea30_14, R.string.a2_mea30_15};
    private int[] draw31_2 = new int[]{
            R.string.a2_mea31_1, R.string.a2_mea31_2, R.string.a2_mea31_3, R.string.a2_mea31_4, R.string.a2_mea31_5, R.string.a2_mea31_6, R.string.a2_mea31_7, R.string.a2_mea31_8, R.string.a2_mea31_9,
            R.string.a2_mea31_10, R.string.a2_mea31_11, R.string.a2_mea31_12, R.string.a2_mea31_13, R.string.a2_mea31_14, R.string.a2_mea31_15};
    private int[] draw32_2 = new int[]{
            R.string.a2_mea32_1, R.string.a2_mea32_2, R.string.a2_mea32_3, R.string.a2_mea32_4, R.string.a2_mea32_5, R.string.a2_mea32_6, R.string.a2_mea32_7, R.string.a2_mea32_8, R.string.a2_mea32_9,
            R.string.a2_mea32_10, R.string.a2_mea32_11, R.string.a2_mea32_12, R.string.a2_mea32_13, R.string.a2_mea32_14, R.string.a2_mea32_15};
    private int[] draw33_2 = new int[]{
            R.string.a2_mea33_1, R.string.a2_mea33_2, R.string.a2_mea33_3, R.string.a2_mea33_4, R.string.a2_mea33_5, R.string.a2_mea33_6, R.string.a2_mea33_7, R.string.a2_mea33_8, R.string.a2_mea33_9,
            R.string.a2_mea33_10, R.string.a2_mea33_11, R.string.a2_mea33_12, R.string.a2_mea33_13, R.string.a2_mea33_14, R.string.a2_mea33_15};
    private int[] draw34_2 = new int[]{
            R.string.a2_mea34_1, R.string.a2_mea34_2, R.string.a2_mea34_3, R.string.a2_mea34_4, R.string.a2_mea34_5, R.string.a2_mea34_6, R.string.a2_mea34_7, R.string.a2_mea34_8, R.string.a2_mea34_9,
            R.string.a2_mea34_10, R.string.a2_mea34_11, R.string.a2_mea34_12, R.string.a2_mea34_13, R.string.a2_mea34_14, R.string.a2_mea34_15};
    private int[] draw35_2 = new int[]{
            R.string.a2_mea35_1, R.string.a2_mea35_2, R.string.a2_mea35_3, R.string.a2_mea35_4, R.string.a2_mea35_5, R.string.a2_mea35_6, R.string.a2_mea35_7, R.string.a2_mea35_8, R.string.a2_mea35_9,
            R.string.a2_mea35_10, R.string.a2_mea35_11, R.string.a2_mea35_12, R.string.a2_mea35_13, R.string.a2_mea35_14, R.string.a2_mea35_15};
    private int[] draw36_2 = new int[]{
            R.string.a2_mea36_1, R.string.a2_mea36_2, R.string.a2_mea36_3, R.string.a2_mea36_4, R.string.a2_mea36_5, R.string.a2_mea36_6, R.string.a2_mea36_7, R.string.a2_mea36_8, R.string.a2_mea36_9,
            R.string.a2_mea36_10, R.string.a2_mea36_11, R.string.a2_mea36_12, R.string.a2_mea36_13, R.string.a2_mea36_14, R.string.a2_mea36_15};
    private int[] draw37_2 = new int[]{
            R.string.a2_mea37_1, R.string.a2_mea37_2, R.string.a2_mea37_3, R.string.a2_mea37_4, R.string.a2_mea37_5, R.string.a2_mea37_6, R.string.a2_mea37_7, R.string.a2_mea37_8, R.string.a2_mea37_9,
            R.string.a2_mea37_10, R.string.a2_mea37_11, R.string.a2_mea37_12, R.string.a2_mea37_13, R.string.a2_mea37_14, R.string.a2_mea37_15};
    private int[] draw38_2 = new int[]{
            R.string.a2_mea38_1, R.string.a2_mea38_2, R.string.a2_mea38_3, R.string.a2_mea38_4, R.string.a2_mea38_5, R.string.a2_mea38_6, R.string.a2_mea38_7, R.string.a2_mea38_8, R.string.a2_mea38_9,
            R.string.a2_mea38_10, R.string.a2_mea38_11, R.string.a2_mea38_12, R.string.a2_mea38_13, R.string.a2_mea38_14, R.string.a2_mea38_15};
    private int[] draw39_2 = new int[]{
            R.string.a2_mea39_1, R.string.a2_mea39_2, R.string.a2_mea39_3, R.string.a2_mea39_4, R.string.a2_mea39_5, R.string.a2_mea39_6, R.string.a2_mea39_7, R.string.a2_mea39_8, R.string.a2_mea39_9,
            R.string.a2_mea39_10, R.string.a2_mea39_11, R.string.a2_mea39_12, R.string.a2_mea39_13, R.string.a2_mea39_14, R.string.a2_mea39_15};
    private int[] draw40_2 = new int[]{
            R.string.a2_mea40_1, R.string.a2_mea40_2, R.string.a2_mea40_3, R.string.a2_mea40_4, R.string.a2_mea40_5, R.string.a2_mea40_6, R.string.a2_mea40_7, R.string.a2_mea40_8, R.string.a2_mea40_9,
            R.string.a2_mea40_10, R.string.a2_mea40_11, R.string.a2_mea40_12, R.string.a2_mea40_13, R.string.a2_mea40_14, R.string.a2_mea40_15};
    private int[] draw41_2 = new int[]{
            R.string.a2_mea41_1, R.string.a2_mea41_2, R.string.a2_mea41_3, R.string.a2_mea41_4, R.string.a2_mea41_5, R.string.a2_mea41_6, R.string.a2_mea41_7, R.string.a2_mea41_8, R.string.a2_mea41_9,
            R.string.a2_mea41_10, R.string.a2_mea41_11, R.string.a2_mea41_12, R.string.a2_mea41_13, R.string.a2_mea41_14, R.string.a2_mea41_15};
    private int[] draw42_2 = new int[]{
            R.string.a2_mea42_1, R.string.a2_mea42_2, R.string.a2_mea42_3, R.string.a2_mea42_4, R.string.a2_mea42_5, R.string.a2_mea42_6, R.string.a2_mea42_7, R.string.a2_mea42_8, R.string.a2_mea42_9,
            R.string.a2_mea42_10, R.string.a2_mea42_11, R.string.a2_mea42_12, R.string.a2_mea42_13, R.string.a2_mea42_14, R.string.a2_mea42_15};
    private int[] draw43_2 = new int[]{
            R.string.a2_mea43_1, R.string.a2_mea43_2, R.string.a2_mea43_3, R.string.a2_mea43_4, R.string.a2_mea43_5, R.string.a2_mea43_6, R.string.a2_mea43_7, R.string.a2_mea43_8, R.string.a2_mea43_9,
            R.string.a2_mea43_10, R.string.a2_mea43_11, R.string.a2_mea43_12, R.string.a2_mea43_13, R.string.a2_mea43_14, R.string.a2_mea43_15};
    private int[] draw44_2 = new int[]{
            R.string.a2_mea44_1, R.string.a2_mea44_2, R.string.a2_mea44_3, R.string.a2_mea44_4, R.string.a2_mea44_5, R.string.a2_mea44_6, R.string.a2_mea44_7, R.string.a2_mea44_8, R.string.a2_mea44_9,
            R.string.a2_mea44_10, R.string.a2_mea44_11, R.string.a2_mea44_12, R.string.a2_mea44_13, R.string.a2_mea44_14, R.string.a2_mea44_15};
    private int[] draw45_2 = new int[]{
            R.string.a2_mea45_1, R.string.a2_mea45_2, R.string.a2_mea45_3, R.string.a2_mea45_4, R.string.a2_mea45_5, R.string.a2_mea45_6, R.string.a2_mea45_7, R.string.a2_mea45_8, R.string.a2_mea45_9,
            R.string.a2_mea45_10, R.string.a2_mea45_11, R.string.a2_mea45_12, R.string.a2_mea45_13, R.string.a2_mea45_14, R.string.a2_mea45_15};
    private int[] draw46_2 = new int[]{
            R.string.a2_mea46_1, R.string.a2_mea46_2, R.string.a2_mea46_3, R.string.a2_mea46_4, R.string.a2_mea46_5, R.string.a2_mea46_6, R.string.a2_mea46_7, R.string.a2_mea46_8, R.string.a2_mea46_9,
            R.string.a2_mea46_10, R.string.a2_mea46_11, R.string.a2_mea46_12, R.string.a2_mea46_13, R.string.a2_mea46_14, R.string.a2_mea46_15};
    private int[] draw47_2 = new int[]{
            R.string.a2_mea47_1, R.string.a2_mea47_2, R.string.a2_mea47_3, R.string.a2_mea47_4, R.string.a2_mea47_5, R.string.a2_mea47_6, R.string.a2_mea47_7, R.string.a2_mea47_8, R.string.a2_mea47_9,
            R.string.a2_mea47_10, R.string.a2_mea47_11, R.string.a2_mea47_12, R.string.a2_mea47_13, R.string.a2_mea47_14, R.string.a2_mea47_15};
    private int[] draw48_2 = new int[]{
            R.string.a2_mea48_1, R.string.a2_mea48_2, R.string.a2_mea48_3, R.string.a2_mea48_4, R.string.a2_mea48_5, R.string.a2_mea48_6, R.string.a2_mea48_7, R.string.a2_mea48_8, R.string.a2_mea48_9,
            R.string.a2_mea48_10, R.string.a2_mea48_11, R.string.a2_mea48_12, R.string.a2_mea48_13, R.string.a2_mea48_14, R.string.a2_mea48_15};
    private int[] draw49_2 = new int[]{
            R.string.a2_mea49_1, R.string.a2_mea49_2, R.string.a2_mea49_3, R.string.a2_mea49_4, R.string.a2_mea49_5, R.string.a2_mea49_6, R.string.a2_mea49_7, R.string.a2_mea49_8, R.string.a2_mea49_9,
            R.string.a2_mea49_10, R.string.a2_mea49_11, R.string.a2_mea49_12, R.string.a2_mea49_13, R.string.a2_mea49_14, R.string.a2_mea49_15};
    private int[] draw50_2 = new int[]{
            R.string.a2_mea50_1, R.string.a2_mea50_2, R.string.a2_mea50_3, R.string.a2_mea50_4, R.string.a2_mea50_5, R.string.a2_mea50_6, R.string.a2_mea50_7, R.string.a2_mea50_8, R.string.a2_mea50_9,
            R.string.a2_mea50_10, R.string.a2_mea50_11, R.string.a2_mea50_12, R.string.a2_mea50_13, R.string.a2_mea50_14, R.string.a2_mea50_15};


    private int index = 0;
    private int i = 0;

    private TextToSpeech mTTS;

    public int[][] main_meaning_b1 = new int[][]{draw0_1, draw1_1, draw2_1, draw3_1, draw4_1, draw5_1, draw6_1, draw7_1, draw8_1, draw9_1, draw10_1, draw11_1, draw12_1, draw13_1, draw14_1, draw15_1, draw16_1, draw17_1, draw18_1, draw19_1, draw20_1, draw21_1, draw22_1, draw23_1, draw24_1, draw25_1, draw26_1, draw27_1, draw28_1, draw29_1, draw30_1, draw31_1, draw32_1, draw33_1, draw34_1, draw35_1, draw36_1, draw37_1, draw38_1, draw39_1, draw40_1, draw41_1, draw42_1, draw43_1, draw44_1, draw45_1, draw46_1, draw47_1, draw48_1, draw49_1, draw50_1};
    public int[][] main_meaning_a2 = new int[][]{draw0_2, draw1_2, draw2_2, draw3_2, draw4_2, draw5_2, draw6_2, draw7_2, draw8_2, draw9_2, draw10_2, draw11_2, draw12_2, draw13_2, draw14_2, draw15_2, draw16_2, draw17_2, draw18_2, draw19_2, draw20_2, draw21_2, draw22_2, draw23_2, draw24_2, draw25_2, draw26_2, draw27_2, draw28_2, draw29_2, draw30_2, draw31_2, draw32_2, draw33_2, draw34_2, draw35_2, draw36_2, draw37_2, draw38_2, draw39_2, draw40_2, draw41_2, draw42_2, draw43_2, draw44_2, draw45_2, draw46_2, draw47_2, draw48_2, draw49_2, draw50_2};

    private int [] id = new int[]{55, 66, 77, 88, 99, 100, 110, 112, 114, 124, 1234, 124, 768, 345, 98};

    private EditText editText = null;

    private LinearLayout lay_definition_transl = null;

    private TextView words_1, words_2;

    private FloatingActionButton fab; Drawable f;

    private int [] counter_true = new int[]{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5}; private int count = 0;

    private TextView meaning = null;

    Level_A2 level_a2 = null;
    Level_B1 level_b1 = null;

    private int[][] main_1 = new int[][]{};
    private int[][] main_2 = new int[][]{};

    private int a2_or_b1 = 0;

    private FloatingActionButton el_next;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.definition);

        editText = findViewById(R.id.writeEdit);
        meaning = findViewById(R.id.meaning);
        meaning.setClickable(false);

        words_1 = findViewById(R.id.words_by_engl);
        words_2 = findViewById(R.id.words_by_transl);

        lay_definition_transl = findViewById(R.id.lay_definition_transl);

        fab = findViewById(R.id.fab);

        el_next = findViewById(R.id.el_next);

        full_array();

        f = editText.getBackground();

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

        if (PreIntermediate.lev.equals("a2")){
            index = PreIntermediate.abs;
            level_a2 = new Level_A2();
            main_1 = level_a2.main_1.clone();
            main_2 = level_a2.main_2.clone();
            a2_or_b1 = 0;

            //actionBar.setTitle(topic_by_a2[tem]);
        } else if (Intermediate.lev.equals("b1")){
            index = Intermediate.abs;
            level_b1 = new Level_B1();
            main_1 = level_b1.main_1.clone();
            main_2 = level_b1.main_2.clone();
            a2_or_b1 = 1;
            //actionBar.setTitle(topic_by_b1[tem]);
        }

        //words_1.setClickable(false);
        words_2.setClickable(false);
        words_get_text();
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

    private void listen(){ speak(getResources().getString((main_1[index][id[i]]))); }

    private void speak(String text){
        float pitch = 0.5f;
        float speed = 0.5f;
        Log.e("TTS", "123");
        //mTTS.setPitch(pitch);
        //mTTS.setSpeechRate(speed);

        mTTS.speak(text,TextToSpeech.QUEUE_FLUSH, null);
    }

    private void words_get_text(){
        if(a2_or_b1 == 0){
            meaning.setText(main_meaning_a2[index][id[i]]);
        } else {
            meaning.setText(main_meaning_b1[index][id[i]]);
        }
        words_1.setText(main_1[index][id[i]]);
        words_2.setText(main_2[index][id[i]]);
    }

    private boolean isTrueWords(){
        String eT = editText.getText().toString();
        eT = eT.trim();
        String res = (getResources().getString((main_1[index][id[i]])));
        if((eT.equals(res)) ){
            return true;
        } else {
            return false;
        }
    }

    public void click(View v) {
        switch (v.getId()) {
            case R.id.text_check:
                lay_definition_transl.setVisibility(View.VISIBLE);

                if(isTrueWords()){
                    if (counter_true[i] != 0){
                        counter_true[i] = 1;
                    }

                    editText.setBackgroundResource(R.color.back_true);
                    fab.setVisibility(View.VISIBLE);
                } else{
                    counter_true[i] = 0;
                    editText.setBackgroundResource(R.color.back_false);
                }
                break;

            case R.id.fab:
                if (i < main_1[index].length - 1){
                    i++;
                    //editText.setBackgroundResource(R.color.colorPrimary);
                    lay_definition_transl.setVisibility(View.GONE);
                    fab.setVisibility(View.GONE);
                    editText.setText("");
                    words_get_text();
                    editText.setBackground(f);
                } else {
                    count = 0;
                    for (int c: counter_true) {
                        if (c == 1){
                            count++;
                        }
                    }
                    Toast mess = Toast.makeText(this, "Correct answers " + count + " of " + main_1[index].length, Toast.LENGTH_LONG);
                    mess.show();
                    el_next.setVisibility(View.VISIBLE);
                    fab.setVisibility(View.GONE);
                }
                break;
            case R.id.el_next:
                Intent intent = new Intent(this, ListenWrite.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.words_by_engl:
                listen();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent;
        if (PreIntermediate.lev.equals("a2")){
            intent = new Intent(this, PreIntermediate.class);
            //actionBar.setTitle(topic_by_a2[tem]);
        } else if (Intermediate.lev.equals("b1")){
            intent = new Intent(this, Intermediate.class);
            //actionBar.setTitle(topic_by_b1[tem]);
        } else {
            intent = new Intent(this, ListenWrite.class);
        }
        startActivity(intent);
        this.finish();
    }
}