package licaza.tdefender.engine.tools.math;

import licaza.tdefender.engine.commons.objects.PathPoint;

import static licaza.tdefender.engine.commons.misc.Constants.Directions.*;
import static licaza.tdefender.engine.commons.misc.Constants.Tiles.*;

public class PathFinding {

    public static int[][] GetRoadDirectionArray(int[][] lvlTypeArr, PathPoint start, PathPoint end) {
        int[][] roadDirectionArray = new int[lvlTypeArr.length][lvlTypeArr[0].length];

        PathPoint currentTile = start;
        int lastDirection = -1;

        // TODO: Think of making tiles 'Comparable'
        while (!IsCurrentTileSameAsEndTile(currentTile, end)) {
            PathPoint previousTile = currentTile;
            currentTile = GetNextRoadTile(previousTile, lastDirection, lvlTypeArr);
            lastDirection = GetDirectionFromPreviousTileToCurrentTile(
                    previousTile, currentTile);

            roadDirectionArray[previousTile.yCord()][previousTile.xCord()] = lastDirection;
        }
        roadDirectionArray[end.yCord()][end.xCord()] = lastDirection;

        return roadDirectionArray;
    }

    private static PathPoint GetNextRoadTile(PathPoint previousTile, int lastDirection,
            int[][] lvlTypeArr) {
        int testDirection = lastDirection;
        PathPoint testTile = GetTileInDirection(previousTile, testDirection, lastDirection);

        while (!IsTileRoad(testTile, lvlTypeArr)) {
            testDirection++;

            // Cannot be more than 4
            testDirection %= 4;

            testTile = GetTileInDirection(previousTile, testDirection, lastDirection);
        }

        return testTile;
    }

    private static PathPoint GetTileInDirection(PathPoint previousTile, int testDirection,
            int lastDirection) {
        return switch (testDirection) {
            case LEFT -> {
                yield (lastDirection != RIGHT) ? new PathPoint(previousTile.xCord() - 1, previousTile.yCord()) : null;
            }
            case UP -> {
                yield (lastDirection != DOWN) ? new PathPoint(previousTile.xCord(), previousTile.yCord() - 1) : null;
            }
            case RIGHT -> {
                yield (lastDirection != LEFT) ? new PathPoint(previousTile.xCord() + 1, previousTile.yCord()) : null;
            }
            case DOWN -> {
                yield (lastDirection != UP) ? new PathPoint(previousTile.xCord(), previousTile.yCord() + 1) : null;
            }
            default -> null;
        };
    }

    private static boolean IsCurrentTileSameAsEndTile(PathPoint currentTile, PathPoint end) {
        if (currentTile.xCord() == end.xCord())
            if (currentTile.yCord() == end.yCord())
                return true;

        return false;
    }

    private static boolean IsTileRoad(PathPoint testTile, int[][] lvlTypeArr) {
        if (testTile != null)
            if (testTile.yCord() >= 0)
                if (testTile.yCord() < lvlTypeArr.length)
                    if (testTile.xCord() >= 0)
                        if (testTile.xCord() < lvlTypeArr[0].length)
                            if (lvlTypeArr[testTile.yCord()][testTile.xCord()] == ROAD_TILE)
                                return true;

        return false;
    }

    private static int GetDirectionFromPreviousTileToCurrentTile(PathPoint previousTile,
            PathPoint currentTile) {
        // Up or down
        if (previousTile.xCord() == currentTile.xCord()) {
            if (previousTile.yCord() > currentTile.yCord())
                return UP;
            else
                return DOWN;
            // Right or left
        } else {
            if (previousTile.xCord() > currentTile.xCord())
                return LEFT;
            else
                return RIGHT;
        }
    }
}
