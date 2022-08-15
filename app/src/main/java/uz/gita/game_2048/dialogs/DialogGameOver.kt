package uz.gita.game_2048.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import uz.gita.game_2048.Contract.GameContract
import uz.gita.game_2048.R

class DialogGameOver (
    context: Context,
    private val exitListener: () -> Unit,
    private val retryListener: () -> Unit

) : Dialog(context){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false)

        setContentView(R.layout.dialog_game_over)
        findViewById<AppCompatButton>(R.id.btn_exit_game_over).setOnClickListener{
            exitListener.invoke()
            dismiss()
        }

        findViewById<AppCompatButton>(R.id.btn_new_game_game_over).setOnClickListener{
           retryListener.invoke()
            dismiss()

        }
    }
}