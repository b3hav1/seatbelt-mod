## Seatbelt Mod
Client-server mod that prevents the player from dismounting a vehicle or seat until the configured key is pressed (`B` by default).

> **Note:** Although the mod doesn't work as originally intended, it works for its main purpose.

## Installation
Download mod from the [Releases](https://github.com/b3hav1/seatbelt-mod/releases) page. Then place the `.jar` file into your `mods` folder and make sure all clients have the mod installed.

> **Note:** I'm not sure on which side (client/both) is strictly required - feedback is welcome.

## Usage
1. Set your preferred key for **Misc → Seatbelt** in the keybinding menu.
2. Mount any vehicle and press key to fasten/unfasten the seatbelt.
3. While fastened, you cannot dismount the vehicle.

## Contributing
Feel free to fork, submit issues, or open pull requests. 
To build from source (assuming you have `git` installed):

```sh
git clone https://github.com/b3hav1/seatbelt-mod
cd seatbelt-mod
./gradlew build
```