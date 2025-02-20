package good.damn.videoapi.arch.state

sealed class VAStateResponse<T>(
    val data: T? = null,
    val error: String? = null
) {
    class Loading<T>: VAStateResponse<T>()

    class Success<T>(
        data: T
    ): VAStateResponse<T>(
        data
    )

    class Error<T>(
        error: String?
    ): VAStateResponse<T>(
        error = error
    )
}

