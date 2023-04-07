package solid.icon.english.dialogs


import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.core.view.isVisible
import solid.icon.english.R

//NOTE: dialog with title, field, spinner and three buttons
class AddingDialog(context: Context) : ParentDialog(context) {

    private var etName: EditText
    private var spinner: Spinner
    var neutralButton: TextView
    var neutralButtonListener: View.OnClickListener? = null


    init {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.adding_dialog)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        tvTitle = findViewById(R.id.tvTitle)!!
        etName = findViewById(R.id.etName)!!
        spinner = findViewById(R.id.spinner)!!
        negativeButton = findViewById(R.id.tvNegative)!!
        neutralButton = findViewById(R.id.tvNeutral)!!
        positiveButton = findViewById(R.id.tvPositive)!!

        setUpSpinner()

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

    fun setNeutralButton(text: CharSequence, listener: View.OnClickListener?) {
        neutralButton.run {
            setText(text)
            isVisible = true
            neutralButtonListener = listener
        }
    }

    fun setNeutralButton(textId: Int, listener: View.OnClickListener?) {
        neutralButton.run {
            text = context.getText(textId)
            isVisible = true
            neutralButtonListener = listener
        }
    }

    fun getTextFromField(): String {
        return etName.text.toString().trim()
    }

    fun getSelectedItem(): String {
        return spinner.selectedItem.toString()
    }

    private fun setUpSpinner() {
        val adapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(
            context, R.array.country,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    fun getEditText(): EditText {
        return etName
    }
}