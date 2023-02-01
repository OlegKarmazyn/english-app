package solid.icon.english.architecture.parents;

import android.content.Context;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import solid.icon.english.R;

public abstract class ActivityGlobal extends AppCompatActivity {

    public enum LessonsName {
        A2, B1, B2
    }

    public enum KeysExtra {
        level,
        num_of_topic,
        title
    }

    protected Context context = this;
    protected String TAG = this.getClass().getSimpleName();

    protected void showActionBar(boolean isShow, String titleText) {
        ActionBar actionBar = getSupportActionBar();
        if (isShow) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(titleText);
        } else {
            actionBar.hide();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }
}


