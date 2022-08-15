package uz.gita.game_2048

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton
import uz.gita.game_2048.Contract.GameContract
import uz.gita.game_2048.data.Movement
import uz.gita.game_2048.dialogs.DialogExit
import uz.gita.game_2048.dialogs.DialogGameOver
import uz.gita.game_2048.presenter.GamePresenter
import uz.gita.game_2048.repository.GameRepository
import uz.gita.game_2048.utils.MovementDetector

class GameActivity : AppCompatActivity(), GameContract.View {

    private lateinit var btnUndo: AppCompatButton
    private lateinit var btnNewGame: AppCompatButton
    private lateinit var score: TextView
    private lateinit var target: TextView
    private lateinit var record: TextView
    private lateinit var dialog: DialogExit
    private lateinit var btnUndo2: AppCompatButton
    private var isUndo : Boolean = true

    private val buttons: ArrayList<TextView> = ArrayList(16)
    private val presenter = GamePresenter(this, GameRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var w : Window = window
        w.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setContentView(R.layout.game_activity)

        loadViews()

        presenter.startGame()


    }

    override fun onPause() {
        presenter.saveData()
        super.onPause()
    }




    @SuppressLint("SetTextI18n")
    private fun loadViews() {
        score = findViewById(R.id.realScore)
        record = findViewById(R.id.realRecord)
        target = findViewById(R.id.realTarget)
        btnUndo = findViewById(R.id.undo)
        btnUndo2 = findViewById(R.id.undo2)
        btnNewGame = findViewById(R.id.restart)




        btnUndo.setOnClickListener{
            if(isUndo) {
                presenter.undo()
                isUndo = false
                btnUndo.visibility = View.INVISIBLE
                btnUndo2.visibility = View.VISIBLE
                btnUndo2.setTextColor(Color.parseColor("#909090")).toString()
            }
        }

        findViewById<AppCompatButton>(R.id.home).setOnClickListener{
            dialog = DialogExit(this , {finish()} , "Exit the game?")
            dialog.setCancelable(false)
            dialog.show()
        }

        findViewById<AppCompatButton>(R.id.restart).setOnClickListener{
            dialog = DialogExit(this, {presenter.restart()}, "Start a new game?")
            isUndo = false
            dialog.setCancelable(false)
            dialog.show()
        }


        val mainContainer = findViewById<LinearLayout>(R.id.mainContainer)
      //  Score = findViewById(R.id.realScore)
        for (i in 0 until mainContainer.childCount) {
            val childContainer = mainContainer.getChildAt(i) as LinearLayout
            for (j in 0 until childContainer.childCount) {
                buttons.add(childContainer.getChildAt(j) as TextView)
            }
        }


       // Score = findViewById(R.id.realScore)
      //  Record = findViewById(R.id.realRecord)
        val movementDetector = MovementDetector(this)
        movementDetector.setOnMovementListener {
            btnUndo.visibility = View.VISIBLE
            btnUndo2.visibility = View.INVISIBLE
            isUndo = true

            when (it) {
                Movement.LEFT -> presenter.moveLeft()
                Movement.RIGHT -> presenter.moveRight()
                Movement.DOWN -> presenter.moveDown()
                Movement.UP -> presenter.moveUp()
            }
        }

        mainContainer.setOnTouchListener(movementDetector)
    }

    override fun changeState(matrix: Array<Array<Int>>) {

        if (isUndo){
            btnUndo.visibility = View.VISIBLE
            btnUndo2.visibility = View.INVISIBLE
            btnUndo2.setTextColor(Color.parseColor("#909090")).toString()
        }else{
            btnUndo.visibility = View.INVISIBLE
            btnUndo2.visibility = View.VISIBLE
            btnUndo2.setTextColor(Color.parseColor("#909090")).toString()
        }

        target.text = presenter.getTarget().toString()
        score.text = presenter.getScore().toString()
        record.text = presenter.getRecord().toString()

        for (i in matrix.indices) {
            for (j in 0 until matrix[i].size) {
                val button = buttons[4 * i + j]
                val value = matrix[i][j]

                if (value == 1024 || value == 2048 || value == 4096 || value == 8192) {
                    button.textSize = 24f
                }

                if (value == 2 || value == 4) {
                    button.setTextColor(Color.parseColor("#766962"))
                } else if (value == 8) {
                    button.setTextColor(Color.parseColor("#944925"))
                } else {
                    button.setTextColor(Color.parseColor("#F9F4F3"))
                }
           //     Score.text = presenter.getScore().toString()
                if (value == 0) button.text = ""
                else button.text = matrix[i][j].toString()
                button.setBackgroundResource(
                    when (value) {
                        0 -> R.drawable.color_0
                        2 -> R.drawable.color_2
                        4 -> R.drawable.color_4
                        8 -> R.drawable.color_8
                        16 -> R.drawable.color_16
                        32 -> R.drawable.color_32
                        64 -> R.drawable.color_64
                        128 -> R.drawable.color_128
                        256 -> R.drawable.color_256
                        512 -> R.drawable.color_512
                        1024 -> R.drawable.color_1024
                        2048 -> R.drawable.color_2048
                        else -> R.drawable.else_color

                    }
                )
            }
        }
    }

    override fun showFinishDialog() {
        val dialog = DialogGameOver(
            this,
            {
                finish()
                presenter.restart()
                isUndo = false
            },
            {
                presenter.restart()
                isUndo = false


            })
        dialog.setCancelable(false)
        dialog.show()
        isUndo = false



    }
}