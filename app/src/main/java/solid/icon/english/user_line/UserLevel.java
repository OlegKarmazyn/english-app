package solid.icon.english.user_line;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.dmoral.toasty.Toasty;
import solid.icon.english.R;
import solid.icon.english.architecture.DividerItemDecorator;
import solid.icon.english.architecture.firebase.database.operations.FirebaseOperation;
import solid.icon.english.architecture.firebase.database.operations.RecipientOperation;
import solid.icon.english.architecture.parents.ActivityGlobal;
import solid.icon.english.architecture.room.App;
import solid.icon.english.architecture.room.SubTopicDao;
import solid.icon.english.architecture.room.SubTopicModel;
import solid.icon.english.architecture.room.TopicModel;
import solid.icon.english.user_line.studying.StudyActivity;

public class UserLevel extends ActivityGlobal {

    RecyclerView recyclerView;
    RelativeLayout loading_layout;
    String[] name_topic;
    String chosenTopics;
    final Context context = this;
    SubTopicDao subTopicDao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topics_layout);

        subTopicDao = App.getInstance().getDatabase().subTopicDao();
        chosenTopics = getIntent().getStringExtra(String.valueOf(KeysExtra.level));
        recyclerView = findViewById(R.id.recycleView);
        loading_layout = findViewById(R.id.loading_layout);

        showActionBar(true, chosenTopics);
        setAdapter();
    }

    private void getNameTopic() {
        List<SubTopicModel> subTopicModels = subTopicDao.getAllByTopicsName(chosenTopics);

        name_topic = new String[subTopicModels.size()];
        int iterator = 0;
        for (SubTopicModel s : subTopicModels) {
            name_topic[iterator] = s.subTopicsName;
            iterator += 1;
        }
    }

    private void setAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecorator(ContextCompat.getDrawable(context, R.drawable.divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setNestedScrollingEnabled(false);

        setDataToUserAdapter();
    }

    public void setDataToUserAdapter() {
        getNameTopic();

        recyclerView.setAlpha(0f);

        UserAdapter userAdapter = new UserAdapter(context, name_topic, UserLevel.this);
        recyclerView.setAdapter(userAdapter);

        recyclerView.animate().alpha(1f);
    }

    private void updateSubTopics() {
        TopicModel topicModel = App.getInstance().getDatabase().topicModelDao().getByTopicsName(chosenTopics);
        if (topicModel.topicsKey == null)
            return;

        setLoadingVisible(true);
        new RecipientOperation().getAllData(topicModel.topicsKey, topicModel.topicsName, () -> {
            setLoadingVisible(false);
            setDataToUserAdapter();
        });
    }

    private void goToStudy() {
        Intent intent = new Intent(context, StudyActivity.class);
        intent.putExtra(ActivityGlobal.KeysExtra.level.name(), chosenTopics); //topics
        intent.putExtra(ActivityGlobal.KeysExtra.isSubTest.name(), true);
        intent.putExtra(ActivityGlobal.KeysExtra.title.name(), "Testing"); //title (subTopics)
        context.startActivity(intent);
        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }

    private void setLoadingVisible(boolean isLoading) {
        if (isLoading)
            loading_layout.setVisibility(View.VISIBLE);
        else
            loading_layout.setVisibility(View.GONE);
    }

    private void uploadSubTopics() {
        new FirebaseOperation().postData(chosenTopics);
        Toasty.info(context, "Sending data ✓").show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sub_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.download_data:
                updateSubTopics();
                return true;
            case R.id.upload_data:
                uploadSubTopics();
                return true;
            case R.id.test_subTopics:
                goToStudy();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
