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
    }

}