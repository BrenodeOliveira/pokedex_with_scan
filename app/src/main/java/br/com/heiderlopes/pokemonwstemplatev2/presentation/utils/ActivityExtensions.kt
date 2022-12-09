package br.com.heiderlopes.pokemonwstemplatev2.presentation.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast

fun Activity.showToastLong(context: Context, message: String, duration: Int) =
    Toast.makeText(context, message, duration).show()