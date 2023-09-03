package solid.icon.english.architecture.firebase.database.operations

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import solid.icon.english.architecture.firebase.database.WordFB
import solid.icon.english.architecture.room.App

class SenderOperation(private val firebaseOperation: FirebaseOperation) {

    private val subTopicDao = App.getInstance().database.subTopicDao()
    private val wordModelDao = App.getInstance().database.wordModelDao()

    fun postAllData(topicsName: String) {
        GlobalScope.launch {
            postTopic(topicsName)
        }
    }

    private fun postTopic(topicsName: String) {
        firebaseOperation.moveTopics(topicsName)
        postSubTopics(topicsName)
    }

    fun uploadAllData(topicsName: String) {
        GlobalScope.launch {
            postSubTopics(topicsName)
        }
    }

    fun postOneSubTopic(newTopicsName: String, oldTopicsName: String, subTopicName: String) {
        val element = subTopicDao.getByNames(oldTopicsName, subTopicName)
        firebaseOperation.moveSubTopics(newTopicsName, element.subTopicsName)
        postWords(newTopicsName, oldTopicsName, element.subTopicsName)
    }

    private fun postWords(newTopicsName: String, oldTopicsName: String, subTopName: String) {
        val list = wordModelDao.getAllBySubTopicsName(subTopName, oldTopicsName)
        for (element in list!!) {
            val wordFB = WordFB()
            wordFB.rusWord = element.rusWord
            wordFB.englishWord = element.englishWord
            wordFB.definition = element.definition

            firebaseOperation.moveWord(newTopicsName, subTopName, wordFB)
        }
    }

    private fun postSubTopics(topicsName: String) {
        val list = subTopicDao.getAllByTopicsName(topicsName)
        for (element in list!!) {
            firebaseOperation.moveSubTopics(topicsName, element.subTopicsName)
            postWords(topicsName, element.subTopicsName)
        }
    }

    private fun postWords(topicsName: String, subTopName: String) {
        val list = wordModelDao.getAllBySubTopicsName(subTopName, topicsName)
        for (element in list!!) {
            val wordFB = WordFB()
            wordFB.rusWord = element.rusWord
            wordFB.englishWord = element.englishWord
            wordFB.definition = element.definition

            firebaseOperation.moveWord(topicsName, subTopName, wordFB)
        }
    }
}