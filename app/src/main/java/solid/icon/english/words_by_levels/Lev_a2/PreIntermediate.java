package solid.icon.english.words_by_levels.Lev_a2;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import solid.icon.english.R;
import solid.icon.english.db_pac.DBHelper;
import solid.icon.english.db_pac.DBmoveINFO;
import solid.icon.english.words_by_levels.LevelByEnglish;
import solid.icon.english.words_by_levels.TestOrLearn;

public class PreIntermediate extends AppCompatActivity {

    private Intent intent0;
    public static int abs = 0;
    public static String lev = "";
    private TextView a2_tema_0, a2_tema_1, a2_tema_2, a2_tema_3, a2_tema_4, a2_tema_5, a2_tema_6, a2_tema_7, a2_tema_8, a2_tema_9, a2_tema_10, a2_tema_11, a2_tema_12, a2_tema_13, a2_tema_14, a2_tema_15, a2_tema_16, a2_tema_17, a2_tema_18, a2_tema_19, a2_tema_20, a2_tema_21, a2_tema_22, a2_tema_23, a2_tema_24, a2_tema_25, a2_tema_26, a2_tema_27, a2_tema_28, a2_tema_29, a2_tema_30, a2_tema_31, a2_tema_32, a2_tema_33, a2_tema_34, a2_tema_35, a2_tema_36, a2_tema_37, a2_tema_38, a2_tema_39, a2_tema_40, a2_tema_41, a2_tema_42, a2_tema_43, a2_tema_44, a2_tema_45, a2_tema_46, a2_tema_47, a2_tema_48, a2_tema_49, a2_tema_50;
    private CheckBox a2_checkBox_0,a2_checkBox_1, a2_checkBox_2, a2_checkBox_3, a2_checkBox_4, a2_checkBox_5, a2_checkBox_6, a2_checkBox_7, a2_checkBox_8, a2_checkBox_9, a2_checkBox_10, a2_checkBox_11, a2_checkBox_12, a2_checkBox_13, a2_checkBox_14, a2_checkBox_15, a2_checkBox_16, a2_checkBox_17, a2_checkBox_18, a2_checkBox_19, a2_checkBox_20, a2_checkBox_21, a2_checkBox_22, a2_checkBox_23, a2_checkBox_24, a2_checkBox_25, a2_checkBox_26, a2_checkBox_27, a2_checkBox_28, a2_checkBox_29, a2_checkBox_30, a2_checkBox_31, a2_checkBox_32, a2_checkBox_33, a2_checkBox_34, a2_checkBox_35, a2_checkBox_36, a2_checkBox_37, a2_checkBox_38, a2_checkBox_39, a2_checkBox_40, a2_checkBox_41, a2_checkBox_42, a2_checkBox_43, a2_checkBox_44, a2_checkBox_45, a2_checkBox_46, a2_checkBox_47, a2_checkBox_48, a2_checkBox_49, a2_checkBox_50;
    private CheckBox[] arr_checkBox = new CheckBox[51];
    private DBmoveINFO dBmoveINFO = new DBmoveINFO(this);

    private int[] key_topics = new int[51];

