package com.example.myapplication.feature.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.gallery.feature.presentation.media.SortOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.sortDataStore: DataStore<Preferences> by preferencesDataStore(name = "sort")

val SORT_ORDER = stringPreferencesKey("sort_order")

fun getSortOrder(context: Context): Flow<SortOrder> {
    return context.sortDataStore.data.map { preferences ->
        val savedValue = preferences[SORT_ORDER] ?: ""
        SortOrder.fromString(savedValue)
    }
}

suspend fun saveSortOrder(context: Context, sortOrder: SortOrder) {
    context.sortDataStore.edit { preferences ->
        preferences[SORT_ORDER] = sortOrder.toString()
    }
}