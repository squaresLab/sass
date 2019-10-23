public class Plan1571769921207 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("C");
StartServer("A");
StartServer("B");


DecreaseTraffic("A");


StartServer("C");

}

}
}
