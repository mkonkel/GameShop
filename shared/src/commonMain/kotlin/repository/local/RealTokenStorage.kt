package repository.local

internal class RealTokenStorage : TokenStorage {
    private val storage = mutableMapOf<String, String>()

    override fun put(key: String, value: String) {
        storage[key] = value
    }

    override fun get(key: String): String? {
        return storage[key]
    }
}