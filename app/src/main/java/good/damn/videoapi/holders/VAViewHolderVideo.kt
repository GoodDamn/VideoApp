package good.damn.videoapi.holders

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import good.damn.videoapi.VAApp
import good.damn.videoapi.adapters.videos.VAListenerOnSelectVideo

class VAViewHolderVideo(
    val textViewTitle: AppCompatTextView,
    val textViewSubtitle: AppCompatTextView,
    val imageViewThumbnail: AppCompatImageView,
    layout: ViewGroup
): RecyclerView.ViewHolder(
    layout
) {

    companion object {
        inline fun create(
            context: Context,
            width: Float,
            height: Float
        ): VAViewHolderVideo {

            val layout = FrameLayout(
                context
            ).apply {
                background = null
                layoutParams = ViewGroup.LayoutParams(
                    width.toInt(),
                    height.toInt()
                )
            }


            val ivThumbnail = AppCompatImageView(
                context
            ).apply {
                layout.addView(
                    this,
                    -1,
                    -1
                )
            }


            val layoutContent = LinearLayout(
                context
            ).apply {
                setBackgroundColor(
                    VAApp.theme.colorTextBack
                )
                orientation = LinearLayout.VERTICAL
                layoutParams = FrameLayout.LayoutParams(
                    -1,
                    -2
                ).apply {
                    gravity = Gravity.BOTTOM
                }
            }

            val tvTitle = AppCompatTextView(
                context
            ).apply {
                background = null
                setTextColor(
                    VAApp.theme.colorText
                )

                layoutContent.addView(
                    this,
                    -1,
                    -2
                )
            }

            val tvSubtitle = AppCompatTextView(
                context
            ).apply {
                background = null
                setTextColor(
                    VAApp.theme.colorText
                )

                layoutContent.addView(
                    this,
                    -1,
                    -2
                )
            }

            layout.addView(
                layoutContent
            )

            return VAViewHolderVideo(
                tvTitle,
                tvSubtitle,
                ivThumbnail,
                layout
            )
        }
    }
}