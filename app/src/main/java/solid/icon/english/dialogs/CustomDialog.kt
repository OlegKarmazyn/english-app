package solid.icon.english.dialogs

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View.OnClickListener
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import androidx.core.view.isVisible
import solid.icon.english.R

class CustomDialog(context: Context) : AppCompatDialog(context) {

    //Optimize: Redone all calls this dialog and create one more dialog with text size
    // like in adding_dialog. Just with one title and two buttons. For example deleting dialog

    lateinit var tvTitle: TextView
    lateinit var etName: EditText
    lateinit var spinner: Spinner
    lateinit var positiveButton: TextView
    lateinit var neutralButton: TextView
    lateinit var negativeButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }

    fun setNeutralButton(text: CharSequence, listener: OnClickListener) {
        neutralButton.text = text
        neutralButton.setOnClickListener(listener)
    }

    fun setNeutralButton(textId: Int, listener: OnClickListener) {
        neutralButton.text = context.getText(textId)
        neutralButton.setOnClickListener(listener)
    }

    fun setPositiveButton(text: CharSequence, listener: OnClickListener) {
        positiveButton.text = text
        positiveButton.setOnClickListener(listener)
    }

    fun setPositiveButton(textId: Int, listener: OnClickListener) {
        positiveButton.text = context.getText(textId)
        positiveButton.setOnClickListener(listener)
    }

    fun setNegativeButton(text: CharSequence, listener: OnClickListener) {
        negativeButton.text = text
        negativeButton.setOnClickListener(listener)
    }

    fun setNegativeButton(textId: Int, listener: OnClickListener) {
        negativeButton.text = context.getText(textId)
        negativeButton.setOnClickListener(listener)
    }

    fun setTitle(text: String) {
        tvTitle.text = text
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
}