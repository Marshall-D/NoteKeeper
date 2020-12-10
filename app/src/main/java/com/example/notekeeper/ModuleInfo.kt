package com.example.notekeeper

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Jim.
 */
@Parcelize
class ModuleInfo @JvmOverloads constructor(
    val moduleId: String,
    val title: String,
    var isComplete: Boolean = false
) : Parcelable {

    override fun toString(): String {
        return title
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as ModuleInfo
        return moduleId == that.moduleId
    }

    override fun hashCode(): Int {
        return moduleId.hashCode()
    }

    init {
        this.isComplete = isComplete
    }
}