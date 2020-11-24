package com.studentis.database.mappers

import com.github.jasync.sql.db.RowData
import com.studentis.models.views.GroupView
import javax.inject.Inject

class GroupViewMapper
    @Inject constructor()
    : RowDataMapper<GroupView> {
    override fun mapRowData(row: RowData): GroupView {
        return GroupView(
                groupId = row.getInt("group_id")!!,
                groupName = row.getString("group_name")!!,
                groupSpecialisationCode = row.getString("group_specialisation_code")!!,
                groupSpecialisationName = row.getString("group_specialisation_name")!!,
                corpusName = row.getString("corpus_name")!!,
                dateEnter = row.getAs("date_enter"),
                dateOut = row.getAs("date_out")
        )
    }

}