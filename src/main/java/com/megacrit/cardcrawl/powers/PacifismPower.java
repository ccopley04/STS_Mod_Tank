//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.megacrit.cardcrawl.powers;


import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

public class PacifismPower extends AbstractPower {
    public static final String POWER_ID = "Pacifism";
    //private static final PowerStrings powerStrings;
    public static final String NAME;
    public static String[] DESCRIPTIONS = new String[2];
    private boolean attackPlayed = false;
    private final int block;

    public PacifismPower(AbstractCreature owner, int block) {
        this.name = NAME;
        this.ID = "Pacifism";
        this.owner = owner;
        this.amount = block;
        this.block = block;
        this.loadRegion("barricade");
        this.updateDescription();
    }

    public void onAfterCardPlayed(AbstractCard card) {
        if(card.type == AbstractCard.CardType.ATTACK){
            this.flash();
            attackPlayed = true;
        }
    }

    public void atEndOfTurn(boolean isPlayer){
        if(!attackPlayed){
            addToBot(new GainBlockAction(owner, amount));
        }
        attackPlayed = false;
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    static {
        NAME = "Pacifism";
        DESCRIPTIONS[0] = "At the end of your turn, if you have played no attacks, gain #b";
        DESCRIPTIONS[1] = " Block.";
    }
}