package com.example.gridapp

import android.os.Bundle
import androidx.compose.runtime.saveable.rememberSaveable
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gridapp.ui.theme.GridAppTheme
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.material3.FloatingActionButton
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.ui.platform.LocalConfiguration



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GridAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CardGridApp()
                }
            }
        }
    }
}

@Composable
fun CardGridApp() {

    var cardList by rememberSaveable { mutableStateOf((0..10).toList()) }


    val columns = if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) 3 else 4

    Box(modifier = Modifier.fillMaxSize()) {
     
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = 80.dp
            ),
            modifier = Modifier.fillMaxSize()
        ) {
            items(cardList.size) { index ->
                CardItem(index = index)
            }
        }


        FloatingActionButton(
            onClick = { cardList = cardList + (cardList.size) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text("+")
        }
    }
}


@Composable
fun CardItem(index: Int) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(80.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (index % 2 == 0) Color.Red else Color.Blue
        )
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = (index + 1).toString(), color = Color.White)
        }
    }
}


