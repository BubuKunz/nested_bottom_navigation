package yan.zubritskiy.s2.screens

import android.view.View
import androidx.navigation.fragment.findNavController
import yan.zubritskiy.s2.R

class Tab1StartFragment : CommonFragment() {
    override val text: String
        get() = "Tab1StartFragment"
    override val buttonText: String
        get() = "Go To second"
    override val listener: (View) -> Unit
        get() = {
            findNavController().navigate(R.id.tab1SecondFragment)
        }

}