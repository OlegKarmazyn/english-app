package solid.icon.english.words_by_levels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import solid.icon.english.R;
import solid.icon.english.words_by_levels.Lev_a2.Level_A2;
import solid.icon.english.words_by_levels.Lev_a2.PreIntermediate;
import solid.icon.english.words_by_levels.lev_b1.Intermediate;
import solid.icon.english.words_by_levels.lev_b1.Level_B1;

public class TestOrLearn extends AppCompatActivity {

    private Intent a2, b1, Write, definition_int ;

    private int [] topic_by_a2;
    private int [] topic_by_b1;

    private int tem;

    public static String doing;

    private Button learn, listen_and_write, test, definition_but;

    private String lev = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_or_learn);
        ActionBar actionBar = getSupportActionBar();

        a2 = new Intent(this, Level_A2.class);
        Write = new Intent(this, ListenWrite.class);
        b1 = new Intent(this, Level_B1.class);
        definition_int = new Intent(this, Definition.class);

        topic_by_a2 = new int[]{R.string.a2_tema_0, R.string.a2_tema_1, R.string.a2_tema_2, R.string.a2_tema_3, R.string.a2_tema_4, R.string.a2_tema_5, R.string.a2_tema_6, R.string.a2_tema_7, R.string.a2_tema_8, R.string.a2_tema_9,
                R.string.a2_tema_10,R.string.a2_tema_11,R.string.a2_tema_12,R.string.a2_tema_13,R.string.a2_tema_14,R.string.a2_tema_15,R.string.a2_tema_16,R.string.a2_tema_17,R.string.a2_tema_18,R.string.a2_tema_19,
                R.string.a2_tema_20,R.string.a2_tema_21,R.string.a2_tema_22,R.string.a2_tema_23,R.string.a2_tema_24,R.string.a2_tema_25,R.string.a2_tema_26,R.string.a2_tema_27,R.string.a2_tema_28,R.string.a2_tema_29,
                R.string.a2_tema_30,R.string.a2_tema_31,R.string.a2_tema_32,R.string.a2_tema_33,R.string.a2_tema_34,R.string.a2_tema_35,R.string.a2_tema_36,R.string.a2_tema_37,R.string.a2_tema_38,R.string.a2_tema_39,
                R.string.a2_tema_40,R.string.a2_tema_41,R.string.a2_tema_42,R.string.a2_tema_43,R.string.a2_tema_44,R.string.a2_tema_45,R.string.a2_tema_46,R.string.a2_tema_47,R.string.a2_tema_48,R.string.a2_tema_49,
                R.string.a2_tema_50};

        topic_by_b1 = new int[]{
                R.string.b1_tema_0, R.string.b1_tema_1, R.string.b1_tema_2, R.string.b1_tema_3, R.string.b1_tema_4, R.string.b1_tema_5, R.string.b1_tema_6, R.string.b1_tema_7, R.string.b1_tema_8, R.string.b1_tema_9,
                R.string.b1_tema_10,R.string.b1_tema_11,R.string.b1_tema_12,R.string.b1_tema_13,R.string.b1_tema_14,R.string.b1_tema_15,R.string.b1_tema_16,R.string.b1_tema_17,R.string.b1_tema_18,R.string.b1_tema_19,
                R.string.b1_tema_20,R.string.b1_tema_21,R.string.b1_tema_22,R.string.b1_tema_23,R.string.b1_tema_24,R.string.b1_tema_25,R.string.b1_tema_26,R.string.b1_tema_27,R.string.b1_tema_28,R.string.b1_tema_29,
                R.string.b1_tema_30,R.string.b1_tema_31,R.string.b1_tema_32,R.string.b1_tema_33,R.string.b1_tema_34,R.string.b1_tema_35,R.string.b1_tema_36,R.string.b1_tema_37,R.string.b1_tema_38,R.string.b1_tema_39,
                R.string.b1_tema_40,R.string.b1_tema_41,R.string.b1_tema_42,R.string.b1_tema_43,R.string.b1_tema_44,R.string.b1_tema_45,R.string.b1_tema_46,R.string.b1_tema_47,R.string.b1_tema_48,R.string.b1_tema_49,
                R.string.b1_tema_50};


        if (PreIntermediate.lev.equals("a2")){
            lev = PreIntermediate.lev;
            tem = PreIntermediate.abs;
            actionBar.setTitle(topic_by_a2[tem]);
        } else if (Intermediate.lev.equals("b1")){
            lev = Intermediate.lev;
            tem = Intermediate.abs;
            actionBar.setTitle(topic_by_b1[tem]);
        }

        learn = findViewById(R.id.learn);
        listen_and_write = findViewById(R.id.listen_and_write);
        test = findViewById(R.id.test);
        definition_but = findViewById(R.id.definition);



        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lev.equals("a2")){
                    doing = "learn";
                    startActivity(a2);
                    finish();
                } else if(lev.equals("b1")){
                    doing = "learn";
                    startActivity(b1);
                    finish();
                }
            }
        });

        definition_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lev.equals("a2")){
                    doing = "deff";
                    startActivity(definition_int);
                    finish();
                } else if(lev.equals("b1")){
                    doing = "deff";
                    startActivity(definition_int);
                    finish();
                }
            }
        });

        listen_and_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lev.equals("a2")){
                    doing = "write";
                    startActivity(Write);
                    finish();
                } else if(lev.equals("b1")){
                    doing = "write";
                    startActivity(Write);
                    finish();
                }
            }
        });


        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lev.equals("a2")) {
                    startActivity(a2);
                    doing = "test";
                    finish();
                } else if(lev.equals("b1")){
                    doing = "test";
                    startActivity(b1);
                    finish();
                }
            }
        });



    }
    @Override
    public void onBackPressed() {
        Intent intent_a2 = new Intent(this, PreIntermediate.class);
        Intent intent_b1 = new Intent(this, Intermediate.class);
        if (lev.equals("a2")) {
            startActivity(intent_a2);
            finish();
        } else if(lev.equals("b1")){
            startActivity(intent_b1);
            finish();
        }
        this.finish();
    }
}