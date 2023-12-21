import di.module.ApiModule
import kotlinx.coroutines.test.runTest
import org.junit.Test
import repository.local.TokenStorage

class LoginApiTest {
    private val mockStorage = object : TokenStorage {
        val storage = mutableMapOf<String, String>()
        override fun put(key: String, value: String) {
            storage.put(key, value)
        }

        override fun get(key: String): String? {
            return storage[key]
        }
    }

    private val api = ApiModule(mockStorage)

    @Test
    fun `login with valid credentials`() = runTest {
        val loginApi = api.apiFactory.loginApi()

        loginApi.login("admin", "admin")
    }
}