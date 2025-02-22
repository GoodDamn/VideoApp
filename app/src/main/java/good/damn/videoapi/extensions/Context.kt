package good.damn.videoapi.extensions

import android.content.Context
import android.widget.Toast

inline fun Context.toast(
    msg: String
) = Toast.makeText(
    this,
    msg,
    Toast.LENGTH_LONG
).show()