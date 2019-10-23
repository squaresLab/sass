public class Plan1571772358334 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

StartServer("B");
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("B");
StartServer("C");


}



}
}
