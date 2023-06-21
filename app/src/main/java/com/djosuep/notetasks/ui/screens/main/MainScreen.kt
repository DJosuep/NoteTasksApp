package com.djosuep.notetasks.ui.screens.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.djosuep.notetasks.R
import com.djosuep.notetasks.ui.theme.NoteTasksTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun MainScreen() {
    Backdrop(
    )
}

@ExperimentalMaterialApi
@Composable
fun Backdrop(
    onBackdropReveal: (Boolean) -> Unit = {}
){
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)

    var selectedNavigationIndex by rememberSaveable(key = "navigation") { mutableStateOf(0) }
    var backdropRevealed by rememberSaveable(key = "backdrop") { mutableStateOf(scaffoldState.isRevealed) }

    LaunchedEffect(scaffoldState) {
        scaffoldState.reveal()
    }

    BackdropScaffold(
        appBar = {
            NoteTasksTopAppBar(
                scope = scope,
                scaffoldState = scaffoldState,
                selectedNavigationIndex = selectedNavigationIndex
            )
        },
        backLayerContent = {

        },
        frontLayerContent = {

        },
        modifier = Modifier,
        scaffoldState = scaffoldState,
        gesturesEnabled = true,
        backLayerBackgroundColor = MaterialTheme.colorScheme.primary,
        backLayerContentColor = MaterialTheme.colorScheme.onPrimary,
        frontLayerShape = RoundedCornerShape(18.dp),
        frontLayerBackgroundColor = MaterialTheme.colorScheme.background,
        frontLayerContentColor = MaterialTheme.colorScheme.onBackground
    ) {

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteTasksTopAppBar(
    scope: CoroutineScope,
    selectedNavigationIndex: Int,
    scaffoldState: BackdropScaffoldState
) {
    val title = listOf(
        R.string.notes,
        R.string.tasks,
        R.string.reminders,
        R.string.donts
    )

    TopAppBar(
        title = {
            Text(text = stringResource(title[selectedNavigationIndex]))
        },
        modifier = Modifier,
        navigationIcon = {
            if (scaffoldState.isConcealed) {
                IconButton(onClick = {
                    scope.launch { scaffoldState.reveal() }
                }) {
                    Icon(painter = painterResource(id = R.drawable.ic_menu), contentDescription = "Menu icon")
                }
            } else {
                IconButton(onClick = {
                    scope.launch { scaffoldState.conceal() }
                }) {
                    Icon(painter = painterResource(id = R.drawable.ic_arrow_left), contentDescription = "Arrow left icon")
                }
            }
        },
        actions = {
             /* TODO: Search icon button */
        },
        backgroundColor = Color.Transparent,
        elevation = 1.dp
    )
}

@ExperimentalMaterialApi
@Composable
@Preview
fun PreviewMainScreen(){
    NoteTasksTheme{
        MainScreen()
    }
}