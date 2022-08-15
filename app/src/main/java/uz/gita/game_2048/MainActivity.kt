package uz.gita.game_2048

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton
import uz.gita.game_2048.dialogs.About
import uz.gita.game_2048.dialogs.DialogExit
import uz.gita.game_2048.repository.LocalData

class MainActivity : AppCompatActivity() {
    private lateinit var dialog: DialogExit


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var w: Window = window
        w.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        setContentView(R.layout.activity_main)
        loadButtons()
    }

        fun loadButtons() {

            findViewById<TextView>(R.id.infobtn).setOnClickListener {
                val dialog = About(this)
                dialog.setCancelable(false)
                dialog.show()
            }

            findViewById<TextView>(R.id.playbtn).setOnClickListener {
                startActivity(Intent(this, GameActivity::class.java))
            }

            findViewById<TextView>(R.id.exitbtn).setOnClickListener {
                dialog = DialogExit(this, { finish() }, "Exit the game?")
                dialog.setCancelable(false)
                dialog.show()
            }

        }
    }