package core;

import map.MapSegment;

public interface IMapSegmentVisitor {
    void visit(MapSegment segment, IRobot robot, ICommand command);
}
