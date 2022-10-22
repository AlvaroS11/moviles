package com.example.datingorrelated

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserSettings (private val context: Context) {

    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("QuestionTime")
        val QUESTION_TIME_KEY = stringPreferencesKey("questionTime")
    }

    val getQuestionTime: Flow<String> = context.dataStore.data
        .map{ preferences ->
            preferences[QUESTION_TIME_KEY] ?: ""

        }
    suspend fun saveQuestionTime(questionTime: String){
        context.dataStore.edit { preferences ->
            preferences[QUESTION_TIME_KEY] = questionTime
        }
    }
}