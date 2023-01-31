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

import java.util.HashMap;

public class FirebaseOperation {

    String TAG = "FirebaseOperation";
    static String email = "admin@gmail.com";
    static String path = "";

    public static void getPath(String topicsName) {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query topicsQuery = ref.orderByChild("topicsName").equalTo(topicsName);

        new FirebaseOperation().readData(topicsQuery, new OnGetDataListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    HashMap hashMap = (HashMap) dataSnapshot1.getValue();
                    String checkingEmail = (String) hashMap.get("email");
                    if (checkingEmail.equals("admin@gmail.com")) {
                        path = String.valueOf(dataSnapshot1.getRef().getPath());
                        Log.e("path", path);
                    }
                }
            }

            @Override
            public void onStart() {
                Log.d("onSTART", "Started");
            }

            @Override
            public void onFailure() {
                Log.d("onFailure", "Failed");
            }
        });
    }

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
}
