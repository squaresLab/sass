public class Plan1571771724093 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
StartServer("A");

for (int i = 0; i < 4 ; i++) {
StartServer("B");
DecreaseTraffic("A");

}

for (int i = 0; i < 5 ; i++) {
StartServer("C");
}



}
}
