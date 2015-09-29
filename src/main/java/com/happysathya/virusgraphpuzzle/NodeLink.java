package com.happysathya.virusgraphpuzzle;

import java.io.IOException;
import java.io.OutputStream;

public class NodeLink {

    private final Node link1;
    private final Node link2;

    public NodeLink(Node link1, Node link2) {
        this.link1 = link1;
        this.link2 = link2;
    }

    public void printLink(OutputStream outputStream) throws IOException {
        outputStream.write(String.format("%d %d", link1.getNumber(), link2.getNumber()).getBytes());
    }

    public Node getLink1() {
        return link1;
    }

    public Node getLink2() {
        return link2;
    }
}
