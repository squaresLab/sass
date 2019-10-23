public class Plan1571770485295 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("C");
for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("A");
StartServer("C");


}


DecreaseTraffic("A");
StartServer("B");


}

}
}
