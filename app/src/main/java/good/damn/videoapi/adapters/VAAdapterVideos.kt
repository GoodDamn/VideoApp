package good.damn.videoapi.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import good.damn.videoapi.VAApp
import good.damn.videoapi.arch.models.VAModelVideoList
import good.damn.videoapi.holders.VAViewHolderVideo

class VAAdapterVideos(
    private val videos: List<VAModelVideoList>
): RecyclerView.Adapter<VAViewHolderVideo>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = VAViewHolderVideo.create(
        parent.context,
        VAApp.width,
        VAApp.height * 0.25f
    )

    override fun getItemCount() =
        videos.size

    override fun onBindViewHolder(
        holder: VAViewHolderVideo,
        position: Int
    ) {
        videos[position].apply {
            holder.textViewTitle.text = title
            holder.textViewSubtitle.text = subtitle

            Picasso.get()
                .load(thumbnail)
                .centerCrop()
                .fit()
                .into(holder.imageViewThumbnail)

        }
    }
}