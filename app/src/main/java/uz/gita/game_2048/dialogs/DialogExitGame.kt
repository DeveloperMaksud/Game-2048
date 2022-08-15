package uz.gita.game_2048.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.GnssAntennaInfo
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import uz.gita.game_2048.R

class DialogExit(context: Context ,val listener : () -> Unit ,val textDis : String): Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_exit)

        findViewById<TextView>(R.id.idstart).text = textDis
        findViewById<AppCompatButton>(R.id.noid).setOnClickListener{ dismiss() }

        findViewById<AppCompatButton>(R.id.yesid).setOnClickListener{
            listener.invoke()
            dismiss()
        }
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}