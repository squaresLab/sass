public class Plan1571769159717 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("C");
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

for (int i = 0; i < 2 ; i++) {
StartServer("B");
}


StartServer("C");
DecreaseTraffic("A");




}

DecreaseTraffic("A");

}
}
