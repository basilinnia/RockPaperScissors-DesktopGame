import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import theme.AppTheme

@Composable
@Preview
fun App() {
    val themeState = remember { mutableStateOf(true) }
    val screenState = remember { mutableStateOf(true) }

    AppTheme(isDark = themeState.value) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            IconButton(
                modifier = Modifier.padding(16.dp),
                onClick = ({ themeState.value = !themeState.value })
            ) {
                Icon(
                    if (themeState.value) Icons.Outlined.LightMode else Icons.Outlined.DarkMode,
                    contentDescription = "Icon",
                    modifier = Modifier.size(48.dp)
                )
            }
            if (screenState.value) {
                MainScreen { screenState.value = false }
            } else {
                GameScreen { screenState.value = true }
            }
        }
    }
}



@Composable
fun MainScreen(navigateToGameScreen: ()-> Unit ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                shape = RoundedCornerShape(14),
                onClick = {navigateToGameScreen()},
                modifier = Modifier.padding(end = 30.dp).size(120.dp, 80.dp)
            ) {
                Text(
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    text = "PLAY"
                )
            }
            Text(
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                text = "Rock Paper Scissors \nwith Compose"
            )
        }
    }

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Rock Paper Scissors") {
        App()
    }
}
