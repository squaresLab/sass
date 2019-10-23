public class Plan1571770367814 extends Plan { 
public static void main(String[] args) { 
DecreaseTraffic("A");
for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

}

DecreaseTraffic("A");
for (int i = 0; i < 3 ; i++) {
StartServer("A");
StartServer("B");

}

StartServer("A");

StartServer("B");



StartServer("B");


}
}
