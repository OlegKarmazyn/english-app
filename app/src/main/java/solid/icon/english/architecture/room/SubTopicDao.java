package solid.icon.english.architecture.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SubTopicDao {

    @Query("SELECT * FROM SubTopicModel")
    List<SubTopicModel> getAll();

    @Query("SELECT * FROM SubTopicModel WHERE id = :id")
    SubTopicModel getById(long id);

    @Query("SELECT * FROM SubTopicModel WHERE topicsName = :topicsName and subTopicsName = :subTopicsName")
    SubTopicModel getByNames(String topicsName, String subTopicsName);

    @Query("SELECT * FROM SubTopicModel WHERE topicsName = :topicsName")
    List<SubTopicModel> getAllByTopicsName(String topicsName);

    @Insert
    void insert(SubTopicModel subTopicModel);

    @Update
    void update(SubTopicModel subTopicModel);

    @Delete
    void delete(SubTopicModel subTopicModel);
}
