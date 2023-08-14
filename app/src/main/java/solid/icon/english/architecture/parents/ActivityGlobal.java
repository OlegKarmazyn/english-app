package solid.icon.english.architecture.parents;

import static solid.icon.english.main_adapters.CustomScaleTransition.enterTransitionSet;
import static solid.icon.english.main_adapters.CustomScaleTransition.exitTransitionSet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

import es.dmoral.toasty.Toasty;
import solid.icon.english.R;

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
        setTransition();
    }

    public boolean doesInternetConnectionExist() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        if (!isConnected)
            Toasty.warning(context, R.string.no_internet_connection).show();
        return isConnected;
    }

    public boolean doesInternetConnectionExist(boolean isShowToast) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        if (!isConnected && isShowToast)
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

        setTransition();
    }

    private void setTransition() {
        Log.e(TAG, "setTransition");
        getWindow().setEnterTransition(enterTransitionSet);
        getWindow().setExitTransition(exitTransitionSet);
    }

//    protected void goToAccount() {
//        startActivity(new Intent(context, AuthActivity.class),
//                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
//    }


    protected void goToActivity(Intent intent) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context);
        context.startActivity(intent, options.toBundle());
    }

    protected void goToActivity(Class<?> toActivity) {
        Intent intent = new Intent(context, toActivity);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context);
        context.startActivity(intent, options.toBundle());
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finishAfterTransition();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
    }
}


