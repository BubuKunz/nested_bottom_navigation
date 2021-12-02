package yan.zubritskiy.s2.screens

import android.view.View
import androidx.navigation.fragment.findNavController
import yan.zubritskiy.s2.R

class AfterBottomViewFragment1Fragment : CommonFragment() {
    override val text: String
        get() = "AfterBottomViewFragment1Fragment"
    override val buttonText: String
        get() = "Go next"
    override val listener: (View) -> Unit
        get() = {
            findNavController().navigate(R.id.afterBottomViewFragment2Fragment)
        }

}