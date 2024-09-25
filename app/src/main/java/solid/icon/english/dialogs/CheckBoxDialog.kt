package solid.icon.english.dialogs

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View.OnClickListener
import android.view.Window
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import solid.icon.english.R

class CheckBoxDialog(context: Context) : AppCompatDialog(context) {

    var checkBox: CheckBox
    var positiveButton: TextView

    init {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_with_checkbox)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        checkBox = findViewById(R.id.checkBox)!!
        positiveButton = findViewById(R.id.tvPositive)!!

        positiveButton.setOnClickListener {
            dismiss()
            listener?.onClick(it)
        }
    }

    private var listener: OnClickListener? = null

    fun setPositiveButton(textResId: Int, listener: OnClickListener) {
        positiveButton.text = context.getText(textResId)
        this.listener = listener
    }

    fun isChecked(): Boolean {
        return checkBox.isChecked
    }
}
