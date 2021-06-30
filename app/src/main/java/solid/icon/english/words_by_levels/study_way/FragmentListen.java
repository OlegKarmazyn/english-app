package solid.icon.english.words_by_levels.study_way;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

import solid.icon.english.R;
import solid.icon.english.Res_array;


public class FragmentListen extends Fragment implements View.OnClickListener {

    String what_level; int num_of_topic;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentListen(String what_level, int num_of_topic) {
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
        return inflater.inflate(R.layout.fragment_listen, container, false);
    }
    private AnimationDrawable animationDrawable = null;
    private ImageView imageView = null;
    private int[][] main_1 = new int[][]{};
    private int[][] main_2 = new int[][]{};

    private int index = 0;
    private int i = 0;

    private TextToSpeech mTTS;

    private int [] id = new int[]{55, 66, 77, 88, 99, 100, 110, 112, 114, 124, 1234, 124, 768, 345, 98};

    private EditText editText;

    private LinearLayout lay_write_learn = null;

    private TextView words1, words2;

    private FloatingActionButton fab; Drawable f;

    TextView text_check_listen;


    private int [] counter_true = new int[]{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5}; private int count = 0;

    private FloatingActionButton el_next;

    private void listen(){ speak(getResources().getString((main_1[index][id[i]]))); }

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

    private void words_get_text(){
        words1.setText(main_1[index][id[i]]);
        words2.setText(main_2[index][id[i]]);
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

    @Override
    public void onPause() {
        super.onPause();
        setVisibleGoneTextView();
        count = 0;
    }

    private void setVisibleGoneTextView(){
        lay_write_learn.setVisibility(View.GONE);
        editText.setBackground(f);
    }

    private void speak(String text){
        float pitch = 0.5f;
        float speed = 0.5f;
        Log.e("TTS", "123");
        //mTTS.setPitch(pitch);
        //mTTS.setSpeechRate(speed);

        mTTS.speak(text,TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onResume() {
        super.onResume();

        for(int i = 0; i < 15;i++){
            id[i]= i * -1;
        }

        imageView = getActivity().findViewById(R.id.img_listen);
        animationDrawable = (AnimationDrawable) imageView.getDrawable();


        editText = getActivity().findViewById(R.id.wrileEdit);

        lay_write_learn = getActivity().findViewById(R.id.lay_write_learn);

        words1 = getActivity().findViewById(R.id.words_by_engl);
        words2 = getActivity().findViewById(R.id.words_by_transl);

        fab = getActivity().findViewById(R.id.fab);

        full_array();

        f = editText.getBackground();

        el_next = getActivity().findViewById(R.id.el_next);

        text_check_listen = getActivity().findViewById(R.id.text_check_listen);

        imageView.setOnClickListener(this);
        fab.setOnClickListener(this);
        el_next.setOnClickListener(this);
        text_check_listen.setOnClickListener(this);

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
        }

//        if (PreIntermediate.lev.equals("a2")){
//            index = PreIntermediate.abs;
//            level_a2 = new Level_A2();
//            main_1 = level_a2.main_1.clone();
//            main_2 = level_a2.main_2.clone();
//            //actionBar.setTitle(topic_by_a2[tem]);
//        } else if (Intermediate.lev.equals("b1")){
            //actionBar.setTitle(topic_by_b1[tem]);
//        }
        words_get_text();

        words1.setClickable(false);
        words2.setClickable(false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_listen:
                animationDrawable.start();
                listen();
                new CountDownTimer(1000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        //mTextField.setText("done!");
                        animationDrawable.stop();
                    }
                }.start();

                break;
            case R.id.text_check_listen:
                lay_write_learn.setVisibility(View.VISIBLE);

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
                    lay_write_learn.setVisibility(View.GONE);
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
                    Toast mess = Toast.makeText(getActivity(), "Correct answers " + count + " of " + main_1[index].length, Toast.LENGTH_LONG);
                    mess.show();
                    el_next.setVisibility(View.VISIBLE);
                    fab.setVisibility(View.GONE);
                }
                break;
//            case R.id.el_next:
//                Intent intent = null;
//                if (PreIntermediate.lev.equals("a2")){
//                    TestOrLearn.doing = "test";
//                    intent = new Intent(getActivity(), Level_A2.class);
//
//                } else if (Intermediate.lev.equals("b1")){
//                    TestOrLearn.doing = "test";
//                    intent = new Intent(getActivity(), Level_B1.class);
//
//                }
//                startActivity(intent);
//                getActivity().finish();
//                break;
        }
    }
}