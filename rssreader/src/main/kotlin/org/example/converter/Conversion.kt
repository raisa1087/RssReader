package org.example.converter

// Conversion commands supported: cut and replace
enum class Conversion {
    CUT, REPLACE;

    fun getValue() = this.toString().toLowerCase()
}