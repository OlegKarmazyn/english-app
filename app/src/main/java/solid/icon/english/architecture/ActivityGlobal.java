package solid.icon.english.architecture;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import solid.icon.english.R;

public abstract class ActivityGlobal extends AppCompatActivity {

    protected Class backClass;
    protected Context context = this;
    protected abstract void setBackClass();

    protected void ShowActionBar(String titleText){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(titleText);
    }

    protected void prepareActivity(String titleText){
        ShowActionBar(titleText);
        setBackClass();
    }

    protected void nextStartActivity(Intent intent){
        startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }

    protected void backStartActivity(Intent intent){
        startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }

    protected void goBack(){
        Intent intent = new Intent(this, backClass);
        startActivity(intent);
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
        this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){

            case android.R.id.home:
                goBack();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed(){
        goBack();
    }
}


