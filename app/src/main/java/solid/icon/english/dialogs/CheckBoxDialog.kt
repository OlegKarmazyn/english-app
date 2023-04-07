package solid.icon.english.dialogs

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View.OnClickListener
import android.view.Window
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import solid.icon.english.R

class CheckBoxDialog(
    context: Context,
) :
    AppCompatDialog(context) {

    lateinit var checkBox: CheckBox
    lateinit var positiveButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_with_checkbox)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        checkBox = findViewById(R.id.checkBox)!!
        positiveButton = findViewById(R.id.tvPositive)!!
    }

    fun setPositiveButton(textId: Int, listener: OnClickListener) {
        positiveButton.text = context.getText(textId)
        positiveButton.setOnClickListener(listener)
    }
}