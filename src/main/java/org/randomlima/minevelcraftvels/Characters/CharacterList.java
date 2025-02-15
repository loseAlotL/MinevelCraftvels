package org.randomlima.minevelcraftvels.Characters;

import org.randomlima.minevelcraftvels.Characters.AdamWarlock.AdamWarlockCharacter;
import org.randomlima.minevelcraftvels.Characters.BlackPanther.BlackPantherCharacter;
import org.randomlima.minevelcraftvels.Characters.BlackWidow.BlackWidowCharacter;
import org.randomlima.minevelcraftvels.Characters.CaptainAmerica.CaptainAmericaCharacter;
import org.randomlima.minevelcraftvels.Characters.CloakAndDagger.CloakAndDaggerCharacter;
import org.randomlima.minevelcraftvels.Characters.DoctorStrange.DoctorStrangeCharacter;
import org.randomlima.minevelcraftvels.Characters.Groot.GrootCharacter;
import org.randomlima.minevelcraftvels.Characters.Hawkeye.HawkeyeCharacter;
import org.randomlima.minevelcraftvels.Characters.Hela.HelaCharacter;
import org.randomlima.minevelcraftvels.Characters.Hulk.HulkCharacter;
import org.randomlima.minevelcraftvels.Characters.InvisibleWoman.InvisibleWomanCharacter;
import org.randomlima.minevelcraftvels.Characters.IronFist.IronFistCharacter;
import org.randomlima.minevelcraftvels.Characters.IronMan.IronManCharacter;
import org.randomlima.minevelcraftvels.Characters.JeffTheLandShark.JeffTheLandSharkCharacter;
import org.randomlima.minevelcraftvels.Characters.Loki.LokiCharacter;
import org.randomlima.minevelcraftvels.Characters.LunaSnow.LunaSnowCharacter;
import org.randomlima.minevelcraftvels.Characters.Magik.MagikCharacter;
import org.randomlima.minevelcraftvels.Characters.Magneto.MagnetoCharacter;
import org.randomlima.minevelcraftvels.Characters.Mantis.MantisCharacter;
import org.randomlima.minevelcraftvels.Characters.MisterFantastic.MisterFantasticCharacter;
import org.randomlima.minevelcraftvels.Characters.MoonKnight.MoonKnightCharacter;
import org.randomlima.minevelcraftvels.Characters.Namor.NamorCharacter;
import org.randomlima.minevelcraftvels.Characters.PeniParker.PeniParkerCharacter;
import org.randomlima.minevelcraftvels.Characters.Psylocke.PsylockeCharacter;
import org.randomlima.minevelcraftvels.Characters.RocketRaccoon.RocketRaccoonCharacter;
import org.randomlima.minevelcraftvels.Characters.ScarletWitch.ScarletWitchCharacter;
import org.randomlima.minevelcraftvels.Characters.SpiderMan.SpiderManCharacter;
import org.randomlima.minevelcraftvels.Characters.SquirrelGirl.SquirrelGirlCharacter;
import org.randomlima.minevelcraftvels.Characters.StarLord.StarLordCharacter;
import org.randomlima.minevelcraftvels.Characters.Storm.StormCharacter;
import org.randomlima.minevelcraftvels.Characters.ThePunisher.ThePunisherCharacter;
import org.randomlima.minevelcraftvels.Characters.Thor.ThorCharacter;
import org.randomlima.minevelcraftvels.Characters.Venom.VenomCharacter;
import org.randomlima.minevelcraftvels.Characters.WinterSoldier.WinterSoldierCharacter;
import org.randomlima.minevelcraftvels.Characters.Wolverine.WolverineCharacter;

public enum CharacterList {
    ADAM_WARLOCK(new AdamWarlockCharacter()),
    BLACK_PANTHER(new BlackPantherCharacter()),
    BLACK_WIDOW(new BlackWidowCharacter()),
    CAPTAIN_AMERICA(new CaptainAmericaCharacter()),
    CLOAK_AND_DAGGER(new CloakAndDaggerCharacter()),
    DOCTOR_STRANGE(new DoctorStrangeCharacter()),
    GROOT(new GrootCharacter()),
    HAWKEYE(new HawkeyeCharacter()),
    HELA(new HelaCharacter()),
    HULK(new HulkCharacter()),
    INVISIBLE_WOMAN(new InvisibleWomanCharacter()),
    IRON_FIST(new IronFistCharacter()),
    IRON_MAN(new IronManCharacter()),
    JEFF_THE_LAND_SHARK(new JeffTheLandSharkCharacter()),
    LOKI(new LokiCharacter()),
    LUNA_SNOW(new LunaSnowCharacter()),
    MAGIK(new MagikCharacter()),
    MAGNETO(new MagnetoCharacter()),
    MANTIS(new MantisCharacter()),
    MISTER_FANTASTIC(new MisterFantasticCharacter()),
    MOON_KNIGHT(new MoonKnightCharacter()),
    NAMOR(new NamorCharacter()),
    PENI_PARKER(new PeniParkerCharacter()),
    PSYLOCKE(new PsylockeCharacter()),
    ROCKET_RACCOON(new RocketRaccoonCharacter()),
    SCARLET_WITCH(new ScarletWitchCharacter()),
    SPIDER_MAN(new SpiderManCharacter()),
    SQUIRREL_GIRL(new SquirrelGirlCharacter()),
    STAR_LORD(new StarLordCharacter()),
    STORM(new StormCharacter()),
    THE_PUNISHER(new ThePunisherCharacter()),
    THOR(new ThorCharacter()),
    VENOM(new VenomCharacter()),
    WINTER_SOLDIER(new WinterSoldierCharacter()),
    WOLVERINE(new WolverineCharacter());

    private final CharacterInterface character;

    CharacterList(CharacterInterface character) {
        this.character = character;
    }

    public CharacterInterface getCharacterClass() {
        return character;
    }
}
