package tech.shalecode.eyesonfootball.Utility

import android.icu.text.SimpleDateFormat
import org.junit.Test

import org.junit.Assert.*
import tech.shalecode.eyesonfootball.Utility.Date.changeDate
import java.util.Date

class DateTest {

    @Test
    fun testchangeDate() {
        val date : String = "2018-11-19"
        assertEquals("Mon, 19 Nov 2018", changeDate(date))
    }
}