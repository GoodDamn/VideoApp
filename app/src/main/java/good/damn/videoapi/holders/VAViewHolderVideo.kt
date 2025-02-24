package good.damn.videoapi.holders

import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import good.damn.videoapi.VAApp
import good.damn.videoapi.extensions.setTextSizePx

class VAViewHolderVideo(
    val textViewTitle: AppCompatTextView,
    val textViewDuration: AppCompatTextView,
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


            val layoutContent = FrameLayout(
                context
            ).apply {
                setBackgroundColor(
                    VAApp.theme.colorTextBack
                )
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

                setTextSizePx(
                    height * 0.1f
                )

                layoutParams = FrameLayout.LayoutParams(
                    -2,
                    -2
                ).apply {
                    gravity = Gravity.START or Gravity.BOTTOM
                }

                layoutContent.addView(
                    this
                )
            }

            val tvDuration = AppCompatTextView(
                context
            ).apply {
                background = null
                setTextColor(
                    VAApp.theme.colorText
                )

                setTextSizePx(
                    height * 0.1f
                )

                layoutParams = FrameLayout.LayoutParams(
                    -2,
                    -2
                ).apply {
                    gravity = Gravity.END or Gravity.BOTTOM
                }

                layoutContent.addView(
                    this
                )
            }

            layout.addView(
                layoutContent
            )

            return VAViewHolderVideo(
                tvTitle,
                tvDuration,
                ivThumbnail,
                layout
            )
        }
    }
}