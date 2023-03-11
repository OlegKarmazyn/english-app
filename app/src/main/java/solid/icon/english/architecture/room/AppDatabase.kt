package solid.icon.english.architecture.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

@Database(
    entities = [WordModel::class, TopicModel::class, SubTopicModel::class],
    version = 1,
    exportSchema = true
//    autoMigrations = [
//        AutoMigration(
//            from = 1,
//            to = 3,
//            DeleteOldColumn::class
//        )
//    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordModelDao(): WordModelDao?
    abstract fun topicModelDao(): TopicModelDao?
    abstract fun subTopicDao(): SubTopicDao?
}

class DeleteOldColumn : AutoMigrationSpec