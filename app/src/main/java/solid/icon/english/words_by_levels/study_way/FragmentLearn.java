package solid.icon.english.words_by_levels.study_way;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Locale;

import solid.icon.english.R;
import solid.icon.english.Res_array;
import solid.icon.english.words_by_levels.lev_b1.Intermediate;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentLearn#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLearn extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentLearn() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentLearn.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentLearn newInstance(String param1, String param2) {
        FragmentLearn fragment = new FragmentLearn();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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

        return inflater.inflate(R.layout.fragment_learn, container, false);
    }

    private int [] id = new int[]{55, 66, 77, 88, 99, 100, 110, 112, 114, 124, 1234, 124, 768, 345, 98};
    public int[][] main_1 = new int[][]{};
    public int[][] main_2 = new int[][]{};

    private TextToSpeech mTTS;

    private final String TAG = "FragmentLearn";
    private int i = 0;

    TextView words1; TextView words2; TextView words3; TextView words4; TextView words5; TextView words6; TextView words7; TextView words8; TextView words9; TextView words10; TextView words11;
    TextView words12; TextView words13; TextView words14; TextView words15;

    TextView words1_1; TextView words2_1; TextView words3_1; TextView words4_1; TextView words5_1; TextView words6_1; TextView words7_1; TextView words8_1; TextView words9_1; TextView words10_1; TextView words11_1;
    TextView words12_1; TextView words13_1; TextView words14_1; TextView words15_1;

    private  int index = 0;

    private int [] counter_flip = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    @Override
    public void onResume(){
    super.onResume();

        for(int i = 0; i < 15;i++){
            id[i]= i * -1;
        }

        main_1 = new Res_array().main_1.clone();
        main_2 = new Res_array().main_2.clone();
        words1 = getActivity().findViewById(R.id.words1); words2 = getActivity().findViewById(R.id.words2); words3 = getActivity().findViewById(R.id.words3); words4 = getActivity().findViewById(R.id.words4); words5 = getActivity().findViewById(R.id.words5);
        words6 = getActivity().findViewById(R.id.words6); words7 = getActivity().findViewById(R.id.words7); words8 = getActivity().findViewById(R.id.words8); words9 = getActivity().findViewById(R.id.words9); words10 = getActivity().findViewById(R.id.words10);
        words11 = getActivity().findViewById(R.id.words11); words12 = getActivity().findViewById(R.id.words12); words13 = getActivity().findViewById(R.id.words13); words14 = getActivity().findViewById(R.id.words14); words15 = getActivity().findViewById(R.id.words15);

        words1_1 = getActivity().findViewById(R.id.words1_1); words2_1 = getActivity().findViewById(R.id.words2_1); words3_1 = getActivity().findViewById(R.id.words3_1); words4_1 = getActivity().findViewById(R.id.words4_1); words5_1 = getActivity().findViewById(R.id.words5_1);
        words6_1 = getActivity().findViewById(R.id.words6_1); words7_1 = getActivity().findViewById(R.id.words7_1); words8_1 = getActivity().findViewById(R.id.words8_1); words9_1 = getActivity().findViewById(R.id.words9_1); words10_1 = getActivity().findViewById(R.id.words10_1);
        words11_1 = getActivity().findViewById(R.id.words11_1); words12_1 = getActivity().findViewById(R.id.words12_1); words13_1 = getActivity().findViewById(R.id.words13_1); words14_1 = getActivity().findViewById(R.id.words14_1); words15_1 = getActivity().findViewById(R.id.words15_1);

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

            full_array();
            index = Intermediate.abs;
            change_test();
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
        }
    }
}