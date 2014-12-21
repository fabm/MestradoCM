package pt.ipg.mcm.errors.wct.utils.webservice;

public class NodeNav {
  private String name;
  private NodeNav next;
  private NodeNav previous;

  public NodeNav(String name) {
    this.name = name;
  }

  public static NodeNav createNodeNavSoap() {
    return new NodeNav("Envelope")
        .setNext("Body");
  }

  public NodeNav getRoot() {
    NodeNav current = this;
    while (current.previous != null) {
      current = current.previous;
    }
    return current;
  }


  public NodeNav setNext(String name) {
    NodeNav next = new NodeNav(name);
    next.previous = this;
    this.next = next;
    return next;
  }

  public NodeNav getPrevious() {
    return previous;
  }

  public String getName() {
    return name;
  }

  public NodeNav getNext() {
    return next;
  }
}
