package com.cleverexpenses.receipts.feature_receipt.presentation.util

import java.time.ZonedDateTime


class ZonedDateTimeConverter {

    fun toTimestamp(value: ZonedDateTime): Long {
        return value.toInstant().toEpochMilli()
    }

    fun toZonedDateTime(timestamp: Long): ZonedDateTime {
        return ZonedDateTime.ofInstant(
                java.time.Instant.ofEpochMilli(timestamp),
                java.time.ZoneId.systemDefault()
            )
        }
    }
