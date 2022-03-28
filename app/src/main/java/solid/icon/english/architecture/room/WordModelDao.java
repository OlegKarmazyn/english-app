package solid.icon.english.architecture.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordModelDao {

    @Query("SELECT * FROM WordModel")
    List<WordModel> getAll();

    @Query("SELECT * FROM WordModel WHERE id = :id")
    WordModel getById(long id);

    @Query("SELECT * FROM WordModel WHERE topicsName = :topicsName")
    List<WordModel> getAllByTopicsName(String topicsName);

    @Query("SELECT * FROM WordModel WHERE subTopicsName = :subTopicsName")
    List<WordModel> getAllBySubTopicsName(String subTopicsName);

    @Insert
    void insert(WordModel wordModel);

    @Update
    void update(WordModel wordModel);

    @Delete
    void delete(WordModel wordModel);
}
