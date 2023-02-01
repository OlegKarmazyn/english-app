package solid.icon.english.user_line;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import solid.icon.english.R;
import solid.icon.english.architecture.DividerItemDecorator;
import solid.icon.english.architecture.parents.ActivityGlobal;
import solid.icon.english.architecture.room.App;
import solid.icon.english.architecture.room.SubTopicDao;
import solid.icon.english.architecture.room.SubTopicModel;

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

        subTopicDao = App.getInstance().getDatabase().subTopicDao();

        chosenTopics = getIntent().getStringExtra(String.valueOf(KeysExtra.level));

        showActionBar(true, chosenTopics);

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
}
