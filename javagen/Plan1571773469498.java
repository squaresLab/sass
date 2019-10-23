public class Plan1571773469498 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("A");
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}



StartServer("C");

}

}
}
