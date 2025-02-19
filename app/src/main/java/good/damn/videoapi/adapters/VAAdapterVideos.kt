package good.damn.videoapi.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import good.damn.videoapi.arch.models.VAModelVideoList
import good.damn.videoapi.holders.VAViewHolderVideo

class VAAdapterVideos(
    private val videos: List<VAModelVideoList>
): RecyclerView.Adapter<VAViewHolderVideo>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = VAViewHolderVideo.create(
        parent.context
    )

    override fun getItemCount() =
        videos.size

    override fun onBindViewHolder(
        holder: VAViewHolderVideo,
        position: Int
    ) {

    }
}