package com.dimonkiv.taskmanagment.ui.theme.calendar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dimonkiv.taskmanagment.model.CalendarItem
import com.dimonkiv.taskmanagment.model.EventItem
import com.dimonkiv.taskmanagment.model.Schedule
import com.dimonkiv.taskmanagment.ui.theme.BluePurple
import com.dimonkiv.taskmanagment.ui.theme.GreenishYellow
import com.dimonkiv.taskmanagment.ui.theme.Orange
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

class CalendarViewModel: ViewModel() {

    var selectedItem by mutableStateOf(CalendarItem())
        private set

    fun updateSelectedItem(item: CalendarItem) {
        selectedItem = item
    }

    fun getCalendarItems(): List<CalendarItem> {
        val items = mutableListOf<CalendarItem>()

        val currentDate = LocalDate.now()
        // Get the current year and month
        val currentYear = currentDate.year
        val currentMonth = currentDate.monthValue
        // Get the number of days in the current month
        val yearMonth = YearMonth.of(currentYear, currentMonth)
        val daysInMonth = yearMonth.lengthOfMonth()

        for(dayInMonth in 1..daysInMonth) {
            items.add(
                CalendarItem(
                    dayOfMonth = dayInMonth.toString(),
                    dayOfWeek = getDayOfWeek(currentYear, currentMonth, dayInMonth),
                    month = currentDate.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                )
            )
        }

        updateSelectedItem(items[0])
        return items
    }

    private fun getDayOfWeek(year: Int, month: Int, day: Int): String {
        val localDate = LocalDate.of(year, month, day)
        val dayOfWeek = localDate.dayOfWeek

        return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
    }

    fun getSchedules(): List<Schedule> {
        val items = mutableListOf<Schedule>()

        items.add(Schedule("12 AM"))

        for (hour in 1..11) {
            val item = Schedule("$hour AM")
            items.add(item)
        }

        items.add(Schedule("12 PM"))
        for (hour in 1..11) {
            val item = Schedule("$hour PM")
            items.add(item)
        }

        items[5].event = getOngoingItem("6", "7")
        items[12].event = getOngoingItem("11", "12")
        items[13].event = getRunningItem("1", "2")
        items[15].event = getUrgentItem("3", "4")

        return items
    }

    private fun getOngoingItem(start: String, end: String): EventItem {
        return EventItem(
            colorRes = BluePurple,
            type = "Ongoing",
            title = "Product Planning",
            schedule = "$start-$end"
        )
    }

    private fun getRunningItem(start: String, end: String): EventItem {
        return EventItem(
            colorRes = GreenishYellow,
            type = "Running",
            title = "Design Meeting",
            schedule = "$start-$end"
        )
    }

    private fun getUrgentItem(start: String, end: String): EventItem {
        return EventItem(
            colorRes = Orange,
            type = "Urgent",
            title = "Landing Page Project",
            schedule = "$start-$end"
        )
    }
}