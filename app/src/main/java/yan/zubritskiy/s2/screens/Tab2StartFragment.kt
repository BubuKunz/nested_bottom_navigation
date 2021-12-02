package yan.zubritskiy.s2.screens

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import yan.zubritskiy.s2.R

class Tab2StartFragment : CommonFragment() {
    override val text: String
        get() = "Tab2StartFragment"
    override val buttonText: String
        get() = "Go To second"
    override val listener: (View) -> Unit
        get() = {
            parentFragment?.findNavController()?.navigate(R.id.tab2SecondFragment)
        }
}