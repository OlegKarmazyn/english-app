package solid.icon.english.architecture.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [WordModel::class, TopicModel::class],
    version = 1,
    exportSchema = true,
//    autoMigrations = [
//        AutoMigration(
//            from = 1,
//            to = 2
//        )
//    ]
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun wordModelDao(): WordModelDao?
    abstract fun topicModelDao(): TopicModelDao?
}