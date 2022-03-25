package solid.icon.english.architecture.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PersonLife.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonLifeDao personLifeDao();
}
