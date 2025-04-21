package main;

	
class Character {
	String name;
    int hp;
    int maxHp;
    int damage;
    int potions;

    Character(String name, int hp, int damage) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.damage = damage;
        this.potions = 3;  // 기본 물약 개수
    }

    boolean isAlive() {
        return hp > 0;
    }

    public void usePotion() {
        if (potions > 0) {
            hp += 30;
            if (hp > maxHp) hp = maxHp;
            potions--;
            System.out.println(" 물약을 사용했습니다! HP 회복 (남은 물약: " + potions + ")");
        } else {
            System.out.println(" 물약이 없습니다!");
        }
    }
}
