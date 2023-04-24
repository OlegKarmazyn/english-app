package solid.icon.english.architecture.local_data

import solid.icon.english.architecture.notification.FirebaseService
import solid.icon.english.architecture.room.App

class LocalOperation {

    private val topicModelDao = App.getInstance().database.topicModelDao()
    private val subTopicDao = App.getInstance().database.subTopicDao()
    private val wordModelDao = App.getInstance().database.wordModelDao()

    fun deleteSubTopic(topicsName: String, subTopicsName: String) {
        val subModel = subTopicDao?.getByNames(topicsName, subTopicsName)
        if (subModel != null)  //deleting all words with this topic
            wordModelDao?.deleteWhere(subModel.subTopicsName, subModel.topicsName)
        subTopicDao?.delete(subModel)
    }

    fun deleteTopic(topicsName: String) {
        val topicModel = topicModelDao?.getByTopicsName(topicsName)
        topicModel?.topicsKey?.let { FirebaseService.unsubscribeToTopic(it) } //NOTE: unsubscribe
        wordModelDao?.deleteWhere(topicsName)
        subTopicDao?.deleteWhere(topicsName)
        topicModelDao?.delete(topicModel)
    }

}