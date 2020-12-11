import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 8080

    embeddedServer(Netty, 8080) {
        routing {
            install(ContentNegotiation) {
                gson {
                    setPrettyPrinting()
                }
            }

            get("/") {

            }
        }
    }.start(wait = true)
}