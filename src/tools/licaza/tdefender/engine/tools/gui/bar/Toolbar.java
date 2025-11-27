// package licaza.tdefender.engine.tools.gui.bar;

// import java.awt.*;
// import java.awt.image.*;
// import java.util.List;
// import java.util.Map;
// import java.util.ArrayList;

// import licaza.tdefender.engine.tools.gui.MyButton;
// import licaza.tdefender.engine.commons.objects.Tile;
// import licaza.tdefender.engine.commons.misc.IntPoint2D;

// public final class Toolbar {
//     private final IntPoint2D pBackground, pDescription;
//     private final MyButton bMenu, bSave;
//     private final Graphics graphics;
//     private final Font font;
//     private final Color bgColor, fontColor;
//     private final int bgWidth, bgHeight, yOffset;
//     private final float fontSize;
//     private final List<TileButtonsRow> rows;
//     private final Map<MyButton, ArrayList<Tile>> tileButtonsMap;
//     private final BufferedImage pathStart, pathEnd;

//     // Refine
//     public void drawSelectedTile() {
//         if (selectedTile != null) {
//             graphics.drawImage(selectedTile.getSprite(), 550, 670, 50, 50, null);
//             graphics.setColor(Color.BLACK);
//             graphics.drawRect(550, 670, 50, 50);
//         }
//     }

//     // Refine
//     public void drawPathButton(MyButton b, BufferedImage img) {
//         graphics.drawImage(img, b.x, b.y, b.width, b.height, null);
//         drawButtonFeedback(g, b);
//     }

//     // Refine
//     public Map<MyButton, List<Tile>> getTileButtonsMap() {
//         return tileButtonsMap;
//     }

//     private Toolbar(final Builder b) {
//         this.graphics = b.graphics;

//         this.pBackground = b.pBackground;
//         this.pDescription = b.pDescription;

//         this.bMenu = b.bMenu;
//         this.bSave = b.bSave;

//         this.font = b.font;

//         this.tileDescriptorColor = b.tileDescriptorColor;
//         this.fontColor = b.fontColor;


//         this.bgWidth = b.bgWidth;
//         this.bgHeight = b.bgHeight;
//         this.yOffset = b.yOffset;

//         this.fontSize = b.fontSize;

//         this.rows = b.rows;

//         this.pathStart = b.pathStart;
//         this.pathEnd = b.pathEnd;
//     }

//             // getbuttons map methos here!

//     // private void drawSelectedTile() {
//     //     if (selectedTile != null) {
//     //         g.drawImage(selectedTile.getSprite(), 550, 670, 50, 50, null);
//     //         g.setColor(Color.BLACK);
//     //         g.drawRect(550, 670, 50, 50);
//     //     }
//     // }

//     private void drawPathButton(Graphics g, MyButton b, BufferedImage img) {
//         g.drawImage(img, b.x, b.y, b.width, b.height, null);
//         drawButtonFeedback(g, b);
//     }


//     // *** Builder pattern ***
//     private static final class Builder {
//         private IntPoint2D pBackground, pDescription;
//         private MyButton bMenu, bSave;
//         private Graphics graphics;
//         private Font font;
//         private Color bgColor, fontColor;
//         private int bgWidth, bgHeight, yOffset;
//         private float fontSize;
//         private List<TileButtonsRow> rows;
//         private BufferedImage pathStart, pathEnd;

//         public Builder(Graphics g) {
//             this.graphics = g;

//             this.rows = new ArrayList<Tile>();
//         }

//         public final Builder setTileDescriptor(IntPoint2D menuBtnPos, IntPoint2D saveBtnPos, Map colorMap,
//                                       int btnsWidth, int btnsHeight) {
//             // Background
//             graphics.setColor(bgColor);
//             graphics.fillRect(bgPos.x(), bgPos.y(), bgWidth, bgHeight);

//             // Description
//             graphics.setFont(HEADER_FONT.deriveFont(fontSize));
//             graphics.setColor(fontColor);
//             graphics.drawString("description...", descriptionPos.x(), descriptionPos.y());

//             return this;
//         }

//         public Builder setColors(Color tileDescriptorBg, Color fontColor) {
//             this.tileDescriptorColor = tileDescriptorColor;
//             this.fontColor = fontColor;

//             return this;
//         }

//         public Builder setMenuButtons(MyButton bMenu, MyButton bSave) {
//             this.bMenu = bMenu;
//             this.bSave = bSave;

//             return this;
//         }

//         public Builder setTileButtonsRow(TileButtonsRow tbr) {
//             rows.add(tbr);

//             return this;
//         }

//         public Builder setRowsYOffset(int offset) {
//             this.yOffset = offset;

//             return this;
//         }

//         public Builder setPathMarkers(BufferedImage start, BufferedImage end) { // setEnemiesPathMarker?
//             this.pathStart = start;
//             this.pathEnd = end;

//             return this;
//         }

//         public Toolbar build() {
//             return new Toolbar(this);
//         }
//     }
// }
