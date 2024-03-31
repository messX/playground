import java.util.*;

class TreeNode{
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data){
        this.data =data;
        this.left = null;
        this.right = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}

class BinarySearchTree{
    TreeNode root;

    public BinarySearchTree(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}
public class RightViewBST {

    public static void printRight(BinarySearchTree tree){
        Queue<TreeNode> queue = new LinkedList<>();
        if(tree.getRoot() != null){
            System.out.println(tree.getRoot().getData());
            if(tree.getRoot().left != null)
                queue.add(tree.getRoot().left);
            if(tree.getRoot().right != null)
                queue.add(tree.getRoot().right);
        }
        while(!queue.isEmpty()){
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                if(i == n-1){
                    System.out.println(node.getData());
                }
                else{
                    if(node.left != null)
                        queue.add(node.left);
                    if(node.right != null)
                        queue.add(node.right);
                }
            }
        }

    }

    public static int treeDiameter(TreeNode node){
        if(node == null){
            return 0;
        }
        //height
        int height = maxDepth(node.left) + maxDepth(node.right) + 1;
        // left diameter and right diameter
        int lDiameter = treeDiameter(node.left);
        int rDiameter = treeDiameter(node.right);
        List<Integer> res = Arrays.asList(height, lDiameter + rDiameter);
        return res.stream().max(Comparator.comparingInt(a -> a)).get();
    }

    public static int maxDepth(TreeNode node){
        if(node == null){
            return -1;
        }
        List<Integer> list = new ArrayList<>();
        list.add(maxDepth(node.left));
        list.add(maxDepth(node.right));
        return list.stream().max(Comparator.comparingInt(a -> a)).get() + 1;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree(new TreeNode(1));
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);
        tree.root.right.left = new TreeNode(6);
        tree.root.right.right = new TreeNode(7);
        //printRight(tree);
        System.out.println(maxDepth(tree.root));
        System.out.println(treeDiameter(tree.root));
    }
}
