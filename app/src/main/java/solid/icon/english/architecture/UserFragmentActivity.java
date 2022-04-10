package solid.icon.english.architecture;

import android.speech.tts.TextToSpeech;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.List;

import solid.icon.english.architecture.room.WordModel;

public abstract class UserFragmentActivity extends Fragment {

    protected List<WordModel> wordModelList;
    protected String topic, subTopic;
    protected String[] englishTranslArr, rusTranslArr;
    protected int size = 0;
    protected float metrics = Dpi.metrics;

    protected int [] id;

    protected TextToSpeech mTTS;

    protected int [] counter_flip;

    protected FragmentActivity context;

    protected String TAG = this.getClass().toString();

    public UserFragmentActivity(List<WordModel> wordModelList, String topic, String subTopic, String[] englishTranslArr, String[] rusTranslArr){
        this.wordModelList = wordModelList;
        this.topic = topic;
        this.subTopic = subTopic;
        this.englishTranslArr = englishTranslArr;
        this.rusTranslArr = rusTranslArr;

        if (wordModelList != null)
        size = wordModelList.size();
        Log.e(TAG, String.valueOf(size));
    }

    public int getDp(int px){
        return (int) (px * metrics);
    }

}

