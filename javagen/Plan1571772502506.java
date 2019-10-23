public class Plan1571772502506 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
DecreaseTraffic("A");
for (int i = 0; i < 2 ; i++) {
DecreaseDimmer("B");
StartServer("B");
StartServer("A");


StartServer("C");

DecreaseTraffic("A");
StartServer("C");
StartServer("B");

StartServer("A");


StartServer("C");


}



}
}
