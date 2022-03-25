package solid.icon.english.architecture.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class PersonLife {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo
    public String nameActive;

    @ColumnInfo
    public String date;

    @ColumnInfo
    public int days;

}

