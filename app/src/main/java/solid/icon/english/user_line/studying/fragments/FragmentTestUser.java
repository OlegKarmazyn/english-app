package solid.icon.english.user_line.studying.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import solid.icon.english.R;
import solid.icon.english.architecture.parents.UserFragmentActivity;
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

    List<LinearLayout> linearLayoutArrayList = new ArrayList<>();
    List<EditText> editTextList = new ArrayList<>();
    List<TextView> textViewList = new ArrayList<>();

    WordModelDao wordModelDao;

    private boolean isCreate = false;

    private Drawable drawable;

    @Override
    public void onResume() {
        super.onResume();

        context = getActivity();

        assert context != null;
        scrollView = context.findViewById(R.id.scrollView);
        verticalLinearLayout = context.findViewById(R.id.verticalLinearLayout);

        wordModelDao = App.instance.getDatabase().wordModelDao();

        /* methods after init */
        if (!isCreate) {
            isCreate = true;

            createAllComponents();
            createCheckButton();
        }

        setNotVisibleItem(0);
    }

    private void createAllComponents() {
        int dp_15 = getDp(15);

        for (int i = 0; i < size; i++) {
            final int randomInt = id[i];

            LinearLayout horizontalLayout = new LinearLayout(context);
            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);

            //params for horizontalLayout
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, dp_15, 0, 0);
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
            rusButtonParams.setMargins(0, 0, 0, 0);
            engButtonParams.setMargins(getDp(10), 0, 0, 0);
            textViewRus.setVisibility(View.VISIBLE);
            textViewEng.setVisibility(View.GONE);

            textViewList.add(textViewEng);
            horizontalLayout.addView(textViewRus);
            horizontalLayout.addView(textViewEng);

            linearLayoutArrayList.add(horizontalLayout);
            verticalLinearLayout.addView(horizontalLayout);


            EditText editText = new EditText(context);
            LinearLayout.LayoutParams layoutParams_editText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams_editText.setMargins(0, dp_15, 0, 0);
            editText.setLayoutParams(layoutParams_editText);
            editText.setGravity(Gravity.CENTER);
            editText.setTextColor(getActivity().getColor(R.color.ios_black));
            editText.setTextLocale(Locale.ENGLISH); //todo check


            editTextList.add(editText);
            verticalLinearLayout.addView(editText);

            drawable = editText.getBackground(); //back
        }
    }

    private void createCheckButton() {
        //counting margin
        int dp_15 = getDp(15);
        outLog("dp_15 = " + dp_15);

        LinearLayout horizontalLayout = new LinearLayout(context);
        horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
        horizontalLayout.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, dp_15, 0, 0);

        horizontalLayout.setLayoutParams(layoutParams);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        //set the properties for check button
        Button button = new Button(context);
        button.setLayoutParams(params);
        button.setText("Check");
        button.setTextSize(15);
        button.setBackgroundResource(R.drawable.person_together);
        button.setPadding(getDp(5), dp_15, getDp(5), dp_15);
        button.setGravity(Gravity.CENTER);

        button.setOnClickListener(v -> {
            int counter = 0;
            for (int i = 0; i < size; i++) {
                int index = id[i];
                if (editTextList.get(i).getText().toString().trim().equals(englishTranslArr[index])) {
                    editTextList.get(i).setBackgroundResource(R.color.back_true);
                    counter++;
                } else {
                    editTextList.get(i).setBackgroundResource(R.color.back_false);
                }
            }
            for (TextView t : textViewList) {
                t.setVisibility(View.VISIBLE);
            }
            Toasty.success(context, "Correct answers " + counter + " of " + englishTranslArr.length, Toast.LENGTH_LONG).show();
        });

        //adding layout to the layout verticalLinearLayout
        horizontalLayout.addView(button);
        verticalLinearLayout.addView(horizontalLayout);

    }

    private void setNaturalDesign() {
        for (EditText e : editTextList) {
            e.setBackground(drawable);
            e.setText("");
        }

        for (TextView t : textViewList) {
            t.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        setNaturalDesign();
    }
}
