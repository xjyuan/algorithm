package com.cy.tree;


import org.junit.jupiter.api.Test;
/**
 * Created by chenying on 2018/4/26.
 */
public class RedBlackTreeTest{
    @Test
    public void test(){
        /**
         *   X
         *  / \
         * a  Y
         *   /\
         *  b  r
         * 先序遍历：X,a,Y,b,r,
         * 旋转后
         *     Y
         *    / \
         *   X  r
         *  /\
         * a b
         * 先序遍历：Y,X,a,b,r,
         */
        RedBlackTree x = new RedBlackTree("X");
        RedBlackTree y = new RedBlackTree("Y");
        RedBlackTree a = new RedBlackTree("a");
        RedBlackTree b = new RedBlackTree("b");
        RedBlackTree r = new RedBlackTree("r");

        x.left = a;
        a.parent = x;

        y.left = b;
        y.right = r;
        b.parent = y;
        r.parent = y;

        x.right = y;
        y.parent = x;

        System.out.println("初始树：");
        RedBlackTree.print(x);
        RedBlackTree.leftRotate(x);
        System.out.println("\n左旋后");
        RedBlackTree.print(y);

        System.out.println("\n右旋后：");

        RedBlackTree.rightRotate(y);
        RedBlackTree.print(x);
    }
}