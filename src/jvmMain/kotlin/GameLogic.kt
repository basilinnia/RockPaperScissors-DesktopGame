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
            Message(playerMove, computerMove.value) {winner ->
                if (winner == "computer") {
                    computerScore.value += 1
                } else if (winner == "player") {
                    playerScore.value += 1
                }
            }
            CurrentMove(playerMove,computerMove.value)
            Moves(setPlayerMove, computerMove)
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
fun Moves(setMove: (String) -> Unit, computerMove: MutableState<String>) {
    Text("Choose your move, rock paper or scissors?", color = Color.Gray)

    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 25.dp), horizontalArrangement = Arrangement.SpaceAround) {
        for (move in moves) {
            Button(
                shape = RoundedCornerShape(14),
                onClick = {
                    setMove(move)
                    computerMove.value = moves.random()
                },
                modifier = Modifier.size(180.dp, 60.dp)
            ) {
                Text(fontWeight = FontWeight.ExtraBold, fontFamily = Nunito, text = move)
            }
        }
    }
}

@Composable
fun Message(playerMove: String, computerMove: String, updateScores: (String) -> Unit) {
    var text = " "
    when (playerMove) {
        "ROCK" ->  text = if (computerMove == "ROCK") "DRAW" else if(computerMove == "PAPER") "COMPUTER WON\uD83C\uDF89!"   else "YOU WON\uD83C\uDF89!"
        "PAPER" -> text = if (computerMove == "PAPER") "DRAW" else if(computerMove == "SCISSORS") "COMPUTER WON\uD83C\uDF89!" else "YOU WON\uD83C\uDF89!"
        "SCISSORS" -> text = if (computerMove == "SCISSORS") "DRAW" else if(computerMove == "ROCK") "COMPUTER WON\uD83C\uDF89!" else "YOU WON\uD83C\uDF89!"
    }
    LaunchedEffect(text) {
        if (text == "COMPUTER WON\uD83C\uDF89!") {
            updateScores("computer")
        } else if (text == "YOU WON\uD83C\uDF89!") {
            updateScores("player")
        }
    }

    Text(text, fontFamily = RedHatDisplay, fontSize = 46.sp, fontWeight = FontWeight.Bold, color = colors.onSecondary)
}
