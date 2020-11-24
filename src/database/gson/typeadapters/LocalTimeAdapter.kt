package com.studentis.database.gson.typeadapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import org.joda.time.LocalTime

class LocalTimeAdapter: TypeAdapter<LocalTime>() {

    override fun write(writer: JsonWriter, value: LocalTime) {
        writer.value(value.toString("HH:mm:ss"))
    }

    override fun read(reader: JsonReader): LocalTime {
        val time = LocalTime.parse(reader.nextString())
        return time
    }

}