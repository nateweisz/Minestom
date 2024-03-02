package net.minestom.server.entity.pathfinding;

import net.minestom.server.coordinate.Point;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class PPath {
    private final Runnable onComplete;
    private final List<PNode> nodes = new ArrayList<>();

    private final double pathVariance;
    private final double maxDistance;
    private int index = 0;
    private final AtomicReference<PathState> state = new AtomicReference<>(PathState.CALCULATING);

    public Point getNext() {
        if (index + 1 >= nodes.size()) return null;
        var current = nodes.get(index + 1);
        return current.point();
    }

    public void setState(PathState newState) {
        state.set(newState);
    }

    enum PathState {
        CALCULATING,
        FOLLOWING,
        TERMINATING, TERMINATED, COMPUTED, BEST_EFFORT, INVALID
    }

    PathState getState() {
        return state.get();
    }

    public List<PNode> getNodes() {
        return nodes;
    }

    public PPath(double maxDistance, double pathVariance, Runnable onComplete) {
        this.onComplete = onComplete;
        this.maxDistance = maxDistance;
        this.pathVariance = pathVariance;
    }

    void runComplete() {
        if (onComplete != null) onComplete.run();
    }

    @Override
    public String toString() {
        return nodes.toString();
    }

    PNode.NodeType getCurrentType() {
        if (index >= nodes.size()) return null;
        var current = nodes.get(index);
        return current.getType();
    }

    @Nullable
    Point getCurrent() {
        if (index >= nodes.size()) return null;
        var current = nodes.get(index);
        return current.point();
    }

    void next() {
        if (index >= nodes.size()) return;
        index++;
    }

    double maxDistance() {
        return maxDistance;
    }

    double pathVariance() {
        return pathVariance;
    }
}