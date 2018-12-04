package com.orego.battlecrane.bcApi.race.human.infantry.implementation

import com.orego.battlecrane.bcApi.manager.BGameContext
import com.orego.battlecrane.bcApi.model.unit.BUnit
import com.orego.battlecrane.bcApi.model.unit.contract.BAttackable
import com.orego.battlecrane.bcApi.model.unit.contract.BHealthable
import com.orego.battlecrane.bcApi.race.human.infantry.BHumanInfantry

open class BHumanMarine(context: BGameContext) : BUnit(context),
    BHumanInfantry, BHealthable, BAttackable {

    companion object {

        private const val DEFAULT_VERTICAL_SIDE = 1

        private const val DEFAULT_HORIZONTAL_SIDE = 1

        private const val DEFAULT_MAX_HEALTH = 1

        private const val DEFAULT_DAMAGE = 1

        private const val DEFAULT_ATTACK_TIMES = 1

        private const val DEFAULT_IS_ATTACK_ENABLE = true
    }

    /**
     * Properties.
     */

    final override val verticalSide =
        DEFAULT_VERTICAL_SIDE

    final override val horizontalSide =
        DEFAULT_HORIZONTAL_SIDE

    final override var currentHealth =
        DEFAULT_MAX_HEALTH

    final override var maxHealth =
        DEFAULT_MAX_HEALTH

    final override var damage =
        DEFAULT_DAMAGE

    final override var attackTimes =
        DEFAULT_ATTACK_TIMES

    final override var isAttackEnable =
        DEFAULT_IS_ATTACK_ENABLE

    /**
     * Observers.
     */

    final override val decreaseHealthObserver: MutableMap<Long, BHealthable.HealthListener> = mutableMapOf()

    final override val increaseHealthObserver: MutableMap<Long, BHealthable.HealthListener> = mutableMapOf()

    final override val damageObserver: MutableMap<Long, BAttackable.DamageListener> = mutableMapOf()

    final override val attackObserver: MutableMap<Long, BAttackable.AttackListener> = mutableMapOf()

    final override val attackEnableObserver: MutableMap<Long, BAttackable.AttackEnableListener> = mutableMapOf()

    /**
     * Implementations.
     */

    class Marine1(context: BGameContext) : BHumanMarine(context)

    class Marine2(context: BGameContext) : BHumanMarine(context)

    class Marine3(context: BGameContext) : BHumanMarine(context)
}