package com.winnews.justwin.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.winnews.justwin.R
import com.winnews.justwin.databinding.FragmentMenuBinding
import com.winnews.justwin.model.PlayerModel
import com.winnews.justwin.ui.game.GameFragment
import com.winnews.justwin.ui.result.ResultFragment

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MenuFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBackClick()
        loadMost()
    }

    private fun loadMost() {
        binding.mostStart.setOnClickListener {
            val user = PlayerModel
            user.clear()
            user.setDateStart()
            val instance = GameFragment.newInstance()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            val bundle = Bundle()
            bundle.putInt("position", 0)
            instance.arguments = bundle
            transaction.replace(R.id.container, instance)
            transaction.commit()
        }
        binding.mostResults.setOnClickListener {
            val instance = ResultFragment.newInstance()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, instance)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        binding.mostExit.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initBackClick() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.moveTaskToBack(true)
                    activity?.finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}