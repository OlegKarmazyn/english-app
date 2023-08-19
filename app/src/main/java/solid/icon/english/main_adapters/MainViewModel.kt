package solid.icon.english.main_adapters

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.*
import solid.icon.english.MainActivity
import solid.icon.english.architecture.local_data.PreferencesOperations
import java.lang.ref.WeakReference

class MainViewModel(activity: MainActivity) : ViewModel() {

    companion object {
        const val APPLICATION_VERSION = 14
    }

    private val activityRef = WeakReference(activity)
    private val context: Context? get() = activityRef.get()?.applicationContext
    val preferences = PreferenceManager.getDefaultSharedPreferences(activity)!!


    //Optimize: separate this code and make different models

    //region first opens app
    fun firstOpen() {
        val pref = PreferencesOperations()
        pref.firstOpen()
        if (pref.getNumberOfVisits() < 4 && !pref.getIsOpenedSite()) activityRef.get()
            ?.showSiteDialog()
    }
    //endregion

    //region checkLatestVersion
    fun checkLatestVersion() {
        GlobalScope.launch {
            getVersion()
            delay(500)
            if (context == null)
                return@launch

            val currentVersion: Int = preferences.getInt("version", APPLICATION_VERSION)
            Log.e("currentVersion = ", "" + currentVersion)
            if (currentVersion > APPLICATION_VERSION) {
                withContext(Dispatchers.Main) {
                    activityRef.get()?.showUpdateDialog()
                }
            }
        }
    }

    private fun getVersion() {
        val mDatabase = FirebaseDatabase.getInstance().getReference("MyApp")

        val postListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val myApp = dataSnapshot.getValue(MyApp::class.java)
                Log.e(
                    "go_FirebaseDB",
                    "version = " + myApp!!.version + " " + "prise = " + myApp.prise
                )
                if (myApp.version != 0 || myApp.prise != null) {
                    val editor: SharedPreferences.Editor = preferences.edit()
                    editor.putInt("version", myApp.version)
                    editor.putString("prise", myApp.prise)
                    editor.apply()
                    Log.e("go_FirebaseDB", "true")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("getVersion", "loadPost:onCancelled", databaseError.toException())
            }
        }
        mDatabase.addValueEventListener(postListener)
    }
    //endregion
}

internal class MyApp {
    var version = 0
    var prise: String? = null

    constructor() {}
    constructor(version: Int, prise: String?) {
        this.version = version
        this.prise = prise
    }
}