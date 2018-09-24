package id.dicoding.nian.footballapps.match

import com.google.gson.Gson
import id.dicoding.nian.footballapps.TestContextProvider
import id.dicoding.nian.footballapps.model.Event
import id.dicoding.nian.footballapps.model.ResultEvent
import id.dicoding.nian.footballapps.repository.ApiTheSportsDb
import id.dicoding.nian.footballapps.repository.ServicesTheSportsDb
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * Created by nian on 9/22/18.
 */
class MatchPresenterTest {

    @Mock
    private lateinit var view: MatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ServicesTheSportsDb
    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetPastEventList() {
        val event: MutableList<Event> = mutableListOf()
        val response = ResultEvent(event)
        val league = "4328"
        val strEvent = "eventspastleague"

        `when`(gson.fromJson(apiRepository
                .doRequest(ApiTheSportsDb.getMatch(league,strEvent)),

                ResultEvent::class.java))
        .thenReturn(response)

        presenter.getMatch(league,strEvent)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(event)
        Mockito.verify(view).hideLoading()
    }

    @Test
    fun testGetNextEventList() {
        val event: MutableList<Event> = mutableListOf()
        val response = ResultEvent(event)
        val league = "4328"
        val strEvent = "eventsnextleague"

        `when`(gson.fromJson(apiRepository
                .doRequest(ApiTheSportsDb.getMatch(league,strEvent)),

                ResultEvent::class.java))
        .thenReturn(response)

        presenter.getMatch(league,strEvent)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(event)
        Mockito.verify(view).hideLoading()
    }
}