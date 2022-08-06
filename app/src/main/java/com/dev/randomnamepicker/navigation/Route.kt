package com.dev.randomnamepicker.navigation

sealed class Route(val path: String) {
    object Home : Route("/")
    object List : Route("/list")
}