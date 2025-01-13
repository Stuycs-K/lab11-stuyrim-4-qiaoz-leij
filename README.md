[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/KprAwj1n)
# APCS - Stuyrim

## Features

Make a clear list of features that work/dont work

:white_check_mark: This feature works.

:question: This feature works partially.

:ballot_box_with_check: This extra (beyond the things that the lab was supposed to do) feature works.

:x: This required feature does not work.

:beetle: This is a bug that affects the game.


## Adventurer Subclasses

## Monk

Resistant To: Bludgeoning, Psychic

Special Resource: Ki Points

Starting Stats: 2 Ki Points, 30 HP

Actions

| Category       | Name            | Action | Description |
| :------------- | :-------------- | :----- | :---------- |
| Attack         | Strike          | Deals 1d8 Bludgeoning Damage, 50% chance of restoring a Ki Point | The Monk punches someone |
| Special Attack | Flurry of Blows | Spends 1 Ki Point, Deals 3d8 Bludgeoning Damage | The Monk strikes someone three times in quick succession |
| Support        | Patient Defense | Spends 1 Ki Point, Applies 5 Block to all allies | The Monk prepares for any attacks |

## Bard

Vulnerable To: Bludgeoning

Resistant To: Psychic

Special Resource: Spell Slots

Starting Stats: 4 Spell Slots, 25 HP

Actions

| Category       | Name               | Action | Description |
| :------------- | :----------------- | :----- | :---------- |
| Attack         | Cutting Insult     | Deals 1d8 Psychic Damage, Gives disadvantage on the next attack roll | The Bard mocks a enemy |
| Special Attack | Dissonant Whispers | Spends 1 Spell Slot, Deals 3d6 Psychic Damage | The Bard makes an enemy hear strange whispers |
| Support        | Bardic Inspiration | Spends 1 Spell Slot, Applies Inspired for 1 turn | The Bard inspires an ally |


## Sorcerer

Vulnerable To: Bludgeoning, Psychic

Resistant To: Fire, Cold, Lightning

Special Resource: Sorcerer Points

Starting Stats: 5 Sorcerer Points, 20 HP

Actions

| Category       | Name          | Action | Description |
| :------------- | :------------ | :----- | :---------- |
| Attack         | Chromatic Orb | Deals 2d8 damage of a random elemental type (Cold, Fire, Lightning) | The Sorcerer hurls a ball of elemental magic |
| Special Attack | Fireball      | Spends 2 Sorcerer Points, Deals 1d8 Fire Damage to all enemies | The Sorcerer hurls a ball of fire that explodes on impact |
| Support        | Panacea       | Spends 1 Sorcerer Point, Heals all allies by 1d8 | The Sorcerer emits a stream of healing magic |



## Enemy Subclasses

## Dire Wolf

Special Resource: Bloodlust

Starting Stats: 2 Bloodlust, 25 HP

Actions

| Category       | Name   | Action | Description |
| :------------- | :----- | :----- | :---------- |
| Support        | Howl   | Applies Strengthened to all Dire Wolves, Adds 1 Bloodlust | The Dire Wolf howls |
| Attack         | Bite   | Deals 1d6 Piercing Damage, Applies Bleeding for 2 turns | The Dire Wolf bites into an adventurer |
| Special Attack | Pounce | Spends 4 Bloodlust, Deals 2d6 Piercing Damage and applies Prone for 1 turn | The Dire Wolf pounces on an adventurer and bites into them |

## Spider Swarm

Vulnerable To: Fire

Resistant To: Bludgeoning

Special Resource: Webbing

Starting Stats: 1 Webbing, 20 HP

Actions

| Category       | Name     | Action | Description |
| :------------- | :------- | :----- | :---------- |
| Attack         | Bite     | Deals 1d6 Piercing Damage, Applies Poisoned for 3 turns, and Adds 1 Webbing | The swarm of spiders bite into an adventurer |
| Special Attack | Web Trap | Spends 3 Webbing, Applies Paralyzed for 2 turns | The swarm of spiders spin a web to trap an adventurer |

## Specter

Vulnerable To: Psychic

Resistant To: Bludgeoning, Fire, Cold, Lightning

Special Resource: Ghastly Points

Starting Stats: 0 Ghastly Points, 40 HP

Actions

| Category       | Name                | Action | Description |
| :------------- | :------------------ | :----- | :---------- |
| Attack         | Haunt               | Deals 2d10 Psychic Damage, Gain 1 Ghastly Point | The Specter haunts an adventurer, spooking them |
| Attack         | Sensory Deprivation | Applies Deaf and Blind for 2 turns, Gains 2 Ghastly Points | The Specter rids an adventurer's senses |
| Special Attack | True Fear           | Spends 5 Ghastly Points, Deals 3d10 Psychic Damage, Applies Paralyzed for 3 turns | The Specter overwhelms an adventurer's mind |

## Note

Powered by the corrupted God of Nature, all Enemies have the following support ability:

Cursed Blessing: Either heals or loses 2d6 HP

# Conditions
| Name         | Effect                                                       |
| :----------- | :----------------------------------------------------------- |
| Inspired     | Gives Advantage on all attack rolls for a turn               |
| Bleeding     | Has a 50% chance to take 1d8 Piercing Damage at the end turn |
| Poisoned     | Takes 1d4 Acid Damage at the end of each turn                |
| Paralyzed    | Skips the next turn                                          |
| Strengthened | Attacks deal an additional 1d6 Damage                        |
| Deaf         | Negates the effect of Inspired                               |
| Blind        | Gives Disadvantage on all attack rolls for a turn            |
| Block        | Spent to reduce the damage of incoming attacks               |
