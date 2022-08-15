package uz.gita.game_2048.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import uz.gita.game_2048.repository.LocalData

class App : Application(){
    private lateinit var localData: LocalData
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        LocalData.getInstance(this)
    }
}