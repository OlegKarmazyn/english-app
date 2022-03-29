package solid.icon.english.user_line;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import solid.icon.english.R;
import solid.icon.english.architecture.ActivityGlobal;
import solid.icon.english.architecture.DividerItemDecorator;
import solid.icon.english.architecture.room.App;
import solid.icon.english.architecture.room.TopicModel;
import solid.icon.english.architecture.room.TopicModelDao;

public class UserLevel extends ActivityGlobal {

    int size = 10;

    RecyclerView recyclerView;
    String name_topic [] = new String[size];
    String level;
    boolean[] key_topics = new boolean[size];
    final Context context = this;
    TopicModelDao topicModelDao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topics_layout);

        topicModelDao = App.getInstance().getDatabase().topicModelDao();

        level = getIntent().getStringExtra(String.valueOf(KeysExtra.level));

        showActionBar(true, level);

        recyclerView = findViewById(R.id.recycleView);

        getNameTopic();

        setAdapter();
    }

    private void getNameTopic(){
        TopicModel topicModel = topicModelDao.getByTopicsName(level);

        name_topic[0] = topicModel.subTopicsName0;
        name_topic[1] = topicModel.subTopicsName1;
        name_topic[2] = topicModel.subTopicsName2;
        name_topic[3] = topicModel.subTopicsName3;
        name_topic[4] = topicModel.subTopicsName4;
        name_topic[5] = topicModel.subTopicsName5;
        name_topic[6] = topicModel.subTopicsName6;
        name_topic[7] = topicModel.subTopicsName7;
        name_topic[8] = topicModel.subTopicsName8;
        name_topic[9] = topicModel.subTopicsName9;

    }


    private void setAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecorator(ContextCompat.getDrawable(context, R.drawable.divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setNestedScrollingEnabled(false);

        setDataToUserAdapter();
    }

    public void setDataToUserAdapter(){
        UserAdapter userAdapter = new UserAdapter(context, name_topic, UserLevel.this);
        recyclerView.setAdapter(userAdapter);
    }

}
