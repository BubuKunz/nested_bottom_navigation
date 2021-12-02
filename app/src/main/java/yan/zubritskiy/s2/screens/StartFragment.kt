package yan.zubritskiy.s2.screens

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import yan.zubritskiy.s2.R

class StartFragment : CommonFragment() {
    override val text: String
        get() = "Start fragment"
    override val buttonText: String
        get() = "Go To main"
    override val listener: (View) -> Unit
        get() = {
            findNavController().navigate(R.id.bottomViewFragment)
        }

}