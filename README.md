# TopMiners

## About
TopMiners creates a leaderboard to display players who have broken the most blocks
- Minecraft 1.18+
- Uses Java Version 17+
- Currently, the leaderboard is limited to 10 slots. Keep this in mind if you are using **PlaceHolders** with **PlaceHolderAPI**

## Commands
- /topm - General help command(Player only)
- /topm help - Aliases of /topm(Player only)
- /topm top - View the leaderboard(Player only)
- /topm stats - View self stats of blocks broken(Player only)
- /topm reload - Reload of config files(Console & Player)

## Permissions
- topminers.help - Usage of "/topm" aswell as any aliases for it
- topminers.top - Usage of "/topm top"
- topminers.stats - Usage of "/topm stats"
- topminers.reload - Usage of "/topm reload"

## PlaceHolderAPI
#### Optional dependency, must have [PlaceHolderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) installed to use.
#### Version 1.0.1 Introduces A PlaceHolder Expansion
- %topminers_blocksbroken% - Returns the amount of blocks broken by a player
  - Usage - %topminers_blocksbroken%
- %topminers_top_leaderboardSpot% - Returns a string of the leaderboard spot(Player - BlocksBroken).  
  - Usage - %topminers_top_10%

## Future Plans
- Make leaderboard infinite with multiple pages, page size being determined by Config.YML
