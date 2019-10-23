public class Plan1571775011948 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

}

StartServer("A");
StartServer("A");



}
}
