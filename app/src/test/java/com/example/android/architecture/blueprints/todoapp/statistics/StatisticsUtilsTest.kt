package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest{

    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        val tasks = listOf(
            Task("title", "desc", isCompleted = false)
        )
        // Ketika list di dalam task dihitung menggunakan task aktif
        val result = getActiveAndCompletedStats(tasks)

        // Maka persetasenya adalah 100 dan 0
        assertThat(result.activeTasksPercent, `is`(100f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveAndCompletedStats_noActive_returnsZeroHundred() {
        val tasks = listOf(
            Task("title", "desc", isCompleted = true)
        )
        // Ketika list di dalam task dihitung menggunakan task yang sudah berjalan
        val result = getActiveAndCompletedStats(tasks)

        // Maka persetasenya adalah 0 dan 100
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(100f))
    }

    @Test
    fun getActiveAndCompletedStats_both_returnsFortySixty() {
        // Diberikan 3 task yang sudah berjalan dan 2 task aktif
        val tasks = listOf(
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false)
        )
        // Ketika list dari task tersebut dihitung
        val result = getActiveAndCompletedStats(tasks)

        // Maka hasilnya adalah 40-60
        assertThat(result.activeTasksPercent, `is`(40f))
        assertThat(result.completedTasksPercent, `is`(60f))
    }

    @Test
    fun getActiveAndCompletedStats_error_returnsZeros() {
        // Ketika terdapat error dalam memuat stats
        val result = getActiveAndCompletedStats(null)

        // Task yang sudah berjalan dan aktif adalah 0
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveAndCompletedStats_empty_returnsZeros() {
        // Ketika tidak terdapat task
        val result = getActiveAndCompletedStats(emptyList())

        // Task yang sudah berjalan dan aktif adalah 0
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }
}