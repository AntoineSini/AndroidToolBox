package fr.isen.antoine.androidtoolbox

import org.junit.Test
import org.junit.Assert.*
import java.text.SimpleDateFormat


class AgeTest {
    fun setup(): FormActivity {
        val sut = FormActivity()
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        sut.currentDate = formatter.parse("27/01/2020")
        return sut
    }

    @Test
    fun getAgeYearTest() {
        //Given
        val sut = setup()
        //Then
        assertEquals(20,sut.getAge(1,0,2000))
    }

    @Test
    fun getAgeMonthTest(){
        //Given
        val sut = setup()
        //Then
        assertEquals(19, sut.getAge(1,1,2000))
    }

    @Test
    fun getAgeDayTest(){
        //Given
        val sut = setup()

        assertEquals(30,sut.getAge(1,0,1990))
    }
}
