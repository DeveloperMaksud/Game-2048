package uz.gita.game_2048.repository

import android.content.Context
import android.content.SharedPreferences

class LocalData {
    companion object {
        private lateinit var sharedPref: SharedPreferences
        fun getInstance(context: Context): SharedPreferences {
            if (!::sharedPref.isInitialized) sharedPref =
                context.getSharedPreferences("DATA", Context.MODE_PRIVATE)
            return sharedPref
        }


        fun setMatrix(matrix: Array<Array<Int>>) {
            for (i in matrix.indices) {
                for (j in 0 until matrix[i].size) {
                    sharedPref.edit().putInt("matrix|$i|$j", matrix[i][j]).apply()
                }
            }
        }

        fun setMatrixUndo(matrixUndo: Array<Array<Int>>) {
            for (i in matrixUndo.indices) {
                for (j in 0 until matrixUndo[i].size) {
                    sharedPref.edit().putInt("matrixUndo|$i|$j", matrixUndo[i][j]).apply()
                }
            }
        }

        fun getMatrix(): Array<Array<Int>> {
            val matrix: Array<Array<Int>> = arrayOf(
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0)
            )

            for (i in matrix.indices) {
                for (j in 0 until matrix[i].size) {
                    matrix[i][j] = sharedPref.getInt("matrix|$i|$j", 0)
                }
            }
            return matrix
        }

        fun getMatrixUndo(): Array<Array<Int>> {
            val matrixUndo: Array<Array<Int>> = arrayOf(
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0),
                arrayOf(0, 0, 0, 0)
            )
            for (i in matrixUndo.indices) {
                for (j in 0 until matrixUndo[i].size) {
                    matrixUndo[i][j] = sharedPref.getInt("matrixUndo|$i|$j", 0)
                }
            }
            return matrixUndo
        }

        fun setScore(score: Int) {
            sharedPref.edit().putInt("score", score).apply()
        }

        fun getScore(): Int {
            return sharedPref.getInt("score", 0)
        }

        fun setRecord(record: Int) {
            sharedPref.edit().putInt("record", record).apply()
        }

        fun getRecord(): Int {
            return sharedPref.getInt("record", 0)
        }

        fun setTarget(target: Int) {
            sharedPref.edit().putInt("target", target).apply()
        }

        fun getTarget(): Int {
            return sharedPref.getInt("target", 0)
        }

        fun setScoreUndo(scoreUndo: Int) {
            sharedPref.edit().putInt("scoreUndo", scoreUndo).apply()
        }

        fun getScoreUndo(): Int {
            return sharedPref.getInt("scoreUndo", 0)
        }

        fun setRecordUndo(recordUndo: Int) {
            sharedPref.edit().putInt("recordUndo", recordUndo).apply()
        }

        fun getRecordUndo(): Int {
            return sharedPref.getInt("recordUndo", 0)
        }
    }
}