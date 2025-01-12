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
     * every character has it's damageEffect
     */
    public String damageEffect;

    /**
     * Indicates if a tank currently has their shield equipped
     */
    public boolean hasShieldEquipped;

    /**
     * Available mana (for mages)
     */
    public int mana;

    // Getter und Setter for every field
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public int getDamageRange() {
        return damageRange;
    }

    public void setDamageRange(int damageRange) {
        this.damageRange = damageRange;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public String getDamageEffect() {
        return damageEffect;
    }

    public void setDamageEffect(String damageEffect) {
        this.damageEffect = damageEffect;
    }

    public boolean isHasShieldEquipped() {
        return hasShieldEquipped;
    }

    public void setHasShieldEquipped(boolean hasShieldEquipped) {
        this.hasShieldEquipped = hasShieldEquipped;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

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

    //  Replace magic number with MAX_LEVEL constant
    private static final int MAX_LEVEL = 30;

    public boolean hasMaxLevel() {
        return level.equals(MAX_LEVEL);
    }
}