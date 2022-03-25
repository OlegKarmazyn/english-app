package solid.icon.english.architecture.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonLifeDao {

    @Query("SELECT * FROM PersonLife")
    List<PersonLife> getAll();

    @Query("SELECT * FROM PersonLife WHERE id = :id")
    PersonLife getById(long id);

    @Query("SELECT * FROM PersonLife WHERE nameActive = :nameActive")
    PersonLife getByNameActive(String nameActive);

    @Insert
    void insert(PersonLife personLife);

    @Update
    void update(PersonLife personLife);

    @Delete
    void delete(PersonLife personLife);
}
