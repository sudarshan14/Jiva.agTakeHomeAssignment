package ag.jiva.mandi.util


sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T? = null) : Resource<T>(data)
    class Failure<T>(message: String, data: T?) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
