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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource

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
