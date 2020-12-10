package com.example.notekeeper

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Jim.
 */
@Parcelize
class CourseInfo(
    val courseId: String,
    val title: String,
    val modules: List<ModuleInfo>
) : Parcelable {

    var modulesCompletionStatus: BooleanArray
        get() {
            val status = BooleanArray(modules.size)
            for (i in modules.indices) status[i] = modules[i].isComplete
            return status
        }
        set(status) {
            for (i in modules.indices) modules[i].isComplete = status[i]
        }

    fun getModule(moduleId: String): ModuleInfo? {
        for (moduleInfo in modules) {
            if (moduleId == moduleInfo.moduleId) return moduleInfo
        }
        return null
    }

    override fun toString(): String {
        return title
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as CourseInfo
        return courseId == that.courseId
    }

    override fun hashCode(): Int {
        return courseId.hashCode()
    }

}