package com.example.practica1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.practica1.ui.theme.Practica1

class ThirdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica1 {
                Scaffold(
                    topBar = {
                        AppTopBar(
                            onBack = { finish() },
                            onNavigateHome = { finish() },
                            onNavigateList = { val intent = Intent(this, SecondActivity::class.java)
                                startActivity(intent) },
                            onNavigateMusic = {}
                        )
                    },
                    bottomBar = {
                        AppBottomBar(
                            onHome = { finish() },
                            onFav = { },
                            onProfile = { },
                            onMusic = {}
                        )
                    }
                ) { innerPadding ->
                    MyContent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun MyContent(modifier: Modifier = Modifier) {
    val mContext = LocalContext.current
    val mMediaPlayer = MediaPlayer.create(mContext, R.raw.sample)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {

            // IconButton for Start Action
            IconButton(onClick = { mMediaPlayer.start() }) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_media_play),
                    contentDescription = "",
                    Modifier.size(100.dp)
                )
            }

            // IconButton for Pause Action
            IconButton(onClick = { mMediaPlayer.pause() }) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_media_pause),
                    contentDescription = "",
                    Modifier.size(100.dp)
                )
            }
        }
    }
}