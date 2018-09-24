package id.dicoding.nian.footballapps.utils

import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by nian on 9/17/18.
 */
open class CoroutineContextProvider {
    open val main: CoroutineContext by lazy { UI }
}