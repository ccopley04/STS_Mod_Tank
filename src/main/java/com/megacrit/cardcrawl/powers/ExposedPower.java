//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.megacrit.cardcrawl.powers;


import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ExposedPower extends AbstractPower {
    public static final String POWER_ID = "Exposed";
    //private static final PowerStrings powerStrings;
    public static final String NAME;
    public static String[] DESCRIPTIONS = new String[3];

    public ExposedPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Exposed";
        this.owner = owner;
        this.amount = amount;
        this.loadRegion("nightmare");
        this.updateDescription();
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
    }

    public void atEndOfRound() {
        if (this.amount == 0) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Exposed"));
        } else {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, "Exposed", 1));
        }
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage * 2;
        } else {
            return damage;
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + ((this.amount > 1)? DESCRIPTIONS[1]: DESCRIPTIONS[2]);
    }


    static {
        NAME = "Exposed";
        DESCRIPTIONS[0] = "Receive double damage from Attacks for #b";
        DESCRIPTIONS[1] = " turns.";
        DESCRIPTIONS[2] = " turn.";
    }
}