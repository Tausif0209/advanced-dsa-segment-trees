public class FruitsIntoBaskets {
    private static class Node{
        int start;
        int end;
        int data;
        Node left;
        Node right;
        Node(int s,int e){
        this.start=s;
        this.end=e;
    }
    }
    Node root;
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        root=constructTree(baskets,0,baskets.length-1);
        int up=0;//count of unplaced
        for(int fruit:fruits){
            int index=findBasket(fruit,root);
            if(index!=-1) updateTree(root,index);
            else up++;
        } return up;
    }
    private void updateTree(Node node,int index){
        if(index<node.start || index>node.end) return ;
        if(index==node.start && index==node.end) {
            node.data=0;
            return;
        }
        updateTree(node.left,index);
        updateTree(node.right,index);
        node.data=Math.max(node.left.data,node.right.data);
    }
    private int findBasket(int fruit,Node node){
        if(fruit>node.data) return -1;
        if(node.start==node.end) return node.start;
        int index=findBasket(fruit,node.left);
        return (index!=-1)? index : findBasket(fruit,node.right);
    }
    private Node constructTree(int[] baskets,int start,int end){
        Node node=new Node(start,end);
        if(start==end){
            node.data=baskets[start];
            return node;
        }
        int mid = start - (start - end)/2;
        node.left=constructTree(baskets,start,mid);
        node.right=constructTree(baskets,mid+1,end);
        node.data=Math.max(node.left.data,node.right.data);
        return node;
    }
  
}
