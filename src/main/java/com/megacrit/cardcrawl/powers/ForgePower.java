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
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class ForgePower extends AbstractPower {
    public static final String POWER_ID = "Forge";
    //private static final PowerStrings powerStrings;
    public static final String NAME;
    public static String[] DESCRIPTIONS = new String[2];

    public ForgePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Forge";
        this.owner = owner;
        this.amount = amount;
        this.loadRegion("repair");
        this.updateDescription();
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
    }

    public void atStartOfTurnPostDraw() {
        addToBot(new ArmamentsAction(false));
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
    }


    static {
        NAME = "Forge";
        DESCRIPTIONS[0] = "At the start of your turn,";
        DESCRIPTIONS[1] = " choose one card to #yUpgrade";
    }
}