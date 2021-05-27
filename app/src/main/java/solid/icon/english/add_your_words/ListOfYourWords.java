//package solid.icon.english.add_your_words;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import solid.icon.english.R;
//
//public class ListOfYourWords extends AppCompatActivity {
//
//    private EditText ed_new_topic;
//
//    private LinearLayout lay_new_topic;
//
//    private final static String TAG = "ListOfYourWords";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.list_of_your_words);
//
//
//        Log.d(TAG, "onCreate");
//
//
//        ed_new_topic = findViewById(R.id.ed_new_topic);
//        lay_new_topic = findViewById(R.id.lay_new_topic);
//    }
//
//
//
//    public void click_new_lay(View view) {
//        switch (view.getId()) {
//            case R.id.lay_new_topic:
//                add_new_topic();
//                break;
//
//        }
//    }
//
//        private void add_new_topic(){
//        int x = (int) ed_new_topic.getTextSize();
//            if(x > 3){
//                DBCreateNewTopic dbmove = new DBCreateNewTopic();
//
//            }else{
//
//            }
//
//        }
//
//
//
//
//    }//Hello!!! New Hello!!!!!!!!
