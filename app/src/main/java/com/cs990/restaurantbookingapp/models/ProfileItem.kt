package com.cs990.restaurantbookingapp.models

/**
 * A Class designed to represent a single user profile.
 * @author Group 1
 * @version 1.0
 */
class ProfileItem(text: String, icon: Int) {

    private val text: String = text
    private val icon: Int = icon

    /**
     * Returns the number of this ProfileItem's icon
     */
    fun getIcon(): Int{
        return this.icon
    }

    /**
     * Returns the text String for this ProfileItem
     */
    fun getText():String {
        return this.text
    }
}