package myfirstmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.CrunchTimeAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ExposedPower;
import com.megacrit.cardcrawl.powers.ForgePower;
import com.megacrit.cardcrawl.powers.InvestmentPower;
import myfirstmod.Tank.Tank;
import myfirstmod.util.CardStats;

public class CrunchTime_Tank extends BaseCard{

    public static final String ID = makeID("Crunch Time");;

    public static final int DAMAGE = 8;
    public static final int UPG_DAMAGE = 2;

    private static final CardStats info = new CardStats(
            Tank.Meta.DARK_GREEN_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            -1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    public CrunchTime_Tank(){
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new CrunchTimeAction(p, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse, m, this.damage));
    }
}
