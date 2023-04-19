package solid.icon.english.architecture.notification

import solid.icon.english.architecture.notification.NotificationData

data class PushNotification(
    val data: NotificationData,
    val to: String
)