package com.example.notekeeper

/**
 * Created by Jim.
 */
class ModuleInfo @JvmOverloads constructor(
    val moduleId: String,
    val title: String,
    isComplete: Boolean = false
) {
    var isComplete = false

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