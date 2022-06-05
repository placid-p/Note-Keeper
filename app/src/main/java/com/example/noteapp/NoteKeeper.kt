package com.example.noteapp

class CourseInfo(val courseId:String, var title: String)
{
    override fun toString(): String {
        return title
    }
}

data class NoteInfo(var course:CourseInfo?=null,var title: String?=null, var text:String?=null)
{

}