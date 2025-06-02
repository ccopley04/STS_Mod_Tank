package myfirstmod;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.Donu;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import java.util.UUID;

public class NukeAction extends AbstractGameAction {
    private int costIncrease;
    private DamageInfo info;
    private static final float DURATION = 0.1F;
    private UUID uuid;

    public NukeAction(AbstractCreature target, DamageInfo info, int costIncrease, UUID uuid) {
        this.info = info;
        this.setValues(target, info);
        this.costIncrease = costIncrease;
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1F;
        this.uuid = uuid;
    }

    public void update() {
        if (this.duration == 0.1F && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.NONE));
            this.target.damage(this.info);
            if ((((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion")) {
                for(AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                    if (c.uuid.equals(this.uuid)) {
                        c.cost += this.costIncrease;
                        c.applyPowers();
                    }
                }
                for(AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
                    c.cost += this.costIncrease;
                    c.applyPowers();
                }
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

        this.tickDuration();
    }
}
