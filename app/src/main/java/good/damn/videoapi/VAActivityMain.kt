package good.damn.videoapi

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import good.damn.videoapi.adapters.VAAdapterPager
import good.damn.videoapi.arch.viewModels.VAViewModelVideoList
import good.damn.videoapi.fragments.VAFragmentListVideos

@AndroidEntryPoint
class VAActivityMain
: AppCompatActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )

        ViewPager2(
            this
        ).apply {
            adapter = VAAdapterPager(
                arrayOf(
                    VAFragmentListVideos()
                ),
                supportFragmentManager,
                lifecycle
            )

            setContentView(
                this
            )
        }
    }


}