package solid.icon.english.architecture.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class SubTopicModel {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo
    public String topicsName;

    @ColumnInfo
    public String subTopicsName;
}

