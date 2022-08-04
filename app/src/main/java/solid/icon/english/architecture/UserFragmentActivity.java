package solid.icon.english.architecture;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.Arrays;
import java.util.List;

import solid.icon.english.architecture.room.WordModel;
import solid.icon.english.user_line.studying.StudyActivity;

public abstract class UserFragmentActivity extends Fragment {

    protected List<WordModel> wordModelList;
    protected String topic, subTopic;
    protected String[] englishTranslArr, rusTranslArr;
    protected StudyActivity studyActivity;
    protected int size = 0;
    protected float metrics = Dpi.metrics;

    protected int [] id;

    protected TextToSpeech mTTS;

    protected int [] counter_flip;

    protected FragmentActivity context;

    protected String TAG = this.getClass().getSimpleName();

    protected UserFragmentActivity(List<WordModel> wordModelList, String topic, String subTopic, StudyActivity studyActivity){
        this.wordModelList = wordModelList;
        this.topic = topic;
        this.subTopic = subTopic;
        this.studyActivity = studyActivity;
        outLog(studyActivity.toString());

        fillMainArrays();
    }

    protected void fillMainArrays(){
        if (wordModelList != null){
            size = wordModelList.size();
            outLog("size = " + size);
        }else { return; }

        englishTranslArr = new String[size];
        rusTranslArr = new String[size];
        int i = 0;
        for (WordModel w : wordModelList) {
            englishTranslArr[i] = w.englishWord;
            rusTranslArr[i] = w.rusWord;
            outLog("englishTranslArr = " + englishTranslArr[i]);
            outLog("rusTranslArr = " + rusTranslArr[i]);
            i++;
        }
    }

    protected void fillArrays() {
        id = new int[size];
        counter_flip = new int[size];
        if(size != 0) {
            Arrays.fill(id, 99999);
            Arrays.fill(counter_flip, 0);
        }
    }

    protected void randomizeArray(){
        int rand;
        boolean isTrue = false;
        int k;


        for (int iter = 0; iter < size; iter++) {
            do {
                k = 0;
                rand = (int) (Math.random() * size);
                for (int j = 0; j < size; j++) {
                    if (rand == id[j]) {
                        isTrue = true;
                    }else{
                        k++;
                    }
                }
                if(k == size){
                    isTrue = false;
                }

            } while (isTrue);
            id[iter] = rand;
        }
    }

    protected int getDp(int px){
        return (int) (px * metrics);
    }

    protected void outLog(String text){
        Log.d(TAG, text);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}

