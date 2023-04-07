package solid.icon.english.dialogs

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View.OnClickListener
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import androidx.core.view.isVisible
import solid.icon.english.R


//NOTE: dialog with one title and two buttons
class TitleDialog(context: Context) : AppCompatDialog(context) {

    private var tvTitle: TextView

    private var positiveButton: TextView
    private var negativeButton: TextView

    private var positiveButtonListener: OnClickListener? = null
    private var negativeButtonListener: OnClickListener? = null

    init {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.title_dialog)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        tvTitle = findViewById(R.id.tvTitle)!!
        negativeButton = findViewById(R.id.tvNegative)!!
        positiveButton = findViewById(R.id.tvPositive)!!

        negativeButton.isVisible = false
        positiveButton.isVisible = false

        positiveButton.setOnClickListener {
            dismiss()
            positiveButtonListener?.onClick(it)
        }

        negativeButton.setOnClickListener {
            dismiss()
            negativeButtonListener?.onClick(it)
        }
    }

    fun setPositiveButton(text: CharSequence, listener: OnClickListener?) {
        positiveButton.run {
            setText(text)
            isVisible = true
            positiveButtonListener = listener
        }
    }

    fun setPositiveButton(textId: Int, listener: OnClickListener?) {
        positiveButton.run {
            text = context.getText(textId)
            isVisible = true
            positiveButtonListener = listener
        }
    }

    fun setNegativeButton(text: CharSequence, listener: OnClickListener?) {
        negativeButton.run {
            setText(text)
            isVisible = true
            negativeButtonListener = listener
        }
    }

    fun setNegativeButton(textId: Int, listener: OnClickListener?) {
        negativeButton.run {
            text = context.getText(textId)
            isVisible = true
            negativeButtonListener = listener
        }
    }

    fun setTitle(text: String) {
        tvTitle.text = text
    }

    override fun setTitle(textId: Int) {
        tvTitle.text = context.getText(textId)
    }
}