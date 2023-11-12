package online.dailyq

import android.app.Application
import online.dailyq.api.ApiService
import online.dailyq.db.AppDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AuthManager.init(this)
        ApiService.init(this)
        AppDatabase.init(this)
        Notifier.init(this)
        Settings.init(this)
    }
}
