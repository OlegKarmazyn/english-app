package solid.icon.english.user_line.studying.fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

import solid.icon.english.architecture.room.WordModel;

public class FragmentAdapter extends FragmentStateAdapter {

    List<WordModel> wordModelList;
    String topic, subTopic;
    String[] englishTranslArr, rusTranslArr;

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle,
                           List<WordModel> wordModelList, String topic, String subTopic, String[] englishTranslArr, String[] rusTranslArr) {
        super(fragmentManager, lifecycle);
        this.wordModelList = wordModelList;
        this.topic = topic;
        this.subTopic = subTopic;
        this.englishTranslArr = englishTranslArr;
        this.rusTranslArr = rusTranslArr;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){

            case 1:
                return new FragmentListen(wordModelList, topic, subTopic, englishTranslArr, rusTranslArr);
            case 2:
                //return new FragmentTest( what_level, num_of_topic);
            case 3:
                //return new FragmentDefinition( what_level, num_of_topic);

            default:
                return new FragmentLearn(wordModelList, topic, subTopic, englishTranslArr, rusTranslArr);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
