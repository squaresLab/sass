public class Plan1571773625274 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
StartServer("C");
StartServer("A");
for (int i = 0; i < 5 ; i++) {
StartServer("B");
}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("C");
StartServer("A");


}





}
}
