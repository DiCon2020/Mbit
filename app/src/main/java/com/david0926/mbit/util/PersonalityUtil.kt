package com.david0926.mbit.util

import android.graphics.Color
import android.util.Log

enum class PersonalityUtil(private var background: String, private var text: String) {
    ISTJ("#FF7575","#FFFFFF"),
    ISFJ("#FF9C75","#FFFFFF"),
    INFJ("#FFB975","#3D62FF"),
    INTJ("#FFE198","#3D62FF"),
    ISTP("#FFC821","#3D62FF"),
    ISFP("#F5E70A","#3D62FF"),
    INFP("#CCF50A","#3D62FF"),
    INTP("#DBFF15","#3D62FF"),
    ESTP ( "#8ed81a","#ffffff"),
    ESFP ( "#56c34e","#ffffff"),
    ENFP ( "#4ec39e","#ffffff"),
    ENTP ( "#4ea3c3","#ffffff"),
    ESTJ ( "#3D62FF","#ffffff"),
    ESFJ ( "#8455fa","#ffffff"),
    ENFJ ( "#c977e8","#ffffff"),
    ENTJ ( "#f684cc","#ffffff");

    fun getBackgroundColor() = Color.parseColor(background)
    fun getTextColor() = Color.parseColor(text)
}