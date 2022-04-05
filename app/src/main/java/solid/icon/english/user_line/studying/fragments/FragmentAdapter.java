package solid.icon.english.user_line.studying.fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {

    String topic; String subTopic;
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, String topic, String subTopic) {
        super(fragmentManager, lifecycle);
        this.topic = topic;
        this.subTopic = subTopic;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){

            case 1:
                //return new FragmentListen( what_level, num_of_topic);
            case 2:
                //return new FragmentTest( what_level, num_of_topic);
            case 3:
                //return new FragmentDefinition( what_level, num_of_topic);

            default:
                return new FragmentLearn( topic, subTopic);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
