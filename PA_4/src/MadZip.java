import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * This class compresses and decompresses zipped files.
 * 
 * @author phanvm
 * @version PA 4
 * 
 *          This work complies with JMU's Honor Code. I have used our Zybook's pseudocode as a base
 *          for my buildCharacterFrequencyTable and huffmanBuildTree
 */
public class MadZip implements java.io.Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * Huffman Tree Node class containing both a key and a value.
   */
  static class Node implements Comparable<Node> {
    Byte key;
    Long frequency;
    Node left;
    Node right;

    public Node() {
      key = null;
      frequency = null;
      left = null;
      right = null;
    }

    public Node(Byte nodeKey, Long frequency, Node left, Node right) {
      key = nodeKey;
      this.frequency = frequency;
      this.left = left;
      this.right = right;
    }

    public int compareTo(Node o) throws NullPointerException, ClassCastException {
      if ((this.key != null && o.key != null) && this.frequency.equals(o.frequency)) {
        return this.key.compareTo(o.key);
      }

      return (int) (this.frequency - o.frequency);
    }
  }

  /**
   * Zips a given file and saves it to a give location.
   * 
   * @param toCompress The file to compress.
   * @param locationToSave The location to save to.
   * @throws IOException Throws IOException
   */
  public static void zip(File toCompress, File locationToSave) throws IOException {
    // ArrayList<Byte> bytes = readBytes(toCompress);
    HashMap<Byte, Long> frequncyTable = MadZip.buildCharacterFrequencyTable(toCompress);
    System.out.println(frequncyTable.size());

    if (toCompress.length() == 0) {
      FileOutputStream fileOut = new FileOutputStream(locationToSave);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      HuffmanSave blank = new HuffmanSave(new BitSequence(), frequncyTable);

      out.writeObject(blank);
      out.close();
      return;
    } else if (toCompress.length() == 1) {
      BitSequence bit = new BitSequence();
      bit.appendBit(1);

      FileOutputStream fileOut = new FileOutputStream(locationToSave);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);

      HuffmanSave one = new HuffmanSave(bit, frequncyTable);
      out.writeObject(one);
      out.close();
      return;
    }

    frequncyTable = MadZip.buildCharacterFrequencyTable(toCompress);
    BitSequence compress = MadZip.huffmanCompress(toCompress);
    HuffmanSave toSave = new HuffmanSave(compress, frequncyTable);
    System.out.println("zip: " + compress.toString());

    try {
      FileOutputStream fileOut = new FileOutputStream(locationToSave);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);

      out.writeObject(toSave);
      out.close();
    } catch (IOException i) {
      i.printStackTrace();
    }
  }

  /**
   * Unzips the given file and saves it to the given location.
   * 
   * @param toUncompress The file to uncompress.
   * @param locationToSave The location to save the file.
   * @throws IOException Throws IOException if the the source file cannot be read or the destination
   *         file cannot be written to.
   * @throws ClassNotFoundException if that exception occurs during deserialization.
   */
  public static void unzip(File toUncompress, File locationToSave)
      throws IOException, ClassNotFoundException {
    HuffmanSave huffman = null;

    try {
      FileInputStream fileIn = new FileInputStream(toUncompress);
      ObjectInputStream in = new ObjectInputStream(fileIn);

      huffman = (HuffmanSave) in.readObject();
      in.close();
      fileIn.close();
    } catch (IOException i) {
      i.printStackTrace();
    }

    BitSequence encodings = huffman.getEncoding();
    System.out.println("unzip: " + encodings.toString());

    HashMap<Byte, Long> frequencies = huffman.getFrequencies();
    FileOutputStream fileOut = new FileOutputStream(locationToSave);

    if (encodings.length() == 0) {
      fileOut.close();
      return;
    } else if (encodings.length() == 1) {
      for (Byte key : frequencies.keySet()) {
        fileOut.write(key);
      }
      fileOut.close();
      return;
    }

    Node root = huffmanBuildTree(frequencies);

    ArrayList<Byte> result = huffmanDecompress(encodings, root);

    try {
      byte[] x = new byte[result.size()];
      for (int i = 0; i < x.length; i++) {
        x[i] = result.get(i);
      }

      fileOut.write(x);
      fileOut.close();
    } catch (IOException i) {
      i.printStackTrace();
    }
  }

  // /**
  // * Helper method for zip reads the bytes from the file.
  // *
  // * @param toRead The file to read the bytes from.
  // * @return ArrayList The list of bytes.
  // * @throws IOException When the file cannot be read
  // */
  // private static ArrayList<Byte> readBytes(File toRead) throws IOException {
  // FileInputStream input = new FileInputStream(toRead);
  // BufferedInputStream bufferedInput = new BufferedInputStream(input);
  // ArrayList<Byte> bytes = new ArrayList<Byte>();
  //
  // while (true) {
  // byte x = (byte) bufferedInput.read();
  // if (x != -1) {
  // bytes.add(x);
  // } else {
  // break;
  // }
  // }
  // bufferedInput.close();
  //
  // return bytes;
  // }

  /**
   * Helper method for huffmanBuildTree.
   * 
   * @param bytes The bytes of the file.
   * @return The Character Frequency Table.
   */
  private static HashMap<Byte, Long> buildCharacterFrequencyTable(File file) {
    HashMap<Byte, Long> table = new HashMap<Byte, Long>();

    try {
      FileInputStream input = new FileInputStream(file);
      BufferedInputStream bufferedInput = new BufferedInputStream(input);

      while (true) {
        int x = bufferedInput.read();
        
        if (x == -1) {
          break;
        }

        byte key = (byte) x;
        
        if (table.containsKey(key)) {
          table.put(key, (table.get(key) + 1));
        } else {
          table.put(key, 1L);
        }
      }

      bufferedInput.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

    return table;
  }

  /**
   * Helper method for zip.
   * 
   * @param inputString The string to create huffman tree for.
   * @return The root node of the Huffman tree.
   */
  private static Node huffmanBuildTree(HashMap<Byte, Long> frequencyTable) {
    // Make a priority queue of nodes
    PriorityQueue<Node> nodes = new PriorityQueue<Node>();
    for (Map.Entry<Byte, Long> entry : frequencyTable.entrySet()) {
      Node newLeaf = new Node(entry.getKey(), entry.getValue(), null, null);
      nodes.add(newLeaf);
    }

    while (nodes.size() > 1) {
      // Dequeue 2 lowest-priority nodes
      Node leftNode = nodes.poll();
      Node rightNode = nodes.poll();

      Long freqSum = rightNode.frequency + leftNode.frequency;
      // its byte should be the the byte of its child's smaller byte value

      Byte smallest;

      if (leftNode.key.compareTo(rightNode.key) > 0) {
        smallest = rightNode.key;
      } else {
        smallest = leftNode.key;
      }
      Node root = new Node(smallest, freqSum, leftNode, rightNode);
      System.out.println(leftNode.key + "----" + rightNode.key);
      nodes.add(root);

    }

    return nodes.poll();

  }

  /**
   * Private helper method for zip builds the byte-encodings for each byte in the huffman tree.
   * 
   * @param root The root of the HuffmanTree.
   * @return HashMap The mapping of the byte-ecnoding for each byte.
   */
  private static HashMap<Byte, String> buildByteEncodings(Node root) {
    HashMap<Byte, String> byteEncoding = new HashMap<Byte, String>();
    buildByteEncodingsHelper(root, "", byteEncoding);
    return byteEncoding;
  }

  /**
   * Private recursive helper method for building byte encodings for a huffman tree.
   * 
   * @param node The current node
   * @param byteEncoding The Mapping of the byte encodings.
   */
  private static void buildByteEncodingsHelper(Node node, String prefix,
      HashMap<Byte, String> byteEncoding) {
    if (node.left == null && node.right == null) {
      byteEncoding.put(node.key, prefix);
    } else {
      buildByteEncodingsHelper(node.left, prefix + "0", byteEncoding);
      buildByteEncodingsHelper(node.right, prefix + "1", byteEncoding);
    }
  }


  /**
   * Huffman tree compress method.
   * 
   * @param bytes The bytes to compress the huffman tree from.
   * @return The Bitsequence from the huffman tree.
   */
  private static BitSequence huffmanCompress(File file) {
    // aaaabbc = 0000101011
    BitSequence result = new BitSequence();

    try {
      FileInputStream input = new FileInputStream(file);
      BufferedInputStream bufferedInput = new BufferedInputStream(input);

      HashMap<Byte, Long> frequency = MadZip.buildCharacterFrequencyTable(file);
      Node root = MadZip.huffmanBuildTree(frequency);
      HashMap<Byte, String> codes = MadZip.buildByteEncodings(root);

      while (true) {
        int x = bufferedInput.read();

        if (x == -1) {
          break;
        }
        
        byte key = (byte) x;

        result.appendBits(codes.get(key));
      }

      bufferedInput.close();
    } catch (IOException e) {
      e.getMessage();
    }


    return result;
  }

  /**
   * Decompresses the HuffmanTree.
   * 
   * @param sequence The bitsequence to translate from the huffman tree.
   * @param root The root of the huffmantree.
   * @return ArrayList The array list of bytes read from the huffmantree.
   */
  private static ArrayList<Byte> huffmanDecompress(BitSequence sequence, Node root) {
    Node node = root;
    ArrayList<Byte> result = new ArrayList<Byte>();
    System.out.println(sequence.toString());

    for (int i = 0; i <= sequence.length(); i++) {
      if (node.left == null && node.right == null) {
        result.add(node.key);
        node = root;
      }

      if (i < sequence.length() && sequence.getBit(i) == 0) {
        node = node.left;
      } else {
        node = node.right;
      }
    }

    // Iterator<Integer> iterator = sequence.iterator();
    // while (iterator.hasNext()) {
    // if (node.left == null && node.right == null) {
    // result.add(node.key);
    // node = root;
    // }
    //
    // if (iterator.next() == 0) {
    // node = node.left;
    // } else {
    // node = node.right;
    // }
    // }
    return result;
  }


  // public static void main(String[] args) throws IOException {
  // HashMap<Byte, Long> table = MadZip.buildCharacterFrequencyTable(new File("text.txt"));
  // System.out.println("Frequency Table: " + table.toString());
  //
  // Node root = MadZip.huffmanBuildTree(table);
  //
  // HashMap<Byte, String> encodings = MadZip.buildByteEncodings(root);
  // System.out.println("Encoding: " + encodings.toString());
  //
  // BitSequence bits = MadZip.huffmanCompress((new File("text.txt")));
  // System.out.println("BitSequnce: " + bits);
  //
  // ArrayList<Byte> x = MadZip.huffmanDecompress(bits, root);
  // for (int i = 0; i < x.size(); i++) {
  // System.out.println(x.get(i));
  // }
  //
  //
  // }

}
