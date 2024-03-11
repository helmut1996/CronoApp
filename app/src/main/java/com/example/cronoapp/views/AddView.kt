package com.example.cronoapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cronoapp.R
import com.example.cronoapp.components.CircleButton
import com.example.cronoapp.components.MainIconButton
import com.example.cronoapp.components.MainTextField
import com.example.cronoapp.components.MainTitle
import com.example.cronoapp.components.formatTiempo
import com.example.cronoapp.model.Cronos
import com.example.cronoapp.viewmodels.CronometroViewModel
import com.example.cronoapp.viewmodels.CronosViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(navController: NavController, cronometroVM:CronometroViewModel, cronosVM:CronosViewModel){

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title = "ADD CRONO") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )

        }
    ) {
        ContentAddView(it, navController,cronometroVM,cronosVM)
    }

}


@Composable
fun ContentAddView(it: PaddingValues, navController: NavController, cronometroVM:CronometroViewModel,cronosVM:CronosViewModel){
   val state = cronometroVM.state

    LaunchedEffect(state.cronometroActivo) {
        cronometroVM.crono()
    }

    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = formatTiempo(tiempo = cronometroVM.tiempo),
              fontSize = 50.sp,
              fontWeight = FontWeight.Bold
            )

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp)

        ) {
            CircleButton(
                icon = painterResource(id = R.drawable.play),
                enabled = !state.cronometroActivo
            ) {
                cronometroVM.iniciar()

            }


            CircleButton(
                icon = painterResource(id = R.drawable.pause),
                enabled = state.cronometroActivo
            ) {
                cronometroVM.pausar()

            }


            CircleButton(
                icon = painterResource(id = R.drawable.stop),
                enabled = !state.cronometroActivo
            ) {
                cronometroVM.detener()

            }

            //mostar guardar
            CircleButton(
                icon = painterResource(id = R.drawable.save),
                enabled = state.showSaveButton
            ) {
                cronometroVM.showTextField()

            }
        }
        if (state.showTextField){
            MainTextField(value = state.title,
                onValueChange = {cronometroVM.onValue(it)},
                label ="Title"
            )

            Button(onClick = {
                cronosVM.addCrono(
                    Cronos(
                        title = state.title,
                        crono = cronometroVM.tiempo
                    )
                )
                cronometroVM.detener()
                navController.popBackStack()

            }) {
                Text(text = "Guardar")
            }
        }
    }
}