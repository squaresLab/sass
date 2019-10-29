public class Plan1571775364825 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

DecreaseTraffic("A");
DecreaseTraffic("A");
for (int i = 0; i < 2 ; i++) {
StartServer("C");
}



DecreaseTraffic("A");
IncreaseTraffic("B");
for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

}





}
}
