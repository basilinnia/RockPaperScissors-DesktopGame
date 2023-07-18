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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameScreen(navigateToMainScreen: () -> Unit) {
  Box( modifier = Modifier.fillMaxSize()){
    Column (modifier =Modifier.fillMaxWidth()) {
        Row ( verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 15.dp, top = 15.dp, end = 15.dp)) {
            IconButton(onClick = navigateToMainScreen) { Icon(Icons.Outlined.ArrowBack, contentDescription = "back") }
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                text = "Rock Paper Scissors with Compose"
            )
        }
        Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            TextButton(onClick = {}){
                Text(color = colors.secondaryVariant,fontSize = 20.sp, text = "Reset The Tour", fontWeight = FontWeight.SemiBold)
            }

            Moves()
        }
    }
  }
}

@Composable
fun Moves() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        for (move in listOf("ROCK", "PAPER", "SCISSORS")) {
            Button(
                shape = RoundedCornerShape(14),
                onClick = ({ /* Add onClick action here */ }),
                modifier = Modifier.size(180.dp, 60.dp)
            ) {
                Text(fontWeight = FontWeight.Bold, text = move)
            }
        }
    }
}
