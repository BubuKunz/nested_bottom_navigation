package yan.zubritskiy.s2.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import yan.zubritskiy.s2.R

abstract class CommonFragment : Fragment(R.layout.fragment_common) {
    abstract val text: String
    abstract val buttonText: String
    abstract val listener: (View) -> Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(text, buttonText, listener)
    }

    private fun setupViews(text: String, buttonText: String, listener: (View) -> Unit) {
        view?.findViewById<TextView>(R.id.text)?.let {
            it.text = text
        }
        view?.findViewById<TextView>(R.id.button)?.let {
            it.text = buttonText
            it.setOnClickListener(listener)
        }
    }
}