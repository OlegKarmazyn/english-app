package solid.icon.english.dialogs

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import androidx.core.view.isVisible

open class ParentDialog(context: Context) : AppCompatDialog(context) {

    lateinit var tvTitle: TextView
    lateinit var positiveButton: TextView
    lateinit var negativeButton: TextView

    var positiveButtonListener: View.OnClickListener? = null
    var negativeButtonListener: View.OnClickListener? = null

    fun setPositiveButton(text: CharSequence, listener: View.OnClickListener?) {
        positiveButton.run {
            setText(text)
            isVisible = true
            positiveButtonListener = listener
        }
    }

    fun setPositiveButton(textResId: Int, listener: View.OnClickListener?) {
        positiveButton.run {
            text = context.getText(textResId)
            isVisible = true
            positiveButtonListener = listener
        }
    }

    fun setNegativeButton(text: CharSequence, listener: View.OnClickListener?) {
        negativeButton.run {
            setText(text)
            isVisible = true
            negativeButtonListener = listener
        }
    }

    fun setNegativeButton(textResId: Int, listener: View.OnClickListener?) {
        negativeButton.run {
            text = context.getText(textResId)
            isVisible = true
            negativeButtonListener = listener
        }
    }

    override fun setTitle(textResId: Int) {
        tvTitle.text = context.getText(textResId)
    }

    fun setTitle(text: String) {
        tvTitle.text = text
    }

}