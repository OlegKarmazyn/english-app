package solid.icon.english.words_by_levels.study_way;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.Locale;

import solid.icon.english.R;
import solid.icon.english.architecture.ActivityGlobal;
import solid.icon.english.architecture.MyFragmentActivity;
import solid.icon.english.architecture.res.Res_array;


public class FragmentDefinition extends MyFragmentActivity implements View.OnClickListener {

    public FragmentDefinition(Serializable what_level, int num_of_topic) {
        this.what_level = what_level;
        this.num_of_topic = num_of_topic;
        defineArrays();
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_definition, container, false);
    }

    private int i = 0;

    private TextToSpeech mTTS;

    public int[][] main_meaning = new int[][]{};


    private int [] id = new int[]{55, 66, 77, 88, 99, 100, 110, 112, 114, 124, 1234, 124, 768, 345, 98};

    private EditText editText = null;

    private LinearLayout lay_definition_transl = null;

    private TextView words1, words2;

    private FloatingActionButton fab; Drawable f;

    private TextView text_check;

    private int [] counter_true = new int[]{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5}; private int count = 0;

    private TextView meaning = null;

    private FloatingActionButton el_next;

    private static boolean isNotTranslating = true;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isNotTranslating = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isNotTranslating = false;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(isNotTranslating) {

            for (int i = 0; i < 15; i++) {
                id[i] = i * -1;
            }
            editText = getActivity().findViewById(R.id.writeEdit);
            meaning = getActivity().findViewById(R.id.meaning);
            meaning.setClickable(false);

            words1 = getActivity().findViewById(R.id.words_by_engl);
            words2 = getActivity().findViewById(R.id.words_by_transl);

            lay_definition_transl = getActivity().findViewById(R.id.lay_definition_transl);

            fab = getActivity().findViewById(R.id.fab);

            text_check = getActivity().findViewById(R.id.text_check);

            el_next = getActivity().findViewById(R.id.el_next);

            full_array();

            f = editText.getBackground();

            fab.setOnClickListener(this);
            text_check.setOnClickListener(this);
            words1.setOnClickListener(this);

            mTTS = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        int result = mTTS.setLanguage(Locale.US);

                        if (result == TextToSpeech.LANG_MISSING_DATA
                                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Log.e("TTS", "Language not supported");
                        } else {

                        }
                    } else {
                        Log.e("TTS", "Initializator failef");
                    }
                }
            });

            words2.setClickable(false);
            words_get_text();
        }
    }

    @Override
    protected void defineArrays() {
        super.defineArrays();
        if(what_level == ActivityGlobal.LessonsName.A2) {
            main_meaning = new Res_array().main_meaning_a2.clone();
        }else if(what_level == ActivityGlobal.LessonsName.B1){
            main_meaning = new Res_array().main_meaning_b1.clone();
        } else if(what_level == ActivityGlobal.LessonsName.B2){
            main_meaning = new Res_array().main_meaning_b2.clone();
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

    private void listen(){ speak(getResources().getString((main_1[num_of_topic][id[i]]))); }

    private void speak(String text){
        float pitch = 0.5f;
        float speed = 0.5f;
        Log.e("TTS", "123");
        //mTTS.setPitch(pitch);
        //mTTS.setSpeechRate(speed);

        mTTS.speak(text,TextToSpeech.QUEUE_FLUSH, null);
    }

    private void words_get_text(){
        meaning.setText(main_meaning[num_of_topic][id[i]]);
        words1.setText(main_1[num_of_topic][id[i]]);
        words2.setText(main_2[num_of_topic][id[i]]);
    }

    private boolean isTrueWords(){
        String eT = editText.getText().toString();
        eT = eT.trim();
        String res = (getResources().getString((main_1[num_of_topic][id[i]])));
        if((eT.equals(res)) ){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onClick(View v) {
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
                if (i < main_1[num_of_topic].length - 1){
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
                    Toast mess = Toast.makeText(getActivity(), "Correct answers " + count + " of " + main_1[num_of_topic].length, Toast.LENGTH_LONG);
                    mess.show();
                    el_next.setVisibility(View.VISIBLE);
                    fab.setVisibility(View.GONE);
                }
                break;
//            case R.id.el_next:
//                Intent intent = new Intent(getActivity(), ListenWrite.class);
//                startActivity(intent);
//                getActivity().finish();
//                break;
            case R.id.words_by_engl:
                listen();
                break;
        }
    }
}