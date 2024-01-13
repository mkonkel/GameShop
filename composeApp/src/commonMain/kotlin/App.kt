import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.michalkonkel.gameshop.domain.user.User
import di.DI
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.appendIfNameAbsent
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

@Composable
fun App() {
    MaterialTheme {
        Content()
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
private fun Content() {
    GlobalScope.launch {
        val client = HttpClient {
            HttpClient {
                install(ContentNegotiation) {
                    json(
                        Json {
                            prettyPrint = true
                            isLenient = true
                            ignoreUnknownKeys = true
                            useAlternativeNames = false
                        }
                    )
                }
                defaultRequest {
                    url {
                        protocol = URLProtocol.HTTP
                        host = "localhost"
                        port = 3000
                    }
                    headers.appendIfNameAbsent("Content-Type", "application/json")
                }
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.ALL
                }
                install(Auth) {
                    bearer {
                        loadTokens {
                            BearerTokens("123", "")
                        }
                        refreshTokens {
                            TODO("Not implemented yet!")
                        }
                        sendWithoutRequest { request ->
                            when (request.url.pathSegments.last()) {
                                "login" -> false
                                else -> true
                            }
                        }
                    }
                }
            }
        }

        val r = client.post("http://localhost:3000/login") {
            contentType(ContentType.Application.Json)
            setBody("""{
                "username": "user",
                "password": "pass"
            }""".trimIndent())
        }

        println(r.bodyAsText())
    }
}