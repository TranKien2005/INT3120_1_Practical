package com.example.mycityapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.MyCityAppTheme
import com.example.mycityapp.data.CategoryProvider
import com.example.mycityapp.model.Category

@Composable
fun SelectCategoryScreen(
    listCategory: List<Category>,
    onItemClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(listCategory.size) { index ->
            val category = listCategory[index]
            Card(
                modifier = Modifier.padding(all= 8.dp),
                onClick = { onItemClick(category) },
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(id = category.image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(96.dp)
                            .padding(all = 8.dp)
                    )
                    Text(
                        text = stringResource(category.title),
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f),
                        style = MaterialTheme.typography.displayMedium
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SelectCategoryScreenPreview(
    modifier: Modifier = Modifier
) {
    MyCityAppTheme {
        SelectCategoryScreen(
            listCategory = CategoryProvider.getCategoryData(),
            onItemClick = {}
        )

    }
}