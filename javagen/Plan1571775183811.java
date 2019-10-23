public class Plan1571775183811 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

}

for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

DecreaseTraffic("A");
DecreaseTraffic("A");

StartServer("A");
StartServer("A");


DecreaseTraffic("A");



}
}
