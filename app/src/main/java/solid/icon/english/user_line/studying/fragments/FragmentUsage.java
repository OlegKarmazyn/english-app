package solid.icon.english.user_line.studying.fragments;

import java.util.List;

import solid.icon.english.architecture.parents.UserFragmentActivity;
import solid.icon.english.architecture.room.WordModel;
import solid.icon.english.user_line.studying.StudyActivity;

public class FragmentUsage extends UserFragmentActivity {

    protected FragmentUsage(List<WordModel> wordModelList, String topic, String subTopic, StudyActivity studyActivity) {
        super(wordModelList, topic, subTopic, studyActivity);
    }

}
