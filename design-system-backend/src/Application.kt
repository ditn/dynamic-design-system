package dev.adambennett.design.system.backend

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.content.*
import io.ktor.http.content.*
import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import java.awt.Color

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.module() {
    install(ContentNegotiation) {
        gson {
        }
    }

    routing {

        get("/theme") {
            call.respond(DesignSystemLightPalette)
        }
    }
}

private val Yellow700 = Color.decode("#F3B711")

private val Blue500 = Color.decode("#0540F2")
private val Blue800 = Color.decode("#001CCF")

private val Red800 = Color.decode("#D00036")

private data class Colors(
    val primary: Color,
    val primaryVariant: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val surface: Color,
    val onSurface: Color,
    val onBackground: Color,
    val error: Color,
    val onError: Color,
)

private val DesignSystemLightPalette = Colors(
    primary = Blue500,
    primaryVariant = Blue800,
    onPrimary = Color.WHITE,
    secondary = Yellow700,
    onSecondary = Color.BLACK,
    surface = Yellow700,
    onSurface = Color.BLACK,
    onBackground = Color.BLACK,
    error = Red800,
    onError = Color.WHITE
)
