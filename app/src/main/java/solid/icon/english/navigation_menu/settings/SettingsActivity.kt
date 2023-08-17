package solid.icon.english.navigation_menu.settings

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import solid.icon.english.architecture.parents.ActivityGlobal
import solid.icon.english.databinding.SettingsActivityBinding
import java.util.*

class SettingsActivity : ActivityGlobal() {

    private lateinit var preferences: SharedPreferences
    private lateinit var binding: SettingsActivityBinding
    private lateinit var mTTS: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SettingsActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        showActionBar(true, "Settings")

        lifecycleScope.launch {
            delay(800)
            preferences = PreferenceManager.getDefaultSharedPreferences(context)
            withContext(Dispatchers.Main) {
                initUI()
            }
        }
    }

    private fun initUI() {
        animationDrawable = binding.imgListen.drawable as AnimationDrawable?
        val pitch = getPitchInt()
        val speech = getSpeechRateInt()
        binding.pitchBar.progress = pitch
        binding.speechBar.progress = speech
        setTextToPitch(pitch)
        setTextToSpeech(speech)

        mTTS = TextToSpeech(context) { status: Int ->
            if (status == TextToSpeech.SUCCESS) {
                val locale = Locale.ENGLISH
                val result: Int = mTTS.setLanguage(locale)
                if (result == TextToSpeech.LANG_MISSING_DATA
                        || result == TextToSpeech.LANG_NOT_SUPPORTED
                ) {
                    Log.e("TTS", "Language not supported")
                } //empty else
            } else {
                Log.e("TTS", "Initialization failed")
            }
        }

        binding.imgListen.setOnClickListener {
            animationDrawable()
        }

        binding.reset.setOnClickListener {
            resetSettings()
        }

        binding.pitchBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                setTextToPitch(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar?.progress!! < 10)
                    seekBar.progress = 10
                savePitchAndSpeech()
            }
        })
        binding.speechBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                setTextToSpeech(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar?.progress!! < 10)
                    seekBar.progress = 10
                savePitchAndSpeech()
            }
        })
    }

    private fun getPitchInt(): Int {
        return (getPitchFloat() * 100).toInt()
    }

    private fun getPitchFloat(): Float {
        return (preferences.getFloat("pitch", 0.7f))
    }

    private fun getSpeechRateInt(): Int {
        return (getSpeechRateFloat() * 100).toInt()
    }

    private fun getSpeechRateFloat(): Float {
        return (preferences.getFloat("speechRate", 0.7f))
    }

    private fun savePitchAndSpeech() {
        try {
            val pitch = binding.pitchBar.progress.toFloat() / 100
            val speechRate = binding.speechBar.progress.toFloat() / 100
            val editor = preferences.edit()
            editor.putFloat("pitch", pitch)
            editor.putFloat("speechRate", speechRate)
            editor.apply()
            Toasty.info(context, "Saved").show()
        } catch (_: java.lang.Exception) {
            Toasty.error(context, "Error").show()
        }
    }

    private fun resetSettings() {
        try {
            val pitch = 0.7f
            val speechRate = 0.7f
            val editor = preferences.edit()
            editor.putFloat("pitch", pitch)
            editor.putFloat("speechRate", speechRate)
            editor.apply()
            initUI()
        } catch (_: java.lang.Exception) {
            Toasty.error(context, "Error").show()
        }
    }

    @SuppressLint("SetTextI18n")
    fun setTextToSpeech(progress: Int) {
        binding.tvSpeech.text = "Speech rate ($progress):"
    }

    @SuppressLint("SetTextI18n")
    fun setTextToPitch(progress: Int) {
        binding.tvPitch.text = "Pitch ($progress):"
    }

    private var animationDrawable: AnimationDrawable? = null
    private fun animationDrawable() {
        animationDrawable!!.start()
        speak()
        object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                animationDrawable!!.stop()
            }
        }.start()
    }

    fun speak(text: String = "I word hard every day") {
        val pitch = getPitchFloat()
        val speech = getSpeechRateFloat()
        mTTS.setPitch(pitch)
        mTTS.setSpeechRate(speech)
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }
}