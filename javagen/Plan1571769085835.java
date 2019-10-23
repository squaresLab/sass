public class Plan1571769085835 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
StartServer("B");

for (int i = 0; i < 3 ; i++) {
StartServer("C");
DecreaseTraffic("A");

StartServer("B");

StartServer("A");

StartServer("A");

}

for (int i = 0; i < 2 ; i++) {
StartServer("C");
}



}
}
