# MultiPower

MultiPower is een Minecraft Spigot/Bukkit plugin die spelers elementaire krachten geeft. Spelers kunnen kiezen uit verschillende elementen (Vuur, Water, Aarde, Lucht) wanneer ze voor het eerst de server joinen, en krijgen speciale vaardigheden gebaseerd op hun keuze.

## Functies

- **Elementaire Krachten**: Spelers kunnen kiezen uit vier verschillende elementen, elk met unieke vaardigheden
- **Intuïtieve Bediening**: Gebruik rechtermuisklik, linkermuisklik en shift om verschillende vaardigheden te activeren
- **Eerste Join Detectie**: Nieuwe spelers krijgen automatisch een menu om hun kracht te kiezen
- **Persistente Data**: Spelergegevens worden opgeslagen, zodat ze hun krachten behouden na het opnieuw verbinden

## Elementen en Vaardigheden

### Vuur 🔥
- **Rechtermuisklik**: Schiet een vuurbal
- **Linkermuisklik**: Creëer vuur op afstand
- **Shift**: Maak een vuurcirkel rondom jezelf

### Water 💧
- **Rechtermuisklik**: Creëer water op afstand
- **Linkermuisklik**: Krijg een snelheidsboost in water
- **Shift**: Krijg de mogelijkheid om onder water te ademen

### Aarde 🌱
- **Rechtermuisklik**: Gooi met stenen
- **Linkermuisklik**: Creëer een stenen muur
- **Shift**: Krijg extra kracht en weerstand

### Lucht 💨
- **Rechtermuisklik**: Stoot vijanden weg met een windvlaag
- **Linkermuisklik**: Schiet vooruit met de wind
- **Shift**: Krijg tijdelijk de mogelijkheid om te vliegen

## Commando's

- `/power` - Toon je huidige kracht
- `/power menu` - Open het kracht selectie menu
- `/power reset` - Reset je kracht (vereist permissie: `multipower.reset`)
- `/power set <kracht>` - Stel direct een kracht in (vereist permissie: `multipower.admin`)

## Permissies

- `multipower.reset` - Sta toe om je kracht te resetten
- `multipower.admin` - Geef toegang tot admin commando's (inclusief reset)

## Installatie

1. Download het MultiPower.jar bestand
2. Plaats het in de plugins map van je Minecraft server
3. Start of herstart je server
4. De plugin zal automatisch de benodigde configuratiebestanden aanmaken

## Configuratie

Je kunt de plugin configureren door het `config.yml` bestand in de MultiPower map aan te passen:

```yaml
# Cooldowns voor abilities (in seconden)
cooldowns:
  fire:
    fireball: 5
    fire-circle: 30
  water:
    create-water: 3
    speed-boost: 15
    water-breathing: 60
  earth:
    throw-stone: 5
    create-wall: 20
    strength: 45
  air:
    push: 8
    dash: 5
    flight: 60

# Moet een speler een kracht kiezen bij het joinen?
force-power-selection: true

# Kunnen spelers van kracht veranderen?
allow-power-change: false
```

## Vereisten

- Spigot of Paper Minecraft server (1.16+)
- Java 8 of hoger

## Bijdragen

Bijdragen aan dit project zijn welkom! Voel je vrij om issues te melden of pull requests in te dienen.

## Licentie

Deze plugin is uitgebracht onder de MIT-licentie. Zie het LICENSE bestand voor meer informatie.
