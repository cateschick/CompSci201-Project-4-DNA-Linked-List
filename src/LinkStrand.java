// Cate Schick
// CompSci 201
// Project 4
// Filename: LinkStrand.java

public class LinkStrand implements IDnaStrand {
    // declare private variables:
    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;

    // maintains class invariants when called
    // creates a single node when called
    @Override
    public void initialize(String source) {
        myFirst = new Node(source);
        myLast = myFirst;
        mySize = myFirst.info.length();
    }

    @Override
    public String toString() {
        // create node and StringBuilder object
        Node list = myFirst;
        StringBuilder string = new StringBuilder();

        while (list.next != null) {
            // append list.info to the StringBuilder object;
            string.append(list.info);

            // continue traversing through the list
            list = list.next;
        }
        string.append(list.info);

        return string.toString();
     }

    // returns the total number of string characters in the LinkStrand object
    // runs in O(1) time and just returns mySize;
    @Override
    public long size() {
        return mySize;
    }

    @Override
    public IDnaStrand getInstance(String source) {
        // returns a LinkStrand object;
//        LinkStrand ret = new LinkStrand(source);
//        return ret;
        return null;
    }

    // Adds one new node to the end of the internal LinkedList;
    // Updates state to maintain the invariant;
    @Override
    public IDnaStrand append(String dna) {
        // Add node to the end of LinkedList
        myLast.next = new Node(dna);

        // traverse to new Node;
        myLast = myLast.next;

        // update state
        int update = myLast.info.length();
        mySize += update;

        // increment myAppends because of new Node
        myAppends += 1;

        return this;
    }

    // return the number of appends, myAppends;
    @Override
    public int getAppendCount() {
        return myAppends;
    }

    // creates a new LinkStrand object that is the reverse
    // DOES NOT MUTATE, returns a new strand
    @Override
    public IDnaStrand reverse() {
        // create a new Node and LinkStrand object
        Node n = myFirst;
        LinkStrand strand = new LinkStrand();

        while (n != null) {
            // create a StringBuilder object
            StringBuilder reverse = new StringBuilder(n.info);
            String s = reverse.reverse().toString();

//            strand.appendToFront(s);

            // traverse through nodes
            n = n.next;
        }
        // return new, reversed strand
        return strand;
    }

    // returns the character at the specified index if that's a valid index
    // otherwise, throws an IndexOutOfBounds Exception
    private int myIndex;
    private int dex;
    private Node list;

    @Override
    public char charAt(int index) {

        // throws an IndexOutOfBounds Exception if invalid index
        if (0 > index) {
            throw new IndexOutOfBoundsException();
        }
        if (this.size() <= myIndex) {
            throw new IndexOutOfBoundsException();
        }

        // return character at index
        // set myIndex and dex to 0; node to First;
        if (myIndex >= index) {
            myIndex = 0;
            dex = 0;
            list = myFirst;
        }
        // if not at specified index, keep iterating until it is found
        while (index != myIndex) {
            myIndex += 1;
            dex += 1;
            if (list.next != null && list.info.length() <= dex) {
                list = list.next;
                dex = 0;
            }
        }
        return list.info.charAt(dex);
    }

    private class Node {
        // construct a Node Class
        String info;
        Node next;

        public Node(String s) {
            info = s;
            next = null;
        }
    }
    }