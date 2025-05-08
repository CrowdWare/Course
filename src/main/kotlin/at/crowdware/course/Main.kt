/*
 * Copyright (C) 2025 CrowdWare
 *
 * This file is part of Course.
 *
 *  Course is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Course is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Course.  If not, see <http://www.gnu.org/licenses/>.
 */

package at.crowdware.course

import at.crowdware.course.util.GlobalAppState
import at.crowdware.course.util.State
import at.crowdware.course.ui.aboutDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import at.crowdware.course.theme.AppTheme
import at.crowdware.course.theme.ExtendedTheme
import at.crowdware.course.ui.WindowCaptionArea
import at.crowdware.course.ui.WindowControlButton
import at.crowdware.course.util.createAppState
import at.crowdware.course.ui.desktop
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.awt.Desktop
import java.awt.Dimension
import java.awt.Frame
import java.awt.Window
import java.io.File
import java.io.IOException
import java.io.PrintStream


private const val APPNAME = "Course"

fun main() = application {
    println("🔥 application startet!")
    var appName = APPNAME
    var appTitle by mutableStateOf("")
    var isAboutDialogOpen by mutableStateOf(false)
    val appState = createAppState()
    GlobalAppState.appState = appState

    loadAppState(APPNAME)
    appState.theme = "Dark"

    appTitle = appName
    val windowState = rememberWindowState(
        width = (appState.windowWidth).dp,
        height = (appState.windowHeight).dp
    )

    if (appState.theme.isEmpty())
        appState.theme = if (androidx.compose.foundation.isSystemInDarkTheme()) "Dark" else "Light"
    val isWindows = System.getProperty("os.name").contains("Windows", ignoreCase = true)
    var isAskingToClose by remember { mutableStateOf(false) }

    // setup logging, all println are stored in a log file
    val isDevMode = System.getenv("DEV_MODE") == "true"
    if (!isDevMode)
        setupLogging(APPNAME)

    System.setProperty("apple.awt.application.name", appName)
    if (Desktop.isDesktopSupported()) {
        val desktop = Desktop.getDesktop()

        if (desktop.isSupported(Desktop.Action.APP_ABOUT)) {
            desktop.setAboutHandler {
                isAboutDialogOpen = true
            }
        }

        if (desktop.isSupported(Desktop.Action.APP_QUIT_HANDLER)) {
            desktop.setQuitHandler { _, quitResponse ->
                var frame: Window = Frame.getWindows()[0]
                onAppClose(frame as ComposeWindow, APPNAME)
                quitResponse.performQuit()
            }
        }
    }

    Window(
        onCloseRequest = { isAskingToClose = true },
        title = appName,
        transparent = !isWindows,
        undecorated = !isWindows,
        resizable = true,
        state = windowState,
        icon = painterResource("icons/WindowsIcon.ico")
    ) {

        var isMaximized by remember { mutableStateOf(window.extendedState == Frame.MAXIMIZED_BOTH) }
        window.minimumSize = Dimension(400, 300)

        LaunchedEffect(appState.theme) {
            // set new location
            window.setLocation(appState.windowX ?: 100, appState.windowY ?: 100)

            // Listen for changes in the window's maximized state
            window.addWindowStateListener {
                isMaximized = (window.extendedState == Frame.MAXIMIZED_BOTH)
            }
        }

        AppTheme(darkTheme = appState.theme == "Dark") {
            var shape = RectangleShape
            var borderShape = RectangleShape

            if (!isWindows) {
                shape = RoundedCornerShape(10.dp)
                borderShape = RoundedCornerShape(10.dp)
            }
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .border(0.5.dp, Color.Gray, borderShape),
                color = Color(55, 55, 55),
                shape = shape

            ) {
                // used on Windows only, no close button on MacOS
                if (isAskingToClose) {
                    onAppClose(window, APPNAME)
                    exitApplication()
                }
                Column {
                    if (!isWindows) {
                        WindowCaptionArea {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .background(ExtendedTheme.colors.captionColor)
                                    .padding(start = 12.dp, top = 6.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxHeight(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // Mac-style window controls (close, minimize, fullscreen)
                                    WindowControlButton(color = Color(255, 92, 92)) {
                                        onAppClose(window, APPNAME)
                                        exitApplication()
                                    } // Close
                                    Spacer(modifier = Modifier.width(8.dp))
                                    WindowControlButton(color = Color(255, 189, 76)) {
                                        window.extendedState = Frame.ICONIFIED
                                    } // Minimize
                                    Spacer(modifier = Modifier.width(8.dp))
                                    WindowControlButton(color = Color(87, 188, 87)) {
                                        val isMaximized = window.extendedState == Frame.MAXIMIZED_BOTH
                                        window.extendedState =
                                            if (isMaximized) Frame.NORMAL else Frame.MAXIMIZED_BOTH
                                    }
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        text = appTitle,
                                        color = MaterialTheme.colors.onPrimary,
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }

                    desktop()

                    if (isAboutDialogOpen) {
                        aboutDialog(
                            appName = appName,
                            version = "1.0",
                            onDismissRequest = { isAboutDialogOpen = false }
                        )
                    }
                }
            }
        }
    }
}

fun onAppClose(frame: ComposeWindow, path: String) {
    saveState(frame, path)
}

fun saveState(frame: ComposeWindow, path: String) {
    // Save the app state when the window is closed
    saveAppState(
        State(
            windowHeight = frame.height,
            windowWidth = frame.width,
            windowX = frame.x,
            windowY = frame.y,
            theme = "Dark"
        ), path
    )
}

fun setupLogging(path: String) {
    val userHome = System.getProperty("user.home")
    val configDirectory = if (System.getProperty("os.name").contains("Windows")) {
        File("$userHome/AppData/Local/$path")
    } else {
        File("$userHome/Library/Application Support/$path")
    }
    val tempFile = File(configDirectory, "$path.log")

    if (!configDirectory.exists()) {
        configDirectory.mkdirs()
    }
    if (!tempFile.exists()) {
        tempFile.createNewFile()
    }

    // Redirect stdout and stderr to the file
    val logStream = PrintStream(tempFile.outputStream())
    System.setOut(logStream)
    System.setErr(logStream)

    println("Logging initialized. Writing to: ${tempFile.absolutePath}")
}

fun saveAppState(state: State, path: String) {
    val userHome = System.getProperty("user.home")
    val configDirectory = if (System.getProperty("os.name").contains("Windows")) {
        File("$userHome/AppData/Local/$path")
    } else {
        File("$userHome/Library/Application Support/$path")
    }

    // Create the directory if it doesn't exist
    if (!configDirectory.exists()) {
        configDirectory.mkdirs()
    }

    val configFile = File(configDirectory, "app_state.json")
    try {
        val jsonState = Json.encodeToString(state)
        configFile.writeText(jsonState)
    } catch (e: IOException) {
        println("Error writing app state: ${e.message}")
        e.printStackTrace()
    }
}

fun loadAppState(path: String) {
    val appState = GlobalAppState.appState
    val userHome = System.getProperty("user.home")
    val configDirectory = if (System.getProperty("os.name").contains("Windows")) {
        File("$userHome/AppData/Local/$path")
    } else {
        File("$userHome/Library/Application Support/$path")
    }
    val configFile = File(configDirectory, "app_state.json")

    if(!configDirectory.exists()) {
        configDirectory.mkdirs()
    }

    // Set default values
    if (appState != null) {
        appState.theme = "Dark"
        appState.windowX = 100
        appState.windowY = 100
        appState.windowWidth = 1400
        appState.windowHeight = 768
    }

    // Try to load saved state if it exists
    if (configFile.exists()) {
        try {
            val jsonState = configFile.readText()
            val state = Json.decodeFromString<State>(jsonState)
            if (appState != null) {
                appState.theme = state.theme
                appState.windowX = state.windowX
                appState.windowY = state.windowY
                appState.windowWidth = state.windowWidth
                appState.windowHeight = state.windowHeight
            }
        } catch (e: Exception) {
            println("Error loading app state: ${e.message}")
            e.printStackTrace()
            // Continue with default values set above
        }
    } else {
        // Create a default state file
        if (appState != null) {
            saveAppState(
                State(
                    windowHeight = appState.windowHeight,
                    windowWidth = appState.windowWidth,
                    windowX = appState.windowX ?: 100,
                    windowY = appState.windowY ?: 100,
                    theme = "Dark"
                ), path
            )
        }
    }
}