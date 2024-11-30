package com.example.contactlistexample.data

import android.provider.ContactsContract.CommonDataKinds.Phone

data class Contact(
    val nombre: String,
    val fono: String,
    val estado: Boolean
) {
    var name = nombre
    var phone = fono
    var isAvailable: Boolean = estado

}

