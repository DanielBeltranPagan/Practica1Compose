package com.example.practica1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practica1.ui.theme.Practica1

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica1 {
                val context = LocalContext.current
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        AppTopBar(
                            onBack = { finish() },
                            onNavigateHome = { finish() },
                            onNavigateList = {},
                            onNavigateMusic = {
                                val intent = Intent(context, ThirdActivity::class.java)
                                context.startActivity(intent)
                            }
                        )
                    },
                    bottomBar = {
                        AppBottomBar(
                            onHome = { finish() },
                            onFav = {},
                            onProfile = {},
                            onMusic = {
                                val intent = Intent(context, ThirdActivity::class.java)
                                context.startActivity(intent)
                            }
                        )
                    }
                ) { innerPadding ->
                    SecondStrings(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

val cadenas2 = listOf(
    "Manzana", "Banana", "Cereza", "Durazno", "Fresa",
    "Naranja", "Kiwi", "Mango", "Pera", "Sandía",
    "Uva", "Piña", "Frambuesa", "Melón", "Mandarina",
    "Arándano", "Coco", "Papaya", "Lima", "Granada"
)

@Composable
fun SecondStrings(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Blue)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(cadenas2) { cadena ->
            Text(
                text = cadena,
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
        item {
            Image(
                painter = painterResource(id = R.drawable.pixnio),
                contentDescription = "Pixnio",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
