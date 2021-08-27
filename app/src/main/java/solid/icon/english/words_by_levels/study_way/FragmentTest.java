package solid.icon.english.words_by_levels.study_way;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Locale;

import solid.icon.english.R;
import solid.icon.english.Res_array;
import solid.icon.english.db_pac.DBmoveINFO;
import solid.icon.english.words_by_levels.universal_topic_level.EnglishLevel;

import static solid.icon.english.words_by_levels.study_way.MainStudyAction.randomOrg;

public class FragmentTest extends Fragment implements View.OnClickListener {

    String what_level; int num_of_topic;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentTest(String what_level, int num_of_topic) {
        this.what_level = what_level;
        this.num_of_topic = num_of_topic;
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    private int [] id = new int[]{55, 66, 77, 88, 99, 100, 110, 112, 114, 124, 1234, 124, 768, 345, 98};
    public int[][] main_1 = new int[][]{};
    public int[][] main_2 = new int[][]{};

    private int counter_true = 0; private Toast mess;

    private TextToSpeech mTTS;

    private final String TAG = "FragmentLearn";
    private Drawable LinDraw;
    private int i = 0;

    TextView words1; TextView words2; TextView words3; TextView words4; TextView words5; TextView words6; TextView words7; TextView words8; TextView words9; TextView words10; TextView words11;
    TextView words12; TextView words13; TextView words14; TextView words15;

    TextView words1_1; TextView words2_1; TextView words3_1; TextView words4_1; TextView words5_1; TextView words6_1; TextView words7_1; TextView words8_1; TextView words9_1; TextView words10_1; TextView words11_1;
    TextView words12_1; TextView words13_1; TextView words14_1; TextView words15_1;

    EditText editText1; EditText editText2; EditText editText3; EditText editText4; EditText editText5; EditText editText6; EditText editText7; EditText editText8; EditText editText9; EditText editText10; EditText editText11;
    EditText editText12; EditText editText13; EditText editText14; EditText editText15;

    LinearLayout lin_1; LinearLayout lin_2; LinearLayout lin_3; LinearLayout lin_4; LinearLayout lin_5; LinearLayout lin_6; LinearLayout lin_7; LinearLayout lin_8; LinearLayout lin_9; LinearLayout lin_10; LinearLayout lin_11;
    LinearLayout lin_12; LinearLayout lin_13; LinearLayout lin_14; LinearLayout lin_15;

    private TextView check;
    private  int index = 0;

    private int [] counter_flip = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private String [] back_words = new String[15];
    private boolean [] isRight = new boolean[]{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};

    @Override
    public void onResume(){
        super.onResume();

        for(int i = 0; i < 15;i++){
            id[i]= i * -1;
        }


        words1 = getActivity().findViewById(R.id.words1); words2 = getActivity().findViewById(R.id.words2); words3 = getActivity().findViewById(R.id.words3); words4 = getActivity().findViewById(R.id.words4); words5 = getActivity().findViewById(R.id.words5);
        words6 = getActivity().findViewById(R.id.words6); words7 = getActivity().findViewById(R.id.words7); words8 = getActivity().findViewById(R.id.words8); words9 = getActivity().findViewById(R.id.words9); words10 = getActivity().findViewById(R.id.words10);
        words11 = getActivity().findViewById(R.id.words11); words12 = getActivity().findViewById(R.id.words12); words13 = getActivity().findViewById(R.id.words13); words14 = getActivity().findViewById(R.id.words14); words15 = getActivity().findViewById(R.id.words15);

        words1_1 = getActivity().findViewById(R.id.words1_1); words2_1 = getActivity().findViewById(R.id.words2_1); words3_1 = getActivity().findViewById(R.id.words3_1); words4_1 = getActivity().findViewById(R.id.words4_1); words5_1 = getActivity().findViewById(R.id.words5_1);
        words6_1 = getActivity().findViewById(R.id.words6_1); words7_1 = getActivity().findViewById(R.id.words7_1); words8_1 = getActivity().findViewById(R.id.words8_1); words9_1 = getActivity().findViewById(R.id.words9_1); words10_1 = getActivity().findViewById(R.id.words10_1);
        words11_1 = getActivity().findViewById(R.id.words11_1); words12_1 = getActivity().findViewById(R.id.words12_1); words13_1 = getActivity().findViewById(R.id.words13_1); words14_1 = getActivity().findViewById(R.id.words14_1); words15_1 = getActivity().findViewById(R.id.words15_1);

        editText1 = getActivity().findViewById(R.id.editText1); editText2 = getActivity().findViewById(R.id.editText2); editText3 = getActivity().findViewById(R.id.editText3); editText4 = getActivity().findViewById(R.id.editText4); editText5 = getActivity().findViewById(R.id.editText5);
        editText6 = getActivity().findViewById(R.id.editText6); editText7 = getActivity().findViewById(R.id.editText7); editText8 = getActivity().findViewById(R.id.editText8); editText9 = getActivity().findViewById(R.id.editText9); editText10 = getActivity().findViewById(R.id.editText10);
        editText11 = getActivity().findViewById(R.id.editText11); editText12 = getActivity().findViewById(R.id.editText12); editText13 = getActivity().findViewById(R.id.editText13); editText14 = getActivity().findViewById(R.id.editText14); editText15 = getActivity().findViewById(R.id.editText15);

        lin_1 = getActivity().findViewById(R.id.lin_1); lin_2 = getActivity().findViewById(R.id.lin_2); lin_3 = getActivity().findViewById(R.id.lin_3); lin_4 = getActivity().findViewById(R.id.lin_4); lin_5 = getActivity().findViewById(R.id.lin_5);
        lin_6 = getActivity().findViewById(R.id.lin_6); lin_7 = getActivity().findViewById(R.id.lin_7); lin_8 = getActivity().findViewById(R.id.lin_8); lin_9 = getActivity().findViewById(R.id.lin_9); lin_10 = getActivity().findViewById(R.id.lin_10);
        lin_11 = getActivity().findViewById(R.id.lin_11); lin_12 = getActivity().findViewById(R.id.lin_12); lin_13 = getActivity().findViewById(R.id.lin_13); lin_14 = getActivity().findViewById(R.id.lin_14); lin_15 = getActivity().findViewById(R.id.lin_15);

        check = getActivity().findViewById(R.id.check);

        LinDraw = lin_1.getBackground();

        words1.setOnClickListener(this);
        words2.setOnClickListener(this);
        words3.setOnClickListener(this);
        words4.setOnClickListener(this);
        words5.setOnClickListener(this);
        words6.setOnClickListener(this);
        words7.setOnClickListener(this);
        words8.setOnClickListener(this);
        words9.setOnClickListener(this);
        words10.setOnClickListener(this);
        words11.setOnClickListener(this);
        words12.setOnClickListener(this);
        words13.setOnClickListener(this);
        words14.setOnClickListener(this);
        words15.setOnClickListener(this);
        check.setOnClickListener(this);

        mTTS = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
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
        if(what_level.equals("b1")){
            main_1 = new Res_array().main_1_learn_b1.clone();
            main_2 = new Res_array().main_2_learn_b1.clone();
            index = num_of_topic;
        } else if(what_level.equals("a2")){
            main_1 = new Res_array().main_1_learn_a2.clone();
            main_2 = new Res_array().main_2_learn_a2.clone();
            index = num_of_topic;
        } else if(what_level.equals("b2")){
            main_1 = new Res_array().main_1_learn_b2.clone();
            main_2 = new Res_array().main_2_learn_b2.clone();
            index = num_of_topic;
        } else if(what_level.equals("day")){
            index = 0;
            main_1 = new Res_array().main_1_learn_b2.clone();
            main_2 = new Res_array().main_2_learn_b2.clone();

            main_1[index] = randomOrg.random_words_english.clone();
            main_2[index] = randomOrg.random_words_translation.clone();
        }
            check.setVisibility(View.VISIBLE);
            text1_1_visibel_vis();
            text1_visibel_gone();
            full_array();
            change_test();

    }

    @Override
    public void onPause() {
        super.onPause();
        setVisibleGoneTextView();
    }

    private void setVisibleGoneTextView(){
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

    private void check_visible_gone(){
        check.setVisibility(View.GONE);
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

    private void equals_back_with_true(){
        String word = "";
        for (int j = 0; j < 15; j++){
            word = getResources().getString((main_1[index][id[j]]));
            back_words[j] = back_words[j].trim();
            isRight[j] = back_words[j].equals(word);
            //System.out.println("что " + back_words[j] + " с чем " + word + " " + isRight[j]);
        }
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
        back_words[t++] = editText15.getText().toString();
        t = 0;
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

    private void speak(String text){
        float pitch = 0.5f;
        float speed = 0.5f;
        Log.e("TTS", "123");
        //mTTS.setPitch(pitch);
        //mTTS.setSpeechRate(speed);

        mTTS.speak(text,TextToSpeech.QUEUE_FLUSH, null);
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
            //el_next.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.words1:
                if(words1_1.getVisibility() == View.GONE){
                    words1_1.setVisibility(View.VISIBLE);
                    counter_flip[0] = 1;
                    calculate_flip();
                }
                speak((String) words1.getText());
                break;

            case R.id.words2:
                if(words2_1.getVisibility() == View.GONE){
                    words2_1.setVisibility(View.VISIBLE);
                    counter_flip[1] = 1;
                    calculate_flip();
                }
                speak((String) words2.getText());
                break;

            case R.id.words3:
                if(words3_1.getVisibility() == View.GONE){
                    words3_1.setVisibility(View.VISIBLE);
                    counter_flip[2] = 1;
                    calculate_flip();
                }
                speak((String) words3.getText());
                break;

            case R.id.words4:
                if(words4_1.getVisibility() == View.GONE){
                    words4_1.setVisibility(View.VISIBLE);
                    counter_flip[3] = 1;
                    calculate_flip();
                }
                speak((String) words4.getText());
                break;

            case R.id.words5:
                if(words5_1.getVisibility() == View.GONE){
                    words5_1.setVisibility(View.VISIBLE);
                    counter_flip[4] = 1;
                    calculate_flip();
                }
                speak((String) words5.getText());
                break;

            case R.id.words6:
                if(words6_1.getVisibility() == View.GONE){
                    words6_1.setVisibility(View.VISIBLE);
                    counter_flip[5] = 1;
                    calculate_flip();
                }
                speak((String) words6.getText());
                break;

            case R.id.words7:
                if(words7_1.getVisibility() == View.GONE){
                    words7_1.setVisibility(View.VISIBLE);
                    counter_flip[6] = 1;
                    calculate_flip();
                }
                speak((String) words7.getText());
                break;

            case R.id.words8:
                if(words8_1.getVisibility() == View.GONE){
                    words8_1.setVisibility(View.VISIBLE);
                    counter_flip[7] = 1;
                    calculate_flip();
                }
                speak((String) words8.getText());
                break;

            case R.id.words9:
                if(words9_1.getVisibility() == View.GONE){
                    words9_1.setVisibility(View.VISIBLE);
                    counter_flip[8] = 1;
                    calculate_flip();
                }
                speak((String) words9.getText());
                break;

            case R.id.words10:
                if(words10_1.getVisibility() == View.GONE){
                    words10_1.setVisibility(View.VISIBLE);
                    counter_flip[9] = 1;
                    calculate_flip();
                }
                speak((String) words10.getText());
                break;

            case R.id.words11:
                if(words11_1.getVisibility() == View.GONE){
                    words11_1.setVisibility(View.VISIBLE);
                    counter_flip[10] = 1;
                    calculate_flip();
                }
                speak((String) words11.getText());
                break;

            case R.id.words12:
                if(words12_1.getVisibility() == View.GONE){
                    words12_1.setVisibility(View.VISIBLE);
                    counter_flip[11] = 1;
                    calculate_flip();
                }
                speak((String) words12.getText());
                break;

            case R.id.words13:
                if(words13_1.getVisibility() == View.GONE){
                    words13_1.setVisibility(View.VISIBLE);
                    counter_flip[12] = 1;
                    calculate_flip();
                }
                speak((String) words13.getText());
                break;

            case R.id.words14:
                if(words14_1.getVisibility() == View.GONE){
                    words14_1.setVisibility(View.VISIBLE);
                    counter_flip[13] = 1;
                    calculate_flip();
                }
                speak((String) words14.getText());
                break;

            case R.id.words15:
                if(words15_1.getVisibility() == View.GONE){
                    words15_1.setVisibility(View.VISIBLE);
                    counter_flip[14] = 1;
                    calculate_flip();
                }
                speak((String) words15.getText());
                break;

            case R.id.check:
                text1_visibel_vis();
                back_inf_from_editText();
                equals_back_with_true();
                set_all_back_to_lin();
                check_visible_gone();
                mess = Toast.makeText(getActivity(), "Correct answers " + counter_true + " of " + 15, Toast.LENGTH_LONG);
                mess.show();
                for(int i = 0; i < 15; i++){
                    isRight[i] = false;
                }
                if(counter_true > 12){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Do you want to mark this topic as done?");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User clicked OK button
                            mark_topic_as_done();
                        }
                    });
                    builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                break;
        }
    }

    private void mark_topic_as_done(){
        DBmoveINFO dBmoveINFO = new DBmoveINFO( getActivity());
        dBmoveINFO.go_check_info(index, EnglishLevel.which_KNOW_TOPIC);
    }
}