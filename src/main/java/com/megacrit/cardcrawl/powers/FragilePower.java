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
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class FragilePower extends AbstractPower {
    public static final String POWER_ID = "Fragile";
    //private static final PowerStrings powerStrings;
    public static final String NAME;
    public static String[] DESCRIPTIONS = new String[2];
    public boolean justApplied;

    public FragilePower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "Fragile";
        this.owner = owner;
        this.loadRegion("wraithForm");
        this.updateDescription();
        justApplied = true;
    }

    public int onLoseHp(int damage) {
        return owner.maxHealth;
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
    }

    public void atStartOfTurn(){
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Fragile"));
    }

    static {
        NAME = "Fragile";
        DESCRIPTIONS[0] = "If you take any damage this turn,";
        DESCRIPTIONS[1] = " immediately die.";
    }
}