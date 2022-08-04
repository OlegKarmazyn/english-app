package solid.icon.english.user_line.studying.fragments;

import android.os.Bundle;
import android.view.Gravity;
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

        setNotVisibleItem(0);
    }

    private void createAllComponents() {
        int dp_15 = getDp(15);

        for (int i = 0; i < size; i++) {
            final int randomInt = id[i];

            LinearLayout horizontalLayout = new LinearLayout(context);
            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
            horizontalLayout.setAlpha(0f);

            //params for horizontalLayout
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, dp_15, 0,0);
            horizontalLayout.setLayoutParams(layoutParams);

            //set params for buttons
            LinearLayout.LayoutParams engButtonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            engButtonParams.weight = 1;
            LinearLayout.LayoutParams rusButtonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            rusButtonParams.weight = 1;


            TextView textViewEng = new TextView(context),
                    textViewRus = new TextView(context);

            //set the properties for English button
            textViewEng.setText(englishTranslArr[randomInt]);
            textViewEng.setTextSize(15);
            textViewEng.setBackgroundResource(R.drawable.person_together);
            textViewEng.setPadding(getDp(5), dp_15, getDp(5), dp_15);
            textViewEng.setLayoutParams(engButtonParams);
            textViewEng.setGravity(Gravity.CENTER);
            textViewEng.setTextColor(getActivity().getColor(R.color.ios_black));


            //set the properties for Russian button
            textViewRus.setText(rusTranslArr[randomInt]);
            textViewRus.setTextSize(15);
            textViewRus.setBackgroundResource(R.drawable.person_together);
            textViewRus.setPadding(getDp(5), dp_15, getDp(5), dp_15);
            textViewRus.setLayoutParams(rusButtonParams);
            textViewRus.setGravity(Gravity.CENTER);
            textViewRus.setTextColor(getActivity().getColor(R.color.ios_black));

            //set margins
            engButtonParams.setMargins(0, 0, 0, 0);
            rusButtonParams.setMargins(getDp(10), 0, 0, 0);
            textViewEng.setVisibility(View.VISIBLE);
            textViewRus.setVisibility(View.GONE);


            horizontalLayout.addView(textViewRus);
            horizontalLayout.addView(textViewEng);
            verticalLinearLayout.addView(horizontalLayout);

            //todo ADD EditText

            horizontalLayout.animate().alpha(1f).setDuration(1300);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        //todo (I don`t know)
    }
}
