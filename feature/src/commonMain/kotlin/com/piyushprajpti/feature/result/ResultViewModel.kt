package com.piyushprajpti.feature.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ResultViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(ResultState())
    val state = _state.onStart {
        // Data is already populated in the State's default values for dummy purpose
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ResultState()
    )

    fun onEvent(event: ResultEvent) {
        when (event) {
            is ResultEvent.OnSubjectSelected -> {
                _state.update { it.copy(selectedSubject = event.subject) }
            }
            is ResultEvent.OnSemesterSelected -> {
                _state.update { it.copy(selectedSemester = event.semester) }
            }
            is ResultEvent.OnYearSelected -> {
                _state.update { it.copy(selectedYear = event.year) }
            }
            ResultEvent.OnSendInterventionAlert -> {
                messageHelper.showMessage("Intervention alert sent to at-risk students")
            }
            ResultEvent.OnShareReport -> {
                messageHelper.showMessage("Sharing report...")
            }
            ResultEvent.OnExportResult -> {
                messageHelper.showMessage("Exporting result as PDF...")
            }
        }
    }
}
