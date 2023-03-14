package solid.icon.english.architecture.firebase.database.operations

import android.util.Log
import com.google.firebase.database.DataSnapshot
import solid.icon.english.architecture.firebase.database.WordFB
import solid.icon.english.architecture.firebase.database.interfaces.OnSuccessListener
import solid.icon.english.architecture.room.App
import solid.icon.english.architecture.room.SubTopicModel
import solid.icon.english.architecture.room.WordModel

class RecipientOperation {

    fun getAllData(key: String, topicsName: String) {
        FirebaseOperation().getDataSnapshotByKey(key) { dataSnapshot ->
            getSubTopics(topicsName, dataSnapshot)
        }
    }

    fun getAllData(key: String, topicsName: String, listener: OnSuccessListener) {
        FirebaseOperation().getDataSnapshotByKey(key) { dataSnapshot ->
            getSubTopics(topicsName, dataSnapshot)
            listener.onSuccess()
        }
    }

    private fun getSubTopics(topicsName: String, dataSnapshot: DataSnapshot) {
        for (dataSnap in dataSnapshot.child("subNames").children) {
            val subName = dataSnap.value as String
            insertNewSubTopics(topicsName, subName)
            getWords(topicsName, subName, dataSnapshot) //getting all words by current subTopic
        }
    }

    val subTopicDao = App.getInstance().database.subTopicDao()!!
    private fun insertNewSubTopics(topicsName: String, subTopicsName: String) {
        var subTopicModel = subTopicDao.getByNames(topicsName, subTopicsName)
        if (subTopicModel == null)
            subTopicModel = SubTopicModel()

        subTopicModel.topicsName = topicsName
        subTopicModel.subTopicsName = subTopicsName
        subTopicDao.upsert(subTopicModel)
    }

    private fun getWords(topicsName: String, subTopicsName: String, dataSnapshot: DataSnapshot) {
        val validatedSubTopic = FirebaseOperation().validateKey(subTopicsName)
        val wordsListOfSnapshot = dataSnapshot.child("subTopics").child(validatedSubTopic)
        for (dataSnap in wordsListOfSnapshot.children) {
            val wordFB = dataSnap.getValue(WordFB::class.java)
            insertNewWordModel(topicsName, subTopicsName, wordFB!!)
        }
    }

    private val wordModelDao = App.getInstance().database.wordModelDao()!!
    private fun insertNewWordModel(topicsName: String, subTopicsName: String, wordFB: WordFB) {
        var wordModel =
            wordModelDao.getWordModelByName(wordFB.englishWord, subTopicsName, topicsName)
        if (wordModel == null)
            wordModel = WordModel()

        wordModel.topicName = topicsName
        wordModel.subTopicName = subTopicsName
        wordModel.englishWord = wordFB.englishWord
        wordModel.rusWord = wordFB.rusWord
        wordModel.definition = wordFB.definition
        wordModelDao.upsert(wordModel)
    }
}