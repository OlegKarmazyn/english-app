package solid.icon.english.architecture.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {WordModel.class, TopicModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WordModelDao wordModelDao();
    public abstract TopicModelDao topicModelDao();
}
