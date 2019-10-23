public class Plan1571771502668 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("A");
StartServer("C");
StartServer("B");


DecreaseTraffic("A");


}

StartServer("A");
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}



}
}
