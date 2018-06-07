package com.raccoon.flags.api

import android.util.SparseArray
import com.raccoon.flags.R

data class DataModel(val name: String, val topLevelDomain: List<String>? = null, val alpha2Code: String, val alpha3Code: String,
                     val callingCodes: List<String>? = null, val capital: String, val altSpellings: List<String>? = null,
                     val region: String, val subregion: String, val population: Int, val latlng: List<Double>? = null,
                     val demonym: String, val area: Double, val gini: Double, val timezones: List<String>? = null,
                     val borders: List<String>? = null, val nativeName: String, val numericCode: String,
                     val currencies: List<Currency>? = null, val languages: List<Language>? = null,
                     val translations: Translations, val regionalBlocs: List<RegionalBloc>? = null, val cioc: String) {

    fun getFlagUri() = "http://www.geonames.org/flags/x/${alpha2Code.toLowerCase()}.gif"

    fun toMapOfData(): SparseArray<String> {
        val resultMap: SparseArray<String> = SparseArray()
        resultMap.append(R.string.country_native_name, nativeName)
        resultMap.append(R.string.country_population, population.toString())
        resultMap.append(R.string.country_capital, capital)
        resultMap.append(R.string.country_codes, "$alpha2Code ($alpha3Code)")
        resultMap.append(R.string.country_languages, toString(languages))
        resultMap.append(R.string.country_currencies, toString(currencies))
        resultMap.append(R.string.country_translations, translations.toString())
        return resultMap
    }

    private fun toString(list: List<Any>?): String {
        var result = ""
        list?.let {
            for (item in it) {
                result = result.plus(item.toString()).plus("\n")
            }
        }
        val trim = result.trim()
        return if (trim.isEmpty()) "No data" else trim
    }
}

data class Currency(private val code: String, private val name: String, private val symbol: String) {

    override fun toString() = "$symbol - $name"

}

data class Language(val iso6391: String, val iso6392: String, private val name: String, private val nativeName: String) {

    override fun toString() = "$name: $nativeName"

}

data class RegionalBloc(val acronym: String, val name: String, val otherAcronyms: List<String>? = null, val otherNames: List<String>? = null)

data class Translations(private val de: String, private val es: String, private val fr: String, private val ja: String, private val it: String, private val br: String, private val pt: String) {

    override fun toString() = "DE: $de \nES: $es \nFR: $fr \nJA: $ja \nIT: $it \nBR: $br \nPT: $pt"

}

data class DetailedModel(val header: Pair<String, String>, val content: SparseArray<String>)
