package com.example.coursesapp

import android.graphics.ColorFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coursesapp.ui.theme.CoursesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CourseGrid(modifier = Modifier.statusBarsPadding())
                }
            }
        }
    }
}

@Composable
fun CourseCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .height(68.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(Color(0xFFE9E4F7))
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Image(
                painter = painterResource(id = topic.imagePath),
                contentDescription = stringResource(id = topic.title),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(68.dp)
            )
            Column(
                modifier = Modifier.fillMaxHeight()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = stringResource(id = topic.title),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_grain),
                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground),
                        contentDescription = null,
                    )
                    Text(
                        text = topic.number.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                            .padding(start = 8.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CourseCardPreview() {
    CoursesAppTheme {
        CourseCard(topic = DataSource.topics[0], modifier = Modifier.padding(8.dp))
    }
}


@Composable
fun CourseGrid(modifier: Modifier = Modifier) {
    val topicLists = DataSource.topics
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
    ) {
        items(topicLists.size) { index ->
            CourseCard(topic = topicLists[index], modifier= Modifier.padding(4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CourseGridPreview() {
    CoursesAppTheme {
        CourseGrid(modifier = Modifier.fillMaxSize())
    }
}