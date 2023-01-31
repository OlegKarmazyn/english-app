package solid.icon.english.architecture.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

@Database(
    entities = [WordModel::class, TopicModel::class, SubTopicModel::class],
    version = 3,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(
            from = 2,
            to = 3,
            DeleteOldColumn::class
        )
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordModelDao(): WordModelDao?
    abstract fun topicModelDao(): TopicModelDao?
    abstract fun subTopicDao(): SubTopicDao?
}

@DeleteColumn(tableName = "TopicModel", columnName = "subTopicsName0")
@DeleteColumn(tableName = "TopicModel", columnName = "subTopicsName1")
@DeleteColumn(tableName = "TopicModel", columnName = "subTopicsName2")
@DeleteColumn(tableName = "TopicModel", columnName = "subTopicsName3")
@DeleteColumn(tableName = "TopicModel", columnName = "subTopicsName4")
@DeleteColumn(tableName = "TopicModel", columnName = "subTopicsName5")
@DeleteColumn(tableName = "TopicModel", columnName = "subTopicsName6")
@DeleteColumn(tableName = "TopicModel", columnName = "subTopicsName7")
@DeleteColumn(tableName = "TopicModel", columnName = "subTopicsName8")
@DeleteColumn(tableName = "TopicModel", columnName = "subTopicsName9")
class DeleteOldColumn : AutoMigrationSpec