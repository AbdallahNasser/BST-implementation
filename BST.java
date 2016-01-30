import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Abdallah Nasser
 */
public class BST {

    private TreeNode root;
    private boolean found = false;
    private boolean right = true;
    private String s = "";

    BST() {
        root = null;
    }

    //checks if the tree is empty
    public boolean isEmpty() {
        return root == null;
    }

    public void insert(int key, String data) {
        root = insert(root, key, data);
    }

    //inserts a node into the tree
    private TreeNode insert(TreeNode node, int key, String data) {
        if (node == null) {
            node = new TreeNode(key, data);
            node.setLastRight(true);
        } else {
            if (key < node.getKey()) {
                node.left = insert(node.left, key, data);
                node.left.setLastRight(false);
            } else {
                node.right = insert(node.right, key, data);
                node.setLastRight(false);
                
            }
        }
        return node;
    }

    //check if key is found in the tree
    public boolean containsKey(int key) {
        lookUp(key);
        return found;
    }

    //search for a node using its key to display its data
    public String lookUp(int key) {
        TreeNode r = root;
        while ((r != null)) {
            int tempKey = r.getKey();
            if (key < tempKey) {
                r = r.getLeft();
            } else if (key > tempKey) {
                r = r.getRight();
            } else {
                found = true;
                return r.getData();
            }
        }
        found = false;
        return "NOT FOUND";
    }

    public void modify(int key, String data) {
        modify(root, key, data);
    }

    //modify a node's data
    private void modify(TreeNode tree, int key, String data) {
        TreeNode r = root;
        while ((r != null)) {
            int tempKey = r.getKey();
            if (key < tempKey) {
                r = r.getLeft();
            } else if (key > tempKey) {
                r = r.getRight();
            } else {
                r.setData(data);
                break;
            }
        }
    }

    //set
    public void set(int key, String data) {
        if (containsKey(key)) {
            modify(key, data);
        } else {
            insert(key, data);
        }
    }

    //delete node
    public void delete(int key) {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tree Empty");
        } else if (!containsKey(key)) {
            JOptionPane.showMessageDialog(null, "Sorry " + key + " is not exist");
        } else {

            TreeNode parent = root;
            TreeNode current = root;
            boolean isLeftChild = false;

            while (current.getKey() != key) {
                parent = current;
                if (key < current.getKey()) {
                    isLeftChild = true;
                    current = current.left;
                } else {
                    isLeftChild = false;
                    current = current.right;
                }
                if (current == null) {
                    continue;
                }
            }
            //if i am here that means we have found the node, then we have 3 cases
            //Case 1: if node to be deleted has no children
            if (current.left == null && current.right == null) {
                if (current == root) {
                    root = null;
                }
                if (isLeftChild == true) {
                    parent.left = null;
                } else {
                    parent.right = null;
                    parent.setLastRight(true);
                }
            } //Case 2 : if node to be deleted has only one child
            else if (current.right == null) {
                if (current == root) {
                    root = current.left;
                } else if (isLeftChild) {
                    parent.left = current.left;
                } else {
                    parent.right = current.left;
                }
            } else if (current.left == null) {
                if (current == root) {
                    root = current.right;
                } else if (isLeftChild) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            } //Case 3 : if node to be deleted has 2 children
            else if (current.left != null && current.right != null) {

                //now we have found the minimum element in the right sub tree
                TreeNode successor = getSuccessor(current);
                if (current == root) {
                    root = successor;
                } else if (isLeftChild) {
                    parent.left = successor;
                } else {
                    parent.right = successor;
                }
                successor.left = current.left;
            }
            JOptionPane.showMessageDialog(null, key + " is deleted from the tree");
        }
    }

    public TreeNode getSuccessor(TreeNode deleleNode) {
        TreeNode successsor = null;
        TreeNode successsorParent = null;
        TreeNode current = deleleNode.right;
        while (current != null) {
            successsorParent = successsor;
            successsor = current;
            current = current.left;
        }
        //check if successor has the right child, it cannot have left child for sure
        // if it does have the right child, add it to the left of successorParent.
        if (successsor != deleleNode.right) {
            successsorParent.left = successsor.right;
            successsor.right = deleleNode.right;
        }
        return successsor;
    }

    public int countNodes() {
        return countNodes(root);
    }

    //find the number of nodes in the tree
    private int countNodes(TreeNode r) {
        if (r == null) {
            return 0;
        } else {
            int l = 1;
            l += countNodes(r.getLeft());
            l += countNodes(r.getRight());
            return l;
        }
    }

    public void display() {
        s = "[";
        display(root);
        s += "]";
        JOptionPane.showMessageDialog(null, s);
    }
    int count = 0;
    //display the tree's nodes
    private void display(TreeNode root) {
        if (root != null) {
            s += "[";
            display(root.left);
            s += " " + root.getKey() + ": \"" + root.getData() + "\"";
            if(root.right != null && root.left != null)
                s+=",";
            count++;
//            if(root.right == null )
  //              s+= "],";
            display(root.right);
            s+= "]";
        if(count < countNodes() && !root.getLastRight())
            s+= ",";
        }
        
    }

    //inorder traversal
    public void inorder() {
        s = "";
        inorder(root);
        JOptionPane.showMessageDialog(null, "inorder is:\n" + s);
    }

    private void inorder(TreeNode r) {
        if (r != null) {
            inorder(r.getLeft());
            s += r.getData() + " ";
            inorder(r.getRight());
        }
    }

    //preorder traversal
    public void preorder() {
        s = "";
        preorder(root);
        JOptionPane.showMessageDialog(null, "Preorder is:\n" + s);
    }

    private void preorder(TreeNode r) {
        if (r != null) {
            s += r.getData() + " ";
            preorder(r.getLeft());
            preorder(r.getRight());
        }
    }

    //postorder traversal
    public void postorder() {
        s = "";
        postorder(root);
        JOptionPane.showMessageDialog(null, "Postorder is:\n" + s);
    }

    private void postorder(TreeNode r) {
        if (r != null) {
            postorder(r.getLeft());
            postorder(r.getRight());
            s += r.getData() + " ";
        }
    }
}
