package com.piyushprajpti.feature.hostel.mess

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import com.piyushprajpti.components.message_helper.model.Alert
import com.piyushprajpti.feature.hostel.mess.models.DaySchedule
import com.piyushprajpti.feature.hostel.mess.models.Meal
import com.piyushprajpti.feature.hostel.mess.models.SatisfactionData
import com.piyushprajpti.feature.hostel.mess.models.SpecialEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HostelMessViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(HostelMessState())
    val state = _state.onStart {
        loadData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HostelMessState()
    )

    private fun loadData() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    upcomingSpecial = SpecialEvent(
                        title = "Diwali Feast",
                        date = "Nov 12",
                        description = "Grand Dinner & Sweets",
                        specialMenu = listOf("Kadhai Paneer", "Dal Makhani", "Butter Naan", "Gulab Jamun", "Rasmalai", "Veg Pulao")
                    ),
                    weeklySchedule = listOf(
                        DaySchedule("MON", listOf(Meal("Breakfast", "Aloo Paratha"), Meal("Lunch", "Rajma Chawal"), Meal("Dinner", "Jeera Rice"))),
                        DaySchedule("TUE", listOf(Meal("Breakfast", "Bread Omelette"), Meal("Lunch", "Veg Pulao"), Meal("Dinner", "Soya Chunk"))),
                        DaySchedule("WED", listOf(Meal("Breakfast", "Poha Jalebi"), Meal("Lunch", "Dal Makhani"), Meal("Dinner", "Paneer Butter"))),
                        DaySchedule("THU", listOf(Meal("Breakfast", "Idli Sambhar"), Meal("Lunch", "Kadhai Paneer"), Meal("Dinner", "Egg Curry"))),
                        DaySchedule("FRI", listOf(Meal("Breakfast", "Puri Sabji"), Meal("Lunch", "Chole Bhature"), Meal("Dinner", "Chicken Curry"))),
                        DaySchedule("SAT", listOf(Meal("Breakfast", "Stuffed Paratha"), Meal("Lunch", "Mix Veg"), Meal("Dinner", "Dal Fry"))),
                        DaySchedule("SUN", listOf(Meal("Breakfast", "Club Sandwich"), Meal("Lunch", "Veg Biryani"), Meal("Dinner", "Paneer Tikka")))
                    ),
                    satisfactionTrend = listOf(
                        SatisfactionData("M", 2.5f),
                        SatisfactionData("T", 3.2f),
                        SatisfactionData("W", 2.8f),
                        SatisfactionData("T", 4.5f),
                        SatisfactionData("F", 4.2f),
                        SatisfactionData("S", 1.5f),
                        SatisfactionData("S", 1.8f)
                    )
                )
            }
        }
    }

    fun onEvent(event: HostelMessEvent) {
        when (event) {
            is HostelMessEvent.OnDaySelected -> _state.update { it.copy(selectedDayIndex = event.index) }
            is HostelMessEvent.OnRateMeal -> _state.update { it.copy(todayRating = event.rating) }
            is HostelMessEvent.OnEmojiRateMeal -> _state.update { it.copy(todayEmojiRating = event.index) }
            is HostelMessEvent.OnSkipMealsToggle -> _state.update { it.copy(isSkipMealsEnabled = event.enabled) }
            HostelMessEvent.OnViewSpecialMenuClick -> {
                val special = _state.value.upcomingSpecial
                if (special != null) {
                    messageHelper.setAlert(
                        Alert(
                            label = special.title,
                            message = "Menu: ${special.specialMenu.joinToString(", ")}",
                            cancelable = true
                        )
                    )
                }
            }
        }
    }
}
