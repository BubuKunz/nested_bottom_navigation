package yan.zubritskiy.s2.screens

import android.view.View
import androidx.navigation.fragment.findNavController
import yan.zubritskiy.s2.R

class Tab2SecondFragment : CommonFragment() {
    override val text: String
        get() = "Tab2SecondFragment"
    override val buttonText: String
        get() = "Go To main"
    override val listener: (View) -> Unit
        get() = {
            parentFragment?.parentFragment?.findNavController()?.navigate(R.id.afterBottomViewFragment1Fragment)
        }

}