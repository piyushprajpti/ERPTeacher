package com.piyushprajpti.feature.events.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class EventDetailViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(EventDetailState())
    val state = _state.onStart {
        loadData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = EventDetailState()
    )

    private fun loadData() {
        val detailedEvent = DetailedEvent(
            id = "tech_fest_2026",
            title = "Annual Tech Fest 2026",
            category = "Cultural",
            organizedBy = "Organized by CS Dept",
            date = "March 20, 2026",
            time = "10:00 AM onwards",
            venue = "Main Auditorium, Campus",
            description = "The biggest annual celebration of technology and innovation at AMS. Join us for hackathons, guest lectures, and gaming tournaments. Experience cutting-edge tech showcases and network with industry leaders.",
            registeredCount = "1,200+ Registered Students",
            bannerUrl = "https://images.unsplash.com/photo-1492684223066-81342ee5ff30?q=80&w=2070&auto=format&fit=crop"
        )

        val glimpses = listOf(
            "https://images.unsplash.com/photo-1511578314322-379afb476865?q=80&w=2070&auto=format&fit=crop",
            "https://images.unsplash.com/photo-1505373877841-8d25f7d46678?q=80&w=2012&auto=format&fit=crop",
            "https://images.unsplash.com/photo-1511578314322-379afb476865?q=80&w=2069&auto=format&fit=crop"
        )

        val relatedEvents = listOf(
            RelatedEvent(
                id = "coding_challenge",
                title = "Coding Challenge",
                date = "March 22",
                time = "02:00 PM",
                imageUrl = "https://images.unsplash.com/photo-1515879218367-8466d910aaa4?q=80&w=2069&auto=format&fit=crop"
            ),
            RelatedEvent(
                id = "alumni_talk",
                title = "Alumni Talk",
                date = "March 25",
                time = "11:00 AM",
                imageUrl = "https://images.unsplash.com/photo-1515187029135-18ee286d815b?q=80&w=2070&auto=format&fit=crop"
            ),
            RelatedEvent(
                id = "hackathon_2026",
                title = "24hr Hackathon",
                date = "March 28",
                time = "09:00 AM",
                imageUrl = "https://images.unsplash.com/photo-1504384308090-c894fdcc538d?q=80&w=2070&auto=format&fit=crop"
            )
        )

        _state.update { 
            it.copy(
                event = detailedEvent,
                glimpses = glimpses,
                relatedEvents = relatedEvents
            )
        }
    }

    fun onEvent(event: EventDetailEvent) {
        when (event) {
            is EventDetailEvent.OnBackClick -> {}
            is EventDetailEvent.OnShareClick -> {
                messageHelper.showMessage("Sharing event details...")
            }
            is EventDetailEvent.OnRegisterClick -> {
                messageHelper.showMessage("Registered successfully!")
            }
            is EventDetailEvent.OnViewAllGlimpses -> {
                messageHelper.showMessage("Navigating to all glimpses...")
            }
            is EventDetailEvent.OnRelatedEventClick -> {
                messageHelper.showMessage("Clicked related event: ${event.id}")
            }
        }
    }
}
