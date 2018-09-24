package id.dicoding.nian.footballapps.repository

import java.net.URL

/**
 * Created by nian on 9/17/18.
 */
class ServicesTheSportsDb {
    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}