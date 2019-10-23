public class Plan1571774923926 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
StartServer("A");
for (int i = 0; i < 6 ; i++) {
StartServer("B");
}

for (int i = 0; i < 2 ; i++) {
StartServer("C");
}



DecreaseTraffic("A");
DecreaseTraffic("A");
StartServer("B");
StartServer("A");

StartServer("C");
StartServer("C");






DecreaseTraffic("A");
StartServer("A");


}
}
