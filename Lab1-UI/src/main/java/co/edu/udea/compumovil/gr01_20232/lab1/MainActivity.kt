package co.edu.udea.compumovil.gr01_20232.lab1

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.edu.udea.compumovil.gr01_20232.lab1.ui.theme.Labs20232Gr01Theme
import java.util.Calendar
import java.util.Date
import java.util.Locale
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val locale = Locale("en") // Idioma predeterminado, español
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        setContent {
            Labs20232Gr01Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    val informacionContacto = InformacionContacto(
                        nombre = "", apellido = "", sexo = "", fechaNacimiento ="",
                        grado = "", telefono = "", direccion= "", email="", ciudad = "", pais = "")

                    NavHost(navController = navigationController, startDestination = "pantalla1"){

                        composable("pantalla1"){ Screen1(informacion = informacionContacto, navigationController) }
                        composable("pantalla2"){ Screen2(informacion = informacionContacto, navigationController) }
                        composable("pantalla3"){ Screen3(informacion = informacionContacto, navigationController) }
                    }
                }
            }
        }
    }

}




@Composable
fun showDatePicker(context: Context, onDateSelected: (String) -> Unit){

    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val formattedDate = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val formattedDate = "${dayOfMonth}/${month + 1}/${year}" // Se suma 1 al mes porque en Calendar, enero es 0
            onDateSelected(formattedDate) // Llama a la función de retorno con la fecha seleccionada
        }, year, month, day
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = "Icono de calendario",
            tint = MaterialTheme.colorScheme.primary
        )

        Text(text = stringResource(id = R.string.phone)+" : ${formattedDate.value}")
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
            datePickerDialog.show()
        }) {
            Text(text = stringResource(id = R.string.Pick))
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EscolaridadDropdownMenu(onEscolaridadSelected: (String) -> Unit) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { newValue ->
            isExpanded = newValue
        }
    ) {
        var escolaridad by remember {
            mutableStateOf("")
        }

        TextField(
            value = escolaridad,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            placeholder = {
                Text(text = stringResource(id = R.string.Pick))
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            }
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(id = R.string.PrimSchool))
                },
                onClick = {
                    escolaridad = "Primaria"
                    onEscolaridadSelected(escolaridad)
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(id = R.string.HighSchool))
                },
                onClick = {
                    escolaridad = "Secundaria"
                    onEscolaridadSelected(escolaridad)
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(id = R.string.College))
                },
                onClick = {
                    escolaridad = "Universidad"
                    onEscolaridadSelected(escolaridad)
                    isExpanded = false
                }
            )
        }
    }
}

enum class Sex {
    MALE, FEMALE
}