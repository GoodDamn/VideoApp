package good.damn.videoapi.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class VAAdapterPager(
    private val fragments: Array<Fragment>,
    manager: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(
    manager,
    lifecycle
) {

    override fun getItemCount() =
        fragments.size

    override fun createFragment(
        position: Int
    ) = fragments[position]
}