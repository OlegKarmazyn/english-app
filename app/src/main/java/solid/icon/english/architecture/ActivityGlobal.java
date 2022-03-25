package solid.icon.english.architecture;

import android.content.Context;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public abstract class ActivityGlobal extends AppCompatActivity {

    public enum LessonsName{
        A2, B1, B2
    }

    public enum KeysExtra{
        level,
        num_of_topic
    }

    protected Context context = this;

    protected void showActionBar(boolean isShow, String titleText){
        ActionBar actionBar = getSupportActionBar();
        if(isShow) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(titleText);
        }else{
            actionBar.hide();
        }

    }
}


