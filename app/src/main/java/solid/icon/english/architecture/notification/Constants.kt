package solid.icon.english.architecture.notification

class Constants {

    companion object {
        const val BASE_URL = "https://fcm.googleapis.com"
        const val SERVER_KEY =
            "AAAA5LLnoXE:APA91bEBC1_CS7RBLdJNEPpcLLpIt__b2Npz-q-Fu9FeUfPdtjtpZJwKm-o4qtTRg-sCoyB1nQGZPGBxJJuuzHIYO9YwbXEMoRb4dl-n3GEBEtp7krvM9h2d8N41bl3uH14AlY4n67Lh"
        const val CONTENT_TYPE = "application/json"
    }

    var bulka = 1
    fun love() {
        val love = true
        if (love)
            bulka = 1
        else
            bulka = 0
    }
}

