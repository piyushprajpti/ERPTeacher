package com.piyushprajpti.feature.common.support

sealed interface SupportEvent {
    data class OnNameChange(val name: String) : SupportEvent
    data class OnEmailChange(val email: String) : SupportEvent
    data class OnSubjectChange(val subject: String) : SupportEvent
    data class OnDescriptionChange(val description: String) : SupportEvent
    data object OnSubmitClick : SupportEvent
}
