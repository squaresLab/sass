public class Plan1571772725271 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("A");
StartServer("C");


DecreaseTraffic("A");

}

StartServer("C");

StartServer("B");
StartServer("A");


}
}
