package myfirstmod.cards;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import myfirstmod.Tank.Tank;
import myfirstmod.util.CardStats;

public class PeakPerformance_Tank extends BaseCard {
    public static final String ID = makeID("Peak Performance");

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = -1;


    private static final CardStats info = new CardStats(
            Tank.Meta.DARK_GREEN_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    public PeakPerformance_Tank() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        setMagic(MAGIC, UPG_MAGIC);
        setCustomVar("currDamage", 0, 0);
        setVarCalculation("currDamage", (card, m, base)->
                calculateDamage());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, calculateDamage())));
    }

    private int calculateDamage(){
        float weakMod = 1;

        if(AbstractDungeon.player != null){
            if(AbstractDungeon.player.getPower("Weakened") != null &&
                    AbstractDungeon.player.getPower("Weakened").amount >= 1){
                weakMod = 0.75F;
            }
            return (int)((int)((float)AbstractDungeon.player.currentHealth / this.magicNumber)* weakMod);
        }
        return 0;
    }
}
