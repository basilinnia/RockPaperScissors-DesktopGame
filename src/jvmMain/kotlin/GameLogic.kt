import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.Nunito
import theme.RedHatDisplay
import theme.SignikaNegative

val moves = listOf("ROCK", "PAPER", "SCISSORS")
@Composable
fun GameScreen(navigateToMainScreen: () -> Unit) {
    val (playerMove, setPlayerMove) = remember { mutableStateOf("ROCK") }
    val computerMove = remember{mutableStateOf("ROCK")}
    val playerScore = remember { mutableStateOf(0) }
    val computerScore = remember { mutableStateOf(0) }

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
                TextButton(onClick = {
                    playerScore.value = 0
                    computerScore.value = 0
                }) {
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
                        text = "PLAYER SCORE: ${playerScore.value}",
                        fontSize = 15.sp
                    )
                    Text(
                        fontFamily = RedHatDisplay,
                        modifier = Modifier.padding(start = 120.dp),
                        color = colors.secondaryVariant,
                        text = "COMPUTER SCORE: ${computerScore.value}",
                        fontSize = 15.sp
                    )
                }
            }
            Text(getWinner(playerMove, computerMove.value), fontFamily = RedHatDisplay, fontSize = 46.sp, fontWeight = FontWeight.Bold, color = colors.onSecondary)
            CurrentMove(playerMove,computerMove.value)
            Moves(setPlayerMove, computerMove, playerScore, computerScore)
        }
    }
}


@Composable
fun CurrentMove(playerMove: String, computerMove:String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource("moves/$playerMove.svg"),
            contentDescription = null,
            modifier = Modifier.size(230.dp),
            colorFilter = ColorFilter.tint(colors.secondaryVariant)
            )
        Text(fontWeight = FontWeight.ExtraBold, fontFamily = Nunito, text = "VS")
        Image(
            painter = painterResource("moves/${computerMove}.svg"),
            contentDescription = null,
            colorFilter = ColorFilter.tint(colors.secondaryVariant),
            modifier = Modifier.size(230.dp).rotate(180f)
        )
    }
}

@Composable
fun Moves(setMove: (String) -> Unit, computerMove: MutableState<String>, playerScore:MutableState<Int>, computerScore:MutableState<Int>) {
    Text("Choose your move, rock paper or scissors?", color = Color.Gray)

    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 25.dp), horizontalArrangement = Arrangement.SpaceAround) {
        for (move in moves) {
            Button(
                shape = RoundedCornerShape(14),
                onClick = {
                    setMove(move)
                    computerMove.value = moves.random()
                    updateScores(getWinner(move, computerMove.value), playerScore, computerScore)
                },
                modifier = Modifier.size(180.dp, 60.dp)
            ) {
                Text(fontWeight = FontWeight.ExtraBold, fontFamily = Nunito, text = move)
            }
        }
    }
}

fun getWinner(playerMove: String, computerMove: String): String {
    return when {
        playerMove == computerMove -> "DRAW"
        (playerMove == "ROCK" && computerMove == "SCISSORS") ||
                (playerMove == "PAPER" && computerMove == "ROCK") ||
                (playerMove == "SCISSORS" && computerMove == "PAPER") -> "YOU WON\uD83C\uDF89!"
        else -> "COMPUTER WON\uD83C\uDF89!"
    }
}

fun updateScores(winner: String, playerScore:MutableState<Int>, computerScore:MutableState<Int>) {
    when (winner) {
        "YOU WON\uD83C\uDF89!"-> playerScore.value += 1
        "COMPUTER WON\uD83C\uDF89!"->computerScore.value += 1
    }
}