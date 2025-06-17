package myfirstmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.CrunchTimeAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ExposedPower;
import com.megacrit.cardcrawl.powers.ForgePower;
import com.megacrit.cardcrawl.powers.InvestmentPower;
import myfirstmod.Tank.Tank;
import myfirstmod.util.CardStats;

public class IronStrike_Tank extends BaseCard{

    public static final String ID = makeID("Iron Strike");;

    public static final int DAMAGE = 12;
    public static final int UPG_DAMAGE = 0;

    public int BASE_DAMAGE;

    public static final int MAGIC = 1;
    public static final int UPG_MAGIC = 1;

    private static final CardStats info = new CardStats(
            Tank.Meta.DARK_GREEN_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    public IronStrike_Tank(){
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);

        BASE_DAMAGE = this.damage;

        setCustomVar("extraDamage", this.damage, 0);
        setVarCalculation("extraDamage", (card, m, base)->calculateDamage());

        setCustomVar("baseDamage", this.damage, 0);
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new DamageAction(m, new DamageInfo(p, calculateDamage(), DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    public int calculateDamage(){
        AbstractPower dex = AbstractDungeon.player.getPower("Dexterity");
        float weakMod = 1;

        if(AbstractDungeon.player != null && dex != null){
            if(AbstractDungeon.player.getPower("Weakened") != null &&
                    AbstractDungeon.player.getPower("Weakened").amount >= 1){
                weakMod = 0.75F;
            }
            return (int)((BASE_DAMAGE + (this.magicNumber * dex.amount)) * weakMod);
        }
        return BASE_DAMAGE;
    }
}