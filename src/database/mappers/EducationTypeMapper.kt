package com.studentis.database.mappers

import com.github.jasync.sql.db.RowData
import com.studentis.models.EducationType
import javax.inject.Inject

class EducationTypeMapper
    @Inject
    constructor(): RowDataMapper<EducationType> {

    override fun mapRowData(row: RowData): EducationType {
        return EducationType(
                educationTypeId = row.getInt("education_type_id")!!,
                educationTypeName = row.getString("education_type_name")!!
        )
    }

}