    private static final String TAG = "myLogs";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topics_by_a2);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Topics to study");

        intent0 = new Intent(this,TestOrLearn .class);

        lev = "a2";


        a2_tema_0 = findViewById(R.id.a2_tema_0);
        a2_tema_1 = findViewById(R.id.a2_tema_1);
        a2_tema_2 = findViewById(R.id.a2_tema_2);
        a2_tema_3 = findViewById(R.id.a2_tema_3);
        a2_tema_4 = findViewById(R.id.a2_tema_4);
        a2_tema_5 = findViewById(R.id.a2_tema_5);
        a2_tema_6 = findViewById(R.id.a2_tema_6);
        a2_tema_7 = findViewById(R.id.a2_tema_7);
        a2_tema_8 = findViewById(R.id.a2_tema_8);
        a2_tema_9 = findViewById(R.id.a2_tema_9);
        a2_tema_10 = findViewById(R.id.a2_tema_10);
        a2_tema_11 = findViewById(R.id.a2_tema_11);
        a2_tema_12 = findViewById(R.id.a2_tema_12);
        a2_tema_13 = findViewById(R.id.a2_tema_13);
        a2_tema_14 = findViewById(R.id.a2_tema_14);
        a2_tema_15 = findViewById(R.id.a2_tema_15);
        a2_tema_16 = findViewById(R.id.a2_tema_16);
        a2_tema_17 = findViewById(R.id.a2_tema_17);
        a2_tema_18 = findViewById(R.id.a2_tema_18);
        a2_tema_19 = findViewById(R.id.a2_tema_19);
        a2_tema_20 = findViewById(R.id.a2_tema_20);
        a2_tema_21 = findViewById(R.id.a2_tema_21);
        a2_tema_22 = findViewById(R.id.a2_tema_22);
        a2_tema_23 = findViewById(R.id.a2_tema_23);
        a2_tema_24 = findViewById(R.id.a2_tema_24);
        a2_tema_25 = findViewById(R.id.a2_tema_25);
        a2_tema_26 = findViewById(R.id.a2_tema_26);
        a2_tema_27 = findViewById(R.id.a2_tema_27);
        a2_tema_28 = findViewById(R.id.a2_tema_28);
        a2_tema_29 = findViewById(R.id.a2_tema_29);
        a2_tema_30 = findViewById(R.id.a2_tema_30);
        a2_tema_31 = findViewById(R.id.a2_tema_31);
        a2_tema_32 = findViewById(R.id.a2_tema_32);
        a2_tema_33 = findViewById(R.id.a2_tema_33);
        a2_tema_34 = findViewById(R.id.a2_tema_34);
        a2_tema_35 = findViewById(R.id.a2_tema_35);
        a2_tema_36 = findViewById(R.id.a2_tema_36);
        a2_tema_37 = findViewById(R.id.a2_tema_37);
        a2_tema_38 = findViewById(R.id.a2_tema_38);
        a2_tema_39 = findViewById(R.id.a2_tema_39);
        a2_tema_40 = findViewById(R.id.a2_tema_40);
        a2_tema_41 = findViewById(R.id.a2_tema_41);
        a2_tema_42 = findViewById(R.id.a2_tema_42);
        a2_tema_43 = findViewById(R.id.a2_tema_43);
        a2_tema_44 = findViewById(R.id.a2_tema_44);
        a2_tema_45 = findViewById(R.id.a2_tema_45);
        a2_tema_46 = findViewById(R.id.a2_tema_46);
        a2_tema_47 = findViewById(R.id.a2_tema_47);
        a2_tema_48 = findViewById(R.id.a2_tema_48);
        a2_tema_49 = findViewById(R.id.a2_tema_49);
        a2_tema_50 = findViewById(R.id.a2_tema_50);

        a2_checkBox_0 = findViewById(R.id.a2_checkBox_0);
        a2_checkBox_1 = findViewById(R.id.a2_checkBox_1);
        a2_checkBox_2 = findViewById(R.id.a2_checkBox_2);
        a2_checkBox_3 = findViewById(R.id.a2_checkBox_3);
        a2_checkBox_4 = findViewById(R.id.a2_checkBox_4);
        a2_checkBox_5 = findViewById(R.id.a2_checkBox_5);
        a2_checkBox_6 = findViewById(R.id.a2_checkBox_6);
        a2_checkBox_7 = findViewById(R.id.a2_checkBox_7);
        a2_checkBox_8 = findViewById(R.id.a2_checkBox_8);
        a2_checkBox_9 = findViewById(R.id.a2_checkBox_9);
        a2_checkBox_10 = findViewById(R.id.a2_checkBox_10);
        a2_checkBox_11 = findViewById(R.id.a2_checkBox_11);
        a2_checkBox_12 = findViewById(R.id.a2_checkBox_12);
        a2_checkBox_13 = findViewById(R.id.a2_checkBox_13);
        a2_checkBox_14 = findViewById(R.id.a2_checkBox_14);
        a2_checkBox_15 = findViewById(R.id.a2_checkBox_15);
        a2_checkBox_16 = findViewById(R.id.a2_checkBox_16);
        a2_checkBox_17 = findViewById(R.id.a2_checkBox_17);
        a2_checkBox_18 = findViewById(R.id.a2_checkBox_18);
        a2_checkBox_19 = findViewById(R.id.a2_checkBox_19);
        a2_checkBox_20 = findViewById(R.id.a2_checkBox_20);
        a2_checkBox_21 = findViewById(R.id.a2_checkBox_21);
        a2_checkBox_22 = findViewById(R.id.a2_checkBox_22);
        a2_checkBox_23 = findViewById(R.id.a2_checkBox_23);
        a2_checkBox_24 = findViewById(R.id.a2_checkBox_24);
        a2_checkBox_25 = findViewById(R.id.a2_checkBox_25);
        a2_checkBox_26 = findViewById(R.id.a2_checkBox_26);
        a2_checkBox_27 = findViewById(R.id.a2_checkBox_27);
        a2_checkBox_28 = findViewById(R.id.a2_checkBox_28);
        a2_checkBox_29 = findViewById(R.id.a2_checkBox_29);
        a2_checkBox_30 = findViewById(R.id.a2_checkBox_30);
        a2_checkBox_31 = findViewById(R.id.a2_checkBox_31);
        a2_checkBox_32 = findViewById(R.id.a2_checkBox_32);
        a2_checkBox_33 = findViewById(R.id.a2_checkBox_33);
        a2_checkBox_34 = findViewById(R.id.a2_checkBox_34);
        a2_checkBox_35 = findViewById(R.id.a2_checkBox_35);
        a2_checkBox_36 = findViewById(R.id.a2_checkBox_36);
        a2_checkBox_37 = findViewById(R.id.a2_checkBox_37);
        a2_checkBox_38 = findViewById(R.id.a2_checkBox_38);
        a2_checkBox_39 = findViewById(R.id.a2_checkBox_39);
        a2_checkBox_40 = findViewById(R.id.a2_checkBox_40);
        a2_checkBox_41 = findViewById(R.id.a2_checkBox_41);
        a2_checkBox_42 = findViewById(R.id.a2_checkBox_42);
        a2_checkBox_43 = findViewById(R.id.a2_checkBox_43);
        a2_checkBox_44 = findViewById(R.id.a2_checkBox_44);
        a2_checkBox_45 = findViewById(R.id.a2_checkBox_45);
        a2_checkBox_46 = findViewById(R.id.a2_checkBox_46);
        a2_checkBox_47 = findViewById(R.id.a2_checkBox_47);
        a2_checkBox_48 = findViewById(R.id.a2_checkBox_48);
        a2_checkBox_49 = findViewById(R.id.a2_checkBox_49);
        a2_checkBox_50 = findViewById(R.id.a2_checkBox_50);

        arr_checkBox[0] = a2_checkBox_0;
        arr_checkBox[1] = a2_checkBox_1;
        arr_checkBox[2] = a2_checkBox_2;
        arr_checkBox[3] = a2_checkBox_3;
        arr_checkBox[4] = a2_checkBox_4;
        arr_checkBox[5] = a2_checkBox_5;
        arr_checkBox[6] = a2_checkBox_6;
        arr_checkBox[7] = a2_checkBox_7;
        arr_checkBox[8] = a2_checkBox_8;
        arr_checkBox[9] = a2_checkBox_9;
        arr_checkBox[10] = a2_checkBox_10;
        arr_checkBox[11] = a2_checkBox_11;
        arr_checkBox[12] = a2_checkBox_12;
        arr_checkBox[13] = a2_checkBox_13;
        arr_checkBox[14] = a2_checkBox_14;
        arr_checkBox[15] = a2_checkBox_15;
        arr_checkBox[16] = a2_checkBox_16;
        arr_checkBox[17] = a2_checkBox_17;
        arr_checkBox[18] = a2_checkBox_18;
        arr_checkBox[19] = a2_checkBox_19;
        arr_checkBox[20] = a2_checkBox_20;
        arr_checkBox[21] = a2_checkBox_21;
        arr_checkBox[22] = a2_checkBox_22;
        arr_checkBox[23] = a2_checkBox_23;
        arr_checkBox[24] = a2_checkBox_24;
        arr_checkBox[25] = a2_checkBox_25;
        arr_checkBox[26] = a2_checkBox_26;
        arr_checkBox[27] = a2_checkBox_27;
        arr_checkBox[28] = a2_checkBox_28;
        arr_checkBox[29] = a2_checkBox_29;
        arr_checkBox[30] = a2_checkBox_30;
        arr_checkBox[31] = a2_checkBox_31;
        arr_checkBox[32] = a2_checkBox_32;
        arr_checkBox[33] = a2_checkBox_33;
        arr_checkBox[34] = a2_checkBox_34;
        arr_checkBox[35] = a2_checkBox_35;
        arr_checkBox[36] = a2_checkBox_36;
        arr_checkBox[37] = a2_checkBox_37;
        arr_checkBox[38] = a2_checkBox_38;
        arr_checkBox[39] = a2_checkBox_39;
        arr_checkBox[40] = a2_checkBox_40;
        arr_checkBox[41] = a2_checkBox_41;
        arr_checkBox[42] = a2_checkBox_42;
        arr_checkBox[43] = a2_checkBox_43;
        arr_checkBox[44] = a2_checkBox_44;
        arr_checkBox[45] = a2_checkBox_45;
        arr_checkBox[46] = a2_checkBox_46;
        arr_checkBox[47] = a2_checkBox_47;
        arr_checkBox[48] = a2_checkBox_48;
        arr_checkBox[49] = a2_checkBox_49;
        arr_checkBox[50] = a2_checkBox_50;

        a2_checkBox_0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Log.d(TAG, "a2_checkBox_0 - onCheckedChanged - if - true");
                    dBmoveINFO.go_check_info(0, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_0.setPaintFlags(a2_tema_0.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_0.setPaintFlags(a2_tema_0.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(0, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Log.d(TAG, "a2_checkBox_1 - onCheckedChanged - if - true");
                    dBmoveINFO.go_check_info(1, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_1.setPaintFlags(a2_tema_1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_1.setPaintFlags(a2_tema_1.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(1, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(2, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_2.setPaintFlags(a2_tema_2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_2.setPaintFlags(a2_tema_2.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(2, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(3, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_3.setPaintFlags(a2_tema_3.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_3.setPaintFlags(a2_tema_3.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(3, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(4, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_4.setPaintFlags(a2_tema_4.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_4.setPaintFlags(a2_tema_4.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(4, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(5, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_5.setPaintFlags(a2_tema_5.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_5.setPaintFlags(a2_tema_5.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(5, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(6, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_6.setPaintFlags(a2_tema_6.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_6.setPaintFlags(a2_tema_6.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(6, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(7, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_7.setPaintFlags(a2_tema_7.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_7.setPaintFlags(a2_tema_7.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(7, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(8, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_8.setPaintFlags(a2_tema_8.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_8.setPaintFlags(a2_tema_8.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(8, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(9, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_9.setPaintFlags(a2_tema_9.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_9.setPaintFlags(a2_tema_9.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(9, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(10, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_10.setPaintFlags(a2_tema_10.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_10.setPaintFlags(a2_tema_10.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(10, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(11, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_11.setPaintFlags(a2_tema_11.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_11.setPaintFlags(a2_tema_11.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(11, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(12, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_12.setPaintFlags(a2_tema_12.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_12.setPaintFlags(a2_tema_12.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(12, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(13, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_13.setPaintFlags(a2_tema_13.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_13.setPaintFlags(a2_tema_13.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(13, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(14, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_14.setPaintFlags(a2_tema_14.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_14.setPaintFlags(a2_tema_14.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(14, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(15, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_15.setPaintFlags(a2_tema_15.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_15.setPaintFlags(a2_tema_15.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(15, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(16, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_16.setPaintFlags(a2_tema_16.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_16.setPaintFlags(a2_tema_16.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(16, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_17.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(17, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_17.setPaintFlags(a2_tema_17.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_17.setPaintFlags(a2_tema_17.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(17, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_18.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(18, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_18.setPaintFlags(a2_tema_18.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_18.setPaintFlags(a2_tema_18.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(18, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_19.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(19, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_19.setPaintFlags(a2_tema_19.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_19.setPaintFlags(a2_tema_19.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(19, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_20.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(20, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_20.setPaintFlags(a2_tema_20.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_20.setPaintFlags(a2_tema_20.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(20, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_21.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(21, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_21.setPaintFlags(a2_tema_21.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_21.setPaintFlags(a2_tema_21.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(21, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_22.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(22, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_22.setPaintFlags(a2_tema_22.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_22.setPaintFlags(a2_tema_22.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(22, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_23.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(23, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_23.setPaintFlags(a2_tema_23.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_23.setPaintFlags(a2_tema_23.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(23, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_24.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(24, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_24.setPaintFlags(a2_tema_24.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_24.setPaintFlags(a2_tema_24.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(24, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_25.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(25, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_25.setPaintFlags(a2_tema_25.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_25.setPaintFlags(a2_tema_25.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(25, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_26.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(26, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_26.setPaintFlags(a2_tema_26.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_26.setPaintFlags(a2_tema_26.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(26, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_27.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(27, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_27.setPaintFlags(a2_tema_27.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_27.setPaintFlags(a2_tema_27.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(27, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_28.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(28, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_28.setPaintFlags(a2_tema_28.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_28.setPaintFlags(a2_tema_28.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(28, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_29.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(29, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_29.setPaintFlags(a2_tema_29.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_29.setPaintFlags(a2_tema_29.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(29, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_30.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(30, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_30.setPaintFlags(a2_tema_30.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_30.setPaintFlags(a2_tema_30.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(30, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_31.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(31, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_31.setPaintFlags(a2_tema_31.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_31.setPaintFlags(a2_tema_31.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(31, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_32.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(32, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_32.setPaintFlags(a2_tema_32.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_32.setPaintFlags(a2_tema_32.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(32, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_33.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(33, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_33.setPaintFlags(a2_tema_33.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_33.setPaintFlags(a2_tema_33.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(33, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_34.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(34, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_34.setPaintFlags(a2_tema_34.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_34.setPaintFlags(a2_tema_34.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(34, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_35.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(35, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_35.setPaintFlags(a2_tema_35.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_35.setPaintFlags(a2_tema_35.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(35, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_36.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(36, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_36.setPaintFlags(a2_tema_36.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_36.setPaintFlags(a2_tema_36.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(36, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_37.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(37, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_37.setPaintFlags(a2_tema_37.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_37.setPaintFlags(a2_tema_37.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(37, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_38.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(38, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_38.setPaintFlags(a2_tema_38.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_38.setPaintFlags(a2_tema_38.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(38, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_39.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(39, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_39.setPaintFlags(a2_tema_39.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_39.setPaintFlags(a2_tema_39.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(39, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_40.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(40, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_40.setPaintFlags(a2_tema_40.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_40.setPaintFlags(a2_tema_40.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(40, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_41.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(41, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_41.setPaintFlags(a2_tema_41.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_41.setPaintFlags(a2_tema_41.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(41, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_42.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(42, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_42.setPaintFlags(a2_tema_42.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_42.setPaintFlags(a2_tema_42.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(42, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_43.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(43, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_43.setPaintFlags(a2_tema_43.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_43.setPaintFlags(a2_tema_43.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(43, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_44.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(44, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_44.setPaintFlags(a2_tema_44.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_44.setPaintFlags(a2_tema_44.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(44, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_45.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(45, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_45.setPaintFlags(a2_tema_45.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_45.setPaintFlags(a2_tema_45.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(45, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_46.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(46, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_46.setPaintFlags(a2_tema_46.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_46.setPaintFlags(a2_tema_46.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(46, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_47.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(47, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_47.setPaintFlags(a2_tema_47.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_47.setPaintFlags(a2_tema_47.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(47, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_48.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(48, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_48.setPaintFlags(a2_tema_48.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_48.setPaintFlags(a2_tema_48.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(48, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_49.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(49, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_49.setPaintFlags(a2_tema_49.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_49.setPaintFlags(a2_tema_49.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(49, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });

        a2_checkBox_50.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    dBmoveINFO.go_check_info(50, DBHelper.KNOW_TOPIC_A2);
                    a2_tema_50.setPaintFlags(a2_tema_50.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    a2_tema_50.setPaintFlags(a2_tema_50.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(50, DBHelper.KNOW_TOPIC_A2);
                }
            }
        });


        go_check_arr();

    }

    private void go_check_arr(){
        for (int i = 0; i < 51; i++){
            key_topics[i] = dBmoveINFO.back_check_info(i, DBHelper.KNOW_TOPIC_A2);
            Log.d(TAG, i +"dBmoveINFO " + String.valueOf(dBmoveINFO.back_check_info(i, DBHelper.KNOW_TOPIC_A2)));
        }
        for (int i = 0; i < 51; i++){
            if(key_topics[i] == 1){
                arr_checkBox[i].setChecked(true);
            }
        }
    }


    public void click(View v) {
        switch (v.getId()) {
            case R.id.a2_tema_0:

                startActivity(intent0);
                abs = 0;
                break;
            case R.id.a2_tema_1:

                startActivity(intent0);
                abs = 1;
                break;
            case R.id.a2_tema_2:

                startActivity(intent0);
                abs = 2;
                break;
            case R.id.a2_tema_3:

                startActivity(intent0);
                abs = 3;
                break;
            case R.id.a2_tema_4:

                startActivity(intent0);
                abs = 4;
                break;
            case R.id.a2_tema_5:

                startActivity(intent0);
                abs = 5;
                break;
            case R.id.a2_tema_6:

                startActivity(intent0);
                abs = 6;
                break;
            case R.id.a2_tema_7:

                startActivity(intent0);
                abs = 7;
                break;
            case R.id.a2_tema_8:

                startActivity(intent0);
                abs = 8;
                break;
            case R.id.a2_tema_9:

                startActivity(intent0);
                abs = 9;
                break;
            case R.id.a2_tema_10:

                startActivity(intent0);
                abs = 10;
                break;
            case R.id.a2_tema_11:

                startActivity(intent0);
                abs = 11;
                break;
            case R.id.a2_tema_12:

                startActivity(intent0);
                abs = 12;
                break;
            case R.id.a2_tema_13:

                startActivity(intent0);
                abs = 13;
                break;
            case R.id.a2_tema_14:

                startActivity(intent0);
                abs = 14;
                break;
            case R.id.a2_tema_15:

                startActivity(intent0);
                abs = 15;
                break;
            case R.id.a2_tema_16:

                startActivity(intent0);
                abs = 16;
                break;
            case R.id.a2_tema_17:

                startActivity(intent0);
                abs = 17;
                break;
            case R.id.a2_tema_18:

                startActivity(intent0);
                abs = 18;
                break;
            case R.id.a2_tema_19:

                startActivity(intent0);
                abs = 19;
                break;
            case R.id.a2_tema_20:

                startActivity(intent0);
                abs = 20;
                break;
            case R.id.a2_tema_21:

                startActivity(intent0);
                abs = 21;
                break;
            case R.id.a2_tema_22:

                startActivity(intent0);
                abs = 22;
                break;
            case R.id.a2_tema_23:

                startActivity(intent0);
                abs = 23;
                break;
            case R.id.a2_tema_24:

                startActivity(intent0);
                abs = 24;
                break;
            case R.id.a2_tema_25:

                startActivity(intent0);
                abs = 25;
                break;
            case R.id.a2_tema_26:

                startActivity(intent0);
                abs = 26;
                break;
            case R.id.a2_tema_27:
                startActivity(intent0);
                abs = 27;
                break;
            case R.id.a2_tema_28:
                startActivity(intent0);
                abs = 28;
                break;
            case R.id.a2_tema_29:

                startActivity(intent0);
                abs = 29;
                break;
            case R.id.a2_tema_30:

                startActivity(intent0);
                abs = 30;
                break;
            case R.id.a2_tema_31:

                startActivity(intent0);
                abs = 31;
                break;
            case R.id.a2_tema_32:

                startActivity(intent0);
                abs = 32;
                break;
            case R.id.a2_tema_33:

                startActivity(intent0);
                abs = 33;
                break;
            case R.id.a2_tema_34:

                startActivity(intent0);
                abs = 34;
                break;
            case R.id.a2_tema_35:

                startActivity(intent0);
                abs = 35;
                break;
            case R.id.a2_tema_36:

                startActivity(intent0);
                abs = 36;
                break;
            case R.id.a2_tema_37:

                startActivity(intent0);
                abs = 37;
                break;
            case R.id.a2_tema_38:

                startActivity(intent0);
                abs = 38;
                break;
            case R.id.a2_tema_39:

                startActivity(intent0);
                abs = 39;
                break;
            case R.id.a2_tema_40:

                startActivity(intent0);
                abs = 40;
                break;
            case R.id.a2_tema_41:

                startActivity(intent0);
                abs = 41;
                break;
            case R.id.a2_tema_42:

                startActivity(intent0);
                abs = 42;
                break;
            case R.id.a2_tema_43:

                startActivity(intent0);
                abs = 43;
                break;
            case R.id.a2_tema_44:

                startActivity(intent0);
                abs = 44;
                break;
            case R.id.a2_tema_45:

                startActivity(intent0);
                abs = 45;
                break;
            case R.id.a2_tema_46:

                startActivity(intent0);
                abs = 46;
                break;
            case R.id.a2_tema_47:

                startActivity(intent0);
                abs = 47;
                break;
            case R.id.a2_tema_48:

                startActivity(intent0);
                abs = 48;
                break;
            case R.id.a2_tema_49:

                startActivity(intent0);
                abs = 49;
                break;
            case R.id.a2_tema_50:

                startActivity(intent0);
                abs = 50;
                break;


        }
        this.finish();
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//
//            case R.id.main_act:
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
//                this.finish();
//                return true;
//            case android.R.id.home:
//                intent_H = new Intent(this, MainActivity.class);
//                startActivity(intent_H);
//                this.finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, LevelByEnglish.class);
        startActivity(intent);
        lev = "";
        this.finish();
    }
}
