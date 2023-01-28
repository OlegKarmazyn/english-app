package solid.icon.english.architecture.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WordModel::class, TopicModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordModelDao(): WordModelDao?
    abstract fun topicModelDao(): TopicModelDao?
}