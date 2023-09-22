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
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.BedroomBaby
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Female
import androidx.compose.material.icons.rounded.LocationCity
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Male
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Screen1Preview() {
    val navController = rememberNavController()
    Scaffold {
        //Screen1(navController = navController)
    }
}

@Composable
@Preview(showBackground = true)
fun Screen1PreviewPreview() {
    //Screen1Preview()
}

data class InformacionContacto(
    var nombre: String?, var apellido: String?,
    var sexo: String?, var fechaNacimiento: String?,
    var grado: String?, var telefono: String?,
    var direccion: String?, var email: String?,
    var ciudad:String?, var pais:String?)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen1(informacion: InformacionContacto, navController: NavHostController) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val context = LocalContext.current
    var selectedSex by rememberSaveable { mutableStateOf<Sex?>(null) }
    var escolaridadSeleccionada by rememberSaveable { mutableStateOf("") }
    var fechaNacimiento by rememberSaveable { mutableStateOf("") }
    var nombre by rememberSaveable { mutableStateOf("") }
    var apellido by rememberSaveable{ mutableStateOf("") }


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
                        text = stringResource(id = R.string.contactinfo),
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
                        LabeledTextField(stringResource(id = R.string.name), "*") { informacion.nombre = it }
                    }
                    item {
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                    item {
                        LabeledTextField(stringResource(id = R.string.lastname), "*") {  informacion.apellido = it }
                    }
                }
            }
        } else {
            item {
                LabeledTextField(stringResource(id = R.string.name), "*") { informacion.nombre = it}
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                LabeledTextField(stringResource(id = R.string.lastname), "*") {informacion.apellido = it }
            }
        }

        nombre = informacion.nombre.toString()
        apellido = informacion.apellido.toString()

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.gender)+" :")

                Spacer(modifier = Modifier.height(8.dp))

                RadioButtonRow(
                    options = listOf(stringResource(id = R.string.male), stringResource(id = R.string.female)),
                    onOptionSelected = { selectedSex = if (it == "Masculino"||it == "Male") Sex.MALE else Sex.FEMALE },
                    selectedOption = if (selectedSex == Sex.MALE) stringResource(id = R.string.male) else stringResource(id = R.string.female)
                )
                informacion.sexo = selectedSex.toString()
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            showDatePicker(context) { selectedDate ->
                fechaNacimiento = selectedDate
            }
            informacion.fechaNacimiento = fechaNacimiento
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
                informacion.grado = escolaridadSeleccionada
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
                Text(text = stringResource(id = R.string.next))
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
    var text by rememberSaveable { mutableStateOf("") }

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
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            imageVector = Icons.Rounded.Female,
            contentDescription = "Icono de persona",
            tint = MaterialTheme.colorScheme.primary
        )
        Icon(
            imageVector = Icons.Rounded.Male,
            contentDescription = "Icono de persona",
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.width(8.dp))
        options.forEach { option ->
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
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



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen2(informacion: InformacionContacto, navController: NavHostController) {

    // Estado para el texto del campo de países y de ciudad
    var countryText by rememberSaveable { mutableStateOf("") }
    var cityText by rememberSaveable { mutableStateOf("") }

    // Estado para almacenar las sugerencias de países y ciudades
    var suggestedCountries by rememberSaveable { mutableStateOf(emptyList<String>()) }
    var suggestedCities by rememberSaveable { mutableStateOf(emptyList<String>()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.contactinfo),
                        color = Color.White
                    )
                },
                //Boton de Topbar para regresar a pantalla 1
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate("pantalla1")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Flecha hacia atras",
                            tint = MaterialTheme.colorScheme.inversePrimary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            )
            //Contenido en el Activity ContactDataActivity
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    //Campo de texto Telefono
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Phone,
                            contentDescription = "Icono de telefono",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        ContactField(label = stringResource(id = R.string.phone), keyboardType = KeyboardType.Phone)
                    }
                    //Campo de texto Direccion
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.LocationOn,
                            contentDescription = "Icono de ubicacion",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        ContactField(label = stringResource(id = R.string.address), keyboardType = KeyboardType.Text, showSuggestions = false)
                    }
                    //Campo de texto Email
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Email,
                            contentDescription = "Icono de Email",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        ContactField(label = stringResource(id = R.string.email), keyboardType = KeyboardType.Email)
                    }
                    //Campo de texto Paises
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Map,
                            contentDescription = "Icono de paises",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        //Funcion de autocompletado con lista Paises
                        CAutocomplete(
                            cname = stringResource(id = R.string.country),
                            cText = countryText,
                            onCTextChanged = { newText ->
                                countryText = newText

                                // Filtra las sugerencias basadas en la entrada de texto
                                suggestedCountries = Paises.filter { country ->
                                    country.contains(newText, ignoreCase = true)
                                }
                            },
                            suggestedC = suggestedCountries
                        )
                    }
                    //Campo de texto Ciudades
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.LocationCity,
                            contentDescription = "Icono de ciudad",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.align(Alignment.CenterVertically)

                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        //Funcion de autocompletado con lista Ciudades
                        CAutocomplete(
                            cname = stringResource(id = R.string.city),
                            cText = cityText,
                            onCTextChanged = { newTxt ->
                                cityText = newTxt

                                // Filtra las sugerencias basadas en la entrada de texto
                                suggestedCities = Ciudades.filter { city ->
                                    city.contains(newTxt, ignoreCase = true)
                                }
                            },
                            suggestedC = suggestedCities
                        )
                    }
                    //Boton Finalizar
                    Button(
                        onClick = {
                            // aqui se recuperaran los datos para imprimir en el logcat.

                            // Asigna los valores de los campos al mapa de datos
                            navController.navigate("pantalla3")

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(text = stringResource(id = R.string.submit), color = Color.White)
                    }
                }

            }
        }
    }


    informacion.ciudad = cityText
    informacion.pais = countryText
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen3(informacion: InformacionContacto, navController: NavHostController){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.Information),
                        color = Color.White
                    )
                },
                //Boton de Topbar para regresar a pantalla 1
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate("pantalla1")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Flecha hacia atras",
                            tint = MaterialTheme.colorScheme.inversePrimary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "${stringResource(id = R.string.name)}: ${informacion.nombre}")
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    Text(text = "${stringResource(id = R.string.lastname)}: ${informacion.apellido}")
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    Text(text = "Sexo: ${informacion.sexo}")
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    Text(text = "${stringResource(id = R.string.gender)}: ${informacion.fechaNacimiento}")
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    Text(text = "${stringResource(id = R.string.EduLevel)}: ${informacion.grado}")
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    Text(text = "${stringResource(id = R.string.country)}: ${informacion.pais}")
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    Text(text = "${stringResource(id = R.string.city)}: ${informacion.ciudad}")
                }

            }
        }
    }

}