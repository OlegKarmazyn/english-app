package solid.icon.english.architecture.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TopicModelDao {

    @Query("SELECT * FROM TopicModel")
    List<TopicModel> getAll();

    @Query("SELECT * FROM TopicModel WHERE id = :id")
    TopicModel getById(long id);

    @Query("SELECT * FROM TopicModel WHERE topicsName = :topicsName")
    TopicModel getByTopicsName(String topicsName);

    @Insert
    void insert(TopicModel TopicModel);

    @Update
    void update(TopicModel TopicModel);

    @Delete
    void delete(TopicModel TopicModel);
}
