package myfirstmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import myfirstmod.Tank.Tank;
import myfirstmod.util.CardStats;

public class Entrench_Tank extends BaseCard{

    public static final String ID = makeID("Entrench");

    public static final int BLOCK = 6;
    public static final int UPG_BLOCK = 2;

    public static final int WEAK = 1;
    public static final int UPG_WEAK = 1;

    private static final CardStats info = new CardStats(
            Tank.Meta.DARK_GREEN_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    public Entrench_Tank(){
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        setMagic(WEAK, UPG_WEAK);
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new GainBlockAction(p, block));
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false)));
    }
}
