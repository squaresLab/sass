public class Plan1571768070685 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("A");
StartServer("C");

}

StartServer("B");

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("B");
StartServer("C");


}


}
}
