public class Plan1571771456662 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("B");
StartServer("A");

DecreaseTraffic("A");


StartServer("C");

}

StartServer("B");


}
}
