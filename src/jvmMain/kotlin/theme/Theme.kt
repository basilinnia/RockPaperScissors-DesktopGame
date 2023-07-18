package theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightTheme = lightColors(
    surface = Color(0xffD9D9D9),
    onSurface  = Color(0xff070A52),
    primary = Color(0xffDF2E38),
    onPrimary = Color.White,
    secondaryVariant = Color.Black,
)

val DarkTheme = darkColors(
    surface = Color(0xff353535),
    onSurface  = Color(0xffBEBFD1),
    primary = Color(0xff2751A3),
    onPrimary = Color.White,
    secondaryVariant = Color.White,
)


@Composable
fun AppTheme(
    isDark: Boolean = true,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = if (isDark) DarkTheme else LightTheme // Use the appropriate color list
    ) {
        Surface {
            content()
        }
    }
}
