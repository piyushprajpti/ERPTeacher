package com.piyushprajpti.feature.syllabus.coverage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SyllabusCoverageViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(SyllabusCoverageState())
    val state = _state.onStart {
        loadInitialData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SyllabusCoverageState()
    )

    private fun loadInitialData() {
        _state.update {
            it.copy(
                totalProgress = 0.72f,
                totalUnits = 8,
                pendingUnits = 3,
                delayedUnits = 1,
                units = listOf(
                    UnitCoverage(
                        id = "1",
                        unitNumber = 1,
                        title = "Fundamentals",
                        subjectName = "Data Structures",
                        courseName = "B.Tech CS",
                        topicsCovered = 12,
                        totalTopics = 12,
                        status = UnitStatus.COMPLETED
                    ),
                    UnitCoverage(
                        id = "2",
                        unitNumber = 2,
                        title = "Advanced Logic",
                        subjectName = "Data Structures",
                        courseName = "B.Tech CS",
                        topicsCovered = 8,
                        totalTopics = 10,
                        status = UnitStatus.IN_PROGRESS
                    ),
                    UnitCoverage(
                        id = "3",
                        unitNumber = 3,
                        title = "Data Architecture",
                        subjectName = "Data Structures",
                        courseName = "B.Tech CS",
                        topicsCovered = 0,
                        totalTopics = 15,
                        status = UnitStatus.LAGGING,
                        dueDateText = "Due 2 days ago"
                    ),
                    UnitCoverage(
                        id = "4",
                        unitNumber = 4,
                        title = "Algorithms",
                        subjectName = "Data Structures",
                        courseName = "B.Tech CS",
                        topicsCovered = 0,
                        totalTopics = 12,
                        status = UnitStatus.LAGGING,
                        dueDateText = "Due tomorrow"
                    )
                ),
                timelineData = listOf(
                    TimelinePoint("WEEK 1", 0.1f, 0.12f),
                    TimelinePoint("WEEK 4", 0.3f, 0.28f),
                    TimelinePoint("WEEK 8", 0.6f, 0.55f),
                    TimelinePoint("WEEK 12", 0.9f, 0.72f)
                )
            )
        }
    }

    fun onEvent(event: SyllabusCoverageEvent) {
        when (event) {
            is SyllabusCoverageEvent.OnBatchMarkTopics -> {
                // Handle batch mark
            }
            is SyllabusCoverageEvent.OnExportReport -> {
                // Handle export
            }
            is SyllabusCoverageEvent.OnUnitClick -> {
                // Handle unit click
            }
        }
    }

    private fun setLoading(value: Boolean) {
        _state.update { it.copy(isLoading = value) }
    }
}
