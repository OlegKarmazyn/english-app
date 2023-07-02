package solid.icon.english.architecture.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class WordModel {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo
    public String topicName;

    @ColumnInfo
    public String subTopicName;

    @ColumnInfo
    public String englishWord;

    @ColumnInfo
    public String rusWord;

    @ColumnInfo
    public String definition;

    @ColumnInfo
    public String sentence;

}

