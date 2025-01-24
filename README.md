# Simple 3D Game Engine

Ich hab mich schon seit dem ich klein bin gefragt, wie Game Engines und vor
allem 3d Engines funktionieren. Eine große Inspiration dafür war schon immer
Minecraft, da es einer meiner liebsten Spiele aller Zeiten ist. In den letzten 
Jahren begann ich mich jedoch für die Doom Engine zu interessieren. Doom kann
überall laufen und das zeigt die Community auch immer wieder mit erstaunlichen
Projekten. Doom benutzt dafür eine sogennante Raycast Engine. In diesem
Repository möchte ich versuchen, eine ähnliche Engine zu bauen ohne Hilfsmittel
wie OpenGL oder Vulkan.

## Game Klasse

Die [Game.java](src/main/java/org/tobii/game/Game.java) Klasse ist die Klasse, in
der Sachen wie die Map oder die Game Loop enthalten sind. Die Klasse vererbt "JFrame"
da wir damit unser Fenster erstellen wollen. Dazu extended die Klasse "Runnable" da
solche Engines Single Threaded laufen und die [Game.java](src/main/java/org/tobii/game/Game.java)
Klasse diesen Thread enthält.

```java
private Thread gameThread;
private boolean gameRunning = true;
private BufferedImage bufferedImage;
public int[] pixel;
```

Wie schon angesprochen enthält unsere Klasse den `gameThread` und dazu noch eine `gameRunning` Variable
welche uns die Information gibt, ob das Spiel laufen soll oder nicht. Das BufferedImage
wird benötigt um unser Bild zu rendern. Ein `BufferedImage` speichert die Bilddaten als ein 2D Raster von Pixeln.
Jeder Pixel verfügt über eigene Farbwerte. Der Vorteil ist, dass das Bild vollständig im Speicher
geladen ist und somat schnelle Manipulationen an den Daten im Bild durchgeführt werden können.

Ein `BufferedImage` kann verschiedene Typen haben:
* `BufferedImage.TYPE_BYTE_GRAY`: Graustufenbild
* `BufferedImage.TYPE_INT_RGB`: RGB-Bild
* `BufferedImage.TYPE_INT_ARGB`: ARGB-Bild mit Transparenz


Das Array `pixel` speichert die Farbwerte von jedem einzelnen Pixel in dem Spiel. Somit kann jeder Bereich
schnell und effizient bearbeitet werden. In diesem Spiel verwenden wir `BufferedImage.TYPE_INT_RGB` somit
werden Rot, Grün und Blau werte ohne Transparenz gespeichert. (Format: 0xRRGGBB)

