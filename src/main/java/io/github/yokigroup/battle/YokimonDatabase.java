package io.github.yokigroup.battle;

import java.util.HashMap;
import java.util.Map;

/**
 * Database used for test classes
 */
public class YokimonDatabase {
    Attack shadowBall = new AttackImpl("Shadow ball", Color.BLACK, 90, Attack.effect.NONE);
    Attack strongPunch = new AttackImpl("strong punch", Color.RED, 80, Attack.effect.NONE);
    Attack curse = new AttackImpl("curse", Color.PURPLE, 70, Attack.effect.NONE);
    Attack slap = new AttackImpl("slap", Color.WHITE, 40, Attack.effect.NONE);
    Attack flameThrow = new AttackImpl("flame throw", Color.RED, 95, Attack.effect.NONE);
    Attack headBut = new AttackImpl("headBut", Color.WHITE, 50, Attack.effect.NONE);
    Attack tailSwipe = new AttackImpl("TailSwipe ", Color.WHITE, 60 , Attack.effect.NONE);
    //future effect
    Attack whirlpool = new AttackImpl("whirlpool ", Color.BLACK, 20 , Attack.effect.NONE);
    Attack bite = new AttackImpl("bite ", Color.BLACK, 60, Attack.effect.NONE);
    //effect
    Attack revenge = new AttackImpl("revenge ", Color.RED, 60 , Attack.effect.NONE);
    Attack confusion = new AttackImpl("confusion", Color.PURPLE, 50, Attack.effect.NONE);
    //effect
    Attack illusion = new AttackImpl("Illusion", Color.PURPLE, 0, Attack.effect.NONE);
    //works only if one or more of the yokimon other team is dead (for nekomata)
    Attack soulAttack = new AttackImpl("Soul Attack", Color.BLACK, 100, Attack.effect.NONE);
    Attack scratch = new AttackImpl("scratch", Color.WHITE, 40 , Attack.effect.NONE);
    //regenerate half of the damage (for baku)
    Attack dreamEater = new AttackImpl("dreamEater", Color.PURPLE, 60, Attack.effect.NONE);
    //only for kitsune
    Attack nineTailSmash = new AttackImpl("nineTailSmash", Color.WHITE, 140, Attack.effect.NONE);
    //effect
    Attack transformation = new AttackImpl("transformation", Color.PURPLE, 0 , Attack.effect.NONE);

    Map<Integer, Attack> stone= new HashMap<>();
    Map<Yokimon.Stats, Integer> baseStats= new HashMap<>();

    private void init() {

        stone.put(1, slap);
        stone.put(3, strongPunch);
        stone.put(7, curse);
        stone.put(15, shadowBall);

        baseStats.put(Yokimon.Stats.HP, 90);
        baseStats.put(Yokimon.Stats.ATK, 80);
        baseStats.put(Yokimon.Stats.DEF, 100);
        baseStats.put(Yokimon.Stats.SPD, 40);
    }

   public Yokimon getOni() {
       init();
       return new YokimonImpl("oni", Color.RED, baseStats, Yokimon.GrowthRate.MEDIUM, 1, stone );
   }

   public Yokimon getNekomata() {
        init();
        return new YokimonImpl("nekomata", Color.PURPLE, baseStats, Yokimon.GrowthRate.MEDIUM, 1, stone );
   }

   public Yokimon getBaku() {
        init();
       return new YokimonImpl("baku", Color.PURPLE, baseStats, Yokimon.GrowthRate.MEDIUM, 1, stone );
   }

   public Yokimon getKitsune() {
        init();
       return new YokimonImpl("kitsune", Color.WHITE, baseStats, Yokimon.GrowthRate.MEDIUM, 1, stone );
   }

   public Yokimon getTanuki() {
        init();
       return new YokimonImpl("tanuki", Color.WHITE, baseStats, Yokimon.GrowthRate.MEDIUM, 1, stone );
   }


}
