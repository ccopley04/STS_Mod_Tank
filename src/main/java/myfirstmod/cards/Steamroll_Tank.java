package myfirstmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import myfirstmod.Tank.Tank;
import myfirstmod.util.CardStats;

public class Steamroll_Tank extends BaseCard {
    public static final String ID = makeID("Steamroll");

    private static final CardStats info = new CardStats(
            Tank.Meta.DARK_GREEN_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    public Steamroll_Tank() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        setCostUpgrade(1);

        setCustomVar("blockrn", 0, 0);
        setVarCalculation("blockrn", (card, m, base)->
            calculateDamage());
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, calculateDamage(), DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
    }

    public int calculateDamage(){
        float weakMod = 1;

        if(AbstractDungeon.player != null){
            if(AbstractDungeon.player.getPower("Weakened") != null &&
                    AbstractDungeon.player.getPower("Weakened").amount >= 1){
                weakMod = 0.75F;
            }
            return (int)(2 * AbstractDungeon.player.currentBlock * weakMod);
        }
        return 0;
    }
}
