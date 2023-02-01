package solid.icon.english.architecture.firebase.database.operations

import com.google.firebase.database.DataSnapshot
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import solid.icon.english.architecture.firebase.database.WordFB

class WordsOperation {

    fun moveWord(subKey: String, wordKey: String, wordFB: WordFB, dataSnapshot: DataSnapshot) {
        GlobalScope.launch {
            dataSnapshot.ref.child("subTopics").child(subKey).child(wordKey).setValue(wordFB)
        }
    }

    fun deleteWord(subKey: String, wordKey: String, dataSnapshot: DataSnapshot) {
        GlobalScope.launch {
            dataSnapshot.child("subTopics").child(subKey).child(wordKey).ref.removeValue()
        }
    }
}