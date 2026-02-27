package com.example.practica1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practica1.ui.theme.Practica1
import kotlin.jvm.java
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material.icons.filled.MusicNote

class MainActivity : ComponentActivity() {
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
                            onNavigateHome = {
                                val intent = Intent(context, MainActivity::class.java)
                                context.startActivity(intent)
                            },
                            onNavigateList = {
                                val intent = Intent(context, SecondActivity::class.java)
                                context.startActivity(intent)
                            },
                            onNavigateMusic = {
                                val intent = Intent(context, ThirdActivity::class.java)
                                context.startActivity(intent)
                            }
                        )
                    },
                    bottomBar = {
                        AppBottomBar(
                            onHome = {
                                val intent = Intent(context, MainActivity::class.java)
                                context.startActivity(intent)
                            },
                            onFav = { Toast.makeText(context, "Favoritos", Toast.LENGTH_SHORT).show() },
                            onProfile = { Toast.makeText(context, "Perfil", Toast.LENGTH_SHORT).show() },
                            onMusic = {
                                val intent = Intent(context, ThirdActivity::class.java)
                                context.startActivity(intent)
                            }
                        )
                    }
                ) { innerPadding ->
                    Strings(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


val cadenas = listOf(
    "Manzana", "Banana", "Cereza", "Durazno", "Fresa",
    "Naranja", "Kiwi", "Mango", "Pera", "Sandía",
    "Uva", "Piña", "Frambuesa", "Melón", "Mandarina",
    "Arándano", "Coco", "Papaya", "Lima", "Granada"
)
// LazyColmn es mejor para tener más elementos en una lista, pero Column sería bueno para elementos fijos
@Composable
fun Strings(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    LazyColumn (
        modifier = modifier
            .fillMaxSize()
            .background(Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        items(cadenas) { cadena ->
            Text(
                text = cadena,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        item {
            Image(
                painter = painterResource(id = R.drawable.pixnio),
                contentDescription = "Pixnio",
                modifier = Modifier.padding(16.dp)
            )
        }
        item {
            FilledButton(
                Onclick = {
                    val intent = Intent(context, SecondActivity::class.java)
                    context.startActivity(intent)
            }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    onBack: () -> Unit,
    onNavigateHome: () -> Unit,
    onNavigateList: () -> Unit,
    onNavigateMusic: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    MediumTopAppBar(

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        title = {
            Text(
                "APP fea",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver"
                )
            }
        },
        actions = {
            IconButton(onClick = onNavigateHome) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Inicio"
                )
            }
            IconButton(onClick = onNavigateList) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = "Lista"
                )
            }
            IconButton(onClick = onNavigateMusic) {
                Icon(Icons.Filled.MusicNote, contentDescription = "Música")
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun AppBottomBar(
    onHome: () -> Unit,
    onFav: () -> Unit,
    onProfile: () -> Unit,
    onMusic: () -> Unit
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        IconButton(onClick = onHome) {
            Icon(Icons.Filled.Home, contentDescription = "Inicio")
        }
        IconButton(onClick = onFav) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favoritos")
        }
        IconButton(onClick = onProfile) {
            Icon(Icons.Filled.Person, contentDescription = "Perfil")
        }
        IconButton(onClick = onMusic) {
            Icon(Icons.Filled.MusicNote, contentDescription = "Música")
        }
    }
}

@Composable
fun FilledButton(Onclick: () -> Unit) {
    Button(onClick = Onclick) {
        Text("Siguiente Pantalla")
    }
}

@Preview(showBackground = true)
@Composable
fun StringsPreview() {
    Practica1 {
        Strings()
    }
}