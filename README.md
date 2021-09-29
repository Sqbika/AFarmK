# AFarmK
Keybind Toggler for Fabric Minecraft 1.17.1

Are you tired of holding \<Button\> and \<Another Button\>? Then this mod is for you. Maybe.

Mod uses keypad buttons for default, but you can change it in controls

# This is a client-side only mod. Putting it on server won't make your server die, but it'll just sit there and exist.

If you want me to add another button, just do an Issue, or better, a PR :D

## Config file

Config has been reworked into a more dynamic, less restrictive format.

New example config:

```json
{
    "enableIndividual": true,
    "profiles": [
        {
            "name": "Sajt",
            "buttons": [
              "SHIFT", "LEFT_CLICK", "RIGHT_CLICK", "FORWARD", "BACKWARD", "LEFT", "RIGHT", "JUMP"
            ],
            "profileKeybind":  []
        }
    ]
}
```
