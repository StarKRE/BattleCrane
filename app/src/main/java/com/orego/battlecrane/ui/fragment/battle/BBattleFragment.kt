package com.orego.battlecrane.ui.fragment.battle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProviders
import com.orego.battlecrane.R
import com.orego.battlecrane.ui.fragment.BFragment
import com.orego.battlecrane.ui.fragment.battle.render.map.BBattleMapRender
import com.orego.battlecrane.ui.fragment.battle.render.tool.unit.implementations.BBuildToolRender
import com.orego.battlecrane.ui.fragment.battle.render.tool.bonus.BBonusToolRender
import com.orego.battlecrane.ui.fragment.battle.render.tool.unit.implementations.BTrainToolRender
import com.orego.battlecrane.ui.util.onMeasured
import com.orego.battlecrane.ui.viewModel.BFactoryViewModel
import kotlinx.android.synthetic.main.fragment_battle.*

class BBattleFragment : BFragment() {

    override val presenter = Presenter()

    override fun onCreateContentView(i: LayoutInflater, c: ViewGroup?, b: Bundle?): View? = i
        .inflate(R.layout.fragment_battle, c, false)!!

    override fun onStart() {
        super.onStart()
        this.presenter.prepareMap(this.fragment_battle_map_constraint_layout)
        this.presenter.prepareBuildTools(this.fragment_battle_build_tools)
        this.presenter.prepareTrainTools(this.fragment_battle_train_tools)
        this.presenter.prepareReinforcementTools(this.fragment_battle_reinforcements_tools)
    }

    inner class Presenter : BFragment.BPresenter() {

        private val gameManager by lazy {
            this.manager.gameContext
        }

        private val context by lazy {
            this@BBattleFragment.context!!
        }

        private val factoryViewModel by lazy {
            ViewModelProviders.of(this.activity).get(BFactoryViewModel::class.java)
        }

        private val mapRender by lazy {
            BBattleMapRender(this.gameManager.mapManager.unitHeap)
        }

        private val buildToolRender by lazy {
            BBuildToolRender(this.gameManager.playerManager)
        }

        private val trainToolRender by lazy {
            BTrainToolRender(this.gameManager.playerManager)
        }

        private val reinforcesToolRender by lazy {
            BBonusToolRender(this.gameManager.playerManager)
        }

        fun prepareMap(constraintLayout: ConstraintLayout) {
            constraintLayout.onMeasured {
                this.mapRender.install(
                    this@BBattleFragment.fragment_battle_map_constraint_layout,
                    this.factoryViewModel.mapFactory,
                    this.context
                )
            }
        }

        fun prepareBuildTools(constraintLayout: ConstraintLayout) {
            constraintLayout.onMeasured {
                this.buildToolRender.install(
                    this@BBattleFragment.fragment_battle_build_tools,
                    this.factoryViewModel.buildToolFactory,
                    this.context
                )
            }
        }

        fun prepareTrainTools(constraintLayout: ConstraintLayout) {
            constraintLayout.onMeasured {
                this.trainToolRender.install(
                    this@BBattleFragment.fragment_battle_train_tools,
                    this.factoryViewModel.trainToolFactory,
                    this.context
                )
            }
        }

        fun prepareReinforcementTools(constraintLayout: ConstraintLayout) {
            constraintLayout.onMeasured {
                this.reinforcesToolRender.install(
                    this@BBattleFragment.fragment_battle_reinforcements_tools,
                    this.factoryViewModel.bonusToolFactory,
                    this.context
                )
            }
        }
    }
}