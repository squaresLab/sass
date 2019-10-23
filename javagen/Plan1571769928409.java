public class Plan1571769928409 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
StartServer("B");
StartServer("C");

}

for (int i = 0; i < 5 ; i++) {
StartServer("A");
}


DecreaseTraffic("A");

StartServer("C");
StartServer("B");

StartServer("B");
DecreaseTraffic("A");



}
}
