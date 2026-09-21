package com.example.testing

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: EditText
    private lateinit var input: TextView

    private var valueOne = 0f
    private var valueTwo = 0f
    private var isAdd = false
    private var isSub = false
    private var isMul = false
    private var isDiv = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)
        input = findViewById(R.id.input)

        val buttons = listOf(
            R.id.button0 to "0", R.id.button1 to "1", R.id.button2 to "2",
            R.id.button3 to "3", R.id.button4 to "4", R.id.button5 to "5",
            R.id.button6 to "6", R.id.button7 to "7", R.id.button8 to "8",
            R.id.button9 to "9", R.id.buttonDot to "."
        )

        buttons.forEach { (id, value) ->
            findViewById<Button>(id).setOnClickListener {
                display.append(value)
            }
        }

        findViewById<Button>(R.id.buttonC).setOnClickListener {
            display.text.clear()
            input.text = ""
        }

        findViewById<Button>(R.id.buttonAdd).setOnClickListener {
            handleOperation("+") { isAdd = true }
        }

        findViewById<Button>(R.id.buttonSub).setOnClickListener {
            handleOperation("-") { isSub = true }
        }

        findViewById<Button>(R.id.buttonMul).setOnClickListener {
            handleOperation("*") { isMul = true }
        }

        findViewById<Button>(R.id.buttonDiv).setOnClickListener {
            handleOperation("/") { isDiv = true }
        }

        findViewById<Button>(R.id.buttonEqual).setOnClickListener {
            val valueText = display.text.toString()
            valueTwo = valueText.toFloatOrNull() ?: return@setOnClickListener

            when {
                isAdd -> {
                    input.append(valueText)
                    display.setText((valueOne + valueTwo).toString())
                    isAdd = false
                }
                isSub -> {
                    input.append(valueText)
                    display.setText((valueOne - valueTwo).toString())
                    isSub = false
                }
                isMul -> {
                    input.append(valueText)
                    display.setText((valueOne * valueTwo).toString())
                    isMul = false
                }
                isDiv -> {
                    input.append(valueText)
                    display.setText((valueOne / valueTwo).toString())
                    isDiv = false
                }
            }
        }
    }

    private fun handleOperation(symbol: String, setFlag: () -> Unit) {
        val valueText = display.text.toString()
        if (valueText.isNotEmpty()) {
            valueOne = valueText.toFloat()
            setFlag()
            input.text = "$valueText $symbol "
            display.text.clear()
        }
    }
}
