public class Plan1571768612827 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("A");
StartServer("C");
StartServer("B");

StartServer("C");



DecreaseTraffic("A");

}

}
}
