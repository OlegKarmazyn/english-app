package solid.icon.english.architecture;

import androidx.fragment.app.Fragment;

import java.util.List;

import solid.icon.english.architecture.room.WordModel;

public abstract class UserFragmentActivity extends Fragment {

    List<WordModel> wordModelList;
    String topic, subTopic;
    String[] englishTranslArr, rusTranslArr;

    public UserFragmentActivity(List<WordModel> wordModelList, String topic, String subTopic, String[] englishTranslArr, String[] rusTranslArr){
        this.wordModelList = wordModelList;
        this.topic = topic;
        this.subTopic = subTopic;
        this.englishTranslArr = englishTranslArr;
        this.rusTranslArr = rusTranslArr;
    }

}

