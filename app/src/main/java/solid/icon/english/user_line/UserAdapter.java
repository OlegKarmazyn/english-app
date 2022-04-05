package solid.icon.english.user_line;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import solid.icon.english.R;
import solid.icon.english.architecture.ActivityGlobal;
import solid.icon.english.architecture.room.App;
import solid.icon.english.architecture.room.TopicModel;
import solid.icon.english.architecture.room.TopicModelDao;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    String TAG = "AdapterUsers";

    Context context;
    String[] titlesArray;
    boolean[] isCheckArray;
    UserLevel userLevel;
    int size;
    TopicModelDao topicModelDao;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public UserAdapter(Context context, String[] titlesArray, int size, UserLevel userLevel) {
        this.context = context;
        this.titlesArray = titlesArray;
        this.userLevel = userLevel;
        topicModelDao = App.getInstance().getDatabase().topicModelDao();
        this.size = size;

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();

        getIsCheckArray(); // last after init
    }

    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_custom, parent, false);
        return new UserAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        /*------------------------------settings------------------------------*/

        if(position != size){

            holder.title.setText(titlesArray[position]);
            holder.checkBox.setChecked(isCheckArray[position]);

            if (position == 0) {
                holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.row_top));
            } else if (position == titlesArray.length - 1) {
                holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.row_bottom));
            } else {
                holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.row_middle));
            }

            if (isCheckArray[position]) {
                holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.title.setPaintFlags(holder.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }

        }else {

            holder.add_topic.setVisibility(View.VISIBLE);
            holder.title.setVisibility(View.GONE);
            holder.checkBox.setVisibility(View.GONE);
        }

        /*------------------------------components------------------------------*/

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                editor.putBoolean(userLevel.level + position, isChecked);
                editor.apply();

                Log.d("holder.checkBox", userLevel.level + position);

                if (isChecked) {
                    holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    holder.title.setPaintFlags(holder.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }
            }
        });

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position != size){

                    Intent intent = new Intent(context, UserLevel.class);
                    intent.putExtra(ActivityGlobal.KeysExtra.level.name(), holder.title.getText().toString());
                    intent.putExtra(ActivityGlobal.KeysExtra.num_of_topic.name(), position);
                    context.startActivity(intent);
                    userLevel.overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);

                }else{
                    showAddDialog(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return size + 1;
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

    public void getIsCheckArray(){
        isCheckArray = new boolean[size];
        for(int i = 0; i < size; i++){
            String mod_key = userLevel.level + i;
            isCheckArray[i] = preferences.getBoolean(mod_key, false);
            Log.d(TAG, "key_topics[" + i + "]" + isCheckArray[i]);
        }
    }

    /*-----------------------------------------dialogs-----------------------------------------*/

    public void showAddDialog(int position){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        final EditText edittext = new EditText(context);
        alert.setTitle("Do you want to add topic?");
        alert.setMessage("Enter name for topic");

        alert.setView(edittext);

        alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                String editText = edittext.getText().toString();

                TopicModel topicModel = topicModelDao.getByTopicsName(userLevel.level);

                switch (position) {
                    case 0: topicModel.subTopicsName0 = editText; break;
                    case 1: topicModel.subTopicsName1 = editText; break;
                    case 2: topicModel.subTopicsName2 = editText; break;
                    case 3: topicModel.subTopicsName3 = editText; break;
                    case 4: topicModel.subTopicsName4 = editText; break;
                    case 5: topicModel.subTopicsName5 = editText; break;
                    case 6: topicModel.subTopicsName6 = editText; break;
                    case 7: topicModel.subTopicsName7 = editText; break;
                    case 8: topicModel.subTopicsName8 = editText; break;
                    case 9: topicModel.subTopicsName9 = editText; break;
                }
                topicModelDao.update(topicModel);

                userLevel.setDataToUserAdapter();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        alert.show();
    }
}
