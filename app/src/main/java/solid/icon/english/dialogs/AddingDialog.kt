package solid.icon.english.dialogs


import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View.OnClickListener
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import androidx.core.view.isVisible
import solid.icon.english.R

//NOTE: dialog with title, field, spinner and three buttons
class AddingDialog(context: Context) : AppCompatDialog(context) {

    private var tvTitle: TextView
    private var etName: EditText
    private var spinner: Spinner
    private var positiveButton: TextView
    private var neutralButton: TextView
    private var negativeButton: TextView

    private var positiveButtonListener: OnClickListener? = null
    private var neutralButtonListener: OnClickListener? = null
    private var negativeButtonListener: OnClickListener? = null

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
    }

    fun setNeutralButton(text: CharSequence, listener: OnClickListener?) {
        neutralButton.run {
            setText(text)
            isVisible = true
            neutralButtonListener = listener
        }
    }

    fun setNeutralButton(textId: Int, listener: OnClickListener?) {
        neutralButton.run {
            text = context.getText(textId)
            isVisible = true
            neutralButtonListener = listener
        }
    }

    fun setPositiveButton(text: CharSequence, listener: OnClickListener?) {
        positiveButton.run {
            setText(text)
            positiveButtonListener = listener
        }
    }

    fun setPositiveButton(textId: Int, listener: OnClickListener?) {
        positiveButton.run {
            text = context.getText(textId)
            positiveButtonListener = listener
        }
    }

    fun setNegativeButton(text: CharSequence, listener: OnClickListener?) {
        negativeButton.run {
            setText(text)
            negativeButtonListener = listener
        }
    }

    fun setNegativeButton(textId: Int, listener: OnClickListener?) {
        negativeButton.run {
            text = context.getText(textId)
            negativeButtonListener = listener
        }
    }

    fun setTitle(text: String) {
        tvTitle.text = text
    }

    fun getTextFromField(): String {
        return etName.text.toString().trim()
    }

    fun getSelectedItem(): String {
        return spinner.selectedItem.toString()
    }

    override fun setTitle(textId: Int) {
        tvTitle.text = context.getText(textId)
    }

    private fun setUpSpinner() {
        val adapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(
            context, R.array.country,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        checkIfClickListener(negativeButton)
        checkIfClickListener(neutralButton)
        checkIfClickListener(positiveButton)
    }

    private fun checkIfClickListener(view: TextView) {
        if (!view.hasOnClickListeners())
            view.isVisible = false
    }

    fun getEditText(): EditText{
        return etName
    }
}