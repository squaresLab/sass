public class Plan1571767979902 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("B");

StartServer("C");
StartServer("A");
DecreaseTraffic("A");



StartServer("B");

}


}
}
