public class Plan1571768463913 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("C");
StartServer("B");
StartServer("A");

DecreaseTraffic("A");



StartServer("C");

}

}
}
