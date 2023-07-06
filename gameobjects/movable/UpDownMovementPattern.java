package thd.gameobjects.movable;

import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class UpDownMovementPattern extends MovementPattern {

    private final Position[] pattern;

    UpDownMovementPattern() {
        super();
        pattern = new Position[]{
                new Position(100, 150),
                new Position(200, 160),
                new Position(300, 150),
                new Position(400, 160),
                new Position(500, 150),
                new Position(400, 160),
                new Position(300, 150),
                new Position(200, 160)
        };
        currentIndex = -1;
    }

    @Override
    protected Position nextTargetPosition() {
        currentIndex++;
        if (currentIndex >= pattern.length) {
            currentIndex = 0;
        }
        return pattern[currentIndex];
    }

    @Override
    protected Position startPosition() {
        return nextTargetPosition();
    }
}
