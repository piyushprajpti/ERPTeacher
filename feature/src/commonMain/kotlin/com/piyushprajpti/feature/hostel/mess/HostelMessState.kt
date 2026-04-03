package com.piyushprajpti.feature.hostel.mess

import com.piyushprajpti.feature.hostel.mess.models.DaySchedule
import com.piyushprajpti.feature.hostel.mess.models.SatisfactionData
import com.piyushprajpti.feature.hostel.mess.models.SpecialEvent

data class HostelMessState(
    val upcomingSpecial: SpecialEvent? = null,
    val weeklySchedule: List<DaySchedule> = emptyList(),
    val selectedDayIndex: Int = 2, // Wednesday
    val todayRating: Int = 0,
    val todayEmojiRating: Int = 0, // 0: None, 1: Bad, 2: OK, 3: Good
    val satisfactionTrend: List<SatisfactionData> = emptyList(),
    val isSkipMealsEnabled: Boolean = false
)
