package solid.icon.english.main_adapters;


import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.logging.Handler;

import es.dmoral.toasty.Toasty;
import solid.icon.english.MainActivity;
import solid.icon.english.R;
import solid.icon.english.architecture.firebase.database.operations.FirebaseOperation;
import solid.icon.english.architecture.local_data.LocalOperation;
import solid.icon.english.architecture.local_data.PreferencesOperations;
import solid.icon.english.architecture.parents.ActivityGlobal;
import solid.icon.english.architecture.room.App;
import solid.icon.english.architecture.room.TopicModel;
import solid.icon.english.architecture.room.TopicModelDao;
import solid.icon.english.dialogs.AddingDialog;
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
    LocalOperation localOperation;
    PreferencesOperations preferencesOperations;

    public AdapterUsers(Context context, String[] titlesArray, MainActivity mainActivity) {
        this.context = context;
        this.titlesArray = titlesArray;
        this.mainActivity = mainActivity;
        topicModelDao = App.getInstance().getDatabase().topicModelDao();
        firebaseOperation = new FirebaseOperation();
        localOperation = new LocalOperation();
        preferencesOperations = new PreferencesOperations();

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
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {

            TopicModel topicModel = topicModelDao.getByTopicsName(titlesArray[position]);
            topicModel.isCheck = isChecked;
            topicModelDao.upsert(topicModel);
            isCheckArray[position] = isChecked;

            if (isChecked) {
                holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.title.setPaintFlags(holder.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
        });

        holder.constraintLayout.setOnClickListener(v -> {
            if (position != size) {
                TopicModel topicModel = topicModelDao.getByTopicsName(titlesArray[position]);
                Intent intent = new Intent(context, UserLevel.class);
                intent.putExtra(ActivityGlobal.KeysExtra.level.name(), holder.title.getText().toString());
                intent.putExtra(ActivityGlobal.KeysExtra.topicsKey.name(), topicModel.topicsKey);
                context.startActivity(intent);
                mainActivity.overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);

            } else {
                showCustomAddingDialog();
            }
        });

        holder.constraintLayout.setOnLongClickListener(v -> {
            /* DELETE if not one element(adding button)*/
            if (position != size)
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
        AddingDialog dialog = new AddingDialog(context);

        dialog.setPositiveButton(R.string.add, v -> {
            String topicsName = dialog.getTextFromField();
            if (topicsName.isEmpty()) {
                Toasty.error(context, context.getString(R.string.name_filed_is_empty)).show();
                return;
            }

            if (topicsName.charAt(0) == '-' && topicsName.length() == 20) {
                getDataFB(topicsName, dialog.getSelectedItem());
            } else {
                if (topicModelDao.getByTopicsName(topicsName) == null) {
                    insertNewTopics(topicsName, dialog.getSelectedItem());
                } else {
                    Toasty.error(context, "\"" + topicsName + "\"" + " already exists").show();
                }
                mainActivity.setDataToUserAdapter();
            }
        });
        dialog.setNegativeButton(R.string.cancel, null);
        dialog.show();
        dialog.getEditText().postDelayed(() -> {
            mainActivity.showSoftKeyboard(dialog.getEditText());
        }, 200);
    }

    private void getDataFB(String key, String country) {
        if (!mainActivity.doesInternetConnectionExist())
            return;

        mainActivity.setLoadingVisible(true);
        Toasty.info(context, context.getString(R.string.loading)).show();
        firebaseOperation.getTopicsWithAllData(key, topicModel -> {
            topicModel.country = country;
            insertNewTopics(topicModel);
            mainActivity.setLoadingVisible(false);
            mainActivity.setDataToUserAdapter();
        });
    }

    private void insertNewTopics(TopicModel topicModel) {
        topicModelDao.upsert(topicModel);
    }

    private void insertNewTopics(String topicsName, String country) {
        TopicModel topicModel = new TopicModel();
        topicModel.topicsName = topicsName;
        topicModel.country = country;
        topicModel.topicsKey = null; //empty!
        topicModelDao.upsert(topicModel);
    }


    /*----------------------------------Delete Data----------------------------------*/
    public void showDeleteDialog(int position) {
        TopicModel topicModel = topicModelDao.getByTopicsName(titlesArray[position]);

        AddingDialog dialog = new AddingDialog(context);

        dialog.setTitle(R.string.what_do_you_want_to_do);
        dialog.getEditText().setText(topicModel.topicsKey);
        dialog.getEditText().setHint("empty key");
        dialog.getEditText().setEnabled(false);

        dialog.setNegativeButton("Delete", v -> {
            String toastText = "\"" + topicModel.topicsName + "\" deleted";
            Toast.makeText(context, toastText, Toast.LENGTH_LONG).show();
            localOperation.deleteTopic(topicModel.topicsName);
            mainActivity.setDataToUserAdapter();
            deleteDataFB(titlesArray[position]);
        });

        if (topicModel.topicsKey != null) {
            Log.e(TAG, "topicModel.topicsKey " + topicModel.topicsKey + ".");
            dialog.setPositiveButton("Share", (v -> sharedKey(topicModel.topicsKey)));
            dialog.setNeutralButton("Copy key", v -> {
                ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = android.content.ClipData.newPlainText("Copied", topicModel.topicsKey);
                clipboard.setPrimaryClip(clip);
                Toasty.success(context, context.getString(R.string.successfully_copied)).show();
            });
        } else {
            dialog.setPositiveButton("Post", (v -> {
                postFB(topicModel.topicsName);
            }));
        }
        dialog.show();
    }

    private void sharedKey(String key) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.english_share_key));
        String shareMessage = key + "\n";
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.english_share_key)));
    }

    private void deleteDataFB(String topicsName) {
        firebaseOperation.deleteTopics(topicsName);
    }

    private void postFB(String topicsName) {
        if (!mainActivity.doesInternetConnectionExist())
            return;

        if (preferencesOperations.getEmail() == null) {
            Toasty.warning(context, context.getString(R.string.can_post_after_registration)).show();
            return;
        }
        if (preferencesOperations.getAllowedTopics() > 0) {
            Toasty.info(context, context.getString(R.string.successfully_uploaded)).show();
            preferencesOperations.decreaseAllowedTopics(1);
            firebaseOperation.postData(topicsName);
        } else {
            Toasty.warning(context, context.getString(R.string.limited_amount_of_topics)).show();
        }
    }
}
