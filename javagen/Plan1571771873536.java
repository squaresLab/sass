public class Plan1571771873536 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("A");

}

for (int i = 0; i < 3 ; i++) {
StartServer("B");
DecreaseTraffic("A");

}

StartServer("B");
StartServer("C");



}
}
