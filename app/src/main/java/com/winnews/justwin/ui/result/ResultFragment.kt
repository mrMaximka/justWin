package com.winnews.justwin.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.winnews.justwin.R
import com.winnews.justwin.databinding.FragmentResultBinding
import com.winnews.justwin.ui.menu.MenuFragment

class ResultFragment : Fragment(), ResultContract.View {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: ResultContract.Presenter
    lateinit var adapter: ResultAdapter

    companion object {
        fun newInstance() = ResultFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ResultPresenter(this)
        initList()
        adapter.setList(presenter.loadResults(context))

        binding.resultToMenu.setOnClickListener {
            val instance = MenuFragment.newInstance()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, instance)
            transaction.commit()
        }
    }

    private fun initList() {
        adapter = ResultAdapter(requireContext())
        binding.resultList.adapter = adapter
    }
}