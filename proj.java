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
public class Proj {

    public static void main(String[] args) {
        BST bst = new BST();
        int key;
        String data;
        boolean f = true;
        
        do {
            int choice = Integer.parseInt(JOptionPane.showInputDialog("Binary Search Tree Operations\n"
                    + "1. Insert\n"
                    + "2. Search\n"
                    + "3. Modify\n"
                    + "4. Set\n"
                    + "5. Delete\n"
                    + "6. Count nodes\n"
                    + "7. Display\n"
                    + "8. Advanced display\n"
                    + "9. Exit"));

            switch (choice) {
                case 1:
                    key = Integer.parseInt(JOptionPane.showInputDialog("Please enter an integer key"));
                    if (bst.containsKey(key)) {
                        JOptionPane.showMessageDialog(null, "This key is already exists!");
                    } else {
                        data = JOptionPane.showInputDialog("Please enter your string data");
                        bst.insert(key, data);
                    }
                    break;
                case 2:
                    key = Integer.parseInt(JOptionPane.showInputDialog("Please enter an integer key to find its data"));
                    JOptionPane.showMessageDialog(null, "Search result : " + bst.lookUp(key));
                    break;
                case 3:
                    key = Integer.parseInt(JOptionPane.showInputDialog("Please enter an integer key to modify its data"));
                    if (!bst.containsKey(key)) {
                        JOptionPane.showMessageDialog(null, "This key does not exist in the tree!");
                    } else {
                        data = JOptionPane.showInputDialog("Please enter your string data");
                        bst.modify(key, data);
                    }
                    break;
                case 4:
                    key = Integer.parseInt(JOptionPane.showInputDialog("Please enter an integer key to modify its data if it exists or add it if not"));
                    data = JOptionPane.showInputDialog("Please enter your string data");
                    bst.set(key, data);
                    break;
                case 5:
                    key = Integer.parseInt(JOptionPane.showInputDialog("Please enter an integer key to delete it and its data"));
                    bst.delete(key);
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Nodes = " + bst.countNodes());
                    break;
                case 7:
                    bst.display();
                    break;
                case 8:
                    bst.postorder();
                    bst.preorder();
                    bst.inorder();
                    break;
                case 9:
                    f = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Wrong Entry \n ");
                    break;
            }

        } while (f);
    }
}
