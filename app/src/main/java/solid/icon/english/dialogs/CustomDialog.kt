package solid.icon.english.dialogs

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import androidx.core.view.isVisible
import solid.icon.english.R

class CustomDialog(
    context: Context,
) :
    AppCompatDialog(context) {

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

    private fun setUpSpinner() {
        val adapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(
            context, R.array.country,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

}