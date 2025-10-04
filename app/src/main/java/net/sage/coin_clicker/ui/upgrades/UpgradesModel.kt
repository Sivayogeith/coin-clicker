package net.sage.coin_clicker.ui.upgrades

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UpgradesModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "uwuwuwuu you can buy human making machines here"
    }
    val text: LiveData<String> = _text
}