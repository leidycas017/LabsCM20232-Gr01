package co.edu.udea.compumovil.gr01_20232.lab1
import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.LocationCity
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.navigation.NavHostController

val Paises = listOf(
    "Argentina",
    "Bolivia",
    "Brasil",
    "Chile",
    "Colombia",
    "Costa Rica",
    "Cuba",
    "Ecuador",
    "El Salvador",
    "Guatemala",
    "Honduras",
    "México",
    "Nicaragua",
    "Panamá",
    "Paraguay",
    "Perú",
    "Puerto Rico",
    "República Dominicana",
    "Uruguay",
    "Venezuela"
)
val Ciudades = listOf(
    "Barranquilla",
    "Bogotá",
    "Bucaramanga",
    "Cali",
    "Cartagena",
    "Medellín",
    "Santa Marta",
)
class ContactDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //ContactDataScreen(navController: NavHostController)
        }
    }
}

@Composable
fun ContactDataScreen() {
    // Estado para el texto del campo de países y de ciudad
    var countryText by remember { mutableStateOf("") }
    var cityText by remember { mutableStateOf("") }

    // Estado para almacenar las sugerencias de países y ciudades
    var suggestedCountries by remember { mutableStateOf(emptyList<String>()) }
    var suggestedCities by remember { mutableStateOf(emptyList<String>()) }
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
                ContactField(label = "Teléfono", keyboardType = KeyboardType.Phone)
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
                ContactField(label = "Dirección", keyboardType = KeyboardType.Text, showSuggestions = false)
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
                ContactField(label = "Email", keyboardType = KeyboardType.Email)
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
                    cname = "Pais",
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
                    cname = "Ciudad",
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

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Finalizar", color = Color.White)

            }
        }

    }
}

@Composable
//formato campos de texto
fun ContactField(label: String, keyboardType: KeyboardType, showSuggestions: Boolean = true) {
    var text by remember { mutableStateOf("") }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        value = text,
        onValueChange = { newText -> text = newText },
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                // Realiza acciones adicionales al completar la entrada de texto, si es necesario.
            }
        ),

    )
}

//Funcion Autocompletado
@Composable
fun CAutocomplete(
    cname:String,
    cText: String,
    onCTextChanged: (String) -> Unit,
    suggestedC: List<String>
) {
    Column {
        //Estilo similar al de los demas campos de texto
        OutlinedTextField(
            value = cText,
            onValueChange = onCTextChanged,
            label = { Text(cname) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            textStyle = TextStyle.Default.copy(color = LocalContentColor.current),
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background) // Fondo del campo de texto
                .padding(8.dp)
        )

        // Lista de sugerencias
        if (suggestedC.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column {
                    for (c in suggestedC) {
                        Text(
                            text = c,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {

                                    onCTextChanged(c)
                                }
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}
