package com.example.radius.ui.theme

import com.example.radius.R

fun getIconDrawable(iconId: String) =
    when (iconId) {
        "1" -> R.drawable.ic_apartment
        "2" -> R.drawable.ic_condo
        "3" -> R.drawable.ic_boat
        "4" -> R.drawable.ic_land
        "6" -> R.drawable.ic_rooms
        "7" -> R.drawable.ic_no_room
        "10" -> R.drawable.ic_swimming
        "11" -> R.drawable.ic_garden
        "12" -> R.drawable.ic_garage
        else -> -1
    }