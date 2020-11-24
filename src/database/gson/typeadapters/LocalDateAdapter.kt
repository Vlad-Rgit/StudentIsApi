package com.studentis.database.gson.typeadapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import org.joda.time.LocalDate


class LocalDateAdapter: TypeAdapter<LocalDate>() {

    override fun write(writer: JsonWriter, value: LocalDate) {
        writer.beginObject()
        writer.name("date")
        writer.jsonValue(value.toString("yyyy-MM-dd"))
        writer.endObject()
    }

    override fun read(reader: JsonReader): LocalDate {
        reader.beginObject()
        val time = LocalDate.parse(reader.nextString())
        reader.endObject()
        return time
    }
}