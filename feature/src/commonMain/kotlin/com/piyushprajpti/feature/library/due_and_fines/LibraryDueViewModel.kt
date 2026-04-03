package com.piyushprajpti.feature.library.due_and_fines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class LibraryDueViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(LibraryDueState())
    val state = _state.onStart {
        loadData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = LibraryDueState()
    )

    private fun loadData() {
        _state.update {
            it.copy(
                overdueBooks = listOf(
                    OverdueBook(
                        title = "Data Structures",
                        dueDate = "Oct 12, 2023",
                        overdueDays = 4,
                        finePerDay = 1.50,
                        totalFine = 6.00
                    ),
                    OverdueBook(
                        title = "Discrete Math",
                        dueDate = "Oct 10, 2023",
                        overdueDays = 6,
                        finePerDay = 1.00,
                        totalFine = 6.50
                    ),
                    OverdueBook(
                        title = "Operating Systems",
                        dueDate = "Oct 05, 2023",
                        overdueDays = 11,
                        finePerDay = 2.00,
                        totalFine = 22.00
                    )
                ),
                recentActivities = listOf(
                    RecentActivity(
                        title = "Database Systems",
                        status = "Returned",
                        date = "Oct 14, 2023"
                    ),
                    RecentActivity(
                        title = "Digital Logic",
                        status = "Returned",
                        date = "Oct 08, 2023"
                    ),
                    RecentActivity(
                        title = "Computer Networks",
                        status = "Returned",
                        date = "Sep 30, 2023"
                    )
                ),
                totalFine = 34.50,
                calculationInfo = "Calculation based on 3 items",
                lastUpdated = "Updated 5 mins ago"
            )
        }
    }

    fun onEvent(event: LibraryDueEvent) {
        when (event) {
            LibraryDueEvent.PayFine -> {
                messageHelper.showMessage("Proceeding to pay fine...")
            }
            LibraryDueEvent.DownloadCertificate -> {
                messageHelper.showMessage("Downloading clearance certificate...")
            }
        }
    }
}
