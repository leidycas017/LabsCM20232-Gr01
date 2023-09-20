package co.edu.udea.compumovil.gr01_20232.lab1

import android.annotation.SuppressLint
import android.content.res.Configuration
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
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
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val context = LocalContext.current
    var selectedSex by remember { mutableStateOf<Sex?>(null) }
    var escolaridadSeleccionada by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
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
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Mostrar los elementos dependiendo de la orientación
        if (isLandscape) {
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    item {
                        LabeledTextField("Nombre", "*") { nombre = it }
                    }
                    item {
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                    item {
                        LabeledTextField("Apellido", "*") {  apellido = it }
                    }
                }
            }
        } else {
            item {
                LabeledTextField("Nombre", "*") { nombre = it}
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                LabeledTextField("Apellido", "*") {apellido = it }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Sexo:")

                Spacer(modifier = Modifier.height(8.dp))

                RadioButtonRow(
                    options = listOf("Masculino", "Femenino"),
                    onOptionSelected = { selectedSex = if (it == "Masculino") Sex.MALE else Sex.FEMALE },
                    selectedOption = if (selectedSex == Sex.MALE) "Masculino" else "Femenino"
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            showDatePicker(context) { selectedDate ->
                fechaNacimiento = selectedDate
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "$fechaNacimiento")
            }
        }


        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Book,
                    contentDescription = "Icono de persona",
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(8.dp))
                EscolaridadDropdownMenu { selectedEscolaridad ->
                    escolaridadSeleccionada = selectedEscolaridad
                }
            }
        }


        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Button(
                onClick = {
                    // Llama a la función para imprimir la información
                    printContactInformation(nombre, apellido, selectedSex, fechaNacimiento, escolaridadSeleccionada)
                    navController.navigate("pantalla2")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Siguiente")
            }
        }
    }
}



fun printContactInformation(nombre: String, apellido: String, sexo: Sex?, fechaNacimiento: String, escolaridad: String) {
    println("Información de contacto:")
    println("Nombre: $nombre")
    println("Apellido: $apellido")
    println("Sexo: ${sexo?.name ?: "No especificado"}")
    println("Fecha de Nacimiento: $fechaNacimiento")
    println("Escolaridad: $escolaridad")
}








@Composable
private fun LabeledTextField(label: String, requiredSymbol: String, onValueChange: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

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

        TextField(
            value = text,
            onValueChange = {
                text = it
                onValueChange(it)
            },
            label = {
                Row {
                    Text(text = label)
                    Text(
                        text = requiredSymbol,
                        color = Color.Red,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words,
                autoCorrect = false,
                imeAction = ImeAction.Done
            )
        )
    }
}

@Composable
private fun RadioButtonRow(options: List<String>, onOptionSelected: (String) -> Unit, selectedOption: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        options.forEach { option ->
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                RadioButton(
                    selected = selectedOption == option,
                    onClick = { onOptionSelected(option) },
                    colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(option)
            }
        }
    }
}



@Composable
fun Screen2(navController: NavHostController){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Green)
    ){
        Text(text = "Pantalla 2", modifier = Modifier
            .align(Alignment.Center)
            .clickable { navController.navigate("pantalla3") })
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