public class Plan1571769122848 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}

StartServer("A");
StartServer("B");
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

}





}
}
