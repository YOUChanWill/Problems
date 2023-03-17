package ProgramLearn;

import java.util.ArrayList;
import java.util.List;

public class Preorder {

    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        help(root,res);
        return res;
    }

    public void help(Node root, List<Integer> res){
        if (root == null){
            return;
        }
        res.add(root.val);
        for (Node ch :
                root.children) {
            help(ch,res);
        }
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
