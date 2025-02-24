package good.damn.videoapi.extensions

inline fun Long.toStringTime(): String {
    val minutes = this / 60
    val seconds = this % 60
    val hours = this / 3600

    val divMinutes = minutes / 10
    val divSeconds = seconds / 10

    val div2Minutes = minutes % 10
    val div2Seconds = seconds % 10

    if (hours == 0L) {
        return "$divMinutes$div2Minutes:$divSeconds$div2Seconds"
    }

    return "$hours:$divMinutes$div2Minutes:$divSeconds$div2Seconds"
}