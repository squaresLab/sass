public class Plan1571775383149 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
for (int i = 0; i < 4 ; i++) {
StartServer("B");
StartServer("C");
StartServer("B");

StartServer("A");


}


for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}


}
}
