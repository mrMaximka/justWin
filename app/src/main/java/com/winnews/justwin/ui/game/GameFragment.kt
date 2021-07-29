package com.winnews.justwin.ui.game

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.winnews.justwin.R
import com.winnews.justwin.databinding.DialogAnswerBinding
import com.winnews.justwin.databinding.FragmentGameBinding
import com.winnews.justwin.model.GameModel
import com.winnews.justwin.ui.result.ResultFragment


class GameFragment : Fragment(), GameContract.View {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: GameContract.Presenter
    private lateinit var model: GameModel

    companion object {
        fun newInstance() = GameFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = GamePresenter(this)
        loadQuestData()
        initBtn()
    }

    private fun initBtn() {
        binding.btnAnswer1.setOnClickListener {
            presenter.onAnswerClick(1, requireContext())
        }
        binding.btnAnswer2.setOnClickListener {
            presenter.onAnswerClick(2, requireContext())
        }
        binding.btnAnswer3.setOnClickListener {
            presenter.onAnswerClick(3, requireContext())
        }
    }

    private fun loadQuestData() {
        val questPosition: Int = arguments?.get("position") as Int
        model = presenter.loadQuest(questPosition, context)

        binding.btnAnswer1.text = model.answer1
        binding.btnAnswer2.text = model.answer2
        binding.btnAnswer3.text = model.answer3

        binding.heals.text = presenter.getHeals()
        binding.questNumber.text = String.format("%s/25", (questPosition + 1).toString())

        val res = resources.getIdentifier(model.image, "drawable", activity?.packageName)
        binding.questImage.setImageDrawable(ResourcesCompat.getDrawable(resources, res, requireActivity().theme))
    }

    override fun setBtnAnim() {
        if (presenter.isWin()){
            showWinAnim()
        }else{
            showFailAnim()
        }
    }

    override fun nextQuest(position: Int) {
        val instance = newInstance()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putInt("position", position)
        instance.arguments = bundle
        transaction.replace(R.id.container, instance)
        transaction.commit()
    }

