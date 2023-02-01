package solid.icon.english.main_adapters;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.dmoral.toasty.Toasty;
import solid.icon.english.MainActivity;
import solid.icon.english.R;
import solid.icon.english.architecture.firebase.database.operations.FirebaseOperation;
import solid.icon.english.architecture.parents.ActivityGlobal;
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
    FirebaseOperation firebaseOperation;

    public AdapterUsers(Context context, String[] titlesArray, MainActivity mainActivity) {
        this.context = context;
        this.titlesArray = titlesArray;
        this.mainActivity = mainActivity;
        topicModelDao = App.getInstance().getDatabase().topicModelDao();
        firebaseOperation = new FirebaseOperation();

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
        //todo: redone!!! it does not work!!!
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {

            TopicModel topicModel = topicModelDao.getById(position + 1); //id starts from 1
            topicModel.isCheck = isChecked;
            topicModelDao.update(topicModel);

            if (isChecked) {
                holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.title.setPaintFlags(holder.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
        });

        holder.constraintLayout.setOnClickListener(v -> {
            if (position != size) {

                Intent intent = new Intent(context, UserLevel.class);
                intent.putExtra(ActivityGlobal.KeysExtra.level.name(), holder.title.getText().toString());
                intent.putExtra(ActivityGlobal.KeysExtra.num_of_topic.name(), position);
                context.startActivity(intent);
                mainActivity.overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);

            } else {
                showCustomAddingDialog();
            }
        });

        holder.constraintLayout.setOnLongClickListener(v -> {
            /* DELETE if not one element(adding button)*/
            if (size != 1)
                showDeleteDialog(position);
            return false;
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

    private void getIsCheckArray() {
        isCheckArray = new boolean[titlesArray.length];
        List<TopicModel> topicModelList = topicModelDao.getAll();

        for (int i = 0, len = titlesArray.length; i < len; i++) {
            isCheckArray[i] = topicModelList.get(i).isCheck;
            Log.d(TAG, "isCheckArray[" + i + "] = " + isCheckArray[i]);
        }
    }

    /*-----------------------------------------dialogs-----------------------------------------*/

    /*----------------------------------Add Data----------------------------------*/
    public void showCustomAddingDialog() {
        Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.adding_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tvCancel = dialog.findViewById(R.id.tvCancel),
                tvAdd = dialog.findViewById(R.id.tvAdd);
        Spinner spinner = dialog.findViewById(R.id.spinner);
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(context, R.array.country,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        tvAdd.setOnClickListener(v -> {
            EditText etName = dialog.findViewById(R.id.etName);
            String topicsName = etName.getText().toString().trim();
            dialog.dismiss();

            if (topicsName.charAt(0) == '-' && topicsName.length() == 20) {
                getDataFB(topicsName);
            } else {
                if (topicModelDao.getByTopicsName(topicsName) == null) {
                    insertNewTopics(topicsName, spinner.getSelectedItem().toString());
                    moveDataFB(topicsName);
                } else {
                    Toasty.error(context, "\"" + topicsName + "\"" + " already exists").show();
                }
                mainActivity.setDataToUserAdapter();
            }
        });

        tvCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void moveDataFB(String topicsName) {
        firebaseOperation.moveTopics(topicsName);
    }

    private void getDataFB(String key) {
        firebaseOperation.getTopicsWithAllData(key, topicModel -> {
            insertNewTopics(topicModel);
            mainActivity.setDataToUserAdapter();
        });
    }

    private void insertNewTopics(TopicModel topicModel) {
        topicModelDao.insert(topicModel);
    }

    private void insertNewTopics(String topicsName, String country) {
        TopicModel topicModel = new TopicModel();
        topicModel.topicsName = topicsName;
        topicModel.country = country;
        topicModel.topicsKey = ""; //empty!
        topicModelDao.insert(topicModel);
    }


    /*----------------------------------Delete Data----------------------------------*/ // TODO: 02.02.2023 make it better
    public void showDeleteDialog(int position) {
        TopicModel topicModel = topicModelDao.getByTopicsName(titlesArray[position]);
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("What do you want to do?");
        EditText editText = new EditText(context);
        editText.setText(topicModel.topicsKey);
        editText.setHint("public key");
        editText.setEnabled(false);
        alert.setView(editText);

        alert.setPositiveButton("Delete", (dialog, which) -> {
            topicModelDao.delete(topicModel);
            mainActivity.setDataToUserAdapter();
            deleteDataFB(titlesArray[position]);
        });
        alert.setNegativeButton("Copy key", (dialog, which) -> {
            ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = android.content.ClipData.newPlainText("Copied", topicModel.topicsKey);
            clipboard.setPrimaryClip(clip);
            Toasty.success(context, "Key successfully copied").show();
        });
        alert.show();
    }

    private void deleteDataFB(String topicsName) {
        firebaseOperation.deleteTopics(topicsName);
    }

    //todo add post words method to firebase with already exists data
}
