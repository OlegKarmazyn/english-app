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
    public String subTopicsName0;

    @ColumnInfo
    public String subTopicsName1;

    @ColumnInfo
    public String subTopicsName2;

    @ColumnInfo
    public String subTopicsName3;

    @ColumnInfo
    public String subTopicsName4;

    @ColumnInfo
    public String subTopicsName5;

    @ColumnInfo
    public String subTopicsName6;

    @ColumnInfo
    public String subTopicsName7;

    @ColumnInfo
    public String subTopicsName8;

    @ColumnInfo
    public String subTopicsName9;

}

