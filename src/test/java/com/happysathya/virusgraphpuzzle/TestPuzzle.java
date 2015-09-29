package com.happysathya.virusgraphpuzzle;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class TestPuzzle {

    @Test
    public void should_buildNodes_bidirectionally() {

        /*
        3
		2
		0 1
		1 2
		0
		*/

        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);

        node0.addConnection(node1);
        node1.addConnection(node2);

        assertEquals(0, node0.getNumber().intValue());
        assertEquals(1, node0.getConnectedNodes().size());
        assertEquals(1, node0.getConnectedNodes().get(0).getNumber().intValue());

        assertEquals(1, node1.getNumber().intValue());
        assertEquals(2, node1.getConnectedNodes().size());
        assertEquals(0, node1.getConnectedNodes().get(0).getNumber().intValue());
        assertEquals(2, node1.getConnectedNodes().get(1).getNumber().intValue());

        assertEquals(2, node2.getNumber().intValue());
        assertEquals(1, node2.getConnectedNodes().size());
        assertEquals(1, node2.getConnectedNodes().get(0).getNumber().intValue());
    }

    @Test
    public void should_dropConnection_bidirectionally() {

         /*
        3
		2
		0 1
		1 2
		0
		*/

        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);

        node0.addConnection(node1);
        node1.addConnection(node2);

        assertEquals(1, node0.getConnectedNodes().size());
        assertEquals(2, node1.getConnectedNodes().size());

        node0.dropConnection(1);

        assertEquals(0, node0.getConnectedNodes().size());
        assertEquals(1, node1.getConnectedNodes().size());

        //should not fail if we ask for non-connected node
        node0.dropConnection(1);

        assertEquals(0, node0.getConnectedNodes().size());
    }

    @Test
    public void singleIteration_PuzzleSolver_Puzzle1() throws IOException {

        /*
        3
		2
		0 1
		1 2
		0
		*/

        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);

        node0.addConnection(node1);
        node1.addConnection(node2);

        List<Node> nodes = Stream.of(node0, node1, node2).collect(Collectors.toList());
        int virus = 0;

        MissionImpossible mi = new MissionImpossible(nodes);
        List<NodeLink> brokenLinks = mi.solveMission(virus);

        assertEquals(1, brokenLinks.size());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        brokenLinks.get(0).printLink(byteArrayOutputStream);
        assertEquals("0 1", new String(byteArrayOutputStream.toByteArray()));
    }

    @Test
    public void singleIteration_PuzzleSolver_Puzzle2() throws IOException {

        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        Node node13 = new Node(13);
        Node node14 = new Node(14);

        node1.addConnection(node0);

        node0.addConnection(node4);
        node0.addConnection(node3);
        node0.addConnection(node2);

        node4.addConnection(node5);
        node4.addConnection(node6);

        node3.addConnection(node10);
        node3.addConnection(node11);

        node2.addConnection(node12);
        node2.addConnection(node13);
        node2.addConnection(node14);

        node6.addConnection(node7);
        node6.addConnection(node8);
        node6.addConnection(node9);

        List<Node> nodes = Stream.of(node0, node1, node2, node3, node4, node5, node6,
                node7, node8, node9, node10, node11, node12, node13, node14).collect(Collectors.toList());
        int virus = 0;

        MissionImpossible mi = new MissionImpossible(nodes);
        List<NodeLink> brokenLinks = mi.solveMission(virus);

        assertEquals(2, brokenLinks.size());
        ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        brokenLinks.get(0).printLink(byteArrayOutputStream1);
        brokenLinks.get(1).printLink(byteArrayOutputStream2);
        assertEquals("0 2", new String(byteArrayOutputStream1.toByteArray()));
        assertEquals("4 6", new String(byteArrayOutputStream2.toByteArray()));
    }

    @Test
    public void singleIteration_PuzzleSolver_Puzzle3() throws IOException {

        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        Node node13 = new Node(13);
        Node node14 = new Node(14);

        node1.addConnection(node0);

        node0.addConnection(node4);
        node0.addConnection(node3);
        node0.addConnection(node2);

        node4.addConnection(node5);
        node4.addConnection(node6);

        node3.addConnection(node10);
        node3.addConnection(node11);

        node2.addConnection(node12);
        node2.addConnection(node13);
        node2.addConnection(node14);

        node6.addConnection(node7);
        node7.addConnection(node8);
        node8.addConnection(node9);

        List<Node> nodes = Stream.of(node0, node1, node2, node3, node4, node5, node6,
                node7, node8, node9, node10, node11, node12, node13, node14).collect(Collectors.toList());
        int virus = 0;

        MissionImpossible mi = new MissionImpossible(nodes);
        List<NodeLink> brokenLinks = mi.solveMission(virus);

        assertEquals(2, brokenLinks.size());
        ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        brokenLinks.get(0).printLink(byteArrayOutputStream1);
        brokenLinks.get(1).printLink(byteArrayOutputStream2);
        assertEquals("0 2", new String(byteArrayOutputStream1.toByteArray()));
        assertEquals("4 6", new String(byteArrayOutputStream2.toByteArray()));
    }

    @Test
    public void singleIteration_PuzzleSolver_Puzzle4() throws IOException {

        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        Node node13 = new Node(13);
        Node node14 = new Node(14);

        node1.addConnection(node0);

        node0.addConnection(node4);
        node0.addConnection(node3);
        node0.addConnection(node2);

        node4.addConnection(node5);
        node4.addConnection(node6);
        node4.addConnection(node7);
        node4.addConnection(node8);
        node4.addConnection(node9);

        node3.addConnection(node10);
        node3.addConnection(node11);

        node2.addConnection(node12);
        node2.addConnection(node13);
        node2.addConnection(node14);

        node6.addConnection(node7);
        node7.addConnection(node8);

        List<Node> nodes = Stream.of(node0, node1, node2, node3, node4, node5, node6,
                node7, node8, node9, node10, node11, node12, node13, node14).collect(Collectors.toList());
        int virus = 4;

        MissionImpossible mi = new MissionImpossible(nodes);
        List<NodeLink> brokenLinks = mi.solveMission(virus);

        assertEquals(1, brokenLinks.size());
        ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
        brokenLinks.get(0).printLink(byteArrayOutputStream1);
        assertEquals("4 0", new String(byteArrayOutputStream1.toByteArray()));
    }

    @Test
    public void singleIteration_PuzzleSolver_Puzzle5() throws IOException {

        Node node0 = new Node(0);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node7 = new Node(7);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        Node node91 = new Node(91);
        Node node92 = new Node(92);
        Node node93 = new Node(93);
        Node node94 = new Node(94);
        Node node95 = new Node(95);
        Node node96 = new Node(96);

        node0.addConnection(node9);
        node0.addConnection(node10);
        node0.addConnection(node11);
        node0.addConnection(node12);
        node0.addConnection(node4);
        node0.addConnection(node3);
        node0.addConnection(node2);

        node3.addConnection(node2);
        node3.addConnection(node4);

        node2.addConnection(node7);
        node2.addConnection(node5);
        node2.addConnection(node6);

        node7.addConnection(node91);
        node7.addConnection(node92);
        node7.addConnection(node93);
        node7.addConnection(node94);
        node7.addConnection(node95);
        node7.addConnection(node96);

        node0.addConnection(node96);

        List<Node> nodes = Stream.of(node0, node2, node3, node4, node5, node6,
                node7, node9, node10, node11, node12, node91, node92, node93, node94, node95, node96).collect(Collectors.toList());
        int virus = 3;

        MissionImpossible mi = new MissionImpossible(nodes);
        List<NodeLink> brokenLinks = mi.solveMission(virus);

        assertEquals(3, brokenLinks.size());
        ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
        brokenLinks.get(0).printLink(byteArrayOutputStream1);
        brokenLinks.get(1).printLink(byteArrayOutputStream2);
        brokenLinks.get(2).printLink(byteArrayOutputStream3);
        assertEquals("3 0", new String(byteArrayOutputStream1.toByteArray()));
        assertEquals("2 7", new String(byteArrayOutputStream2.toByteArray()));
        assertEquals("0 96", new String(byteArrayOutputStream3.toByteArray()));
    }


}
