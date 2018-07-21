package io.pjhjohn.calculator.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import io.pjhjohn.calculator.App
import java.util.*

object Storage {

    const val LAST_ACTIVITY_CLASSNAME = "last_activity_classname"

    private fun local(): SharedPreferences {
        val context = App.context()
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    /* SharedPreference Method Mapping */
    operator fun contains(key: String): Boolean = local().contains(key)

    val all: Map<String, *> get() = local().all
    fun getBoolean(key: String, fallback: Boolean): Boolean = local().getBoolean(key, fallback)
    fun getFloat(key: String, fallback: Float): Float = local().getFloat(key, fallback)
    fun getInt(key: String, fallback: Int): Int = local().getInt(key, fallback)
    fun getLong(key: String, fallback: Long): Long = local().getLong(key, fallback)
    fun getString(key: String, fallback: String): String = local().getString(key, fallback)

    /* SharedPreferences.Editor Method Mapping */
    fun remove(key: String) = local().edit().remove(key).apply()
    fun clear() = local().edit().clear().apply()

    @SuppressLint("CommitPrefEdits")
    fun put(pair: Pair<Any?, String>): Any? = with(local().edit()) {
        val value = pair.first
        val key = pair.second
        when (value) {
            is Boolean -> putBoolean(key, value)
            is Float -> putFloat(key, value)
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is String -> putString(key, value)
            null -> remove(key)
            else -> InputMismatchException("Only primitive types can be stored in SharedPreferences")
        }
        apply()
        value
    }

}
