public class Plan1571775506859 extends Plan { 
public static void main(String[] args) { 
DecreaseTraffic("A");
for (int i = 0; i < 5 ; i++) {
StartServer("B");
}

for (int i = 0; i < 4 ; i++) {
StartServer("B");
StartServer("C");
StartServer("A");


}

DecreaseTraffic("A");

StartServer("C");



}
}
