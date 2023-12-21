package repository.local

internal interface TokenStorage {
    fun put(key: String, value: String)
    fun get(key: String): String?
}