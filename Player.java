public class Player {
    private  String name;
    private  int bagCap;
    private Item[] items;


public  Player(String name,int bagCap){
    this.name=name;
    this.bagCap=bagCap;
    this.items=new Item[bagCap];
}
public String getName(){
    return this.name;
}



//public void pickItem(Item item)
//{
  //  int counter=0;
    //boolean isPlaced=false;
    //while(isPlaced=false&&counter<items.length){
      //  if(items[counter]==null){
        //    items[counter]=item;
          //  isPlaced=true;
        //}
       // counter+=1;
   // }
   // if (isPlaced==true){System.out.println("");}
//}








}
