package solid.icon.english.architecture.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class TopicModel {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo
    public String topicsName;

    @ColumnInfo
    public String country;

    @ColumnInfo(name = "topicsKey")
    public String topicsKey;

    @ColumnInfo
    public boolean isCheck;
}

