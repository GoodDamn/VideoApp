package good.damn.videoapi.holders

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class VAViewHolderVideo(
    val textViewTitle: AppCompatTextView,
    val textViewSubtitle: AppCompatTextView,
    val layout: ViewGroup
): RecyclerView.ViewHolder(
    layout
) {

    companion object {
        inline fun create(
            context: Context
        ): VAViewHolderVideo {

            val layout = FrameLayout(
                context
            ).apply {
                setBackgroundColor(0)
            }


            val layoutContent = LinearLayout(
                context
            ).apply {
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
                layoutContent.addView(
                    this,
                    -1,
                    -2
                )
            }

            val tvSubtitle = AppCompatTextView(
                context
            ).apply {
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
                layout
            )
        }
    }
}