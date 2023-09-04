package com.example.laboratorio6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                LogIn()
        }
    }
}


enum class Screen {
    LogIn,
    MyGallery
}


//Funcion para hacer login en la pantalla
@Composable
fun LogIn(){
    var username by remember {mutableStateOf("")}
    var password by remember {mutableStateOf("")}
    var currentScreen by remember { mutableStateOf(Screen.LogIn) }
    Column(
        modifer = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "LogIn",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))

        //Seccion para el area del Username
        BasicTextField(
            value = username, onValueChange = {username = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(55.dp),
            singleLine = true,
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
        )

        //Seccion para el area del Password
        BasicTextField(
            value = password, onValueChange = {password = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(55.dp),
            singleLine = true,
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        //espacio para el boton
        Button(
            onClick = {
                if(successfullLogIn(username, password)){
                    currentScreen = Screen.MyGallery
                }
            },
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green)

        ){
            Text(
                text = "LogIn",
                color = Color.Green
            )
        }
        if(currentScreen == Screen.MyGallery){
            MyGallery()
        }

    }
}

//para verificar que el usuario y contraseña sean correctos
fun successfullLogIn(username: String, password: String): Boolean {
    return username == "mon21699" && password == "123abc"
}


fun LogOut(): Boolean {
    return true
}

@Composable
fun MyGallery(onNavigate: (Int) -> Unit, currentPage: Int){
    val artworkTitle = listOf(
        "La noche estrellada",
        "La Mona Lisa"
    )

    val imageDescription = listOf(
        "Vincent Van Gogh",
        "Leonardo Da Vinci"
    )
    val imageResources = listOf(
        R.drawable.
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
       Image(
           painter = painterResource(id = imageResources[currentPage]),
           modifier = Modifier.fillMaxWidth().fillMaxHeight(0.8f).padding(16.dp),
       )
        Text(
            text = "Foto numero ${currentPage+1}",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Button(
                onClick = {
                    onNavigate(currentPage-1)
                },
                modifier = Modifier.widthIn(1.dp)
            ){
                Text(text = "Previous")
            }

            Button(
                onClick = {
                    onNavigate(currentPage+1)
                },
                modifier = Modifier.widthIn(1.dp)
            ){
                Text(text = "Next")
            }
        }

    }
}



