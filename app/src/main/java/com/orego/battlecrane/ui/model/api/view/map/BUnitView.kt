package com.orego.battlecrane.ui.model.api.view.map

import com.orego.battlecrane.bc.api.model.unit.BUnit
import com.orego.battlecrane.ui.model.api.view.BView

abstract class BUnitView(entity: BUnit) : BView<BUnit>(entity)