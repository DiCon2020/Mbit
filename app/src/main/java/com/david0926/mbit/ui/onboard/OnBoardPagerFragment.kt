package com.david0926.mbit.ui.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class OnBoardPagerFragment : Fragment() {
    private var layoutId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null && args.containsKey(KEY_LAYOUT_ID)) layoutId = args.getInt(KEY_LAYOUT_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    companion object {
        private const val KEY_LAYOUT_ID = "layoutId"
        @JvmStatic
        fun newInstance(layoutId: Int): OnBoardPagerFragment {
            val fragment = OnBoardPagerFragment()

            //put layout id as parameter
            val args = Bundle()
            args.putInt(KEY_LAYOUT_ID, layoutId)
            fragment.arguments = args
            return fragment
        }
    }
}