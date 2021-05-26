package solid.icon.english.db_pac;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

 public class DBmoveINFO {

    static DBHelper dbHelper;

    private static final String TAG = "myLogs";

    public DBmoveINFO(@Nullable Context context){
    dbHelper = new DBHelper(context);
    }

    public void create(@Nullable Context context) {
        //dbHelper = new DBHelper(context);

        final SQLiteDatabase database = dbHelper.getWritableDatabase();
        final ContentValues contentValues = new ContentValues();

        final Cursor cursor = database.query(DBHelper.TABLE_MAIN, null, null, null, null, null, null);

        if(cursor.moveToFirst())
        {
//            String t = "0";
//            for (int i = 0; i < 51; i++) {
//                t = String.valueOf(cursor.getInt(cursor.getColumnIndex((DBHelper.KNOW_TOPIC))));
//                Log.d(TAG, t);
//                cursor.moveToNext();
//            }

            cursor.close();
        }else{
            for (int i = 0; i < 51; i++) {
                contentValues.put(DBHelper.KNOW_TOPIC_A2, 0);
                database.insert(DBHelper.TABLE_MAIN, null, contentValues);
                contentValues.put(DBHelper.KNOW_TOPIC_B1, 0);
                database.insert(DBHelper.TABLE_MAIN, null, contentValues);
                cursor.moveToNext();
            }

            cursor.close();
        }
    }


    public int[] done_topic(){
        int[] key_topics = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        final SQLiteDatabase database = dbHelper.getWritableDatabase();
        final ContentValues contentValues = new ContentValues();

        final Cursor cursor = database.query(DBHelper.TABLE_MAIN, null, null, null, null, null, null);

        if(cursor.moveToFirst())
        {
            for (int i = 0; i < 51; i++) {
                key_topics[i] = cursor.getInt(cursor.getColumnIndex((DBHelper.KNOW_TOPIC_A2)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return key_topics;
    }

     public void go_check_info(int a, String s){
         final SQLiteDatabase database = dbHelper.getWritableDatabase();
         final ContentValues contentValues = new ContentValues();

         final Cursor cursor = database.query(DBHelper.TABLE_MAIN, null, null, null, null, null, null);
         if(cursor.moveToFirst())
         {

             //cursor.moveToPosition(a);
             contentValues.put(s, 1);
             database.update(DBHelper.TABLE_MAIN, contentValues, DBHelper.KEY_ID + "= ?", new String[] {String.valueOf(a+1)});
             Log.d(TAG,  a +" - go_check_info - if - true");

         }
         cursor.close();
     }

     public int back_check_info(int a, String s){
         final SQLiteDatabase database = dbHelper.getWritableDatabase();
         final ContentValues contentValues = new ContentValues();

         final Cursor cursor = database.query(DBHelper.TABLE_MAIN, null, null, null, null, null, null);
         if(cursor.moveToFirst())
         {

             cursor.moveToPosition(a);
             a = cursor.getInt(cursor.getColumnIndex((s)));

         }
         cursor.close();
         return a;
     }

     public void delete_check_info(int a, String s){
         final SQLiteDatabase database = dbHelper.getWritableDatabase();
         final ContentValues contentValues = new ContentValues();

         final Cursor cursor = database.query(DBHelper.TABLE_MAIN, null, null, null, null, null, null);
         if(cursor.moveToFirst()) {
             //cursor.moveToPosition(a);
             contentValues.put(s, 0);
             database.update(DBHelper.TABLE_MAIN, contentValues, DBHelper.KEY_ID + "= ?", new String[] {String.valueOf(a+1)});

             cursor.close();
         }
     }

}
