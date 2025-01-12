package at.aau.serg.exercises.gamelogic;

public class Character {
    public String name;
    public Integer level;

    /**
     * Changed from string to enum.
     * Allowed: MAGE, TANK, DAMAGE_DEALER
     */
    public CharacterClass characterClass;

    public double damage;

    /**
     * Used for range and magic damage types, melee ignores this value
     */
    public int damageRange;

    /**
     * Changed from short to enum.
     * Allowed: MELEE, RANGE, MAGIC
     */
    public DamageType damageType;

    /**
     * None (null), fire, poison, pierce
     */
    public String damageEffect;

    /**
     * Indicates if a tank currently has their shield equipped
     */
    public boolean hasShieldEquipped;

    /**
     * Available mana of mage
     */
    public int mana;

    public String getDescription() {
        switch (characterClass) {
            case MAGE:
                return "Mage: A spellcaster who wields powerful magic.";
            case TANK:
                return "Tank: A sturdy protector who absorbs damage and defends allies.";
            case DAMAGE_DEALER:
                return "Damage Dealer: A swift attacker who deals heavy damage.";
            default:
                throw new IllegalStateException("Unexpected character class: " + characterClass);
        }
    }

    public boolean hasMaxLevel() {
        return level.equals(30);
    }
}