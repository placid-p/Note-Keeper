package com.example.noteapp

object DataManager {
    val courses=HashMap<String,CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initilizeCourses()
        initilizeNotes()
    }
    private fun initilizeCourses(){
        var course=CourseInfo("android","Android")
        courses.set(course.courseId,course)

        course = CourseInfo(courseId = "java",title = "JAVA")
        courses.set(course.courseId,course)

    }
    private fun initilizeNotes(){
        var course = courses["android"]!!
        var note = NoteInfo(course, "Dynamic intent resolution",
            "Wow, intents allow components to be resolved at runtime")
        notes.add(note)
        note = NoteInfo(course, "Delegating intents",
            "PendingIntents are powerful; they delegate much more than just a component invocation")
        notes.add(note)

        course = courses["java"]!!
        note = NoteInfo(course, "Service default threads",
            "Did you know that by default an Android Service will tie up the UI thread?")
        notes.add(note)
        note = NoteInfo(course, "Long running operations",
            "Foreground Services can be tied to a notification icon")
        notes.add(note)


    }
}