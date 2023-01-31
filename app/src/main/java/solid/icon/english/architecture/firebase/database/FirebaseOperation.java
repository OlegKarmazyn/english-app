package solid.icon.english.architecture.firebase.database;


import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import solid.icon.english.architecture.firebase.StaticData;
import solid.icon.english.architecture.room.App;
import solid.icon.english.architecture.room.SubTopicDao;
import solid.icon.english.architecture.room.SubTopicModel;
import solid.icon.english.architecture.room.TopicModel;
import solid.icon.english.architecture.room.TopicModelDao;

public class FirebaseOperation {

    private static final String TAG = "FirebaseOperation";

    /* --------------------------------Read Data------------------------------ */
    public void readData(Query ref, final OnGetDataListener listener) {
        listener.onStart();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onFailure();
            }
        });
    }

    /* --------------------------------Get Path------------------------------ */
    public static void getPath(String topicsName) {



//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//        Query topicsQuery = ref.orderByChild("topicsName").equalTo(topicsName);
//
//        new FirebaseOperation().readData(topicsQuery, new OnGetDataListener() {
//            @Override
//            public void onSuccess(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onStart() {
//                Log.d("onSTART", "Started");
//            }
//
//            @Override
//            public void onFailure() {
//                Log.d("onFailure", "Failed");
//            }
//        });

    }

    /* --------------------------------Move Topics------------------------------ */
    public void moveTopics(String topicsName) {
        String email = StaticData.email;
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().push();
        TopicModelDao topicModelDao = App.getInstance().getDatabase().topicModelDao();
        TopicModel topicModel = topicModelDao.getByTopicsName(topicsName);
        topicModel.topicsKey = dbRef.getKey();
        topicModelDao.update(topicModel);
        dbRef.child("topicsName").setValue(topicsName);
        dbRef.child("email").setValue(email);
    }

    /* --------------------------------Get All Data from Firebase DB---------------------------- */
    public void getFullTopics(String key, String topicsName) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(key).child("subNames");
        Log.e(TAG, "ref: " + ref);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String subName = (String) dataSnapshot1.getValue();
                    list.add(subName);
                }

                insertNewSubTopics();
            }

            List<String> list = new ArrayList<>();

            private void insertNewSubTopics() {
                SubTopicDao subTopicDao = App.getInstance().getDatabase().subTopicDao();
                for (String s : list) {
                    Log.e(TAG, "s: " + s);
                    SubTopicModel subTopicModel = new SubTopicModel();
                    subTopicModel.topicsName = topicsName;
                    subTopicModel.subTopicsName = s;
                    subTopicDao.insert(subTopicModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });
    }

    /* -----------------------------Delete all data from Firebase DB--------------------------- */
    public void deleteTopics(String topicsName) {
        String email = StaticData.email;
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query topicsQuery = ref.orderByChild("topicsName").equalTo(topicsName);

        topicsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    HashMap hashMap = (HashMap) dataSnapshot1.getValue();
                    String checkingEmail = (String) hashMap.get("email");
                    if (checkingEmail.equals(email)) {
                        dataSnapshot1.getRef().removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });
    }

}
