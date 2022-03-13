package com.miu.aalemu.quize_app

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private var result: Array<Int> = arrayOf(0,0)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reset.setOnClickListener{
            reset()
        }

        submit.setOnClickListener {
            val date = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            showDialog("Congratulations! You submitted on $date , You achieved ${result[0] + result[1]}%")
        }

    }

    fun answer(view: View){
        val r:RadioGroup  = view.parent as RadioGroup
        when (r.id){
            R.id.radio1 ->
                if(radio1B.isChecked)
                    result[0]=50;
                else
                    result[0]=0;
            R.id.radio2 ->
                if(radio2C.isChecked)
                    result[1]=50;
                else
                    result[1]=0;
        }
    }

    private fun showDialog(message: String){
        val alertDialog: AlertDialog = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton("OK")
                { dialog, _ ->
                    dialog.dismiss()
                    reset()
                }
            }
            builder.setMessage(message)
                .setTitle(title)
            builder.create()
        }
        alertDialog.show()
    }

    private fun reset(){
        radio2.clearCheck();
        radio1.clearCheck();
        result = arrayOf(0,0)
    }
}