package solid.icon.english.architecture.room;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {

    public static App instance;

    public static int item_selected = 0;

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this,
                AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
