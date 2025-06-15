//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.megacrit.cardcrawl.powers;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class InvestmentPower extends AbstractPower {
    public static final String POWER_ID = "Defensive Investment";
    //private static final PowerStrings powerStrings;
    public static final String NAME;
    public static String[] DESCRIPTIONS = new String[2];

    public InvestmentPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Defensive Investment";
        this.owner = owner;
        this.amount = amount;
        this.loadRegion("energized_green");
        this.updateDescription();
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
    }

    public void atEndOfRound(){
        addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, this.amount)));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }


    static {
        NAME = "Defensive Investment";
        DESCRIPTIONS[0] = "At the end of your turn, gain ";
        DESCRIPTIONS[1] = " #yDexterity.";
    }
}