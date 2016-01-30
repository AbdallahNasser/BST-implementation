/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abdallah Nasser
 */
public class TreeNode {

    private int key;
    private String data;
    private boolean isLastRight;
    TreeNode left;
    TreeNode right;

    TreeNode(int key, String data) {
        this.key = key;
        this.data = data;
        this.right = null;
        this.left = null;
    }

    public void setLeft(TreeNode n) {
        left = n;
    }
    
    public void setRight(TreeNode n) {
        right = n;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }
    
    public void setData(String d) {
        data = d;
    }

    public int getKey() {
        return key;
    }
    
    public String getData() {
        return data;
    }
    
    public void setLastRight(boolean b){
        isLastRight = b;
    }
    public boolean getLastRight(){
        return isLastRight;
    }
}
