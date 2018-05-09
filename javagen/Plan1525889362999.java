public class Plan1525889362999 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("A");
StartServer("B");
StartServer("C");


DecreaseTraffic("A");
StartServer("B");



}

}
}
