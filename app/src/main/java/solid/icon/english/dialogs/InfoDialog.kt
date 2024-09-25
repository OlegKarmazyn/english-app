package solid.icon.english.dialogs

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View.OnClickListener
import android.view.Window
import android.widget.TextView
import androidx.core.view.isVisible
import solid.icon.english.R
//Note: dialog with title and message + 3 buttons
class InfoDialog(context: Context) : ParentDialog(context) {

    private var tvMessage: TextView
    private var neutralButton: TextView

    private var neutralButtonListener: OnClickListener? = null

    init {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.info_dialog)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        tvTitle = findViewById(R.id.tvTitle)!!
        tvMessage = findViewById(R.id.tvMessage)!!
        negativeButton = findViewById(R.id.tvNegative)!!
        neutralButton = findViewById(R.id.tvNeutral)!!
        positiveButton = findViewById(R.id.tvPositive)!!

        positiveButton.setOnClickListener {
            dismiss()
            positiveButtonListener?.onClick(it)
        }

        neutralButton.setOnClickListener {
            dismiss()
            neutralButtonListener?.onClick(it)
        }

        negativeButton.setOnClickListener {
            dismiss()
            negativeButtonListener?.onClick(it)
        }

        positiveButton.isVisible = false
        negativeButton.isVisible = false
        neutralButton.isVisible = false
    }

    fun setNeutralButton(text: CharSequence, listener: OnClickListener?) {
        neutralButton.run {
            setText(text)
            isVisible = true
            neutralButtonListener = listener
        }
    }

    fun setNeutralButton(textResId: Int, listener: OnClickListener?) {
        neutralButton.run {
            text = context.getText(textResId)
            isVisible = true
            neutralButtonListener = listener
        }
    }

    fun setMessage(textResId: Int) {
        tvMessage.text = context.getText(textResId)
    }

    fun setMessage(text: String?) {
        if (text.isNullOrBlank())
            tvMessage.isVisible = false
        tvMessage.text = text
    }
}