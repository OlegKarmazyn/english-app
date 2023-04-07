package solid.icon.english.dialogs

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import androidx.core.view.isVisible
import solid.icon.english.R


//NOTE: dialog with one title and two buttons
class TitleDialog(context: Context) : ParentDialog(context) {

    init {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.title_dialog)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        tvTitle = findViewById(R.id.tvTitle)!!
        negativeButton = findViewById(R.id.tvNegative)!!
        positiveButton = findViewById(R.id.tvPositive)!!

        positiveButton.setOnClickListener {
            dismiss()
            positiveButtonListener?.onClick(it)
        }

        negativeButton.setOnClickListener {
            dismiss()
            negativeButtonListener?.onClick(it)
        }

        positiveButton.isVisible = false
        negativeButton.isVisible = false
    }
}