package good.damn.videoapi

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import good.damn.videoapi.theme.VATheme
import good.damn.videoapi.theme.VAThemeDark

@HiltAndroidApp
class VAApp
: Application() {

    companion object {
        var theme: VATheme = VAThemeDark()

        var width = 0f
        var height = 0f
    }

    override fun onCreate() {
        super.onCreate()

        resources.displayMetrics.apply {
            width = widthPixels.toFloat()
            height = heightPixels.toFloat()
        }
    }

}