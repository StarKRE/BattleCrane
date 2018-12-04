package com.orego.battlecrane.ui.fragment.battleLoading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.orego.battlecrane.R
import com.orego.battlecrane.bcApi.manager.BGameContext
import com.orego.battlecrane.ui.fragment.BFragment
import com.orego.battlecrane.ui.fragment.battle.BBattleFragment
import com.orego.battlecrane.ui.viewModel.BFactoryViewModel
import com.orego.battlecrane.ui.viewModel.BScenarioProviderViewModel
import kotlinx.coroutines.*

//TODO MAKE OVER BATTLE FRAGMENT!!!
class BBattleLoadingFragment : BFragment() {

    override val presenter = Presenter()

    override fun onCreateContentView(i: LayoutInflater, c: ViewGroup?, b: Bundle?) = i
        .inflate(R.layout.fragment_loading_battle, c, false)!!

    override fun onStart() {
        super.onStart()
        this.presenter.start()
    }

    inner class Presenter : BPresenter() {

        private val scenarioProviderViewModel by lazy {
            ViewModelProviders
                .of(this.activity)
                .get(BScenarioProviderViewModel::class.java)
        }

        private val factoryViewModel by lazy {
            ViewModelProviders
                .of(this.activity)
                .get(BFactoryViewModel::class.java)
        }

        fun start() =
            GlobalScope.launch(Dispatchers.Main) {
                val scenarioProvider = this@Presenter.scenarioProviderViewModel.scenarioProvider
                val gameScenario = scenarioProvider.gameScenario
                //Install game manager:
                val gameManagerInstallationJob = async { BGameContext(gameScenario) }
                //Install ui renders:
                val uiRenderInstallationJob = async { this@Presenter.factoryViewModel.install(scenarioProvider)}
                //Get results:
                this@Presenter.manager.gameContext = gameManagerInstallationJob.await()
                uiRenderInstallationJob.await()
                //Game is ready:
                this@Presenter.replaceFragment(BBattleFragment::class.java)
            }
    }
}