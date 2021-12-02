package yan.zubritskiy.s2.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import yan.zubritskiy.s2.R

enum class State { TAB1, TAB2, TAB3 }

class BottomViewFragment : Fragment(R.layout.fragment_bottom_view) {

    //todo save state (use fragment without view)
    private lateinit var retainData: RetainFragment
    var previousFragmentsCount: Int = 0
    lateinit var backStackListener: () -> Unit

    //    var navF: Fragment? = null
    var isWaitingToGoBack = false
    private fun init(savedInstanceState: Bundle?) {
        var isFromSavedState = savedInstanceState != null
//        navF = parentFragmentManager.primaryNavigationFragment
        var f =
            childFragmentManager.findFragmentByTag(RetainFragment.TAG) as? RetainFragment
        if (f == null) {
            f = RetainFragment()
            childFragmentManager.beginTransaction().add(f, RetainFragment.TAG)
                .commit()
        }
        retainData = f
        previousFragmentsCount = retainData.fragments.size
        backStackListener = {
            if (!isWaitingToGoBack && !isFromSavedState) {
                val lastFragment =
                    childFragmentManager.fragments.lastOrNull { it is NavHostFragment }
                if (previousFragmentsCount!! > 0 && lastFragment == null) {
                    Log.d("TestTAG", "goBack: 1")
                    goBack()

                } else if (lastFragment !is RetainFragment) {
                    val f = childFragmentManager.fragments.lastOrNull {
                        it is NavHostFragment && it.tag == State.TAB1.toString()
                    }
                    if (f == null) {
                        Log.d("TestTAG", "goBack: 2")
                        try {

                            goBack()
                        } catch (e: Exception) {
                            Log.d("TestTAG", e.message, e)

                        }
                    }
                    //            val currentTab =
                    //                lastFragment?.let {
                    //                    retainData.getTabByFragment(it as NavHostFragment)
                    //                }
                    //            if (previousSelectedTab == State.TAB1 && previousFragmentsCount > 1) {
                    //                goBack()
                    //            }
                    //            previousSelectedTab = currentTab
                    previousFragmentsCount =
                        childFragmentManager.fragments.filterIsInstance<NavHostFragment>().size
                }
            }
            isFromSavedState = false


        }
        //        retainData.retainInstance = true
        //        retainInstance = true
        //        var previousSelectedTab: State? = retainData.selectedTab
        childFragmentManager.addOnBackStackChangedListener(backStackListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        childFragmentManager.removeOnBackStackChangedListener(backStackListener)
    }

    private fun goBack() {
        isWaitingToGoBack = true
//        childFragmentManager.removeOnBackStackChangedListener(backStackListener)
//        repeat(childFragmentManager.fragments.size) {
//            childFragmentManager.popBackStack()
//        }
//        parentFragmentManager.popBackStack()
        val childTr = childFragmentManager.beginTransaction()
        childFragmentManager.fragments.forEach {
            childTr.remove(it)
        }

//        (view as? ConstraintLayout)?.removeAllViews()
        childTr
            .setPrimaryNavigationFragment(null)
            .commitAllowingStateLoss()
        parentFragmentManager.beginTransaction()
            .setPrimaryNavigationFragment(null)
            .remove(this)
            .commitAllowingStateLoss()
        parentFragment?.parentFragmentManager?.popBackStack()
//        val callback = object :
//            FragmentManager.FragmentLifecycleCallbacks() {
//            override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
//                super.onFragmentDetached(fm, f)
//                Log.d("TestTAG", "onFragmentDetached: $f")
////                parentFragmentManager.popBackStackImmediate()
//            }
//
//            override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
//                super.onFragmentStopped(fm, f)
//                Log.d("TestTAG", "onFragmentStopped: $f")
//            }
//
//
//            override fun onFragmentSaveInstanceState(
//                fm: FragmentManager,
//                f: Fragment,
//                outState: Bundle
//            ) {
//                super.onFragmentSaveInstanceState(fm, f, outState)
//                Log.d("TestTAG", "onFragmentSaveInstanceState: $f")
//            }
//
//            override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
//                super.onFragmentViewDestroyed(fm, f)
////                parentFragmentManager.unregisterFragmentLifecycleCallbacks(this)
//                Log.d("TestTAG", "onFragmentViewDestroyed: $f")
//            }
//
//            override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
//                super.onFragmentDestroyed(fm, f)
//                Log.d("TestTAG", "onFragmentDestroyed: $f")
//            }
//        }
//        parentFragmentManager.registerFragmentLifecycleCallbacks(callback, true)
//        childFragmentManager.executePendingTransactions()
//        repeat(childFragmentManager.fragments.size) {
//            childFragmentManager.popBackStack()
//        }
//        val transaction = childFragmentManager.beginTransaction()
//        childFragmentManager.fragments.forEach {
//            transaction.remove(it)
//        }
//        transaction.commitAllowingStateLoss()

//        childFragmentManager.executePendingTransactions()
//        Handler(Looper.getMainLooper()).post {
//            if (!parentFragmentManager.isStateSaved && !childFragmentManager.isStateSaved) {
//                parentFragmentManager.popBackStack()
//            }
//            parentFragment?.findNavController()?.popBackStack()
//        }
//        parentFragment?.findNavController()?.clearBackStack(R.id.startFragment)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(savedInstanceState)

        switchToTab(retainData.selectedTab)
        with(view) {
            findViewById<TextView>(R.id.tab1).setOnClickListener {
                switchToTab(State.TAB1)
            }
            findViewById<TextView>(R.id.tab2).setOnClickListener {
                switchToTab(State.TAB2)
            }
            findViewById<TextView>(R.id.tab3).setOnClickListener {
                switchToTab(State.TAB3)
            }
        }
    }

    private fun switchToTab(state: State) {
//        childFragmentManager.fragments.firstOrNull { it is NavHostFragment }?.let {
//            childFragmentManager.saveBackStack(it.hashCode().toString())
//        }
//        childFragmentManager.restoreBackStack(finalHost.hashCode().toString())
        Log.d("TestTAG", "switchToTab: ")
        var transaction = childFragmentManager.beginTransaction()
        val tag = state.toString()
        val tagFragment = childFragmentManager.findFragmentByTag(tag)
        if (tagFragment != null) {
            transaction.remove(tagFragment)
                .commit()
            transaction = childFragmentManager.beginTransaction()
            transaction.add(R.id.tab_host_container, tagFragment, tag)
                .setPrimaryNavigationFragment(tagFragment)
                .addToBackStack(tag)
                .commitAllowingStateLoss()
        } else {
            val finalHost = retainData.getHost(state)

//            finalHost.navController.addOnDestinationChangedListener(object:
//                NavController.OnDestinationChangedListener{
//                override fun onDestinationChanged(
//                    controller: NavController,
//                    destination: NavDestination,
//                    arguments: Bundle?
//                ) {
//                    destination.id
//                }
//
//            })
            if (state == State.TAB1) {
                transaction.add(R.id.tab_host_container, finalHost, tag)
            } else {
                transaction.add(R.id.tab_host_container, finalHost, tag)
            }.setPrimaryNavigationFragment(finalHost)
                .addToBackStack(tag)
                .commitAllowingStateLoss()
        }

//        childFragmentManager.beginTransaction()
//            .replace(R.id.tab_host_container, finalHost)
//            .addToBackStack(finalHost.hashCode().toString())
//            .setPrimaryNavigationFragment(finalHost) // equivalent to app:defaultNavHost="true"
//            .commit()
    }

}

public class RetainFragment : Fragment() {
    companion object {
        const val TAG = "RetainFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun getTabByFragment(f: NavHostFragment): State? {
        fragments.forEach {
            if (it.value == f) {
                return it.key
            }
        }
        return null
    }

    var selectedTab = State.TAB1
    val fragments = mutableMapOf<State, NavHostFragment>()

    fun getHost(state: State): NavHostFragment {
        selectedTab = state
        if (!fragments.containsKey(state)) {
            val finalHost = NavHostFragment.create(state.graphId())
            fragments[state] = finalHost
        }
        return fragments[state]!!
    }


    fun State.graphId() = when (this) {
        State.TAB1 -> R.navigation.tab_1_nav_graph
        State.TAB2 -> R.navigation.tab_2_nav_graph
        State.TAB3 -> R.navigation.tab_3_nav_graph
    }
}