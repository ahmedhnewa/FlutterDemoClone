import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.*
import java.util.prefs.Preferences

class MainService {
    var counter by mutableStateOf(0)
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val pref: Preferences = Preferences.userNodeForPackage(MainService::class.java)

    init {
        coroutineScope.launch {
            val _counter = getCounter()
            withContext(Dispatchers.Main) {
                counter = _counter
            }
        }
    }

    fun deletePreferences() {
        pref.removeNode()
        counter = 0
    }

    fun increaseCounter() {
        counter++

        coroutineScope.launch {
            pref.putInt("clickTimes", counter)
        }
    }

    @JvmName("getCounter1")
    suspend fun getCounter(): Int {
        return withContext(Dispatchers.IO) {
            pref.getInt("clickTimes", 0)
        }
    }
}