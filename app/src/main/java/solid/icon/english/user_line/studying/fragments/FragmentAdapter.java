package solid.icon.english.user_line.studying.fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

import solid.icon.english.architecture.room.WordModel;
import solid.icon.english.user_line.studying.StudyActivity;

public class FragmentAdapter extends FragmentStateAdapter {

    List<WordModel> wordModelList;
    String topic, subTopic;
    StudyActivity studyActivity;

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle,
                           List<WordModel> wordModelList, String topic, String subTopic,
                           StudyActivity studyActivity) {
        super(fragmentManager, lifecycle);
        this.wordModelList = wordModelList;
        this.topic = topic;
        this.subTopic = subTopic;
        this.studyActivity = studyActivity;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){

            case 1:
                return new FragmentListen(wordModelList, topic, subTopic, studyActivity);
            case 2:
                //return new FragmentTest( what_level, num_of_topic);
            case 3:
                //return new FragmentDefinition( what_level, num_of_topic);

            default:
                return new FragmentLearn(wordModelList, topic, subTopic, studyActivity);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
