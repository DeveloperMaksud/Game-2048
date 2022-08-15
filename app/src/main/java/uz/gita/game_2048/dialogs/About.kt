package uz.gita.game_2048.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import uz.gita.game_2048.R

class About(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.about_info)
        findViewById<AppCompatButton>(R.id.btn_ok).setOnClickListener{ dismiss() }
        setCancelable(false)
    }

}