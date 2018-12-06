package com.orego.battlecrane.ui.viewModel

import androidx.lifecycle.ViewModel
import com.orego.battlecrane.bc.api.model.action.BAction
import com.orego.battlecrane.bc.api.model.unit.BUnit
import com.orego.battlecrane.ui.model.api.render.BViewRender
import com.orego.battlecrane.ui.model.api.render.action.BActionRender
import com.orego.battlecrane.ui.model.api.scenarioSupport.BUiScenarioSupport
import com.orego.battlecrane.ui.model.api.view.map.BUnitView

class BViewFactoryViewModel : ViewModel() {

    val mapFactory = BViewRender.ViewFactory<BUnit, BUnitView>()

    val buildToolFactory = BActionRender.ViewFactory<BUnit>()

    val trainToolFactory = BActionRender.ViewFactory<BUnit>()

    val bonusToolFactory = BActionRender.ViewFactory<BAction>()

    fun install(scenarioSupport: BUiScenarioSupport) {
        scenarioSupport.unitBuilders.forEach { this.mapFactory.addBuilder(it) }
        scenarioSupport.buildingToolsBuilders.forEach { this.buildToolFactory.addBuilder(it) }
        scenarioSupport.trainToolsBuilders.forEach { this.trainToolFactory.addBuilder(it) }
        scenarioSupport.bonusToolsBuilders.forEach { this.bonusToolFactory.addBuilder(it) }
        this.buildToolFactory.defaultBuilder = scenarioSupport.defaultUnitToolBuilder
        this.trainToolFactory.defaultBuilder = scenarioSupport.defaultUnitToolBuilder
        //TODO: MAKE BONUSES:
//        this.bonusToolFactory.defaultBuilder = scenarioSupport.defaultBonusToolBuilder
    }
}