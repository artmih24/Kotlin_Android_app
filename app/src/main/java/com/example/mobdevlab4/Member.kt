package com.example.mobdevlab4

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Member(
    var name: String,
    var team: String? = null,
    var checked: Boolean = false
) : Parcelable