package com.studentis.di.components

import com.studentis.database.repo.ScheduleViewRepo
import com.studentis.database.repo.TeacherRepo
import com.studentis.database.repo.TeacherViewRepo
import com.studentis.database.repo.UserRepo
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface RepoComponent {
    fun getUserRepo(): UserRepo
    fun getTeacherRepo(): TeacherRepo
    fun getTeacherViewRepo(): TeacherViewRepo
    fun getScheduleViewRepo(): ScheduleViewRepo
}