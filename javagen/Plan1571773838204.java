public class Plan1571773838204 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
DecreaseTraffic("A");
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}



StartServer("C");

}

}
}
