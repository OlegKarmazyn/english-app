package solid.icon.english.architecture.parents;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import solid.icon.english.R;
import solid.icon.english.setting.SettingsActivity;
import solid.icon.english.architecture.firebase.StaticData;

public abstract class ActivityGlobal extends AppCompatActivity {

    public enum LessonsName {
        A2, B1, B2
    }

    public enum KeysExtra {
        level,
        num_of_topic,
        title,
        isSubTest
    }

    protected Context context = this;
    protected String TAG = this.getClass().getSimpleName();

    @Override
    protected void onResume() {
        super.onResume();
        updateEmail();
    }

    protected void showActionBarWithoutTitle(boolean isShow){
        ActionBar actionBar = getSupportActionBar();
        if (isShow) {
            actionBar.setTitle("");
        } else {
            actionBar.hide();
        }
    }

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

    protected void updateEmail() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        StaticData.email = preferences.getString("email", "admin@gmail.com");
    }

    private void startUserAcc() {
        startActivity(new Intent(context, SettingsActivity.class));
        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
                return true;
            case R.id.person:
                startUserAcc();
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


