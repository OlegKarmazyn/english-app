package solid.icon.english.navigation_menu.account.timetable

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import solid.icon.english.architecture.parents.ActivityGlobal
import solid.icon.english.databinding.TimetableActivityBinding


class TimetableActivity : ActivityGlobal() {

    private lateinit var binding: TimetableActivityBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TimetableActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        showActionBar(true, "Timetable")

        lifecycleScope.launch {
            delay(800)
            withContext(Dispatchers.Main) {
                binding.calendarWebView.settings.javaScriptEnabled = true
                binding.calendarWebView.loadUrl("https://calendar.google.com/calendar/embed?height=600&wkst=2&bgcolor=%23f8f8ff&ctz=Europe%2FKiev&mode=WEEK&showCalendars=0&showTabs=1&title=English%20VS&src=ZW5nbGlzaC52aWt0b3JpaWEuc2Nob29sQGdtYWlsLmNvbQ")
            }
        }
    }
}