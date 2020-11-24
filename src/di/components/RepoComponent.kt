package com.studentis.di.components

import com.studentis.database.repo.*
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface RepoComponent {
    fun getUserRepo(): UserRepo
    fun getTeacherRepo(): TeacherRepo
    fun getTeacherViewRepo(): TeacherViewRepo
    fun getScheduleViewRepo(): ScheduleViewRepo
    fun getGroupViewRepo(): GroupViewRepo
    fun getEducationTypeRepo(): EducationTypeRepo
    fun getStudentRepo(): StudentRepo
    fun getStudentStatusOnLessonRepo(): StudentStatusOnLessonRepo
}