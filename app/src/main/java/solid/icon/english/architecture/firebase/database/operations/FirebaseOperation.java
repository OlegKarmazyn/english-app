package solid.icon.english.architecture.firebase.database.operations;


import static solid.icon.english.architecture.firebase.StaticData.email;

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

import solid.icon.english.architecture.firebase.database.OnGetDataListener;
import solid.icon.english.architecture.room.App;
import solid.icon.english.architecture.room.SubTopicDao;
import solid.icon.english.architecture.room.SubTopicModel;

public class FirebaseOperation {

    private static final String TAG = "FirebaseOperation";
    private final TopicsOperation topicsOperation = new TopicsOperation();
    private final SubTopicsOperation subTopicsOperation = new SubTopicsOperation();
    private final WordsOperation wordsOperation = new WordsOperation();

    /* --------------get permission to change data if owner of it------------------- */
    public void getPathIfAllowed(String topicsName, final OnGetDataListener listener) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query topicsQuery = ref.orderByChild("topicsName").equalTo(topicsName);

        topicsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    HashMap hashMap = (HashMap) dataSnapshot1.getValue();
                    String checkingEmail = (String) hashMap.get("email");
                    if (checkingEmail.equals(email)) {
                        listener.onSuccess(dataSnapshot1);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });
    }

    public String validateKey(String s) {
        String[] arr = s.split("");
        String key = "";
        for (String ch : arr) {
            if (ch.equals(".") || ch.equals("#") || ch.equals("$") || ch.equals("[") || ch.equals("]")) {
                ch = "_";
            }
            key += ch;
        }
        return key;
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

    /* ------------------------------------Topics---------------------------- */
    public void moveTopics(String topicsName) {
        topicsOperation.moveTopics(topicsName);
    }

    public void deleteTopics(String topicsName) {
        getPathIfAllowed(topicsName, topicsOperation::deleteTopics);
    }

    /* ------------------------------------Sub topics---------------------------- */
    public void moveSubTopics(String topicsName, String subTopicsName) {
        getPathIfAllowed(topicsName, dataSnapshot ->
                subTopicsOperation.moveSubTopics(subTopicsName, dataSnapshot));
    }

    public void deleteSubTopics(String topicsName, String subTopicsName) {
        getPathIfAllowed(topicsName, dataSnapshot ->
                subTopicsOperation.deleteSubTopics(subTopicsName, dataSnapshot));
    }

}
