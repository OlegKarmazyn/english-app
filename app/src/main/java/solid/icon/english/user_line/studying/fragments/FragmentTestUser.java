package solid.icon.english.user_line.studying.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import solid.icon.english.R;
import solid.icon.english.architecture.UserFragmentActivity;
import solid.icon.english.architecture.room.App;
import solid.icon.english.architecture.room.WordModel;
import solid.icon.english.architecture.room.WordModelDao;
import solid.icon.english.user_line.studying.StudyActivity;

public class FragmentTestUser extends UserFragmentActivity {

    protected FragmentTestUser(List<WordModel> wordModelList, String topic, String subTopic, StudyActivity studyActivity) {
        super(wordModelList, topic, subTopic, studyActivity); //empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.user_fragment_learn, container, false);
    }

    ScrollView scrollView;

    LinearLayout verticalLinearLayout;

    List<TextView> buttonListOfEnglish = new ArrayList<>(),
            buttonListOfRus = new ArrayList<>();

    WordModelDao wordModelDao;

    private boolean isCreate = false;

    @Override
    public void onResume() {
        super.onResume();

        context = getActivity();

        assert context != null;
        scrollView = context.findViewById(R.id.scrollView);
        verticalLinearLayout = context.findViewById(R.id.verticalLinearLayout);

        wordModelDao = App.instance.getDatabase().wordModelDao();

        /* methods after init */
        if(!isCreate) {
            isCreate = true;

            fillArrays();
            randomizeArray();

            createAllComponents();
        }
    }

    private void createAllComponents() {
        int dp_15 = getDp(15);

        for (int i = 0; i < size; i++) {
            final int randomInt = id[i];


        }
    }


    @Override
    public void onPause() {
        super.onPause();
        //todo (I don`t know)
    }
}
