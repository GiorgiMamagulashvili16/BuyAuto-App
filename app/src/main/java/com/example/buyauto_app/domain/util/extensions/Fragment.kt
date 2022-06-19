package com.example.buyauto_app.domain.util.extensions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> Fragment.flowObserver(flow: Flow<T>,observer: (T) -> Unit,) {
    viewLifecycleOwner.lifecycleScope.launch {
        flow.collect { observer(it) }
    }
}
fun <T> Fragment.liveDataObserver(liveData: LiveData<T>,observer: (T) -> Unit){
    liveData.observe(viewLifecycleOwner){
        observer.invoke(it)
    }
}
inline fun Fragment.createSnackBar(
    message: String,
    length: Int = Snackbar.LENGTH_SHORT,
    snackBarFunction: Snackbar.() -> Unit
) {
    val snackBar = Snackbar.make(requireView(), message, length)
    snackBar.snackBarFunction()
    snackBar.show()
}

fun Snackbar.snackAction(textColor: Int? = null, action: String, listener: (View) -> Unit) {
    setAction(action, listener)
    textColor?.let { setTextColor(textColor) }
}