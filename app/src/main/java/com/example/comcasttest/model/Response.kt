package com.example.comcasttest.model

data class Response(
        val RelatedTopics: List<Topic>
)

data class Topic(
        val FirstURL: String,
        val Icon: TopicIcon,
        val Result: String,
        val Text: String,
        var characterName: String
)

data class TopicIcon(
        val URL: String
)