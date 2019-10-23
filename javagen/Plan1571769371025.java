public class Plan1571769371025 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
StartServer("A");

for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("B");
StartServer("A");


DecreaseTraffic("A");

}

for (int i = 0; i < 3 ; i++) {
StartServer("C");
}



}
}
