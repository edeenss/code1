package main;
import java.util.Random;
import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("RPG 턴제 게임");
        // 무기 설정
        Weapon[] weapons = {
            new Weapon("나무 검", 5),
            new Weapon("환상의 검", 20),
            new Weapon("전설의 검", 40)
        };

        // 플레이어 설정
        System.out.print("플레이어 이름을 입력하세요: ");
        String playerName = sc.nextLine();
        System.out.println("무기를 선택하세요:");
        for (int i = 0; i < weapons.length; i++) {
            System.out.println((i + 1) + ". " + weapons[i].name + " (공격력: " + weapons[i].damage + ")");
        }

        int choice = sc.nextInt();
        if (choice < 1 || choice > weapons.length) choice = 1;
        Weapon selectedWeapon = weapons[choice - 1];
        Character player = new Character(playerName, 150, selectedWeapon.damage);
        Character monster = new Character("슬라임", 100, 10);

        boolean isDefending = false;  // 방어 상태 저장

        System.out.println("\n=== 전투 시작 ===");

        while (player.isAlive() && monster.isAlive()) {
            System.out.println("\n[" + player.name + " HP: " + player.hp + " | 물약: " + player.potions + "개]");
            System.out.println("[" + monster.name + " HP: " + monster.hp + "]");

            System.out.println("\n행동을 선택하세요:");
            System.out.println("1. 공격");
            System.out.println("2. 물약 사용");
            System.out.println("3. 도망");
            System.out.println("4. 방어");

            int action = sc.nextInt();
            isDefending = false; //

            if (action == 1) {
                System.out.println(player.name + " 용사의 공격!");
                monster.hp -= player.damage;
                if (monster.hp < 0) monster.hp = 0;
                System.out.println(monster.name + " HP: " + monster.hp);
            } else if (action == 2) {
                player.usePotion();
            } else if (action == 3) {
                boolean escape = rand.nextBoolean(); // 50% 확률
                if (escape) {
                    System.out.println(" 도망에 성공했습니다! 전투 종료.");
                    break;
                } else {
                    System.out.println(" 도망 실패! 몬스터의 공격이 계속 됩니다ㄴ!");
                }
            } else if (action == 4) {
                isDefending = true;
                System.out.println("방어 자세를 취함  다음 공격 피해가 절반으로 줄어듭니다.");
            } else {
                System.out.println("잘못된 선택입니다. 턴을 소모했습니다.");
            }

            // 몬스터 턴
            if (monster.isAlive()) {
                System.out.println(monster.name + "이(가) 공격합니다!");
                int damage = monster.damage;
                if (isDefending) {
                    damage = damage / 2;
                }
                player.hp -= damage;
                if (player.hp < 0) player.hp = 0;
                System.out.println(player.name + " HP: " + player.hp + " (이번 턴 피해: " + damage + ")");
            }

            // 종료 조건
            if (!player.isAlive()) {
                System.out.println("\n " + player.name + "이(가) 쓰러졌습니다. 게임 오버!");
                break;
            } else if (!monster.isAlive()) {
                System.out.println("\n " + monster.name + "을(를) 쓰러뜨렸습니다! 승리!");
                break;
            }
        }

        System.out.println("\n=== 게임 끝 ===");
    }
}
		