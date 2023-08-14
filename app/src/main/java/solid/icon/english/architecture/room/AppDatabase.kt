package solid.icon.english.architecture.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [WordModel::class, TopicModel::class, SubTopicModel::class],
    version = 2,
    autoMigrations = [
        AutoMigration(
            from = 1,
            to = 2
        )
    ],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordModelDao(): WordModelDao
    abstract fun topicModelDao(): TopicModelDao
    abstract fun subTopicDao(): SubTopicDao
}

//class DeleteOldColumn : AutoMigrationSpec