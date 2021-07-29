package com.winnews.justwin.ui.start

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.winnews.justwin.R
import com.winnews.justwin.databinding.FragmentStartBinding
import com.winnews.justwin.ui.menu.MenuFragment

class StartFragment : Fragment(), StartContract.View {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: StartContract.Presenter

    companion object {
        fun newInstance() = StartFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = StartPresenter(this)
        if (presenter.checkTheInternet(requireContext())){
            presenter.checkProvider(view)
        }
        else{
            showRefresh(view)
        }
    }

    private fun showRefresh(view: View) {
        requireActivity().window?.setBackgroundDrawableResource(R.color.white)
        binding.refreshLayout.visibility = View.VISIBLE
        binding.refreshLayout.setOnRefreshListener {
            if (presenter.checkTheInternet(requireContext())){
                binding.refreshLayout.visibility = View.GONE
                presenter.checkProvider(view)
            }
            binding.refreshLayout.isRefreshing = false
        }
    }

    override fun showLink(link: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(link)
        startActivity(i)
    }

    override fun startMost() {
        val instance = MenuFragment.newInstance()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, instance)
        transaction.commit()
    }
}