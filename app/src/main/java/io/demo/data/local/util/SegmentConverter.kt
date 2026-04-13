package io.demo.data.local.util

import androidx.room.TypeConverter
import io.demo.data.network.model.Segment
import kotlinx.serialization.json.Json

class SegmentConverter {

    @TypeConverter
    fun fromSegments(list: List<Segment>): String {
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun toSegments(json: String): List<Segment> {
        return Json.decodeFromString(json)
    }

}