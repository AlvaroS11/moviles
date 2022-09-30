package com.example.trivia

data class Question (
    val id:Int,
    val question: String,
    val firstImage: Int,
    val secondImage: Int,
    val answer: String
)