package org.randomlima.minevelcraftvels.Characters;

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
    THE_PUNISHER(new PunisherCharacter()),
    THOR(new ThorCharacter()),
    VENOM(new VenomCharacter()),
    WINTER_SOLDIER(new WinterSoldierCharacter()),
    WOLVERINE(new WolverineCharacter());

    private final ICharacter character;

    CharacterList(ICharacter character) {
        this.character = character;
    }

    public ICharacter getCharacterClass() {
        return character;
    }
}
