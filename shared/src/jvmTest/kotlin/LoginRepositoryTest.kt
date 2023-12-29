import di.module.RepositoryModule
import kotlinx.coroutines.test.runTest
import org.junit.Test

class LoginRepositoryTest {
    private val loginRepository = RepositoryModule().remoteRepositoryFactory.loginRepository()

    @Test
    fun `login with valid credentials`() =
        runTest {
            loginRepository.login("admin", "pass")
        }
}
