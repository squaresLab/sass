public class Plan1571771928344 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
DecreaseTraffic("A");
StartServer("C");


StartServer("B");
StartServer("B");
DecreaseTraffic("A");
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}



for (int i = 0; i < 4 ; i++) {
StartServer("C");
}



DecreaseTraffic("A");
StartServer("B");
StartServer("C");
StartServer("A");





}
}
