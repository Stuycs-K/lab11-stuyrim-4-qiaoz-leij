[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/KprAwj1n)
# APCS - Stuyrim

## Features

:white_check_mark: This feature works.

:question: This feature works partially.

:ballot_box_with_check: This extra (beyond the things that the lab was supposed to do) feature works.

:x: This required feature does not work.

:beetle: This is a bug that affects the game.

1. Generating 3 random adventurers and enemies :white_check_mark:
   - Generate random names from a preset list :ballot_box_with_check:
2. Adventurer attack, support, special :white_check_mark:
3. Enemy attack, support, special :white_check_mark:
   - Unique support for Dire Wolf, general support for all enemies :ballot_box_with_check:
4. Prompt user for command :white_check_mark:
   - Ensure commands are valid :white_check_mark:
5. Display wrapping results in box after each action :white_check_mark:
   - Action history, scrolling text within the box :ballot_box_with_check:
6. Quit when one team wins or when quit command is typed :white_check_mark:
7. Adventurers and enemies have vulnerabilities and resistances to certain attack types :ballot_box_with_check:
8. Actions can apply status effects :ballot_box_with_check:

## Adventurer Subclasses

## Monk

Resistant To: Bludgeoning, Psychic :ballot_box_with_check:

Special Resource: Ki Points :white_check_mark:

Starting Stats: 2 Ki Points, 30 HP :white_check_mark:

Actions

| Category       | Name            | Action | Description | Status |
| :------------- | :-------------- | :----- | :---------- | :----- |
| Attack         | Strike          | Deals 1d8 Bludgeoning Damage, 50% chance of restoring a Ki Point | The Monk punches someone | :white_check_mark: |
| Special Attack | Flurry of Blows | Spends 1 Ki Point, Deals 3d8 Bludgeoning Damage | The Monk strikes someone three times in quick succession | :white_check_mark: |
| Support        | Patient Defense | Spends 1 Ki Point, Applies 5 Block to all allies | The Monk prepares for any attacks | :white_check_mark: |

## Bard

Vulnerable To: Bludgeoning :ballot_box_with_check:

Resistant To: Psychic :ballot_box_with_check:

Special Resource: Spell Slots :white_check_mark:

Starting Stats: 4 Spell Slots, 25 HP :white_check_mark:

Actions

| Category       | Name               | Action | Description | Status |
| :------------- | :----------------- | :----- | :---------- | :----- |
| Attack         | Cutting Insult     | Deals 1d8 Psychic Damage, Gives disadvantage on the next attack roll | The Bard mocks a enemy | :white_check_mark: |
| Special Attack | Dissonant Whispers | Spends 1 Spell Slot, Deals 3d6 Psychic Damage | The Bard makes an enemy hear strange whispers | :white_check_mark: |
| Support        | Bardic Inspiration | Spends 1 Spell Slot, Applies Inspired for 1 turn | The Bard inspires an ally | :white_check_mark: |


## Sorcerer

Vulnerable To: Bludgeoning, Psychic :ballot_box_with_check:

Resistant To: Fire, Cold, Lightning :ballot_box_with_check:

Special Resource: Sorcerer Points :white_check_mark:

Starting Stats: 5 Sorcerer Points, 20 HP :white_check_mark:

Actions

| Category       | Name          | Action | Description | Status |
| :------------- | :------------ | :----- | :---------- | :----- |
| Attack         | Chromatic Orb | Deals 2d8 damage of a random elemental type (Cold, Fire, Lightning) | The Sorcerer hurls a ball of elemental magic | :white_check_mark: |
| Special Attack | Fireball      | Spends 2 Sorcerer Points, Deals 1d8 Fire Damage to all enemies | The Sorcerer hurls a ball of fire that explodes on impact | :white_check_mark: |
| Support        | Panacea       | Spends 1 Sorcerer Point, Heals all allies by 1d8 | The Sorcerer emits a stream of healing magic | :white_check_mark: |



## Enemy Subclasses

## Dire Wolf

Special Resource: Bloodlust :white_check_mark:

Starting Stats: 2 Bloodlust, 25 HP :white_check_mark:

Actions

| Category       | Name   | Action | Description | Status |
| :------------- | :----- | :----- | :---------- | :----- |
| Support        | Howl   | Applies Strengthened to all Dire Wolves, Adds 1 Bloodlust | The Dire Wolf howls | :white_check_mark: |
| Attack         | Bite   | Deals 1d6 Piercing Damage, Applies Bleeding for 2 turns | The Dire Wolf bites into an adventurer | :white_check_mark: |
| Special Attack | Pounce | Spends 4 Bloodlust, Deals 2d6 Piercing Damage and applies Prone for 1 turn | The Dire Wolf pounces on an adventurer and bites into them | :white_check_mark: |

## Spider Swarm

Vulnerable To: Fire :ballot_box_with_check:

Resistant To: Bludgeoning :ballot_box_with_check:

Special Resource: Webbing :white_check_mark:

Starting Stats: 1 Webbing, 20 HP :white_check_mark:

Actions

| Category       | Name     | Action | Description | Status |
| :------------- | :------- | :----- | :---------- | :----- |
| Attack         | Bite     | Deals 1d6 Piercing Damage, Applies Poisoned for 3 turns, and Adds 1 Webbing | The swarm of spiders bite into an adventurer | :white_check_mark: |
| Special Attack | Web Trap | Spends 3 Webbing, Applies Paralyzed for 2 turns | The swarm of spiders spin a web to trap an adventurer | :white_check_mark: |

## Specter

Vulnerable To: Psychic :ballot_box_with_check:

Resistant To: Bludgeoning, Fire, Cold, Lightning :ballot_box_with_check:

Special Resource: Ghastly Points :white_check_mark:

Starting Stats: 0 Ghastly Points, 40 HP :white_check_mark:

Actions

| Category       | Name                | Action | Description | Status |
| :------------- | :------------------ | :----- | :---------- | :----- |
| Attack         | Haunt               | Deals 2d10 Psychic Damage, Gain 1 Ghastly Point | The Specter haunts an adventurer, spooking them | :white_check_mark: |
| Attack         | Sensory Deprivation | Applies Deaf and Blind for 2 turns, Gains 2 Ghastly Points | The Specter rids an adventurer's senses | :white_check_mark: |
| Special Attack | True Fear           | Spends 5 Ghastly Points, Deals 3d10 Psychic Damage, Applies Paralyzed for 3 turns | The Specter overwhelms an adventurer's mind | :white_check_mark: |

## Note

Powered by the corrupted God of Nature, all Enemies have the following support ability:

Cursed Blessing: Either heals or loses 2d6 HP :white_check_mark:

# Conditions
| Name         | Effect                                                       | Status |
| :----------- | :----------------------------------------------------------- | :----- |
| Inspired     | Gives Advantage on all attack rolls for a turn               | :ballot_box_with_check: |
| Bleeding     | Has a 50% chance to take 1d8 Piercing Damage at the end turn | :ballot_box_with_check: |
| Poisoned     | Takes 1d4 Acid Damage at the end of each turn                | :ballot_box_with_check: |
| Paralyzed    | Skips the next turn                                          | :ballot_box_with_check: |
| Strengthened | Attacks deal an additional 1d6 Damage                        | :ballot_box_with_check: |
| Deaf         | Negates the effect of Inspired                               | :ballot_box_with_check: |
| Blind        | Gives Disadvantage on all attack rolls for a turn            | :ballot_box_with_check: |
| Block        | Spent to reduce the damage of incoming attacks               | :ballot_box_with_check: |
