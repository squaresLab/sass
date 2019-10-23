public class Plan1571773053257 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("A");

StartServer("B");

}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

StartServer("C");
StartServer("A");

StartServer("A");

StartServer("B");



}
}
