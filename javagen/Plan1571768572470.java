public class Plan1571768572470 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("A");
StartServer("B");

for (int i = 0; i < 3 ; i++) {
StartServer("C");
}


StartServer("A");
DecreaseTraffic("A");


StartServer("B");

}

StartServer("A");
DecreaseTraffic("A");
StartServer("B");



}
}
