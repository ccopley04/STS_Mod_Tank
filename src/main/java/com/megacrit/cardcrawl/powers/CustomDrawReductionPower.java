//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.megacrit.cardcrawl.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class CustomDrawReductionPower extends AbstractPower {
    public static final String POWER_ID = "Future Draw Reduction";
    //private static final PowerStrings powerStrings;
    public static final String NAME;
    public static String[] DESCRIPTIONS = new String[3];
    private boolean justApplied = true;
    private int total;

    public CustomDrawReductionPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Future Draw Reduction";
        this.owner = owner;
        this.amount = amount;
        this.total = this.amount;
        this.updateDescription();
        this.loadRegion("lessdraw");
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        for(int i = 0; i < amount; i++) {
            if(AbstractDungeon.player.gameHandSize >= 1) {
                --AbstractDungeon.player.gameHandSize;
            }
        }
    }
    public void atEndOfRound() {
        total = this.amount;
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Future Draw Reduction"));
    }

    public void onRemove() {
        for(int i = 0; i < total; i++) {
            if(AbstractDungeon.player.gameHandSize < 5) {
                ++AbstractDungeon.player.gameHandSize;
            }
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + ((this.amount > 1)? "s ": " ")+ DESCRIPTIONS[2];
    }

    static {
        NAME = "Future Draw Reduction";
        DESCRIPTIONS[0] = "Draw ";
        DESCRIPTIONS[1] = " less card";
        DESCRIPTIONS[2] = "next turn.";
    }
}
