public class Plan1571767830410 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 3 ; i++) {
StartServer("C");
DecreaseTraffic("A");

for (int i = 0; i < 2 ; i++) {
StartServer("B");
}


StartServer("C");
StartServer("A");


}


}
}
