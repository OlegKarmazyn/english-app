package solid.icon.english.architecture.parents;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import es.dmoral.toasty.Toasty;
import solid.icon.english.R;
import solid.icon.english.setting.SettingsActivity;

public abstract class ActivityGlobal extends AppCompatActivity {

    public enum LessonsName {
        A2, B1, B2
    }

    public enum KeysExtra {
        level,
        num_of_topic,
        topicsKey,
        title,
        isSubTest
    }

    protected Context context = this;

    protected String TAG = this.getClass().getSimpleName();

    protected void showActionBarWithoutTitle(boolean isShow) {
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        if (isShow) {
            actionBar.setTitle("");
        } else {
            actionBar.hide();
        }
    }

    public boolean doesInternetConnectionExist() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        if (!isConnected)
            Toasty.warning(context, R.string.no_internet_connection).show();
        return isConnected;
    }

    public void hideSoftKeyboard(EditText input) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
    }

    public void showSoftKeyboard(EditText editText) {
        editText.requestFocus(); // set focus to the edit text
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT); // show the keyboard
        }
    }

    protected void showActionBar(boolean isShow, String titleText) {
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        if (isShow) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(titleText);
        } else {
            actionBar.hide();
        }
    }

    private void startUserAcc() {
        startActivity(new Intent(context, SettingsActivity.class));
        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }

    @SuppressLint("NonConstantResourceId")
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


