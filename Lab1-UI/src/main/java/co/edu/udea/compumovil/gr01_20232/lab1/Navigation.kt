package co.edu.udea.compumovil.gr01_20232.lab1

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Screen1Preview() {
    val navController = rememberNavController()
    Scaffold {
        Screen1(navController = navController)
    }
}

@Composable
@Preview(showBackground = true)
fun Screen1PreviewPreview() {
    Screen1Preview()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen1(navController: NavHostController) {

        val context = LocalContext.current
        var selectedSex by remember { mutableStateOf<Sex?>(null) }

        Column(
            Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
            ) {
                TopAppBar(
                    // Customize Colors here
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            text = "Información de contacto",
                            color = Color.White
                        )
                    },
                )
            }

            // Campo de entrada de texto con un icono
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), // Ajusta el relleno según sea necesario
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Person, // Cambia el icono según tus necesidades
                    contentDescription = "Icono de persona",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp)) // Espaciado entre el icono y el campo de entrada
                var myText by remember { mutableStateOf("") }
                TextField(
                    value = myText,
                    onValueChange = { myText = it },
                    label = { Text(text = "Nombre") })

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), // Ajusta el relleno según sea necesario
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Person, // Cambia el icono según tus necesidades
                    contentDescription = "Icono de persona",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp)) // Espaciado entre el icono y el campo de entrada
                var myText by remember { mutableStateOf("") }
                TextField(
                    value = myText,
                    onValueChange = { myText = it },
                    label = { Text(text = "Apellido") })

            }


            // Radio buttons para el sexo
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                // Fila para "Sexo"
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = "Icono de persona",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Sexo:", modifier = Modifier.padding(end = 16.dp))
                }

                // Fila para "Masculino" y "Femenino"
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Radio button "Masculino" y su texto
                    RadioButton(
                        selected = selectedSex == Sex.MALE,
                        onClick = { selectedSex = Sex.MALE },
                        colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
                    )
                    Text("Masculino", modifier = Modifier.padding(start = 8.dp))

                    Spacer(modifier = Modifier.width(16.dp)) // Espacio entre los radio buttons

                    // Radio button "Femenino" y su texto
                    RadioButton(
                        selected = selectedSex == Sex.FEMALE,
                        onClick = { selectedSex = Sex.FEMALE },
                        colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
                    )
                    Text("Femenino", modifier = Modifier.padding(start = 8.dp))
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                showDatePicker(context)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Book,
                    contentDescription = "Icono de persona",
                    tint = MaterialTheme.colorScheme.primary
                )
                EscolaridadDropdownMenu()
            }
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Siguiente",
                    modifier = Modifier
                        .clickable { navController.navigate("pantalla2") }
                )
            }



        }
}


@Composable
fun Screen2(navController: NavHostController){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Green)
    ){
        Text(text = "Pantalla 2", modifier = Modifier.align(Alignment.Center).clickable{navController.navigate("pantalla3")})
    }
}

@Composable
fun Screen3(navController: NavHostController){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Magenta)
    ){
        Text(text = "Pantalla 3", modifier = Modifier.align(Alignment.Center))
    }
}