package com.happysathya.virusgraphpuzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MissionImpossible {

    private final List<Node> nodes;
    private final List<NodeLink> brokenLinks;
    private final Set<Integer> droppedVirus;

    public MissionImpossible(final List<Node> allNodes) {
        this.nodes = new ArrayList<>();
        nodes.addAll(Optional.ofNullable(allNodes).orElse(Collections.emptyList()));
        this.brokenLinks = new ArrayList<>();
        this.droppedVirus = new HashSet<>();
    }

    public List<NodeLink> solveMission(int virus) {
        solveMission(Stream.of(virus).collect(Collectors.toSet()));
        return brokenLinks;
    }

    private void solveMission(final Set<Integer> virusNumbers) {

        if (virusNumbers.isEmpty()) {
            return;
        }

        List<NodeLink> linkNodes = new ArrayList<>();
        List<Node> nextToBeAffectedNodes = new ArrayList<>();

        virusNumbers.forEach(virusNumber -> {
            Node virusAffectedNode = nodes.stream().filter(node -> node.getNumber().equals(virusNumber)).findFirst().get();
            List<Node> connectedNodesFromVirusNode = virusAffectedNode.getConnectedNodes();
            nextToBeAffectedNodes.addAll(connectedNodesFromVirusNode);
            connectedNodesFromVirusNode.forEach(connectedNode -> linkNodes.add(new NodeLink(virusAffectedNode, connectedNode)));
        });
        List<NodeLink> sortedNodeLinksInOrder = linkNodes.stream()
                .sorted((o1, o2) -> Integer.compare(o2.getLink2().getWeight(), o1.getLink2().getWeight())).collect(Collectors.toList());

        NodeLink nodeLinkToBeDisconnected = findLinkToBeDisconnected(sortedNodeLinksInOrder);
        nodeLinkToBeDisconnected.getLink1().dropConnection(nodeLinkToBeDisconnected.getLink2());
        brokenLinks.add(nodeLinkToBeDisconnected);

        Set<Integer> siblingVirusNumbers = nextToBeAffectedNodes.stream()
                .filter(node -> !Objects.equals(node.getNumber(), nodeLinkToBeDisconnected.getLink2().getNumber()))
                .map(Node::getNumber)
                .collect(Collectors.toSet());
        Set<Integer> newVirusNumbers = Stream.concat(virusNumbers.stream(), siblingVirusNumbers.stream()).collect(Collectors.toSet());
        solveMission(filterOutVirusNumbers(newVirusNumbers));
    }

    private Set<Integer> filterOutVirusNumbers(Set<Integer> virusNumbers) {
        Set<Integer> virusNumbersToBeDropped = virusNumbers.stream().filter(virusNumber -> {
            Node virusNode = nodes.stream().filter(node -> node.getNumber().equals(virusNumber)).findFirst().get();
            Set<Integer> connectedNodeNumbers = virusNode.getConnectedNodes().stream().map(Node::getNumber).collect(Collectors.toSet());
            return isSubset(virusNumbers, connectedNodeNumbers);
        }).collect(Collectors.toSet());
        droppedVirus.addAll(virusNumbersToBeDropped);
        Set<Integer> newVirusNumbers = virusNumbers.stream()
                .filter(virus -> !droppedVirus.contains(virus)).collect(Collectors.toSet());
        newVirusNumbers.forEach(virusNumber -> nodes.stream()
                .filter(node -> node.getNumber().equals(virusNumber)).findFirst()
                .ifPresent(node -> virusNumbersToBeDropped.forEach(node::dropConnection)));
        return newVirusNumbers;
    }

    private boolean isSubset(Set<Integer> set, Set<Integer> subset) {
        for (Integer element : subset) {
            if (!set.contains(element) && !droppedVirus.contains(element)) {
                return false;
            }
        }
        return true;
    }

    private NodeLink findLinkToBeDisconnected(List<NodeLink> sortedNodeLinksInOrder) {
        for (int i = 0; i < sortedNodeLinksInOrder.size(); i++) {
            boolean noCircularLinks = true;
            for (int j = i + 1; j < sortedNodeLinksInOrder.size() && noCircularLinks; j++) {
                if (sortedNodeLinksInOrder.get(i).getLink2().getNumber().equals(sortedNodeLinksInOrder.get(j).getLink2().getNumber())) {
                    noCircularLinks = false;
                }
            }
            if (noCircularLinks) {
                return sortedNodeLinksInOrder.get(i);
            }
        }
        return sortedNodeLinksInOrder.get(0);
    }
}
