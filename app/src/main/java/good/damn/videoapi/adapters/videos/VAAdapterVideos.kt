package good.damn.videoapi.adapters.videos

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import good.damn.videoapi.VAApp
import good.damn.videoapi.arch.models.VAModelVideoListItem
import good.damn.videoapi.holders.VAViewHolderVideo

class VAAdapterVideos(
    private val videos: List<VAModelVideoListItem>,
    private val onSelectVideo: VAListenerOnSelectVideo
): RecyclerView.Adapter<VAViewHolderVideo>(){

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

            holder.itemView.setOnClickListener {
                onSelectVideo.onSelectVideo(
                    this
                )
            }

            Picasso.get()
                .load(thumbnail)
                .centerCrop()
                .fit()
                .into(holder.imageViewThumbnail)

        }
    }
}