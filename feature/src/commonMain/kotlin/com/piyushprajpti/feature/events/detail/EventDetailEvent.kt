package com.piyushprajpti.feature.events.detail

sealed interface EventDetailEvent {
    data object OnBackClick : EventDetailEvent
    data object OnShareClick : EventDetailEvent
    data object OnRegisterClick : EventDetailEvent
    data object OnViewAllGlimpses : EventDetailEvent
    data class OnRelatedEventClick(val id: String) : EventDetailEvent
}
