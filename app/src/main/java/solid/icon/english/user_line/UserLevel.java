package solid.icon.english.user_line;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import solid.icon.english.R;
import solid.icon.english.architecture.DividerItemDecorator;
import solid.icon.english.architecture.firebase.database.interfaces.OnSuccessListener;
import solid.icon.english.architecture.firebase.database.operations.RecipientOperation;
import solid.icon.english.architecture.local_data.LocalOperation;
import solid.icon.english.architecture.parents.ActivityGlobal;
import solid.icon.english.architecture.room.App;
import solid.icon.english.architecture.room.SubTopicDao;
import solid.icon.english.architecture.room.SubTopicModel;
import solid.icon.english.architecture.room.TopicModel;

public class UserLevel extends ActivityGlobal {

    RecyclerView recyclerView;
    String[] name_topic;
    String chosenTopics;
    final Context context = this;
    SubTopicDao subTopicDao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topics_layout);
        showActionBar(true, chosenTopics);

        subTopicDao = App.getInstance().getDatabase().subTopicDao();
        chosenTopics = getIntent().getStringExtra(String.valueOf(KeysExtra.level));
        recyclerView = findViewById(R.id.recycleView);

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
        LocalOperation localOperation = new LocalOperation();
        for (String sub: name_topic) {
            localOperation.deleteSubTopic(chosenTopics, sub);
        }
        TopicModel topicModel = App.getInstance().getDatabase().topicModelDao().getByTopicsName(chosenTopics);
        new RecipientOperation().getAllData(topicModel.topicsKey, topicModel.topicsName, this::setDataToUserAdapter);
        Toasty.info(context, "Loading...").show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sub_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.download_data:
                updateSubTopics();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
