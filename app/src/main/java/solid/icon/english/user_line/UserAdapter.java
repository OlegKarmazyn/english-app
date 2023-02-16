package solid.icon.english.user_line;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import es.dmoral.toasty.Toasty;
import solid.icon.english.R;
import solid.icon.english.architecture.firebase.database.operations.FirebaseOperation;
import solid.icon.english.architecture.local_data.LocalOperation;
import solid.icon.english.architecture.parents.ActivityGlobal;
import solid.icon.english.architecture.room.App;
import solid.icon.english.architecture.room.SubTopicDao;
import solid.icon.english.architecture.room.SubTopicModel;
import solid.icon.english.user_line.studying.StudyActivity;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private static final String TAG = "UserAdapter";

    Context context;
    String[] titlesArray;
    boolean[] isCheckArray;
    UserLevel userLevel;
    int size;
    SubTopicDao subTopicDao;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    FirebaseOperation firebaseOperation = new FirebaseOperation();
    LocalOperation localOperation = new LocalOperation();

    public UserAdapter(Context context, String[] titlesArray, UserLevel userLevel) {
        this.context = context;
        this.titlesArray = titlesArray;
        this.userLevel = userLevel;
        subTopicDao = App.getInstance().getDatabase().subTopicDao();
        this.size = titlesArray.length;

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
        // last after init
        getIsCheckArray();
    }

    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_custom, parent, false);
        return new UserAdapter.MyViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        /*------------------------------settings------------------------------*/

        if (position == 0) {
            holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.row_top));
        } else {
            holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.row_middle));
        }

        if (position != size) {

            holder.title.setText(titlesArray[position]);
            holder.checkBox.setChecked(isCheckArray[position]);

            if (isCheckArray[position]) {
                holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.title.setPaintFlags(holder.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }

        } else {

            holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.row_bottom));
            holder.add_topic.setVisibility(View.VISIBLE);
            holder.title.setVisibility(View.GONE);
            holder.checkBox.setVisibility(View.GONE);
        }

        /*------------------------------components------------------------------*/

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {

            editor.putBoolean(userLevel.chosenTopics + position, isChecked);
            editor.apply();

            Log.d("holder.checkBox", userLevel.chosenTopics + position);

            if (isChecked) {
                holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.title.setPaintFlags(holder.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
        });

        holder.constraintLayout.setOnClickListener(v -> {
            if (position != size) {

                Intent intent = new Intent(context, StudyActivity.class);
                intent.putExtra(ActivityGlobal.KeysExtra.level.name(), userLevel.chosenTopics); //topics
                intent.putExtra(ActivityGlobal.KeysExtra.num_of_topic.name(), position); //position
                intent.putExtra(ActivityGlobal.KeysExtra.title.name(), holder.title.getText()); //title (subTopics)
                context.startActivity(intent);
                userLevel.overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);

            } else {
                showAddDialog();
            }
        });

        holder.constraintLayout.setOnLongClickListener(v -> {
            if (position != size)
                showDeleteDialog(position);
            return false;
        });

    }

    @Override
    public int getItemCount() {
        // add one because of ADD BUTTON is item
        return titlesArray.length + 1;
    }

    /*---------------------------------------MyViewHolder---------------------------------------*/

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        CheckBox checkBox;
        ImageView add_topic;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            checkBox = itemView.findViewById(R.id.checkBox);
            add_topic = itemView.findViewById(R.id.add_topic);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }

    /*-----------------------------------------getIsCheckArray--------------------------------*/

    public void getIsCheckArray() {
        isCheckArray = new boolean[size];
        for (int i = 0; i < size; i++) {
            String mod_key = userLevel.chosenTopics + i;
            isCheckArray[i] = preferences.getBoolean(mod_key, false);
            Log.d(TAG, "key_topics[" + i + "]" + isCheckArray[i]);
        }
    }

    /*-----------------------------------------dialogs-----------------------------------------*/
    /*----------------------------------Add Data----------------------------------*/
    public void showAddDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        final EditText editText = new EditText(context);
        alert.setTitle("Do you want to add topic?");
        alert.setMessage("Enter name for topic");

        alert.setView(editText);

        alert.setPositiveButton("Add", (dialog, whichButton) -> {
            String subTopicsName = editText.getText().toString().trim();
            SubTopicModel subTopicModel = subTopicDao.getByNames(userLevel.chosenTopics, subTopicsName);
            if (subTopicModel == null) {
                subTopicModel = new SubTopicModel();
                subTopicModel.topicsName = userLevel.chosenTopics;
                subTopicModel.subTopicsName = subTopicsName;
                subTopicDao.insert(subTopicModel);
                moveDataFB(userLevel.chosenTopics, subTopicsName);
            } else {
                Toasty.error(context, "\"" + subTopicsName + "\"" + " already exists").show();
            }
            userLevel.setDataToUserAdapter();
        });

        alert.setNegativeButton("Cancel", null);

        alert.show();
    }

    private void moveDataFB(String topicsName, String subTopicsName) {
        firebaseOperation.moveSubTopics(topicsName, subTopicsName);
    }

    /*----------------------------------Delete Data----------------------------------*/
    public void showDeleteDialog(int position) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Do you want to delete topic?");
        alert.setPositiveButton("Yes", (dialog, which) -> {
            localOperation.deleteSubTopic(userLevel.chosenTopics, titlesArray[position]);
            deleteDataFB(userLevel.chosenTopics, titlesArray[position]);
            userLevel.setDataToUserAdapter();
        });
        alert.show();
    }

    private void deleteDataFB(String topicsName, String subTopicsName) {
        firebaseOperation.deleteSubTopics(topicsName, subTopicsName);
    }
}
