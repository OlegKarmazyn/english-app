package solid.icon.english.architecture.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordModelDao {

    @Query("SELECT * FROM WordModel")
    List<WordModel> getAll();

    @Query("SELECT * FROM WordModel WHERE id = :id")
    WordModel getById(long id);

    @Query("SELECT * FROM WordModel WHERE topicName = :topicName")
    List<WordModel> getAllByTopicsName(String topicName);

    @Query("SELECT * FROM WordModel WHERE subTopicName = :subTopicName and topicName = :topicName")
    List<WordModel> getAllBySubTopicsName(String subTopicName, String topicName);

    @Query("SELECT * FROM WordModel WHERE englishWord = :englishWord and subTopicName = :subTopicName and topicName = :topicName")
    WordModel getWordModelByName(String englishWord, String subTopicName, String topicName);

    @Query("DELETE FROM WordModel WHERE subTopicName = :subTopicName and topicName = :topicName")
    void deleteWhere(String subTopicName, String topicName);

    @Query("DELETE FROM WordModel WHERE topicName = :topicName")
    void deleteWhere(String topicName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(WordModel wordModel);

    @Delete
    void delete(WordModel wordModel);
}
