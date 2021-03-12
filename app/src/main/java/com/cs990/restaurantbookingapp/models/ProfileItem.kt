package com.cs990.restaurantbookingapp.models

class ProfileItem(text: String, icon: Int) {

    private val text: String = text
    private val icon: Int = icon

    fun getIcon(): Int{
        return this.icon
    }

    fun getText():String {
        return this.text
    }

}