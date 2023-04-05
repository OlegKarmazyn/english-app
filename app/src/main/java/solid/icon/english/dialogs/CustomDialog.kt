package solid.icon.english.dialogs

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatDialog
import solid.icon.english.R

class CustomDialog(
    context: Context
) :
    AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.adding_dialog)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}