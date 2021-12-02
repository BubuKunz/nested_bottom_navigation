package yan.zubritskiy.s2.screens

import android.view.View
import androidx.navigation.fragment.findNavController
import yan.zubritskiy.s2.R

class AfterBottomViewFragment2Fragment : CommonFragment() {
    override val text: String
        get() = "AfterBottomViewFragment2Fragment"
    override val buttonText: String
        get() = "Go next"
    override val listener: (View) -> Unit
        get() = {
        }

}