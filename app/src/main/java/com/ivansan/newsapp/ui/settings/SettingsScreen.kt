package com.ivansan.newsapp.ui.settings

import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



//comp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    preferences: SharedPreferences
) {
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current
    var usersApiKey by remember {
        mutableStateOf(preferences.getString(PREFERENCES_API_KEY,""))
    }


    val languages = mapOf(
        Pair(null,"All"),Pair("en","English"),Pair("he","Hebrew"),
        Pair("ar","Arabic"),Pair("ru","Russian")
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember {
        mutableStateOf(
            languages[
                preferences.getString(PREFERENCES_LANGUAGE,null)
            ]
        )
    }

    val packageManager = context.packageManager
    val packageName = context.packageName
    val packageInfo = packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp,Alignment.CenterVertically)

    ) {
        Row{
            Text(
            text = "Add API key from ",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
            )
            Text(
                text = "newsdata.io",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Blue,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    uriHandler.openUri("https://newsdata.io/")
                }
            )

        }

        TextField(
            value = usersApiKey!!,
            singleLine = true,
            label = {
                    Text(text = "API Key")
            },
            onValueChange = {newText ->
                usersApiKey = newText
                preferences.edit()
                    .putString(PREFERENCES_API_KEY,usersApiKey)
                    .apply()
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    preferences.edit()
                        .putString(PREFERENCES_API_KEY,usersApiKey)
                        .apply()
                    Toast.makeText(context, "Api key is saved", Toast.LENGTH_SHORT).show()

                }
            )
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = "Chose news language",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
            )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {

                TextField(
                    value = selectedText!!,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor()
                )


            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                languages.forEach { (_, item) ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            preferences.edit()
                                .putString(PREFERENCES_LANGUAGE,languages.entries.find { it.value == selectedText }?.key)
                                .apply()
                        }
                    )
                }
            }
        }
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "Author: Ivan Sannikov",
                fontSize = 10.sp
            )
            Text(
                text = "Version Name: " + packageInfo.versionName,
                fontSize = 10.sp
            )
        }
    }
}
