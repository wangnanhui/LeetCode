package com.tire;

import java.util.HashMap;
import java.util.Map;

public class TireTree {

   private  Map<Character,TireNode> trees = new HashMap<>()  ;
   public void  addWordsToTree(String words ){
       if(words == null ||words.length() == 0 )
           return ;
       char [] chs = words.toCharArray();

       TireNode tire = trees.get(chs[0]) ;
       if(tire == null){
           trees.put(chs[0],creatNewTire(chs));
       }else{



       }


   }

   public void putExeistWordToTree(TireNode root , char [] chs , int start){
       char c = chs[0] ;
       if(root.c == c){
           start ++;
           if(root.next != null){
               putExeistWordToTree(root.next,chs,start);
           }else {
               if(root.tree == null){
                   root.tree.put(c,creatNewTire(null));

               }

           }
       }else {



       }



    }

   public TireNode creatNewTire(char [] chs){
       TireNode node = new TireNode(chs[0]);
       TireNode nodeTemp = node ;
       for (int i = 1; i < chs.length; i++) {
           nodeTemp.next = new TireNode(chs[i]);
           nodeTemp  =  nodeTemp.next ;
       }
       return node ;

   }


}
class TireNode{
    public char c  ;
    public TireNode next ;
    public boolean end ;
    public Map<Character ,TireNode>  tree ;
    public TireNode(){

    }

    public TireNode(char c){
        this.c = c ;
    }

}