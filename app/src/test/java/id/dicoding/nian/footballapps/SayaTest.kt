package id.dicoding.nian.footballapps

import org.mockito.Mockito.*
import org.mockito.*
import com.google.gson.Gson
import id.dicoding.nian.footballapps.match.MatchPresenter
import id.dicoding.nian.footballapps.match.MatchView
import id.dicoding.nian.footballapps.repository.ServicesTheSportsDb
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

/**
 * Created by nian on 9/23/18.
 */

class SayaTest {

    @Mock
    private
    lateinit var view: MatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ServicesTheSportsDb
    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }
    @Test
    fun testS() {
        val nada:String = "asd"
    }
}