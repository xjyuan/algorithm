package com.cy.tree;


public class RedBlackTree {
    RedBlackTree parent;
    RedBlackTree left;
    RedBlackTree right;
    String key;
    int color;

    public RedBlackTree(String key){
        this.key = key;
    }

    //中序遍历
    static void print(RedBlackTree redBlackTree){
        System.out.print(redBlackTree.key+",");
        if(redBlackTree.left != null){
            print(redBlackTree.left);
        }
        if(redBlackTree.right != null){
            print(redBlackTree.right);
        }
    }

    //左旋
    static void leftRotate(RedBlackTree x){
        RedBlackTree y = x.right;
        x.right = y.left;
        if(y.left != null){
            y.left.parent = x;
        }
        y.parent = x.parent;
        if(x.parent == null){
            //y is root
        }else if(x == x.parent.left){
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    //右旋
    static void rightRotate(RedBlackTree y){
        RedBlackTree x = y.left;
        y.left = x.right;
        if(x.right.parent != null){
            x.right.parent = y;
        }
        x.right = y;
        if(y.parent == null){
            //x is root
        }else if(y.parent.left == y){
            y.parent.left = x;
        }else {
            y.parent.right = x;
        }
        x.parent = y.parent;
        y.parent = x;
    }
}