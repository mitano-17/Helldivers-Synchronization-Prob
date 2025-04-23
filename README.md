# Helldivers-Synchronization-Prob
A major course output simulating process synchronization. 
## Scenario
The Ministry of Defense (MoD) has initiated a crucial operation to counter the invasion of Automatons and Terminids threatening Super Earth. As part of this operation, elite soldiers (i.e. Helldivers) are deployed on missions and engage in intense battles against the enemy forces.

The recent ~propaganda~ information dissemination efforts by the MoD have resulted in a sharp increase in patriotism -- leading to massive numbers of citizen signups. The upper command is concerned with the quality of the recruits while hoping to send out Helldivers as soon as possible. Democracy must continue to spread! Hence, to ensure the success of each mission, all succeeding missions must have the following:
1. Each mission must consist of exactly 4 Helldivers
2. Among the 4 Helldivers, there must be at least 1 "Super Citizen," an elite member highly skilled in combat and tactics
3. Missions can accommodate up to 2 Super Citizens at most
4. Regular Citizens, though not as highly skilled as Super Citizens, are still valuable assets and must compose the remaining slots in the team.
5. Mission signups are on a first-come, first-served basis, so if a certain type of citizen builds up a queue, they must wait until they can be served (headquarters can only do so much...)
6. Once the team has been properly composed, the Helldivers are directly launched into their mission
7. If a team cannot be formed (e.g. only 2 Regular Citizens and 1 Super Citizen are left), then the remaining citizens are sent home

## Task
Your task is to develop a synchronization mechanism to ensure that teams are assembled correctly according to the rules described above. Your solution should not result in deadlock or starvation and should exit only when teams cannot be formed and/or there are no more citizens left.

## Input
The program accepts two inputs from the user, described as follows:
1. `r` – the number of Regular Citizens
2. `s` – the number of Super Citizens

There should be minimal program interaction. The program will ask the user to input the values for `r` and `s`.

## Output
Given that thread execution may vary per execution, it is possible that any sample output would not align with your output. Hence, utilize the following rules as a guide for producing output:
- When a Regular Citizen is signing up, display `Regular Citizen <rc_id> is signing up`
- When a Super Citizen is signing up, display `Super Citizen <sc_id> is signing up`
- When any Citizen joins a team, display `<citizen_type> <citizen_id> has joined team <team_id>`
- When a team is properly composed and ready to launch, display `team <team_id> is ready and now launching to battle (sc: <super_count> | rc: <regular_count>)`
- Once all teams have been launched and/or there are not enough citizens to form teams, the program should display the total teams sent and the number of Regular and Super Citizens that were not sent off


<h2>💌 Credits ✉️</h2>
This project is done by <b>ERMITANO, Kate Justine, KHO, Denise Jilian, URETA, Therese</b>, and <b>YU, Beverly Kate</b> as a requirement to pass CSOPESY under the instructions of <b>Dr Edward Tighe</b>, submitted on April 13, 2024.
