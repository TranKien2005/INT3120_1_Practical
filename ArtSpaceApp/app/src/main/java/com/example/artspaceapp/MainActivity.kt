package com.example.artspaceapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtBox(modifier: Modifier = Modifier, imageArtPath: Int = 0) {

    val image = painterResource(id = imageArtPath)
    Box(
        modifier = modifier
            .padding(32.dp)
            .shadow(
                elevation = 16.dp,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                clip = true
            )
            .background(Color.White, shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
                .fillMaxSize()
                .background(color = Color.Black),
            contentScale = ContentScale.Fit
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TitleBox(modifier: Modifier = Modifier, title:String = "Android Logo", artist:String = "Google", year:String = "2007") {
    Column(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(color = Color.Gray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(all = 8.dp).fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "$artist, ($year)",
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp).fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonBox(
    modifier: Modifier = Modifier,
    onPrevious: () -> Unit = {},
    onNext: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Button(
            onClick = onNext,
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp),
            colors = buttonColors(containerColor = Color.Blue, contentColor = Color.White)
        ) {
            Text(text = "Previous")
        }
        Button(
            onClick = onPrevious,
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp),
            colors = buttonColors(containerColor = Color.Blue, contentColor = Color.White)
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    val imageArtPath = remember { mutableIntStateOf(0) }

    val title = when (imageArtPath.intValue) {
        0 -> "Android Logo"
        1 -> "abcd"
        2 -> "efgh"
        else -> "Android Logo"
    }
    val artist = when (imageArtPath.intValue) {
        0 -> "Google"
        1 -> "ijkl"
        2 -> "mnop"
        else -> "Google"
    }

    val year = when (imageArtPath.intValue) {
        0 -> "2007"
        1 -> "2021"
        2 -> "2022"
        else -> "2007"
    }

    val artImage = when (imageArtPath.intValue) {
        0 -> R.drawable.art0
        1 -> R.drawable.art1
        2 -> R.drawable.art2
        else -> R.drawable.art0
    }


    val onNext: () -> Unit = {
        imageArtPath.intValue = (imageArtPath.intValue + 1) % 3
    }
    val onPrevious: () -> Unit = {
        imageArtPath.intValue = if (imageArtPath.intValue - 1 < 0) 2 else imageArtPath.intValue - 1
    }
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) {
            ArtBox(modifier = Modifier.padding(top = 60.dp).height(500.dp).width(400.dp), imageArtPath = artImage)
            TitleBox(modifier = Modifier.padding(top = 16.dp), title = title, artist = artist, year = year)
            ButtonBox(
                modifier = Modifier.padding(top = 60.dp),
                onPrevious = onPrevious,
                onNext = onNext
            )
        } else {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ArtBox(modifier = Modifier.height(400.dp).width(320.dp), imageArtPath = artImage)
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TitleBox(modifier = Modifier.padding(bottom = 16.dp), title = title, artist = artist, year = year)
                    ButtonBox(onPrevious = onPrevious, onNext = onNext)
                }
            }
        }
    }
}

