package solid.icon.english.main_adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
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

import java.util.List;

import solid.icon.english.MainActivity;
import solid.icon.english.R;
import solid.icon.english.architecture.ActivityGlobal;
import solid.icon.english.architecture.room.App;
import solid.icon.english.architecture.room.TopicModel;
import solid.icon.english.architecture.room.TopicModelDao;
import solid.icon.english.user_line.UserLevel;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.MyViewHolder> {

    String TAG = "AdapterUsers";

    Context context;
    String[] titlesArray;
    boolean[] isCheckArray;
    MainActivity mainActivity;
    int size;
    TopicModelDao topicModelDao;

    public AdapterUsers(Context context, String[] titlesArray, MainActivity mainActivity) {
        this.context = context;
        this.titlesArray = titlesArray;
        this.mainActivity = mainActivity;
        topicModelDao = App.getInstance().getDatabase().topicModelDao();

        getIsCheckArray(); // last after init
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_custom, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        /*------------------------------settings------------------------------*/

        if (position == 0) {
            holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.row_top));
        } else {
            holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.row_middle));
        }

        if(position != size){

            holder.title.setText(titlesArray[position]);
            holder.checkBox.setChecked(isCheckArray[position]);

            if (isCheckArray[position]) {
                holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.title.setPaintFlags(holder.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }

        }else {

            holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.row_bottom));
            holder.add_topic.setVisibility(View.VISIBLE);
            holder.title.setVisibility(View.GONE);
            holder.checkBox.setVisibility(View.GONE);
        }

        /*------------------------------components------------------------------*/

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                TopicModel topicModel = topicModelDao.getById(position + 1);
                topicModel.isCheck = isChecked;
                topicModelDao.update(topicModel);

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
                    mainActivity.overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);

                }else{
                    showAddDialog();
                }
            }
        });

        holder.constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                /* DELETE */
                /* DELETE */
                showDeleteDialog(position);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        size = titlesArray.length;
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

    private void getIsCheckArray(){
        isCheckArray = new boolean[titlesArray.length];
        List<TopicModel> topicModelList = topicModelDao.getAll();

        for (int i = 0, len = titlesArray.length; i < len; i++) {
            isCheckArray[i] = topicModelList.get(i).isCheck;
            Log.d(TAG, "isCheckArray[" + i + "] = " + isCheckArray[i]);
        }
    }

    /*-----------------------------------------dialogs-----------------------------------------*/

     public void showAddDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        final EditText edittext = new EditText(context);
        alert.setTitle("Do you want to add topic?");
        alert.setMessage("Enter name for topic");

        alert.setView(edittext);

        alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                String editText = edittext.getText().toString();

                TopicModel topicModel = new TopicModel();
                topicModel.topicsName = editText;
                topicModelDao.insert(topicModel);

                mainActivity.setDataToUserAdapter();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        alert.show();
    }

    public void showDeleteDialog(int position){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Do you want to delete topic?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TopicModel topicModel = topicModelDao.getByTopicsName(titlesArray[position]);
                topicModelDao.delete(topicModel);
            }
        });
        alert.show();
    }
}
