//package com.orego.battlecrane.ui.model.std.race.human.action.infantry
//
//import android.content.Context
//import android.widget.ImageView
//import com.orego.battlecrane.bc.api.model.entity.main.BAction
//import com.orego.battlecrane.bc.std.race.human.unit.building.implementation.producable.BHumanBarracks
//import com.orego.battlecrane.ui.model.api.render.action.BActionViewRenderItem
//import com.orego.battlecrane.ui.model.api.view.action.BActionView
//import com.orego.battlecrane.ui.util.byAssets
//
//class BHumanTrainMarineLvl3View(action: BHumanBarracks.TrainMarineLvl3Factory.Action, dimension: Int, context: Context) : BActionView(action) {
//
//    companion object {
//
//        private const val PATH = "race/human/action/train_marine_lvl_3.png"
//    }
//
//    override val displayedView = ImageView(context).byAssets(context, dimension, dimension, PATH)
//
//    class Builder : BActionViewRenderItem.ViewBuilder {
//
//        override fun build(value: BAction, dimension: Int, context: Context) =
//            BHumanTrainMarineLvl3View(value as BHumanBarracks.TrainMarineLvl3Factory.Action, dimension, context)
//
//        override val type: String = BHumanBarracks.TrainMarineLvl3Factory.Action::class.java.name
//    }
//}