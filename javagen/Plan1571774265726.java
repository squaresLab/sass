public class Plan1571774265726 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("A");

StartServer("A");
StartServer("C");


DecreaseTraffic("A");
StartServer("B");


}


}
}
