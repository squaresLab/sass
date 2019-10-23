public class Plan1571775446035 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
StartServer("C");
for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("A");
StartServer("B");


DecreaseTraffic("A");

}



}
}
