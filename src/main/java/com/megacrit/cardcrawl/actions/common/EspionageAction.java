//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.megacrit.cardcrawl.actions.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.RemoveAllPowersAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.powers.AbstractPower.PowerType.BUFF;
import static com.megacrit.cardcrawl.powers.AbstractPower.PowerType.DEBUFF;

public class EspionageAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final AbstractMonster m;
    private final ArrayList<AbstractPower> playerPowers;
    private final ArrayList<AbstractPower> monsterPowers;
    private boolean isDone = false;

    public EspionageAction(AbstractMonster m, ArrayList<AbstractPower> playerPowers, ArrayList<AbstractPower> monsterPowers) {
        this.p = AbstractDungeon.player;
        this.m = m;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.EXHAUST;
        this.playerPowers = playerPowers;
        this.monsterPowers = monsterPowers;
    }

    public void update() {
        if(!isDone) {
            for(AbstractPower power : playerPowers){
                if(power.type == DEBUFF) {
                    addToBot(new ApplyPowerAction(m, m, power));
                }
            }

            for(AbstractPower power : monsterPowers){
                if(power.type == DEBUFF) {
                    addToBot(new ApplyPowerAction(p, p, power));
                }
            }
        }


        isDone = true;
        this.tickDuration();
    }
}