    private fun showWinAnim() {
        when (model.rightAnswer.toInt()) {
            1 -> {
                val animationClose: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_close)
                binding.btnAnswer1.startAnimation(animationClose)
                animationClose.setAnimationListener(object : Animation.AnimationListener{
                    override fun onAnimationStart(animation: Animation?) {
                        binding.btnAnswer1.visibility = View.INVISIBLE
                    }
                    override fun onAnimationEnd(animation: Animation?) {

                        binding.btnAnswer1.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.btn_win))
                        val animationOpen: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_open)
                        binding.btnAnswer1.startAnimation(animationOpen)
                        animationOpen.setAnimationListener(object : Animation.AnimationListener{
                            override fun onAnimationStart(animation: Animation?) {
                                binding.btnAnswer1.visibility = View.VISIBLE
                            }
                            override fun onAnimationEnd(animation: Animation?) {
                                showAnswerDialog()
                            }
                            override fun onAnimationRepeat(animation: Animation?) {}
                        })
                    }
                    override fun onAnimationRepeat(animation: Animation?) {}
                })
            }
            2 -> {
                val animationClose: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_close)
                binding.btnAnswer2.startAnimation(animationClose)
                animationClose.setAnimationListener(object : Animation.AnimationListener{
                    override fun onAnimationStart(animation: Animation?) {
                        binding.btnAnswer2.visibility = View.INVISIBLE
                    }
                    override fun onAnimationEnd(animation: Animation?) {
                        binding.btnAnswer2.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.btn_win))
                        val animationOpen: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_open)
                        binding.btnAnswer2.startAnimation(animationOpen)
                        animationOpen.setAnimationListener(object : Animation.AnimationListener{
                            override fun onAnimationStart(animation: Animation?) {
                                binding.btnAnswer2.visibility = View.VISIBLE
                            }
                            override fun onAnimationEnd(animation: Animation?) {
                                showAnswerDialog()
                            }
                            override fun onAnimationRepeat(animation: Animation?) {}
                        })
                    }
                    override fun onAnimationRepeat(animation: Animation?) {}
                })
            }
            3 -> {
                val animationClose: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_close)
                binding.btnAnswer3.startAnimation(animationClose)
                animationClose.setAnimationListener(object : Animation.AnimationListener{
                    override fun onAnimationStart(animation: Animation?) {
                        binding.btnAnswer3.visibility = View.INVISIBLE
                    }
                    override fun onAnimationEnd(animation: Animation?) {
                        binding.btnAnswer3.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.btn_win))
                        val animationOpen: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_open)
                        binding.btnAnswer3.startAnimation(animationOpen)
                        animationOpen.setAnimationListener(object : Animation.AnimationListener{
                            override fun onAnimationStart(animation: Animation?) {
                                binding.btnAnswer3.visibility = View.VISIBLE
                            }
                            override fun onAnimationEnd(animation: Animation?) {
                                showAnswerDialog()
                            }
                            override fun onAnimationRepeat(animation: Animation?) {}
                        })
                    }
                    override fun onAnimationRepeat(animation: Animation?) {}
                })
            }
            else -> {
                Log.d("WIN", "Error WinAnim")
            }
        }
    }

    private fun showFailAnim() {

        when (presenter.getAnswerClick()) {
            1 -> {
                val animationClose: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_close)
                binding.btnAnswer1.startAnimation(animationClose)
                animationClose.setAnimationListener(object : Animation.AnimationListener{
                    override fun onAnimationStart(animation: Animation?) {
                        binding.btnAnswer1.visibility = View.INVISIBLE
                    }
                    override fun onAnimationEnd(animation: Animation?) {

                        binding.btnAnswer1.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.btn_lose))
                        val animationOpen: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_open)
                        binding.btnAnswer1.startAnimation(animationOpen)
                        animationOpen.setAnimationListener(object : Animation.AnimationListener{
                            override fun onAnimationStart(animation: Animation?) {
                                binding.btnAnswer1.visibility = View.VISIBLE
                            }
                            override fun onAnimationEnd(animation: Animation?) {
                                showWinAnim()
                            }
                            override fun onAnimationRepeat(animation: Animation?) {}
                        })
                    }
                    override fun onAnimationRepeat(animation: Animation?) {}
                })
            }
            2 -> {
                val animationClose: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_close)
                binding.btnAnswer2.startAnimation(animationClose)
                animationClose.setAnimationListener(object : Animation.AnimationListener{
                    override fun onAnimationStart(animation: Animation?) {
                        binding.btnAnswer2.visibility = View.INVISIBLE
                    }
                    override fun onAnimationEnd(animation: Animation?) {
                        binding.btnAnswer2.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.btn_lose))
                        val animationOpen: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_open)
                        binding.btnAnswer2.startAnimation(animationOpen)
                        animationOpen.setAnimationListener(object : Animation.AnimationListener{
                            override fun onAnimationStart(animation: Animation?) {
                                binding.btnAnswer2.visibility = View.VISIBLE
                            }
                            override fun onAnimationEnd(animation: Animation?) {
                                showWinAnim()
                            }
                            override fun onAnimationRepeat(animation: Animation?) {}
                        })
                    }
                    override fun onAnimationRepeat(animation: Animation?) {}
                })
            }
            3 -> {
                val animationClose: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_close)
                binding.btnAnswer3.startAnimation(animationClose)
                animationClose.setAnimationListener(object : Animation.AnimationListener{
                    override fun onAnimationStart(animation: Animation?) {
                        binding.btnAnswer3.visibility = View.INVISIBLE
                    }
                    override fun onAnimationEnd(animation: Animation?) {
                        binding.btnAnswer3.setBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.btn_lose))
                        val animationOpen: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_open)
                        binding.btnAnswer3.startAnimation(animationOpen)
                        animationOpen.setAnimationListener(object : Animation.AnimationListener{
                            override fun onAnimationStart(animation: Animation?) {
                                binding.btnAnswer3.visibility = View.VISIBLE
                            }
                            override fun onAnimationEnd(animation: Animation?) {
                                showWinAnim()
                            }
                            override fun onAnimationRepeat(animation: Animation?) {}
                        })
                    }
                    override fun onAnimationRepeat(animation: Animation?) {}
                })
            }
            else -> {
                Log.d("WIN", "Error WinAnim")
            }
        }
    }

    private fun showAnswerDialog() {

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        val settingsBinding = DialogAnswerBinding.inflate(layoutInflater)
        val view = settingsBinding.root
        builder.setView(view)
        val alertDialog: AlertDialog = builder.create()

        if (!presenter.isHealsNotNull()){
            healsMinus()
            settingsBinding.answerResult.text = "Неверно"
            settingsBinding.answerResult.setTextColor(
                ContextCompat.getColor(
                    requireActivity().applicationContext,
                    R.color.red_600
                )
            )
            settingsBinding.answerButton.text = "Завершить"
            presenter.saveResults(requireContext())
            settingsBinding.answerButton.setOnClickListener {
                showResult()
                alertDialog.cancel()
            }
        }
        else{
            if (!presenter.isWin()){
                healsMinus()
                settingsBinding.answerResult.text = "Неверно"
                settingsBinding.answerResult.setTextColor(
                    ContextCompat.getColor(
                        requireActivity().applicationContext,
                        R.color.red_600
                    )
                )
            }
            else{
                settingsBinding.answerResult.text = "Верно"
                settingsBinding.answerResult.setTextColor(
                    ContextCompat.getColor(
                        requireActivity().applicationContext,
                        R.color.green_600
                    )
                )
            }
            if (presenter.isNextQuest()){
                settingsBinding.answerButton.text = "Дальше"
                settingsBinding.answerButton.setOnClickListener {
                    presenter.getNextQuest()
                    alertDialog.cancel()
                }
            }
            else{
                settingsBinding.answerButton.text = "Завершить"
                presenter.saveResults(requireContext())
                settingsBinding.answerButton.setOnClickListener {
                    showResult()
                    alertDialog.cancel()
                }
            }
        }
        alertDialog.setCancelable(false)
        alertDialog.window?.setBackgroundDrawableResource(R.color.transparent)
        alertDialog.show()

    }

    private fun healsMinus() {
        binding.heals.text = presenter.getHeals()
        binding.healsMinus.visibility = View.VISIBLE
        binding.healsMinus.animate()
            .alpha(0.0F)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    if (binding.healsMinus.isEnabled) {
                        binding.healsMinus.visibility = View.INVISIBLE
                    }
                }
            })
            .duration = 1500
    }

    private fun showResult() {
        val instance = ResultFragment.newInstance()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, instance)
        transaction.commit()
    }

}