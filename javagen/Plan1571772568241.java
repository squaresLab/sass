public class Plan1571772568241 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
IncreaseTraffic("B");

}

StartServer("B");
for (int i = 0; i < 4 ; i++) {
StartServer("B");
StartServer("A");
StartServer("C");


}



}
}
