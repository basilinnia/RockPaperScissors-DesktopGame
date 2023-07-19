import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.Nunito
import theme.RedHatDisplay
import theme.SignikaNegative
import javax.xml.transform.Source

@Composable
fun GameScreen(navigateToMainScreen: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = navigateToMainScreen) {
                Icon(Icons.Outlined.ArrowBack, contentDescription = "back")
            }
            Text(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 26.sp,
                text = "Rock Paper Scissors with Compose",
                fontFamily = Nunito
            )
        }
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 15.dp, vertical = 15.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TextButton(onClick = {}) {
                    Text(
                        color = colors.secondaryVariant,
                        fontFamily = SignikaNegative,
                        fontSize = 20.sp,
                        text = "Reset The Tour",
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        fontFamily = RedHatDisplay,
                        color = colors.secondaryVariant,
                        text = "PLAYER SCORE: ",
                        fontSize = 15.sp
                    )
                    Text(
                        fontFamily = RedHatDisplay,
                        modifier = Modifier.padding(start = 120.dp),
                        color = colors.secondaryVariant,
                        text = "COMPUTER SCORE: ",
                        fontSize = 15.sp
                    )
                }
            }
            Message()
            CurrentMove()
            Moves()
        }
    }
}


@Composable
fun CurrentMove() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource("moves/rock.svg"),
            contentDescription = null,
            modifier = Modifier.size(230.dp),
            colorFilter = ColorFilter.tint(colors.secondaryVariant)
            )
        Text(fontWeight = FontWeight.ExtraBold, fontFamily = Nunito, text = "VS")
        Image(
            painter = painterResource("moves/paper.svg"),
            contentDescription = null,
            colorFilter = ColorFilter.tint(colors.secondaryVariant),
            modifier = Modifier.size(230.dp).rotate(180f)
        )
    }
}

@Composable
fun Moves() {
    Text("Choose your move, rock paper or scissors?", color = Color.Gray)

    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 25.dp), horizontalArrangement = Arrangement.SpaceAround) {
        for (move in listOf("ROCK", "PAPER", "SCISSORS")) {
            Button(
                shape = RoundedCornerShape(14),
                onClick = { /* Add onClick action here */ },
                modifier = Modifier.size(180.dp, 60.dp)
            ) {
                Text(fontWeight = FontWeight.ExtraBold, fontFamily = Nunito, text = move)
            }
        }
    }
}

@Composable
fun Message() {
    Text("YOU WON\uD83C\uDF89!", fontFamily = RedHatDisplay, fontSize = 46.sp, fontWeight = FontWeight.Bold, color = colors.onSecondary)
}
