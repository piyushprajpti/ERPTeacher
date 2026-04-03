package com.piyushprajpti.feature.events.detail

data class EventDetailState(
    val isLoading: Boolean = false,
    val event: DetailedEvent? = null,
    val relatedEvents: List<RelatedEvent> = emptyList(),
    val glimpses: List<String> = emptyList()
)

data class DetailedEvent(
    val id: String,
    val title: String,
    val category: String,
    val organizedBy: String,
    val date: String,
    val time: String,
    val venue: String,
    val description: String,
    val registeredCount: String,
    val bannerUrl: String
)

data class RelatedEvent(
    val id: String,
    val title: String,
    val date: String,
    val time: String,
    val imageUrl: String
)
