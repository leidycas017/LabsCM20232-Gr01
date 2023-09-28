package co.edu.udea.compumovil.gr01_20232.lab1
import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable

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
    }
}


@Composable
//formato campos de texto
fun ContactField(label: String, keyboardType: KeyboardType, showSuggestions: Boolean = true) {
    var text by rememberSaveable { mutableStateOf("") }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        value = text,
        onValueChange = { newText -> text = newText },
        label = { Text(text = label
        ) },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType,
            imeAction = ImeAction.Next

        ),

    )

}

//Funcion Autocompletado
@Composable
fun CAutocomplete(
    cname: String,
    cText: String,
    onCTextChanged: (String) -> Unit,
    suggestedC: List<String>
) {
    var showSuggestions by remember { mutableStateOf(true) }

    Column {
        // Estilo similar al de los demás campos de texto
        OutlinedTextField(
            value = cText,
            onValueChange = {
                onCTextChanged(it)
                showSuggestions = true // Mostrar sugerencias cuando se edita el texto
            },
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

        // Lista de sugerencias (se muestra si showSuggestions es true)
        if (showSuggestions && suggestedC.isNotEmpty()) {
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
                                    showSuggestions = false // Ocultar la lista de sugerencias al seleccionar
                                }
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}
