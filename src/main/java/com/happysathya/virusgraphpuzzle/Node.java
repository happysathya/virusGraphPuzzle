package com.happysathya.virusgraphpuzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Node {

    private final Integer number;
    private final List<Node> connectedNodes;

    public Node(Integer number) {
        this.number = number;
        this.connectedNodes = new ArrayList<>();
    }

    public void addConnection(Node nodeToConnect) {
        if (!connectedNodes.stream()
                .anyMatch(node -> node.getNumber().equals(nodeToConnect.getNumber()))) {
            connectedNodes.add(nodeToConnect);
            nodeToConnect.addConnection(this);
        }
    }

    public void dropConnection(Integer nodeNumber) {
        Optional<Node> connectedNode = connectedNodes.stream()
                .filter(node -> node.getNumber().equals(nodeNumber))
                .findFirst();
        connectedNode.ifPresent(this::dropConnection);
    }

    public void dropConnection(Node connectedNode) {
        connectedNodes.remove(connectedNode);
        connectedNode.dropConnection(this.number);
    }

    public Integer getNumber() {
        return number;
    }

    public List<Node> getConnectedNodes() {
        return connectedNodes;
    }

    public Integer getWeight() {
        return connectedNodes.size();
    }
}
