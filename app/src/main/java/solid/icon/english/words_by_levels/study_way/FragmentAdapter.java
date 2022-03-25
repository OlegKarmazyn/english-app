package solid.icon.english.words_by_levels.study_way;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.io.Serializable;

public class FragmentAdapter extends FragmentStateAdapter {

    Serializable what_level; int num_of_topic;
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, Serializable what_level, int num_of_topic) {
        super(fragmentManager, lifecycle);
        this.what_level = what_level;
        this.num_of_topic = num_of_topic;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){

            case 1:
                return new FragmentListen( what_level, num_of_topic);
            case 2:
                return new FragmentTest( what_level, num_of_topic);
            case 3:
                return new FragmentDefinition( what_level, num_of_topic);

            default:
                return new FragmentLearn( what_level, num_of_topic);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
