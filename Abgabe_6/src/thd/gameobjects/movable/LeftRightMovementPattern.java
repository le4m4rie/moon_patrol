package thd.gameobjects.movable;

import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class LeftRightMovementPattern extends MovementPattern {
    private final Position[] pattern;

    LeftRightMovementPattern() {
        super();
        pattern = new Position[]{
                new Position(50, 50),
                new Position(150, 60),
                new Position(250, 40),
                new Position(350, 50),
                new Position(450, 60),
                new Position(550, 50),
                new Position(450, 50),
                new Position(300, 100),
                new Position(150, 50)};
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
