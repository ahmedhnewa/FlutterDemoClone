// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.Dimension

@Composable
@Preview
fun App() {
    val service = remember { MainService() }

    MaterialTheme(colors = lightColors(
        primary = Color(0xFF0098FC),
        secondary = Color(0xFF0098FC)
    )) {
        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                floatingActionButtonPosition = FabPosition.End,
                floatingActionButton = {
                    FloatingActionButton(service::increaseCounter,) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                    }
                },
                topBar = {
                    TopAppBar(
                        title = {
                            Text("Compose Demo Home Page", Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                        },
                    )
                },
            ) {
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("You have pushed the button this many times:")
                    Text(service.counter.toString(), style = MaterialTheme.typography.h4)
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        this.window.minimumSize = Dimension(800, 600)
        App()
    }
}
