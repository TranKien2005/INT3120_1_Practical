package com.example.lemonadeapp

import android.os.Bundle
import android.provider.CalendarContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeAppTheme {

            }
        }
    }
}

@Composable
fun LemonApp(modifier: Modifier = Modifier) {
    val result = remember { mutableStateOf(0) }
    var state: Int = 0
    val imagePather = when (result.value) {
        0 -> R.drawable.lemon_tree
        1 -> R.drawable.lemon_squeeze
        2 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val description = when (result.value) {
        0 -> R.string.lemonTree
        1 -> R.string.lemonSqueeze
        2 -> R.string.lemonDrink
        else -> R.string.lemonRestart
    }
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        )
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color(0xFFFFEB3B)),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = "Lemonade",
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
                    .wrapContentSize()
            ){
                Image(
                    painter = painterResource(id = imagePather),
                    contentDescription = stringResource(description),
                    modifier = Modifier.padding(bottom = 16.dp).clickable() {
                        if (result.value == 1) {
                            if (state == 0) {
                                state = (2..4).random()
                                state -= 1
                            }
                            else{
                                state--
                                if (state == 0) {
                                    result.value = (result.value + 1) % 4
                                }
                            }
                        } else {
                            result.value = (result.value + 1) % 4
                        }
                    }
                )
                Text(
                    text = stringResource(description),
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeAppTheme {
        LemonApp(Modifier.fillMaxSize())
    }
}