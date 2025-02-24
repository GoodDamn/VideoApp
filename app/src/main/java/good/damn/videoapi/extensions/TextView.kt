package good.damn.videoapi.extensions

import android.util.TypedValue
import android.widget.TextView

inline fun TextView.setTextSizePx(
    v: Float
) = setTextSize(
    TypedValue.COMPLEX_UNIT_PX,
    v
)