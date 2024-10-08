package thePackmaster.powers.shamanpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.shamanpack.FadingEmber;
import thePackmaster.powers.AbstractPackmasterPower;

public class FueledByEmbersPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("FueledByEmbers");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FueledByEmbersPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF,false, owner, amount);
    }

    @Override
    public void updateDescription() {
        this.description = (this.amount == 1 ? DESCRIPTIONS[0] : DESCRIPTIONS[1]).replace("{0}", this.amount + "");
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.addToBot(new DrawCardAction(this.amount));
        this.addToBot(new MakeTempCardInHandAction(new FadingEmber(), this.amount));
        this.addToBot(new MakeTempCardInDrawPileAction(new FadingEmber(), this.amount, true, true));
    }
}