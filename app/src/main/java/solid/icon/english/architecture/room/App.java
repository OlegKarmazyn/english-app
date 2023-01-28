package solid.icon.english.architecture.room;

import android.app.Application;
import android.util.DisplayMetrics;

import androidx.room.Room;

import solid.icon.english.architecture.Dpi;

public class App extends Application {

    public static App instance;

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this,
                AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();

        setDp();
    }

    private void setDp() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        Dpi.metrics = metrics.density;
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
