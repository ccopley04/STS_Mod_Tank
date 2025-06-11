package myfirstmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FragilePower;
import com.megacrit.cardcrawl.powers.PacifismPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.watcher.BlockReturnPower;
import myfirstmod.Tank.Tank;
import myfirstmod.util.CardStats;

public class Fragility_Tank extends BaseCard {
    public static final String ID = makeID("Fragility");

    private static final int BLOCK = 25;
    private static final int UPG_BLOCK = 10;

    private static final CardStats info = new CardStats(
            Tank.Meta.DARK_GREEN_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    public Fragility_Tank() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        setBlock(BLOCK, UPG_BLOCK);//Sets the card's damage and how much it changes when upgraded.
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new GainBlockAction(p, this.block));
        addToBot(new ApplyPowerAction(p, p, new FragilePower(p)));
    }
}