package com.example.datingorrelated

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserSettings (private val context: Context) {

    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("SettingsPreferences")
        val QUESTION_TIME_KEY = stringPreferencesKey("questionTime")
        val THEME_KEY = stringPreferencesKey("preferredTheme")
        val VOLUME_SOUND_EFFECTS_KEY = stringPreferencesKey("soundEffectsVolume")
        val VOLUME_MAIN_THEME_KEY = stringPreferencesKey("mainThemeVolume")
        val SHOW_ANSWER_KEY = stringPreferencesKey("showAnswer")
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

    val getPreferredTheme: Flow<String> = context.dataStore.data
        .map{ preferences ->
            preferences[THEME_KEY] ?: ""
        }

    suspend fun savePreferredTheme(preferredTheme: String){
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = preferredTheme
        }
    }

    val getVolumeSoundEffects: Flow<String> = context.dataStore.data
        .map{ preferences ->
            preferences[VOLUME_SOUND_EFFECTS_KEY] ?: ""
        }

    suspend fun saveVolumeSoundEffects(soundEffectsVolume: String){
        context.dataStore.edit { preferences ->
            preferences[VOLUME_SOUND_EFFECTS_KEY] = soundEffectsVolume
        }
    }

    val getVolumeMainTheme: Flow<String> = context.dataStore.data
        .map{ preferences ->
            preferences[VOLUME_MAIN_THEME_KEY] ?: ""
        }

    suspend fun saveVolumeMainTheme(mainThemeVolume: String){
        context.dataStore.edit { preferences ->
            preferences[VOLUME_MAIN_THEME_KEY] = mainThemeVolume
        }
    }

    val getShowAnswer: Flow<String> = context.dataStore.data
        .map{ preferences ->
            preferences[SHOW_ANSWER_KEY] ?: ""
        }

    suspend fun saveShowAnswer(showAnswer: String){
        context.dataStore.edit { preferences ->
            preferences[SHOW_ANSWER_KEY] = showAnswer
        }
    }
}